package poker;

import java.util.List;

public class JogadorHumano extends Jogador {
	
	private boolean jogoEncerrado = false;
	private TipoDeJogador tipoDeJogador = TipoDeJogador.HUMANO;
	private boolean fezAposta = false;
	
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

	public boolean fezAposta() {
		return fezAposta;
	}

	public void setFezAposta(boolean fezAposta) {
		this.fezAposta = fezAposta;
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
	public void cobrir(List<Ficha> valorDaAposta) {
		// TODO Auto-generated method stub
		super.cobrir(valorDaAposta);
	}

	@Override
	public void pedirMesa() {
		// TODO Auto-generated method stub
		super.pedirMesa();
	}

	@Override
	public void aumentar(List<Ficha> valorDaAposta) {
		// TODO Auto-generated method stub
		super.aumentar(valorDaAposta);
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		super.sair();
	}

	@Override
	public void mostrarCartas(CartasDaMesa cartasDaMesa) {
		super.mostrarCartas(cartasDaMesa);
	}

}
