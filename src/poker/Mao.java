package poker;

import java.util.ArrayList;
import java.util.List;

public class Mao {
	
	private List<Carta> cartas;

	public Mao() {
		cartas = new ArrayList<>();
		cartas.add(new Carta());
		cartas.add(new Carta());
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

}
