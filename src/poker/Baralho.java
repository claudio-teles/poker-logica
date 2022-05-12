package poker;

import java.util.ArrayList;
import java.util.List;

public class Baralho {
	
	private List<Carta> cartas;
	
	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public Baralho() {
		super();
		cartas = new ArrayList<>();
		int numeroDaCarta = 1;
		// repita enquanto numero de cartas for menor que 14
		while (numeroDaCarta < 14) {
			String lit = "LIT".concat(String.valueOf(numeroDaCarta));
			String lif = "LIF".concat(String.valueOf(numeroDaCarta));
			if (numeroDaCarta == 1) {
				cartas.add(new Carta((short) 1, (short) 14, "ESPADA", 'A', lit, lif, false));
				cartas.add(new Carta((short) 1, (short) 14, "PAUS", 'A', lit, lif, false));
				cartas.add(new Carta((short) 1, (short) 14, "COPAS", 'A', lit, lif, false));
				cartas.add(new Carta((short) 1, (short) 14, "OURO", 'A', lit, lif, false));
				numeroDaCarta++;
			}
			
			if (numeroDaCarta >= 2 || numeroDaCarta <= 10) {
				cartas.add(new Carta((short) numeroDaCarta, (short) numeroDaCarta, "ESPADA", '-', lit, lif, false));
				cartas.add(new Carta((short) numeroDaCarta, (short) numeroDaCarta, "OURO", '-', lit, lif, false));
				cartas.add(new Carta((short) numeroDaCarta, (short) numeroDaCarta, "COPAS", '-', lit, lif, false));
				cartas.add(new Carta((short) numeroDaCarta, (short) numeroDaCarta, "OURO", '-', lit, lif, false));
				numeroDaCarta++;
			}
			
			if (numeroDaCarta == 11) {
				cartas.add(new Carta((short) 11, (short) numeroDaCarta, "ESPADA", 'J', lit, lif, false));
				cartas.add(new Carta((short) 11, (short) numeroDaCarta, "PAUS", 'J', lit, lif, false));
				cartas.add(new Carta((short) 11, (short) numeroDaCarta, "COPAS", 'J', lit, lif, false));
				cartas.add(new Carta((short) 11, (short) numeroDaCarta, "OURO", 'J', lit, lif, false));
				numeroDaCarta++;
			}
			
			if (numeroDaCarta == 12) {
				cartas.add(new Carta((short) 12, (short) numeroDaCarta, "ESPADA", 'Q', lit, lif, false));
				cartas.add(new Carta((short) 12, (short) numeroDaCarta, "PAUS", 'Q', lit, lif, false));
				cartas.add(new Carta((short) 12, (short) numeroDaCarta, "COPAS", 'Q', lit, lif, false));
				cartas.add(new Carta((short) 12, (short) numeroDaCarta, "OURO", 'Q', lit, lif, false));
				numeroDaCarta++;
			}
			
			if (numeroDaCarta == 13) {
				cartas.add(new Carta((short) 13, (short) numeroDaCarta, "ESPADA", 'K', lit, lif, false));
				cartas.add(new Carta((short) 13, (short) numeroDaCarta, "PAUS", 'K', lit, lif, false));
				cartas.add(new Carta((short) 13, (short) numeroDaCarta, "COPAS", 'K', lit, lif, false));
				cartas.add(new Carta((short) 13, (short) numeroDaCarta, "OURO", 'K', lit, lif, false));
				numeroDaCarta++;
			}
		}
	}

	@Override
	public String toString() {
		return "Baralho [cartas=" + cartas + "]";
	}

	public void inicializarCartas(int totalDeJogadores) {
		
	}

}
