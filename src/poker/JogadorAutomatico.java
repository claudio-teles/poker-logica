package poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JogadorAutomatico extends Jogador {
	
	private TipoDeJogador tipoDeJogador = TipoDeJogador.AUTOMATICO;

	public JogadorAutomatico() {}

	public TipoDeJogador getTipoDeJogador() {
		return tipoDeJogador;
	}

	public void setTipoDeJogador(TipoDeJogador tipoDeJogador) {
		this.tipoDeJogador = tipoDeJogador;
	}
	
	public Map<AcaoDoJogador, List<Ficha>> escolherAcaoDoJogador(List<Carta> cartas, TipoDeRodada rodada, Mesa mesa) {
		Map<AcaoDoJogador, List<Ficha>> mapaAcaoFicha = null;
		List<Ficha> fichas = null;
		
		// Calcular somatório das fichas do jog. automatico, blind e blind aumentada; precisa ser maior ou igual a 3x
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
			if (somatorioFichasJogAutomatico >= (3 * somatorioBlind) || somatorioFichasJogAutomatico >= (3 * somatorioBlindAumentada)) {
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
			if (somatorioFichasJogAutomatico >= (3 * somatorioBlind) || somatorioFichasJogAutomatico >= (3 * somatorioBlindAumentada)) {
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
		float valoresIguais = 0;
		float naipesIguais = 0;
		float valoresEmSequencia = 0;
		
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
				
				// se cartas[j].getValor() == cartas[j+1].getValor()
				if (0 < j&&j < (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getValorAlternativo() == copiaCartas.get(j + 1).getValorAlternativo()) {
						valoresIguais += 0.2f;
						if (quantidadeDeCartas > 0) {
							copiaCartas.remove(0);
							quantidadeDeCartas -= 1;
						}
					}
				}
				if (j == (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getValorAlternativo() == copiaCartas.get(quantidadeDeCartas - 1).getValorAlternativo()) {
						valoresIguais += 0.2f;
						if (quantidadeDeCartas > 0) {
							copiaCartas.remove(0);
							quantidadeDeCartas -= 1;
						}
					}
				}
				
				// se cartas[j].getNaipe() == cartas[j+1].getNaipe()
				if (0 < j&&j < (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getNaipe() == copiaCartas.get(j + 1).getNaipe()) {
						naipesIguais += 0.2f;
						if (quantidadeDeCartas > 0) {
							copiaCartas.remove(0);
							quantidadeDeCartas -= 1;
						}
					}
				}
				if (j == (quantidadeDeCartas - 2)) {
					if (copiaCartas.get(0).getNaipe() == copiaCartas.get(quantidadeDeCartas - 1).getNaipe()) {
						naipesIguais += 0.2f;
						if (quantidadeDeCartas > 0) {
							copiaCartas.remove(0);
							quantidadeDeCartas -= 1;
						}
					}
				}
				
				// se modulo(cartas[j].getValor() - cartas[j+1].getValor()) <= 4
				if (0 < j&&j < (quantidadeDeCartas - 2)) {
					if (Math.abs(copiaCartas.get(0).getValorAlternativo() - copiaCartas.get(j + 1).getValorAlternativo()) <= 4) {
						valoresEmSequencia += 0.2f;
						if (quantidadeDeCartas > 0) {
							copiaCartas.remove(0);
							quantidadeDeCartas -= 1;
						}
					}
				}
				if (j == (quantidadeDeCartas - 2)) {
					if (Math.abs(copiaCartas.get(0).getValorAlternativo() - copiaCartas.get(quantidadeDeCartas - 1).getValorAlternativo()) <= 4) {
						valoresEmSequencia += 0.2f;
						if (quantidadeDeCartas > 0) {
							copiaCartas.remove(0);
							quantidadeDeCartas -= 1;
						}
					}
				}
			}
		}
		
		float nivelDeConfianca = valoresEmSequencia + naipesIguais + valoresIguais;
		return nivelDeConfianca;
	}

	@Override
	public void cobrir(List<Ficha> valorDaAposta) {
		super.cobrir(valorDaAposta);
	}

	@Override
	public void pedirMesa() {
		super.pedirMesa();
	}

	@Override
	public void aumentar(List<Ficha> valorDaAposta) {
		super.aumentar(valorDaAposta);
	}

	@Override
	public void sair() {
		super.sair();
	}

	@Override
	public void mostrarCartasDaMao() {
		super.mostrarCartasDaMao();
	}

}
