package poker;

public class JogadorHumano extends Jogador {
	
	private boolean jogoEncerrado = false;
	private TipoDeJogador tipoDeJogador = TipoDeJogador.HUMANO;
	
	public JogadorHumano() {
		super();
	}

	public boolean isJogoEncerrado() {
		return jogoEncerrado;
	}

	public void setJogoEncerrado(boolean jogoEncerrado) {
		this.jogoEncerrado = jogoEncerrado;
	}

	public TipoDeJogador getTipoDeJogador() {
		return tipoDeJogador;
	}

	public void setTipoDeJogador(TipoDeJogador tipoDeJogador) {
		this.tipoDeJogador = tipoDeJogador;
	}

	public void comprarFichas(int valorDaStack, Mesa mesa) {
		this.setStack(valorDaStack);
		try {
			this.setFichasDeApostaRodada(mesa.entregarStack(valorDaStack));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AcaoDoJogador escolherAcao(AcaoDoJogador acaoDoJogador) {
		return acaoDoJogador;
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
