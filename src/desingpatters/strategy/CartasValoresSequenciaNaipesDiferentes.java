package desingpatters.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class CartasValoresSequenciaNaipesDiferentes implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa, int quantidadeCartasSaida) {
		List<Carta> cartas = new ArrayList<>();
		cartas.addAll(jogador.getMao().getCartas());
		cartas.addAll(cartasDaMesa.getCartas());

		List<Integer> valoresDeTodasAsCartas = new ArrayList<>();
		List<Short> valoresEmSequencia = new ArrayList<>();
		
		for (Carta carta : cartas) {
			valoresDeTodasAsCartas.add((int) carta.getValorAlternativo());
		}
		
		short maiorValor = (short) valoresDeTodasAsCartas.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
		short menorValor = (short) valoresDeTodasAsCartas.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);
		
		for (short i = maiorValor; i >= menorValor; i--) {
			valoresEmSequencia.add(i);
		}
		
		List<Carta> sequenciaDeCartas = new ArrayList<>();
		List<Carta> sequenciaOrdem = new ArrayList<>();
		
		for (Carta carta : cartas) {
			short valorAlternativo = carta.getValorAlternativo();
			if (valoresEmSequencia.contains(valorAlternativo)) {
				sequenciaDeCartas.add(carta);
			}
		}
		
		sequenciaOrdem.addAll(sequenciaDeCartas.stream().sorted(Comparator.comparingInt(Carta::getValorAlternativo)).limit(5).collect(Collectors.toList()));
		
		if (sequenciaOrdem.size() == quantidadeCartasSaida) {
			return new ResultadoDeAnalise(
						jogador, ECombinacaoDeCarta.SEQUENCIA, 
						sequenciaOrdem.stream().sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).collect(Collectors.toList())
					);
		}
		
		return null;
	}

}
