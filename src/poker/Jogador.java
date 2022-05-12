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
	private Mao mao;
	private boolean vezDeJogar;
	private boolean vencedor;
	private List<Ficha> fichasDeApostaRodada;
	private boolean visivel;
	private AcaoDoJogador acaoDoJogador;
	
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

	public List<Ficha> getFichasDeApostaRodada() {
		return fichasDeApostaRodada;
	}

	public void setFichasDeApostaRodada(List<Ficha> fichasDeApostaRodada) {
		this.fichasDeApostaRodada = fichasDeApostaRodada;
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
	
	public void distribuirCartasDaPartida(Baralho baralho, List<Jogador> jogadores, Mesa mesa) {
		
	}
	
	public void apostarSmallBlind() {
		
	}
	
	public void apostarBigBlind(List<Ficha> fichas) {
		
	}

	
	public void embaralharCartas(Baralho baralho) {
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

	@Override
	public String toString() {
		return "Jogador [indice=" + indice + ", nome=" + nome + ", imagem=" + imagem + ", dinheiro=" + dinheiro
				+ ", stack=" + stack + ", valorDaFicha=" + valorDaFicha + ", fichas=" + fichas + ", mao=" + mao
				+ ", vezDeJogar=" + vezDeJogar + ", vencedor=" + vencedor + ", fichasDeApostaRodada="
				+ fichasDeApostaRodada + ", visivel=" + visivel + ", acaoDoJogador=" + acaoDoJogador + "]";
	}
	
}
