package desingpatters.strategy;

import java.util.List;

import poker.Carta;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class ResultadoDeAnalise {
	
	private Jogador jogador;
	private ECombinacaoDeCarta eCombinacaoDeCarta;
	private List<Carta> cartas;
	
	public ResultadoDeAnalise() {
		super();
	}

	public ResultadoDeAnalise(Jogador jogador, ECombinacaoDeCarta eCombinacaoDeCarta, List<Carta> cartas) {
		super();
		this.jogador = jogador;
		this.eCombinacaoDeCarta = eCombinacaoDeCarta;
		this.cartas = cartas;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public ECombinacaoDeCarta getCombinacaoDeCarta() {
		return eCombinacaoDeCarta;
	}

	public void setCombinacaoDeCarta(ECombinacaoDeCarta eCombinacaoDeCarta) {
		this.eCombinacaoDeCarta = eCombinacaoDeCarta;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	@Override
	public String toString() {
		return "ResultadoDeAnalise [jogador=" + jogador + ", eCombinacaoDeCarta=" + eCombinacaoDeCarta + ", cartas="
				+ cartas + "]";
	}

}
