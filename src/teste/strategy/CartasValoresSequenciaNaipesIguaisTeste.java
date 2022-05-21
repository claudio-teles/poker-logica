package teste.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.CartasValoresSequenciaNaipesIguais;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class CartasValoresSequenciaNaipesIguaisTeste {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testeStraightFlush() {
		List<Carta> mesa = new ArrayList<Carta>();
		mesa.add(new Carta((short) 9, (short) 9, "PAUS", '-', "limf1", "lit1", true));
		mesa.add(new Carta((short) 7, (short) 7, "PAUS", '-', "limf2", "lit2", true));
		mesa.add(new Carta((short) 5, (short) 5, "PAUS", '-', "limf3", "lit3", true));
		mesa.add(new Carta((short) 3, (short) 3, "COPAS", '-', "limf4", "lit4", true));
		mesa.add(new Carta((short) 2, (short) 2, "ESPADA", '-', "limf5", "lit5", true));

		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(mesa);

		List<Carta> mao = new ArrayList<>();
		mao.add(new Carta((short) 6, (short) 6, "PAUS", '-', "limf6", "lit6", true));
		mao.add(new Carta((short) 8, (short) 8, "PAUS", '-', "limf7", "lit7", true));

		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(mao);

		ResultadoDeAnalise resultado = new CartasValoresSequenciaNaipesIguais()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 5);

		short somatorio = 0;
		for (Carta carta : resultado.getCartas()) {
			somatorio += carta.getValorAlternativo();
		}

		assertEquals(35, somatorio);
	}

	@Test
	public void testeRoyalStraightFlush() {
		List<Carta> mesa = new ArrayList<Carta>();
		mesa.add(new Carta((short) 1, (short) 14, "ESPADA", 'A', "limf1", "lit1", true));
		mesa.add(new Carta((short) 12, (short) 12, "ESPADA", 'Q', "limf2", "lit2", true));
		mesa.add(new Carta((short) 11, (short) 11, "ESPADA", 'J', "limf3", "lit3", true));
		mesa.add(new Carta((short) 3, (short) 3, "OURO", '-', "limf4", "lit4", true));
		mesa.add(new Carta((short) 8, (short) 8, "PAUS", '-', "limf5", "lit5", true));

		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(mesa);

		List<Carta> mao = new ArrayList<>();
		mao.add(new Carta((short) 10, (short) 10, "ESPADA", '-', "limf6", "lit6", true));
		mao.add(new Carta((short) 13, (short) 13, "ESPADA", 'K', "limf7", "lit7", true));

		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(mao);

		ResultadoDeAnalise resultado = new CartasValoresSequenciaNaipesIguais()
				.encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 5);

		short somatorio = 0;
		for (Carta carta : resultado.getCartas()) {
			somatorio += carta.getValorAlternativo();
		}

		assertEquals(60, somatorio);
	}

}
