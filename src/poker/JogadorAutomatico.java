package poker;

public class JogadorAutomatico extends Jogador {
	
	private TipoDeJogador tipoDeJogador = TipoDeJogador.AUTOMATICO;

	public JogadorAutomatico() {}

	public TipoDeJogador getTipoDeJogador() {
		return tipoDeJogador;
	}

	public void setTipoDeJogador(TipoDeJogador tipoDeJogador) {
		this.tipoDeJogador = tipoDeJogador;
	}

	@Override
	public void cobrir(int valor) {
		// TODO Auto-generated method stub
		super.cobrir(valor);
	}

	@Override
	public void pedirMesa() {
		// TODO Auto-generated method stub
		super.pedirMesa();
	}

	@Override
	public void aumentar(int valor) {
		// TODO Auto-generated method stub
		super.aumentar(valor);
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		super.sair();
	}

	@Override
	public void mostrarCartasDaMao() {
		// TODO Auto-generated method stub
		super.mostrarCartasDaMao();
	}

}
