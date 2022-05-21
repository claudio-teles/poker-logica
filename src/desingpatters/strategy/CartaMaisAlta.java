package desingpatters.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import poker.Carta;
import poker.CartasDaMesa;
import poker.ECombinacaoDeCarta;
import poker.Jogador;

public class CartaMaisAlta implements CombinacaoDeCartas {

	@Override
	public ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa, int quantidadeCartasSaida) {
		List<Carta> listaUnicaCartas = new ArrayList<>();
		listaUnicaCartas.addAll(jogador.getMao().getCartas());
		listaUnicaCartas.addAll(cartasDaMesa.getCartas());
		
		List<Carta> cartas = new ArrayList<>();
		cartas.add(listaUnicaCartas.stream().max(Comparator.comparingInt(Carta::getValorAlternativo)).get());
		
		return new ResultadoDeAnalise(jogador, ECombinacaoDeCarta.CARTA_MAIS_ALTA, cartas);
	}

}
