package poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Mesa {

	private String localimagemPlanoDeFundo;
	private boolean intencaoEscolherDealer;
	private Jogador jogadorPremiado;
	private int ocorrenciaDeDistribuicaoDeCartas;
	private boolean podeEmbaralhar;
	private boolean dealerDefinido;
	private boolean smallBlindDefinidoDefinido;
	private boolean bigBlindDefinido;
	private boolean blindDefinido;
	private List<Ficha> blind;
	private Painel painel;
	private List<Jogador> jogadoresDoTorneio;
	private boolean smallBlindApostado = false;
	private boolean bigBlindApostado = false;
	private boolean novaPartida;
	private boolean partidaEmAndamento;
	private boolean rodadaEmAndamento;
	private int quantidadeDeRodadaDaPartida;
	private int valorDoTipoDeRodada;
	private boolean vencedor;
	private boolean empate;

	public Mesa() {
	}

	public String getLocalimagemPlanoDeFundo() {
		return localimagemPlanoDeFundo;
	}

	public void setLocalimagemPlanoDeFundo(String localimagemPlanoDeFundo) {
		this.localimagemPlanoDeFundo = localimagemPlanoDeFundo;
	}

	public boolean isIntencaoEscolherDealer() {
		return intencaoEscolherDealer;
	}

	public void setIntencaoEscolherDealer(boolean intencaoEscolherDealer) {
		this.intencaoEscolherDealer = intencaoEscolherDealer;
	}

	public boolean isVencedor() {
		return vencedor;
	}

	public void setVencedor(boolean vencedor) {
		this.vencedor = vencedor;
	}

	public Jogador getJogadorPremiado() {
		return jogadorPremiado;
	}

	public void setJogadorPremiado(Jogador jogadorPremiado) {
		this.jogadorPremiado = jogadorPremiado;
	}

	public int getOcorrenciaDeDistribuicaoDeCartas() {
		return ocorrenciaDeDistribuicaoDeCartas;
	}

	public void setOcorrenciaDeDistribuicaoDeCartas(int ocorrenciaDeDistribuicaoDeCartas) {
		this.ocorrenciaDeDistribuicaoDeCartas = ocorrenciaDeDistribuicaoDeCartas;
	}

	public boolean isPodeEmbaralhar() {
		return podeEmbaralhar;
	}

	public void setPodeEmbaralhar(boolean podeEmbaralhar) {
		this.podeEmbaralhar = podeEmbaralhar;
	}

	public boolean isDealerDefinido() {
		return dealerDefinido;
	}

	public void setDealerDefinido(boolean dealerDefinido) {
		this.dealerDefinido = dealerDefinido;
	}

	public boolean isBlindDefinido() {
		return blindDefinido;
	}

	public void setBlindDefinido(boolean blindDefinido) {
		this.blindDefinido = blindDefinido;
	}

	public boolean isSmallBlindDefinidoDefinido() {
		return smallBlindDefinidoDefinido;
	}

	public void setSmallBlindDefinidoDefinido(boolean smallBlindDefinidoDefinido) {
		this.smallBlindDefinidoDefinido = smallBlindDefinidoDefinido;
	}

	public boolean isBigBlindDefinido() {
		return bigBlindDefinido;
	}

	public void setBigBlindDefinido(boolean bigBlindDefinido) {
		this.bigBlindDefinido = bigBlindDefinido;
	}

	public List<Ficha> getBlind() {
		return blind;
	}

	public void setBlind(List<Ficha> blind) {
		this.blind = blind;
	}

	public Painel getPainel() {
		return painel;
	}

	public void setPainel(Painel painel) {
		this.painel = painel;
	}

	public List<Jogador> getJogadoresDoTorneio() {
		return jogadoresDoTorneio;
	}

	public void setJogadoresDoTorneio(List<Jogador> jogadoresDoTorneio) {
		this.jogadoresDoTorneio = jogadoresDoTorneio;
	}

	public boolean isSmallBlindApostado() {
		return smallBlindApostado;
	}

	public void setSmallBlindApostado(boolean smallBlindApostado) {
		this.smallBlindApostado = smallBlindApostado;
	}

	public boolean isBigBlindApostado() {
		return bigBlindApostado;
	}

	public void setBigBlindApostado(boolean bigBlindApostado) {
		this.bigBlindApostado = bigBlindApostado;
	}

	public boolean isEmpate() {
		return empate;
	}

	public void setEmpate(boolean empate) {
		this.empate = empate;
	}

	public List<Ficha> entregarStack(int valorDaStack) throws Exception {
		List<Ficha> fichas = new ArrayList<>();
		if (valorDaStack >= 20) {
			int sobrou = valorDaStack;
			if (sobrou > 15) {
				for (int i = 1; i <= 15; i++) {
					fichas.add(new Ficha(1, "branco", null));// 15 x 1
					sobrou -= 1;
				}
			}
			if (sobrou > 50) {
				for (int i = 1; i <= 10; i++) {
					fichas.add(new Ficha(5, "vermelho", null));// 10 x 5
					sobrou -= 5;
				}
			}
			if (sobrou > 50) {
				for (int i = 1; i <= 5; i++) {
					fichas.add(new Ficha(10, "laranja", null));// 5 x 10
					sobrou -= 10;
				}
			}
			if (sobrou > 80) {
				for (int i = 1; i <= 4; i++) {
					fichas.add(new Ficha(20, "amarelo", null));// 4 x 20
					sobrou -= 20;
				}
			}
			if (sobrou > 75) {
				for (int i = 1; i <= 3; i++) {
					fichas.add(new Ficha(25, "verde", null));// 3 x 25
					sobrou -= 25;
				}
			}
			if (sobrou > 500) {
				for (int i = 1; i <= 5; i++) {
					fichas.add(new Ficha(100, "preto", null));// 5 x 100
					sobrou -= 100;
				}
			}
			if (sobrou > 1000 && sobrou < 2000) {
				for (int i = 1; i <= 2; i++) {
					fichas.add(new Ficha(500, "roxo", null));// 2 x 500
					sobrou -= 500;
				}
			}
			if (sobrou > 2000) {
				for (int i = 1; i <= 2; i++) {
					fichas.add(new Ficha(1000, "castanho", null));// 2 x 1000
					sobrou -= 1000;
				}
			}
		} else {
			throw new Exception("O valor da da stack para compra de fichas precisa ser maior que 20.");
		}
		return fichas;
	}

	public void definirBlind(List<Ficha> fichaDesejada) throws Exception {
		int somatorio = 0;
		for (Ficha ficha : fichaDesejada) {
			somatorio += ficha.getValor();
		}
		if (this.dealerDefinido && somatorio >= 2) {
			this.setBlind(fichaDesejada);
			this.blindDefinido = true;
			this.podeEmbaralhar = true;
		} else {
			throw new Exception("Quantidade insuficiente de fichas, insira um valor maior ou igual a 2.");
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public Jogador definirDealer(CartasDoDealer cartasDoDealer) {
		Jogador jogadorComCartaDeMaiorValor = new Jogador();
		List<Jogador> jogadores = new ArrayList<>();
		jogadores.addAll(getJogadoresDoTorneio());

		if (intencaoEscolherDealer) {
			int somatorio = 0;
			List<Jogador> jogadoresAptos = new ArrayList<>();

			for (Jogador jogador : jogadores) {
				for (Ficha ficha : jogador.getFichas()) {
					somatorio += ficha.getValor();
				}
				if (somatorio >= 20) {
					jogadoresAptos.add(jogador);
					if (jogadoresAptos.size() == jogadores.size())
						break;
				}
				somatorio = 0;
			}

			int quantidadeDeJogadoresPraRetirar = (jogadores.size() - jogadoresAptos.size());

			List<Integer> listaDeNumeros = new ArrayList<>();
			Map<Integer, Jogador> m = new HashMap<>();

			List<Carta> novasCartas = cartasDoDealer.getCartasDoDealer();
			for (int i = 0; i < quantidadeDeJogadoresPraRetirar; i++) {
				novasCartas.remove(novasCartas.size() - 1);
			}

			for (Carta carta : novasCartas) {
				listaDeNumeros.add((int) carta.getValor());
			}

			for (int i = 0; i < novasCartas.size(); i++) {
				Jogador j = jogadoresAptos.get(i);
				Carta c = cartasDoDealer.getCartasDoDealer().get(i);

				if (!listaDeNumeros.contains(c.getValor())) {
					m.put((int) c.getValor(), j);
				}
			}

			jogadorComCartaDeMaiorValor = m.get(maiorValor(listaDeNumeros));
			jogadorComCartaDeMaiorValor.setVezDeSerDealer(true);

			int indiceDealer = jogadorComCartaDeMaiorValor.getIndice();
			int indiceSmallBlind = 0;
			int indiceBigBlind = 0;

			if (indiceDealer < (jogadoresAptos.size() - 1)) {
				indiceSmallBlind = indiceDealer + 1;
			}

			if (indiceDealer < (jogadoresAptos.size() - 2)) {
				indiceBigBlind = indiceDealer + 2;
			}

			if (indiceDealer == (jogadoresAptos.size())) {
				indiceSmallBlind = 1;
				indiceBigBlind = 2;
			}
			analizarVezDeApostar(jogadoresAptos, indiceSmallBlind, indiceBigBlind);

			this.dealerDefinido = true;
		} else {
			smallBlindApostado = false;
			return null;
		}
		return jogadorComCartaDeMaiorValor;
	}

	private void analizarVezDeApostar(List<Jogador> jogadoresAptos, int indiceSmallBlind, int indiceBigBlind) {
		jogadoresAptos.stream()
				.filter(jogador -> (jogador.getIndice() == indiceSmallBlind || jogador.getIndice() == indiceBigBlind))
				.forEach(jogador -> {
					if (jogador.getIndice() == indiceSmallBlind) {
						jogador.setVezDeSerBigBlind(true);
					}
					if (jogador.getIndice() == indiceBigBlind) {
						jogador.setVezDeSerBigBlind(true);
					}
				});
	}

	public int maiorValor(List<Integer> listaDeNumeros) {
		int numero = 0;
		for (int i = 0; i < listaDeNumeros.size(); i++) {
			int n = listaDeNumeros.get(i);
			if (n == numero) {
				continue;
			} else if (n > numero) {
				numero = n;
			}
		}
		return numero;
	}

	public void definirQuantidadeDeJogadores(int quantidadeDeJogadores) {

	}

	public void retirarJogadorDaPartida(Jogador jogador, Partida partida) {

	}

	public void iniciarPartida() {
		if (this.isSmallBlindApostado() && this.isBigBlindApostado()) {
			novaPartida = true;
			
			while (novaPartida) {
				partidaEmAndamento = true;
				rodadaEmAndamento = true;
				
				int numeroDePartidas = this.getPainel().getPartidas().size();
				int numero = numeroDePartidas + 1;
				
				Partida partida = new Partida();
				partida.setNumero(numero);
				partida.setPartidaEmAndamento(true);
				
				Baralho baralho = new Baralho();
				List<Carta> cartasEmbaralhadas = new ArrayList<>();
				
				Jogador dealer = new Jogador();
				
				for (Iterator<Jogador> iterator = getJogadoresDoTorneio().iterator(); iterator.hasNext();) {
					Jogador jogador = (Jogador) iterator.next();

					if (jogador.isVezDeSerDealer()) {
						dealer = jogador;
					}
				}
				
				List<Rodada> rodadas = new ArrayList<>();
				rodadas.addAll(partida.getRodadas());
				rodadas.add(new Rodada(TipoDeRodada.PRE_FLOP));
				
				while (partidaEmAndamento && rodadaEmAndamento) {
					Rodada ultimaRodada = rodadas.get(rodadas.size() - 1);
					valorDoTipoDeRodada = ultimaRodada.getTipoDeRodada().ordinal();
					
					if (valorDoTipoDeRodada < 4) {
						cartasEmbaralhadas.addAll(dealer.embaralharCartas(baralho));
						baralho.setCartas(cartasEmbaralhadas);
						quantidadeDeRodadaDaPartida = partida.getRodadas().size();
						if (quantidadeDeRodadaDaPartida == 0) {
							this.executarRodada(partida);
						}
						if (quantidadeDeRodadaDaPartida >= 1) {
							cartasEmbaralhadas.addAll(dealer.queimarCarta(baralho));
							this.executarRodada(partida);
						} 
					} else {
						valorDoTipoDeRodada = 4;
						rodadaEmAndamento = false;
					}
					// se alguém tiver aumentado a posta
					if (!rodadaEmAndamento) {
						switch (valorDoTipoDeRodada) {
						case 0:
							ultimaRodada = new Rodada(TipoDeRodada.FLOP);
							rodadas.add(ultimaRodada);
							this.rodadaEmAndamento = true;
							quantidadeDeRodadaDaPartida++;
							break;

						case 1:
							ultimaRodada = new Rodada(TipoDeRodada.TURN);
							rodadas.add(ultimaRodada);
							this.rodadaEmAndamento = true;
							quantidadeDeRodadaDaPartida++;
							break;

						case 2:
							ultimaRodada = new Rodada(TipoDeRodada.RIVER);
							rodadas.add(ultimaRodada);
							this.rodadaEmAndamento = true;
							quantidadeDeRodadaDaPartida++;
							break;

						case 3:
							ultimaRodada = new Rodada(TipoDeRodada.SHOWDOWN);
							rodadas.add(ultimaRodada);
							this.rodadaEmAndamento = true;
							quantidadeDeRodadaDaPartida++;
							break;

						case 4:
							partida.setRodadas(rodadas);
							this.painel.getPartidas().add(partida);
							this.partidaEmAndamento = false;
							this.rodadaEmAndamento = false;
							rodadas.clear();
							quantidadeDeRodadaDaPartida = 0;
							break;
						}
					}
				}
			}
		}
	}

	public void executarRodada(Partida partida) {
		this.setVencedor(partida.getJogador().isVencedor());
		Rodada ultimaRodada = partida.getRodadas().get(partida.getRodadas().size() - 1);
		
		if (
				this.partidaEmAndamento && this.rodadaEmAndamento && (this.isVencedor() == false)
				&& (this.empate == false) && (ultimaRodada.getTipoDeRodada() != null)
			) {
			TipoDeRodada etapaDaRodada = ultimaRodada.getTipoDeRodada();
			
			Jogador jogadorDaVez = new Jogador();
			
			for (Jogador jogador : getJogadoresDoTorneio()) {
				if (jogador.isVezDeJogar()) {
					jogadorDaVez = jogador;
				}
			}
			
			if (jogadorDaVez instanceof JogadorHumano) {
				List<Ficha> aposta = jogadorDaVez.getValorDaUltimaAposta();
				
				int somatorioDaAposta = 0;
				for (Ficha ficha : aposta) {
					somatorioDaAposta += ficha.getValor();
				}
				
				if (jogadorDaVez.getAcaoDoJogador() == AcaoDoJogador.COBRIR && (etapaDaRodada != TipoDeRodada.SHOWDOWN)) {
					jogadorDaVez.cobrir(somatorioDaAposta);
				}
				
				if (jogadorDaVez.getAcaoDoJogador() == AcaoDoJogador.PEDIR_MESA && (etapaDaRodada != TipoDeRodada.PRE_FLOP && etapaDaRodada != TipoDeRodada.SHOWDOWN)) {
					jogadorDaVez.pedirMesa();
				}
				
				if (jogadorDaVez.getAcaoDoJogador() == AcaoDoJogador.AUMENTAR && (etapaDaRodada != TipoDeRodada.SHOWDOWN)) {
					jogadorDaVez.aumentar(somatorioDaAposta);
				}
				
				if (jogadorDaVez.getAcaoDoJogador() == AcaoDoJogador.SAIR  && (etapaDaRodada != TipoDeRodada.PRE_FLOP && etapaDaRodada != TipoDeRodada.SHOWDOWN)) {
					jogadorDaVez.sair();
				}
				
				if (jogadorDaVez.getAcaoDoJogador() == AcaoDoJogador.MOSTRAR_CARTAS && (etapaDaRodada == TipoDeRodada.SHOWDOWN)) {
					jogadorDaVez.mostrarCartasDaMao();
				}
			} else if (jogadorDaVez instanceof JogadorAutomatico) {
				
			}
		}
	}

	public void avaliarVencedor(Partida partida) {

	}

	public void desempatar() {

	}

	public void fazerPremiacao(Painel painel) {

	}

	public void jogadorTemFichas(Jogador jogador) {

	}

	public void escolherJogadorDaVez(Rodada rodada) {

	}

	public void avaliarVencedorDaRodada(List<Jogador> jogadores, Rodada rodada) {

	}

	public void determinarEmpateDaRodada() {

	}

}
