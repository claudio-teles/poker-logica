package poker;

import java.util.ArrayList;
import java.util.List;

public class Baralho {
	
	private List<Carta> cartas;
	
	public List<Carta> getCartas() {
		return cartas;
	}

	public Baralho() {
		super();
		cartas = new ArrayList<>();
		int numeroDaCarta = 1;
		// repita enquanto numero de cartas for menor que 14
		while (numeroDaCarta <= 14) {
			String lit = "LIT".concat(String.valueOf(numeroDaCarta));
			String lif = "LIF".concat(String.valueOf(numeroDaCarta));
			if (numeroDaCarta == 1) {
				cartas.add(new Carta((short) 1, (short) 14, "ESPADA", 'A', lit, lif, false));
				cartas.add(new Carta((short) 1, (short) 14, "PAUS", 'A', lit, lif, false));
				cartas.add(new Carta((short) 1, (short) 14, "COPAS", 'A', lit, lif, false));
				cartas.add(new Carta((short) 1, (short) 14, "OURO", 'A', lit, lif, false));
			} else {
				if (numeroDaCarta >= 2 || numeroDaCarta <= 10) {
					cartas.add(new Carta((short) numeroDaCarta, (short) 0, "ESPADA", '-', lit, lif, false));
					cartas.add(new Carta((short) numeroDaCarta, (short) 0, "OURO", '-', lit, lif, false));
					cartas.add(new Carta((short) numeroDaCarta, (short) 0, "COPAS", '-', lit, lif, false));
					cartas.add(new Carta((short) numeroDaCarta, (short) 0, "OURO", '-', lit, lif, false));
				} else {
					if (numeroDaCarta == 11) {
						cartas.add(new Carta((short) 11, (short) 0, "ESPADA", 'J', lit, lif, false));
						cartas.add(new Carta((short) 11, (short) 0, "PAUS", 'J', lit, lif, false));
						cartas.add(new Carta((short) 11, (short) 0, "COPAS", 'J', lit, lif, false));
						cartas.add(new Carta((short) 11, (short) 0, "OURO", 'J', lit, lif, false));
					} else {

						if (numeroDaCarta == 12) {
							cartas.add(new Carta((short) 12, (short) 0, "ESPADA", 'Q', lit, lif, false));
							cartas.add(new Carta((short) 12, (short) 0, "PAUS", 'Q', lit, lif, false));
							cartas.add(new Carta((short) 12, (short) 0, "COPAS", 'Q', lit, lif, false));
							cartas.add(new Carta((short) 12, (short) 0, "OURO", 'Q', lit, lif, false));
						} else {
							if (numeroDaCarta == 13) {
								cartas.add(new Carta((short) 13, (short) 0, "ESPADA", 'K', lit, lif, false));
								cartas.add(new Carta((short) 13, (short) 0, "PAUS", 'K', lit, lif, false));
								cartas.add(new Carta((short) 13, (short) 0, "COPAS", 'K', lit, lif, false));
								cartas.add(new Carta((short) 13, (short) 0, "OURO", 'K', lit, lif, false));
							}
						}
					}
				}
			}
			numeroDaCarta++;
		}
	}

	@Override
	public String toString() {
		return "Baralho [cartas=" + cartas + "]";
	}

	public void inicializarCartas(int totalDeJogadores) {
		
	}

}
