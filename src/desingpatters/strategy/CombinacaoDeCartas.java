package desingpatters.strategy;

import poker.CartasDaMesa;
import poker.Jogador;

public interface CombinacaoDeCartas {
	
	ResultadoDeAnalise encontrarMelhorCombinacaoDeCartas(Jogador jogador, CartasDaMesa cartasDaMesa, int quantidadeCartasSaida);

}
