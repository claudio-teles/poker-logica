package poker;

import java.util.List;

public class CartasDaMesa {
	
	private List<Carta> cartas;

	public CartasDaMesa() {
		super();
	}

	public CartasDaMesa(List<Carta> cartas) {
		super();
		this.cartas = cartas;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

}
