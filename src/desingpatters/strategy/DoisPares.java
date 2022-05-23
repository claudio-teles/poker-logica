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

		List<Carta> cartasDoJogadorClone = new ArrayList<>();
		cartasDoJogadorClone.addAll(cartasDoJogador);
		
		List<Carta> cartasMesaClone = new ArrayList<>();
		cartasMesaClone.addAll(cartasMesa);
		
		ResultadoDeAnalise resultado1 = new CartasValoresIguaisNaipesDiferentes()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, (quantidadeCartasSaida - 2));

		for (Carta carta : resultado1.getCartas()) {
			if (cartasDoJogadorClone.contains(carta)) {
				cartasDoJogadorClone.remove(carta);
			}
			if (cartasMesaClone.contains(carta)) {
				cartasMesaClone.remove(carta);
			}
		}

		jogador.getMao().setCartas(cartasDoJogadorClone);
		cartasDaMesa.setCartas(cartasMesaClone);

		ResultadoDeAnalise resultado2 = new CartasValoresIguaisNaipesDiferentes()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, (quantidadeCartasSaida - 2));

		List<Carta> listaDeDoisPares = new ArrayList<>();
		listaDeDoisPares.addAll(resultado1.getCartas());
		listaDeDoisPares.addAll(resultado2.getCartas());

		return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.DOIS_PARES, listaDeDoisPares.stream()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).collect(Collectors.toList()));
	}

}
