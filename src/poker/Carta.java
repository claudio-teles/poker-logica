package poker;

public class Carta {
	
	private short valor;
	private short valorAlternativo;
	private String naipe;
	private char letra;
	private String localImagemDeFrente;
	private String localImagemDeTras;
	private boolean visivel = false;
	
	public Carta() {
		super();
	}

	public Carta(short valor, short valorAlternativo, String naipe, char letra, String localImagemDeFrente,
			String localImagemDeTras, boolean visivel) {
		super();
		this.valor = valor;
		this.valorAlternativo = valorAlternativo;
		this.naipe = naipe;
		this.letra = letra;
		this.localImagemDeFrente = localImagemDeFrente;
		this.localImagemDeTras = localImagemDeTras;
		this.visivel = visivel;
	}

	public short getValor() {
		return valor;
	}

	public void setValor(short valor) {
		this.valor = valor;
	}

	public short getValorAlternativo() {
		return valorAlternativo;
	}

	public void setValorAlternativo(short valorAlternativo) {
		this.valorAlternativo = valorAlternativo;
	}

	public String getNaipe() {
		return naipe;
	}

	public void setNaipe(String naipe) {
		this.naipe = naipe;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public String getLocalImagemDeFrente() {
		return localImagemDeFrente;
	}

	public void setLocalImagemDeFrente(String localImagemDeFrente) {
		this.localImagemDeFrente = localImagemDeFrente;
	}

	public String getLocalImagemDeTras() {
		return localImagemDeTras;
	}

	public void setLocalImagemDeTras(String localImagemDeTras) {
		this.localImagemDeTras = localImagemDeTras;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	@Override
	public String toString() {
		return "Carta [valor=" + valor + ", valorAlternativo=" + valorAlternativo + ", naipe=" + naipe + ", letra="
				+ letra + ", localImagemDeFrente=" + localImagemDeFrente + ", localImagemDeTras=" + localImagemDeTras
				+ ", visivel=" + visivel + "]";
	}

}
