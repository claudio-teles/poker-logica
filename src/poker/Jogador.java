package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jogador {
	
	private int indice;
	private String nome;
	private String imagem;
	private int dinheiro;
	private int stack;
	private int valorDaFicha;
	private List<Ficha> fichas;
	private Mao mao = new Mao();
	private boolean vezDeJogar;
	private boolean vencedor;
	private boolean visivel;
	private AcaoDoJogador acaoDoJogador;
	private List<Ficha> valorDaUltimaAposta;
	private boolean vezDeSerDealer = false;
	private boolean vezDeSerSmallBlind = false;
	private boolean vezDeSerBigBlind = false;
	private List<Ficha> fichasDeApostaRodada;
	
	public Jogador() {
		super();
	}

	public Jogador(int indice, String nome) {
		super();
		this.indice = indice;
		this.nome = nome;
	}

	public Jogador(int indice, String nome, List<Ficha> fichas) {
		super();
		this.indice = indice;
		this.nome = nome;
		this.fichas = fichas;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public int getValorDaFicha() {
		return valorDaFicha;
	}

	public void setValorDaFicha(int valorDaFicha) {
		this.valorDaFicha = valorDaFicha;
	}

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}

	public Mao getMao() {
		return mao;
	}

	public void setMao(Mao mao) {
		this.mao = mao;
	}

	public boolean isVezDeJogar() {
		return vezDeJogar;
	}

	public void setVezDeJogar(boolean vezDeJogar) {
		this.vezDeJogar = vezDeJogar;
	}

	public boolean isVencedor() {
		return vencedor;
	}

	public void setVencedor(boolean vencedor) {
		this.vencedor = vencedor;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public AcaoDoJogador getAcaoDoJogador() {
		return acaoDoJogador;
	}

	public void setAcaoDoJogador(AcaoDoJogador acaoDoJogador) {
		this.acaoDoJogador = acaoDoJogador;
	}

	public List<Ficha> getValorDaUltimaAposta() {
		return valorDaUltimaAposta;
	}

	public void setValorDaUltimaAposta(List<Ficha> valorDaUltimaAposta) {
		this.valorDaUltimaAposta = valorDaUltimaAposta;
	}

	public boolean isVezDeSerDealer() {
		return vezDeSerDealer;
	}

	public void setVezDeSerDealer(boolean vezDeSerDealer) {
		this.vezDeSerDealer = vezDeSerDealer;
	}

	public boolean isVezDeSerSmallBlind() {
		return vezDeSerSmallBlind;
	}

	public void setVezDeSerSmallBlind(boolean vezDeSerSmallBlind) {
		this.vezDeSerSmallBlind = vezDeSerSmallBlind;
	}

	public boolean isVezDeSerBigBlind() {
		return vezDeSerBigBlind;
	}

	public void setVezDeSerBigBlind(boolean vezDeSerBigBlind) {
		this.vezDeSerBigBlind = vezDeSerBigBlind;
	}

	public List<Ficha> getFichasDeApostaRodada() {
		return fichasDeApostaRodada;
	}

	public void setFichasDeApostaRodada(List<Ficha> fichasDeApostaRodada) {
		this.fichasDeApostaRodada = fichasDeApostaRodada;
	}

	public List<Carta> distribuirCartasDoDealer(Baralho baralho, List<Jogador> jogadores, Mesa mesa) {
		List<Carta> cartasDoDealer = new ArrayList<>();
		if (!mesa.isDealerDefinido()) {
			boolean cicloDeContagem = true;
			while (cicloDeContagem) {
				Carta cartaSelecionda = baralho.getCartas().get(new Random().nextInt((52)));
				if (!cartasDoDealer.contains(cartaSelecionda)) {
					cartasDoDealer.add(cartaSelecionda);
					if (cartasDoDealer.size() == jogadores.size()) {
						cicloDeContagem = false;
					}
				} else {
					continue;
				}
			}
			mesa.setDealerDefinido(true);
			return cartasDoDealer;
		} else {
			mesa.setIntencaoEscolherDealer(true);
			mesa.setPodeEmbaralhar(true);
		}
		return null;
	}
	
	public List<Jogador> distribuirCartasDaPartida(Baralho baralho, List<Jogador> jogadores, Mesa mesa) {
		if (mesa.isBigBlindApostado()) {
			Jogador dealer = new Jogador();
			for (Jogador jogador : jogadores) {
				if (jogador.isVezDeSerDealer()) {
					dealer = jogador;
				}
			}
			
			List<Carta> cartasDoBaralhoMisturadas = dealer.embaralharCartas(baralho);
			
			int quantidadeDeJogadores = jogadores.size();
			for (int i = 0; i < quantidadeDeJogadores; i++) {
				Jogador jogador = jogadores.get(i);
				Carta primeiraCarta = cartasDoBaralhoMisturadas.get(i);
				jogador.getMao().getCartas().set(0, primeiraCarta);
				
				cartasDoBaralhoMisturadas.set(i, null);
				baralho.setCartas(cartasDoBaralhoMisturadas);
			}
			
			for (int i = quantidadeDeJogadores; i < (2 * quantidadeDeJogadores); i++) {
				Jogador jogador = jogadores.get(i - quantidadeDeJogadores);
				Carta primeiraCarta = cartasDoBaralhoMisturadas.get(i);
				jogador.getMao().getCartas().set(1, primeiraCarta);
				
				cartasDoBaralhoMisturadas.set(i, null);
				baralho.setCartas(cartasDoBaralhoMisturadas);
			}
			
			return jogadores;
		}
		return null;
	}
	
	public List<Ficha> apostarSmallBlind(List<Ficha> blind, Mesa mesa) {
		if (
				mesa.isBlindDefinido() && mesa.isSmallBlindDefinidoDefinido() && mesa.isBigBlindDefinido() &&
				mesa.isSmallBlindApostado() == false && mesa.isIntencaoEscolherDealer() == false
			) {
			
			String localDaImagem = "lif1";
			String cor = "";
			int somatorio = 0;
			for (Ficha ficha : blind) {
				somatorio += ficha.getValor();
			}
			
			int numeroDaCor = ((somatorio >= 1 && somatorio < 5) ? 1 : (somatorio >= 5 && somatorio < 10) ? 2 : (somatorio >= 10 && somatorio < 20) ? 3 : (somatorio >= 20 && somatorio < 25) ? 4 : (somatorio >= 25 && somatorio < 100) ? 5 : (somatorio >= 100 && somatorio < 500) ? 6 : (somatorio >= 500 && somatorio < 1000) ? 7 : 8);
			
			switch (numeroDaCor) {
			case 2:
				cor = "vermelho";
				break;
				
			case 3:
				cor = "laranja";
				break;
				
			case 4:
				cor = "amarelo";
				break;
				
			case 5:
				cor = "verde";
				break;
				
			case 6:
				cor = "preto";
				break;
				
			case 7:
				cor = "roxo";
				break;
				
			case 8:
				cor = "castanho";
				break;

			default:
				cor = "branco";
				break;
			}
			
			Ficha metadeDoBlind = new Ficha((somatorio / 2), cor, localDaImagem);
			
			valorDaUltimaAposta = new ArrayList<>();
			valorDaUltimaAposta.clear();
			valorDaUltimaAposta.add(metadeDoBlind);
			mesa.setSmallBlindApostado(true);
		}
		return valorDaUltimaAposta;
	}
	
	public List<Ficha> apostarBigBlind(List<Ficha> fichas, Mesa mesa) {
		if (mesa.isSmallBlindApostado()) {
			valorDaUltimaAposta = new ArrayList<>();
			valorDaUltimaAposta.clear();
			fichas.forEach(ficha -> valorDaUltimaAposta.add(ficha));
			mesa.setBigBlindApostado(true);
			return valorDaUltimaAposta;
		}
		return null;
	}

	
	public List<Carta> embaralharCartas(Baralho baralho) {
		Carta[] cartas = new Carta[52];
		int numeroDeCartasRestantes = 52;
		
		int i = 0;
		while (numeroDeCartasRestantes != 0) {
			cartas[i] = baralho.getCartas().get(new Random().nextInt((numeroDeCartasRestantes)));
			baralho.getCartas().remove(cartas[i]);
			i++;
			numeroDeCartasRestantes--;
		}
		
		Baralho baralhoComCartasAleatorias = new Baralho();
		List<Carta> cartasAleatorias = Arrays.asList(cartas);
		baralhoComCartasAleatorias.setCartas(cartasAleatorias);
		return cartasAleatorias;
	}
	
	public void cobrir(int valor) {
		System.out.println("super clase Jogador.cobrir()");
	}
	
	public void pedirMesa() {
		System.out.println("super clase Jogador.pedirMesa()");
	}
	
	public void aumentar(int valor) {
		System.out.println("super clase Jogador.aumentar(valor)");
	}
	
	public void sair() {
		System.out.println("super clase Jogador.sair()");
	}
	
	public void mostrarCartasDaMao() {
		System.out.println("super clase Jogador.mostrarCartasDaMao");
	}
	
	public List<Carta> queimarCarta(Baralho baralho) {
		Carta primeiraCarta = baralho.getCartas().get(0);
		Carta ultimaCarta = baralho.getCartas().get(51);
		
		baralho.getCartas().set(0, ultimaCarta);
		baralho.getCartas().set(51, primeiraCarta);
		
		return baralho.getCartas();
	}

	@Override
	public String toString() {
		return "Jogador [indice=" + indice + ", nome=" + nome + ", imagem=" + imagem + ", dinheiro=" + dinheiro
				+ ", stack=" + stack + ", valorDaFicha=" + valorDaFicha + ", fichas=" + fichas + ", mao=" + mao
				+ ", vezDeJogar=" + vezDeJogar + ", vencedor=" + vencedor + ", visivel=" + visivel + ", acaoDoJogador="
				+ acaoDoJogador + ", valorDaUltimaAposta=" + valorDaUltimaAposta + ", vezDeSerDealer=" + vezDeSerDealer
				+ ", vezDeSerSmallBlind=" + vezDeSerSmallBlind + ", vezDeSerBigBlind=" + vezDeSerBigBlind
				+ ", getIndice()=" + getIndice() + ", getNome()=" + getNome() + ", getImagem()=" + getImagem()
				+ ", getDinheiro()=" + getDinheiro() + ", getStack()=" + getStack() + ", getValorDaFicha()="
				+ getValorDaFicha() + ", getFichas()=" + getFichas() + ", getMao()=" + getMao() + ", isVezDeJogar()="
				+ isVezDeJogar() + ", isVencedor()=" + isVencedor() + ", isVisivel()=" + isVisivel()
				+ ", getAcaoDoJogador()=" + getAcaoDoJogador() + ", getValorDaUltimaAposta()="
				+ getValorDaUltimaAposta() + ", isVezDeSerDealer()=" + isVezDeSerDealer() + ", isVezDeSerSmallBlind()="
				+ isVezDeSerSmallBlind() + ", isVezDeSerBigBlind()=" + isVezDeSerBigBlind() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
