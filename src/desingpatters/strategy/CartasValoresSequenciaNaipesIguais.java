package desingpatters.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class CartasValoresSequenciaNaipesIguais implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa,
			int quantidadeCartasSaida) {
		List<Carta> cartas = new ArrayList<>();
		cartas.addAll(jogador.getMao().getCartas());
		cartas.addAll(cartasDaMesa.getCartas());

		List<Carta> listaEspada = new ArrayList<>();
		List<Carta> listaOuro = new ArrayList<>();
		List<Carta> listaPaus = new ArrayList<>();
		List<Carta> listaCopas = new ArrayList<>();

		listaEspada.addAll(cartas.stream().filter(carta -> carta.getNaipe().equals("ESPADA"))
				.limit(quantidadeCartasSaida).collect(Collectors.toList()));
		listaOuro.addAll(cartas.stream().filter(carta -> carta.getNaipe().equals("OURO")).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));
		listaPaus.addAll(cartas.stream().filter(carta -> carta.getNaipe().equals("PAUS")).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));
		listaCopas.addAll(cartas.stream().filter(carta -> carta.getNaipe().equals("COPAS")).limit(quantidadeCartasSaida)
				.collect(Collectors.toList()));

		List<Carta> listaMaiorRepeticaoNaipes = new ArrayList<>();
		if (listaEspada.size() == quantidadeCartasSaida) {
			listaMaiorRepeticaoNaipes.addAll(listaEspada);
		}
		if (listaOuro.size() == quantidadeCartasSaida) {
			listaMaiorRepeticaoNaipes.addAll(listaOuro);
		}
		if (listaPaus.size() == quantidadeCartasSaida) {
			listaMaiorRepeticaoNaipes.addAll(listaPaus);
		}
		if (listaCopas.size() == quantidadeCartasSaida) {
			listaMaiorRepeticaoNaipes.addAll(listaCopas);
		}

		Carta maiorCarta = listaMaiorRepeticaoNaipes.stream().max(Comparator.comparingInt(Carta::getValorAlternativo))
				.get();

		List<Carta> sequencia5_CartasAoReverso = new ArrayList<Carta>();
		sequencia5_CartasAoReverso.addAll(listaMaiorRepeticaoNaipes.stream()
				.sorted(Comparator.comparingInt(Carta::getValorAlternativo).reversed()).collect(Collectors.toList()));

		if (maiorCarta.getValorAlternativo() <= 13) {
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.STRAIGHT_FLUSH, sequencia5_CartasAoReverso);
		}

		if (maiorCarta.getValorAlternativo() == 14) {
			return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.ROYAL_STRAIGHT_FLUSH, sequencia5_CartasAoReverso);
		}
		return null;
	}

}
