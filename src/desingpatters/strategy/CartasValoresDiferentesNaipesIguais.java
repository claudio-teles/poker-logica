package desingpatters.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class CartasValoresDiferentesNaipesIguais implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa, int quantidadeCartasSaida) {
		List<Carta> cartasNaipesMisturadas = new ArrayList<>();
		cartasNaipesMisturadas.addAll(jogador.getMao().getCartas());
		cartasNaipesMisturadas.addAll(cartasDaMesa.getCartas());

		List<Carta> lEspada = new ArrayList<>();
		List<Carta> lOuro = new ArrayList<>();
		List<Carta> lPaus = new ArrayList<>();
		List<Carta> lCopas = new ArrayList<>();

		lEspada.addAll(cartasNaipesMisturadas.stream().filter(c -> c.getNaipe().equals("ESPADA")).sequential()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));
		lOuro.addAll(cartasNaipesMisturadas.stream().filter(c -> c.getNaipe().equals("OURO")).sequential()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));
		lPaus.addAll(cartasNaipesMisturadas.stream().filter(c -> c.getNaipe().equals("PAUS")).sequential()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));
		lCopas.addAll(cartasNaipesMisturadas.stream().filter(c -> c.getNaipe().equals("COPAS")).sequential()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));

		if (lEspada.size() == quantidadeCartasSaida) {
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.FLUSH, lEspada);
		}

		if (lOuro.size() == quantidadeCartasSaida) {
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.FLUSH, lOuro);
		}

		if (lPaus.size() == quantidadeCartasSaida) {
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.FLUSH, lPaus);
		}

		if (lCopas.size() == quantidadeCartasSaida) {
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.FLUSH, lCopas);
		}
		return null;
	}
}
