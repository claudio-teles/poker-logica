package poker;

public class Rodada {

	private TipoDeRodada tipoDeRodada;

	public Rodada() {
	}

	public Rodada(TipoDeRodada tipoDeRodada) {
		super();
		this.tipoDeRodada = tipoDeRodada;
	}

	public TipoDeRodada getTipoDeRodada() {
		return tipoDeRodada;
	}

	public void setTipoDeRodada(TipoDeRodada tipoDeRodada) {
		this.tipoDeRodada = tipoDeRodada;
	}
}
