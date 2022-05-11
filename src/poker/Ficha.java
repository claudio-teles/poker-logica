package poker;

public class Ficha {
	
	private int valor;
	private String cor;
	private String localDaImagem;

	public Ficha() {}

	public Ficha(int valor, String cor, String localDaImagem) {
		super();
		this.valor = valor;
		this.cor = cor;
		this.localDaImagem = localDaImagem;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getLocalDaImagem() {
		return localDaImagem;
	}

	public void setLocalDaImagem(String localDaImagem) {
		this.localDaImagem = localDaImagem;
	}

	@Override
	public String toString() {
		return "Ficha [valor=" + valor + ", cor=" + cor + ", localDaImagem=" + localDaImagem + "]";
	}

}
