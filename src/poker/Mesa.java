package poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mesa {
	
	private String localimagemPlanoDeFundo;
	private boolean intencaoEscolherDealer;
	private boolean vencedor;
	private Jogador jogadorPremiado;
	private int ocorrenciaDeDistribuicaoDeCartas;
	private boolean podeEmbaralhar;
	private boolean dealerDefinido;
	private boolean blindDefinido;
	private Ficha blind;
	private Painel painel;
	private boolean rodadaEmAndamento;
	private List<Jogador> jogadoresDoTorneio;
	private boolean smallBlindApostado;

	public Mesa() {}
	
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

	public Ficha getBlind() {
		return blind;
	}

	public void setBlind(Ficha blind) {
		this.blind = blind;
	}

	public Painel getPainel() {
		return painel;
	}

	public void setPainel(Painel painel) {
		this.painel = painel;
	}

	public boolean isRodadaEmAndamento() {
		return rodadaEmAndamento;
	}

	public void setRodadaEmAndamento(boolean rodadaEmAndamento) {
		this.rodadaEmAndamento = rodadaEmAndamento;
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
			if (sobrou > 1000) {
				for (int i = 1; i <= 2; i++) {
					fichas.add(new Ficha(500, "roxo", null));// 2 x 500
					sobrou -= 500;
				}
			}
		} else {
			 throw new Exception("O valor da da stack para compra de fichas precisa ser maior que 20.");
		}
		return fichas;
	}
	
	public void definirBlind(Ficha fichaDesejada) {
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Jogador definirDealer(List<Jogador> jogadores, CartasDoDealer cartasDoDealer) {
		Jogador jogadorComCartaDeMaiorValor = new Jogador();
		
		if (intencaoEscolherDealer) {
			int somatorio = 0;
			List<Jogador> jogadoresAptos = new ArrayList<>();
			
			for (Jogador jogador : jogadores) {
				for (Ficha ficha : jogador.getFichas()) {
					somatorio += ficha.getValor();
					if (somatorio >= 20) {
						jogadoresAptos.add(jogador);
					}
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
				Jogador j = jogadores.get(i);
				Carta c = cartasDoDealer.getCartasDoDealer().get(i);
				
				if (!listaDeNumeros.contains(c.getValor())) {
					m.put((int) c.getValor(), j);
				}
			}
			
			jogadorComCartaDeMaiorValor = m.get(maiorValor(listaDeNumeros));
							
		} else {
			smallBlindApostado = false;
			return null;
		}
		return jogadorComCartaDeMaiorValor;
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
		
	}
	
	public void executarRodada(Partida partida) {
		
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
