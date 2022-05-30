package poker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import desingpatters.strategy.CartaMaisAlta;
import desingpatters.strategy.CartasValoresDiferentesNaipesIguais;
import desingpatters.strategy.CartasValoresIguaisNaipesDiferentes;
import desingpatters.strategy.CartasValoresSequenciaNaipesDiferentes;
import desingpatters.strategy.CartasValoresSequenciaNaipesIguais;
import desingpatters.strategy.DoisPares;
import desingpatters.strategy.FullHouse;

public class JogadorAutomatico extends Jogador {

	private TipoDeJogador tipoDeJogador = TipoDeJogador.AUTOMATICO;

	public JogadorAutomatico() {
	}

	public TipoDeJogador getTipoDeJogador() {
		return tipoDeJogador;
	}

	public void setTipoDeJogador(TipoDeJogador tipoDeJogador) {
		this.tipoDeJogador = tipoDeJogador;
	}

	public Map<AcaoDoJogador, List<Ficha>> escolherAcaoDoJogador(List<Carta> cartas, TipoDeRodada rodada, Mesa mesa) {
		Map<AcaoDoJogador, List<Ficha>> mapaAcaoFicha = null;
		List<Ficha> fichas = null;

		// Calcular somatório das fichas do jog. automatico, blind e blind aumentada;
		// precisa ser maior ou igual a 3x
		int somatorioBlind = 0;
		for (Ficha ficha : mesa.getBlind()) {
			somatorioBlind += ficha.getValor();
		}

		int somatorioBlindAumentada = 0;
		for (Ficha ficha : mesa.getBlindAumentada()) {
			somatorioBlindAumentada += ficha.getValor();
		}

		int somatorioFichasJogAutomatico = 0;
		for (Ficha ficha : this.getFichas()) {
			somatorioFichasJogAutomatico += ficha.getValor();
		}

		if (this.isVezDeSerSmallBlind()) {// Completar a blind, caso o jog. automatico seja o Small Blind
			if (rodada.equals(TipoDeRodada.FLOP)) {
				fichas = new ArrayList<>();
				int soma = 0;
				for (Ficha ficha : this.getValorDaUltimaAposta()) {
					if (soma <= (somatorioBlind / 2)) {
						soma += ficha.getValor();
					}
				}
			}
		}

		float confianca = calcularNivelConfianca(cartas);

		if (0.0f < confianca && confianca <= 0.25f) {
			if (rodada.equals(TipoDeRodada.PRE_FLOP)) {
				fichas = new ArrayList<>();

				mapaAcaoFicha = new HashMap<AcaoDoJogador, List<Ficha>>();
				mapaAcaoFicha.put(AcaoDoJogador.SAIR, fichas);

				return mapaAcaoFicha;
			} else {
				fichas = new ArrayList<>();

				mapaAcaoFicha = new HashMap<AcaoDoJogador, List<Ficha>>();
				mapaAcaoFicha.put(AcaoDoJogador.PEDIR_MESA, fichas);

				return mapaAcaoFicha;
			}
		}

		if (0.25f < confianca && confianca <= 0.5f) {
			if (rodada.equals(TipoDeRodada.PRE_FLOP)) {
				if (somatorioFichasJogAutomatico >= (3 * somatorioBlind)
						|| somatorioFichasJogAutomatico >= (3 * somatorioBlindAumentada)) {
					fichas = new ArrayList<>();

					for (Ficha ficha : mesa.getBlind()) {
						fichas.add(ficha);
						this.getFichas().remove(ficha);
					}

					mesa.setBlindAumentada(mesa.getBlind());

					mapaAcaoFicha = new HashMap<AcaoDoJogador, List<Ficha>>();
					mapaAcaoFicha.put(AcaoDoJogador.COBRIR, fichas);

					return mapaAcaoFicha;
				} else {
					if (somatorioFichasJogAutomatico >= (3 * somatorioBlind)
							|| somatorioFichasJogAutomatico >= (3 * somatorioBlindAumentada)) {
						fichas = new ArrayList<>();

						for (Ficha ficha : mesa.getBlindAumentada()) {
							fichas.add(ficha);
						}

						mesa.setBlindAumentada(mesa.getBlind());

						mapaAcaoFicha = new HashMap<AcaoDoJogador, List<Ficha>>();
						mapaAcaoFicha.put(AcaoDoJogador.COBRIR, fichas);

						return mapaAcaoFicha;
					}
				}
			}
		}

		if (0.5f < confianca && confianca <= 0.75f) {
			if (somatorioFichasJogAutomatico >= (3 * somatorioBlind)
					|| somatorioFichasJogAutomatico >= (3 * somatorioBlindAumentada)) {
				fichas = new ArrayList<>();

				for (int i = 0; i < 2; i++) {// dobrar a aposta
					for (Ficha ficha : mesa.getBlindAumentada()) {
						fichas.add(ficha);
					}
				}
				mesa.setBlindAumentada(mesa.getBlindAumentada());

				mapaAcaoFicha = new HashMap<AcaoDoJogador, List<Ficha>>();
				mapaAcaoFicha.put(AcaoDoJogador.AUMENTAR, fichas);

				return mapaAcaoFicha;
			}
		}

		if (confianca > 0.75) {
			if (somatorioFichasJogAutomatico >= (3 * somatorioBlind)
					|| somatorioFichasJogAutomatico >= (3 * somatorioBlindAumentada)) {
				fichas = new ArrayList<>();

				for (int i = 0; i < 3; i++) {// triplicar a aposta
					for (Ficha ficha : mesa.getBlindAumentada()) {
						fichas.add(ficha);
					}
				}
				mesa.setBlindAumentada(mesa.getBlindAumentada());

				mapaAcaoFicha = new HashMap<AcaoDoJogador, List<Ficha>>();
				mapaAcaoFicha.put(AcaoDoJogador.AUMENTAR, fichas);

				return mapaAcaoFicha;
			}
		}
		return null;
	}

	public float calcularNivelConfianca(List<Carta> cartas) {

		int valoresIguais = 0;
		int naipesIguais = 0;
		int valoresEmSequencia = 0;

		List<Carta> copiaCartas = new ArrayList<>();
		copiaCartas.addAll(cartas.stream().filter(carta -> carta.isVisivel()).collect(Collectors.toList()));

		int quantidadeDeCartas = copiaCartas.size();

		Carta array[] = new Carta[2];
		for (int i = 0; i < quantidadeDeCartas; i++) {
			for (int j = 0; j < quantidadeDeCartas; j++) {
				array[0] = copiaCartas.get(0);
				array[1] = copiaCartas.get(j);
				copiaCartas.set(0, array[1]);
				copiaCartas.set(j, array[0]);

				// se cartas[0].getValor() == cartas[j].getValor()
				if (0 < j&&j < (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getValorAlternativo() == copiaCartas.get(j).getValorAlternativo()) {
						if (valoresIguais < 4) {
							valoresIguais += 1;
						}
					}
				}
				if (j == (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getValorAlternativo() == copiaCartas.get(quantidadeDeCartas - 1)
							.getValorAlternativo()) {
						if (valoresIguais < 4) {
							valoresIguais += 1;
						}
					}
				}

				// se cartas[0].getNaipe() == cartas[j].getNaipe()
				if (0 < j&&j < (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getNaipe() == copiaCartas.get(j).getNaipe()) {
						if (naipesIguais < 4) {
							naipesIguais += 1;
						}
					}
				}
				if (j == (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getNaipe() == copiaCartas.get(quantidadeDeCartas - 1).getNaipe()) {
						if (naipesIguais < 4) {
							naipesIguais += 1;
						}
					}
				}

				// se modulo(cartas[0].getValor() - cartas[j].getValor()) <= 4
				if (0 < j&&j < (quantidadeDeCartas - 2)) {
					if (Math.abs(copiaCartas.get(0).getValorAlternativo()
							- copiaCartas.get(j).getValorAlternativo()) <= 4) {
						if (valoresEmSequencia < 4) {
							valoresEmSequencia += 1;
						}
					}
				}
				if (j == (quantidadeDeCartas - 2)) {
					if (Math.abs(copiaCartas.get(0).getValorAlternativo()
							- copiaCartas.get(quantidadeDeCartas - 1).getValorAlternativo()) <= 4) {
						if (valoresEmSequencia < 4) {
							valoresEmSequencia += 1;
						}
					}
				}
			}
		}

		float nivelDeConfianca = (0.0833f * (valoresEmSequencia + naipesIguais + valoresIguais));
		return nivelDeConfianca;
	}

	@Override
	public void cobrir(List<Ficha> valorDaAposta) {
		super.cobrir(valorDaAposta);
		this.setValorDaUltimaAposta(valorDaAposta);
		this.setFichasDeApostaRodada(valorDaAposta);
		System.out.println("JogadorAutomatico.cobrir(valor)");
	}

	@Override
	public void pedirMesa() {
		super.pedirMesa();
		this.setValorDaUltimaAposta(new ArrayList<Ficha>());
		this.setFichasDeApostaRodada(new ArrayList<Ficha>());
		System.out.println("JogadorAutomatico.pedirMesa()");
	}

	@Override
	public void aumentar(List<Ficha> valorDaAposta) {
		super.aumentar(valorDaAposta);
		this.setValorDaUltimaAposta(valorDaAposta);
		this.setFichasDeApostaRodada(valorDaAposta);
		System.out.println("JogadorAutomatico.aumentar(valor)");
	}

	@Override
	public void sair() {
		super.sair();
		this.setValorDaUltimaAposta(new ArrayList<Ficha>());
		this.setFichasDeApostaRodada(new ArrayList<Ficha>());
		System.out.println("JogadorAutomatico.sair()");
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void mostrarCartas(CartasDaMesa cartasDaMesa) {
		super.mostrarCartas(cartasDaMesa);

		List<Carta> cartas = new ArrayList<>();
		cartas.addAll(this.getMao().getCartas());
		cartas.addAll(cartasDaMesa.getCartas());
		
		long quantidadeCartasValoresIguais = cartas.stream()
				.filter(carta -> Comparator.comparing(Carta::getValorAlternativo).equals(carta.getValorAlternativo()))
				.count();

		long quantidadeNaipesIguais = cartas.stream()
				.filter(carta -> Comparator.comparing(Carta::getNaipe).equals(carta.getNaipe())).count();

		int ultimoIndice = cartas.size() - 1;
		int quantidadeValoresEmSequencia = 0;
		int distancia = 0;
		for (int i = 0; i < ultimoIndice; i++) {
			if ((0 <= i&&i < (ultimoIndice - 1))) {
				distancia = Math.abs(cartas.get(i + 0).getValorAlternativo() - cartas.get(i + 1).getValorAlternativo());
				if (1 <= distancia&&distancia <= 4) {
					quantidadeValoresEmSequencia++;
				}
			}
			if (i == (ultimoIndice - 1)) {
				distancia = Math.abs(cartas.get(ultimoIndice - 1).getValorAlternativo() - cartas.get(ultimoIndice - 0).getValorAlternativo());
				if (1 <= distancia&&distancia <= 4) {
					quantidadeValoresEmSequencia++;
				}
			}
		}
		
		List<Carta> copiaCartas = new ArrayList<>();
		copiaCartas.addAll(cartas);
		
		int quantidadeDePar = 0;
		int tamanhoCopia = copiaCartas.size();
		for (int i = 0; i < tamanhoCopia; i++) {
			for (int j = 0; j < tamanhoCopia; j++) {
				copiaCartas.set(0, copiaCartas.get(j));
				copiaCartas.set(j, copiaCartas.get(0));
				if (0 <= j&&j < (tamanhoCopia - 2)) {
					if (copiaCartas.get(0).getValorAlternativo() == copiaCartas.get(j).getValorAlternativo()) {
						copiaCartas.remove(0);
						copiaCartas.remove(j + 1);
						quantidadeDePar += 1;
						tamanhoCopia -= 2;
					}
				}
				if (j == (tamanhoCopia - 2)) {
					if (copiaCartas.get(tamanhoCopia - 2).getValorAlternativo() == copiaCartas.get(tamanhoCopia - 1)
							.getValorAlternativo()) {
						copiaCartas.remove(tamanhoCopia - 2);
						copiaCartas.remove(tamanhoCopia - 1);
						quantidadeDePar += 1;
						tamanhoCopia -= 2;
					}
				}
			} 
		}
		 
		long menorValorNaSequencia = cartas
				.stream()
				.min(Comparator.comparingInt(Carta::getValorAlternativo))
				.get().getValorAlternativo();
		
		// -------------------------------------------------------------------------------------------------------------

		if ((Math.abs(quantidadeCartasValoresIguais - 7) == 0) && quantidadeNaipesIguais < 5) {// Carta Mais Alta
			new CartaMaisAlta().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 1);
		}

		if ((Math.abs(quantidadeCartasValoresIguais - 7) == 2)) {// Par
			new CartasValoresIguaisNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 2);
		}
		
		if (quantidadeDePar >= 2) {// Dois pares
			new DoisPares().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 4);
		}

		if ((Math.abs(quantidadeCartasValoresIguais - 7) == 3)) {// Trinca
			new CartasValoresIguaisNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 3);
		}
		
		if (quantidadeValoresEmSequencia >= 5 && quantidadeNaipesIguais <= 4) {// Sequência
			new CartasValoresSequenciaNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 5);
		}
		
		if (quantidadeNaipesIguais == 5) {// Flush
			new CartasValoresDiferentesNaipesIguais().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 5);
		}
		
		if ((Math.abs(quantidadeCartasValoresIguais - 7) == 3) && quantidadeDePar == 2) {// Fullhouse
			new FullHouse().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 5);
		}
		
		if ((Math.abs(quantidadeCartasValoresIguais - 7) == 4)) {// Quadra
			new CartasValoresIguaisNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 4);
		}
		
		if (quantidadeValoresEmSequencia >= 5 && quantidadeNaipesIguais >= 5 && menorValorNaSequencia < 10) {// Straight Flush
			new CartasValoresSequenciaNaipesIguais().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 5);
		}
		
		if (quantidadeValoresEmSequencia >= 5 && quantidadeNaipesIguais >= 5 && menorValorNaSequencia == 10) {// Royal Straight Flush
			new CartasValoresSequenciaNaipesIguais().encontrarMelhorCombinacaoDeCartas(this, cartasDaMesa, 5);
		}

		System.out.println("JogadorAutomatico.mostrarCartas(cartasDaMesa)");
	}

}
