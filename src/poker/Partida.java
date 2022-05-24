package poker;

import java.util.List;

public class Partida {
	
	private int numero;
	private Jogador jogador;
	private boolean partidaEmAndamento;
	private List<Rodada> rodadas;

	public Partida() {}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogadore) {
		this.jogador = jogadore;
	}

	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

}
