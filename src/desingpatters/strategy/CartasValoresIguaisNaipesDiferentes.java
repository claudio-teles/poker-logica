package desingpatters.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class CartasValoresIguaisNaipesDiferentes implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa, int quantidadeCartasSaida) {
		List<Carta> cartas = new ArrayList<>();
		cartas.addAll(jogador.getMao().getCartas());
		cartas.addAll(cartasDaMesa.getCartas());
		
		List<Carta> listaTemporaria = new ArrayList<>();
		List<Carta> par = new ArrayList<>();
		List<Carta> trinca = new ArrayList<>();
		List<Carta> quadra = new ArrayList<>();
		
		Map<Integer, Integer> mapaValorCartaFrequencia = new HashMap<>();
		Map<Integer, Integer> mapaFrequenciaValorCarta = new HashMap<>();
		
		for (int i = 2; i <= 14; i++) {
			mapaValorCartaFrequencia.put(i, 0);
		}
		
		boolean loopRodando = true;
		int totalCartas = cartas.size();
		
		while (loopRodando) {
			for (int i = 0; i < totalCartas; i++) {
				Carta carta = new Carta();
				carta = cartas.get(i);
				if (!(listaTemporaria.contains(carta))) {
					for (int j = 2; j <= 14; j++) {
						if (j == carta.getValorAlternativo()) {
							int frequenciaAnterior = mapaValorCartaFrequencia.get((int) carta.getValorAlternativo());
							int novaFrequencia = frequenciaAnterior + 1;
							mapaValorCartaFrequencia.replace(j, novaFrequencia);
							listaTemporaria.add(carta);
						}
						if (i == (totalCartas - 1) && j == 14) {
							loopRodando = false;
						}
					}
				} 
			}
		}
		
		mapaValorCartaFrequencia.forEach((valorDeCarta, frequencia) -> {
			mapaFrequenciaValorCarta.put(frequencia, valorDeCarta);
		});
		
		int valorRepete4X = 0;// get(4)
		if (quantidadeCartasSaida == 4) {
			valorRepete4X = mapaFrequenciaValorCarta.get(4);
		}
		int valorRepete3X = 0;// get(3)
		if (quantidadeCartasSaida == 3) {
			valorRepete3X = mapaFrequenciaValorCarta.get(3);
		}
		int valorRepete2X = 0;// get(2)
		if (quantidadeCartasSaida == 2) {
			valorRepete2X = mapaFrequenciaValorCarta.get(2);
		}
		
		for (Carta carta : cartas) {
			if (carta.getValorAlternativo() == valorRepete4X) {
				quadra.add(carta);
			}
			if (carta.getValorAlternativo() == valorRepete3X) {
				trinca.add(carta);
			}
			if (carta.getValorAlternativo() == valorRepete2X) {
				par.add(carta);
			}
		}

		switch (quantidadeCartasSaida) {
		case 2:
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.PAR, par);
		case 3:
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.TRINCA, trinca);
		case 4:
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.QUADRA, quadra);
		}
		
		return null;
	}

}
