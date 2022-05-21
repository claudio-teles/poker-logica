package desingpatters.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class DoisPares implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa,
			int quantidadeCartasSaida) {
		List<Carta> cartasDoJogador = new ArrayList<>();
		cartasDoJogador.addAll(jogador.getMao().getCartas());

		List<Carta> cartasMesa = new ArrayList<>();
		cartasMesa.addAll(cartasDaMesa.getCartas());

		ResultadoDeAnalise resultado1 = new CartasValoresIguaisNaipesDiferentes()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, (quantidadeCartasSaida - 2));

		for (Carta carta : resultado1.getCartas()) {
			if (cartasDoJogador.contains(carta)) {
				cartasDoJogador.remove(carta);
			}
			if (cartasMesa.contains(carta)) {
				cartasMesa.remove(carta);
			}
		}

		jogador.getMao().setCartas(cartasDoJogador);
		cartasDaMesa.setCartas(cartasMesa);

		ResultadoDeAnalise resultado2 = new CartasValoresIguaisNaipesDiferentes()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, (quantidadeCartasSaida - 2));

		List<Carta> listaDeDoisPares = new ArrayList<>();
		listaDeDoisPares.addAll(resultado1.getCartas());
		listaDeDoisPares.addAll(resultado2.getCartas());

		return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.DOIS_PARES, listaDeDoisPares.stream()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).collect(Collectors.toList()));
	}

}
