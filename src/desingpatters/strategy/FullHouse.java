package desingpatters.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class FullHouse implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa,
			int quantidadeCartasSaida) {
		List<Carta> cartas = new ArrayList<>();
		cartas.addAll(jogador.getMao().getCartas());
		cartas.addAll(cartasDaMesa.getCartas());

		List<Carta> trinca = new ArrayList<>();
		List<Carta> par = new ArrayList<>();
		List<Carta> cartasFullHouse = new ArrayList<>();

		ResultadoDeAnalise resultadoTrinca = new CartasValoresIguaisNaipesDiferentes()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 3);
		trinca.addAll(resultadoTrinca.getCartas());

		ResultadoDeAnalise resultadoPar = new CartasValoresIguaisNaipesDiferentes()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 2);
		par.addAll(resultadoPar.getCartas());

		cartasFullHouse.addAll(trinca);
		cartasFullHouse.addAll(par);

		return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.FULL_HOUSE, cartasFullHouse.stream()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).collect(Collectors.toList()));
	}

}
