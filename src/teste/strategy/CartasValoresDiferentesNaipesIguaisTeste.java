package teste.strategy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.CartasValoresDiferentesNaipesIguais;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class CartasValoresDiferentesNaipesIguaisTeste {

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
	public void test() {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta((short) 2, (short) 2, "OURO", '-', "limf1", "lit1", true));
		cartas.add(new Carta((short) 5, (short) 5, "OURO", '-', "limf2", "lit2", true));
		cartas.add(new Carta((short) 1, (short) 14, "PAUS", 'A', "limf3", "lit3", true));
		cartas.add(new Carta((short) 7, (short) 7, "OURO", '-', "limf4", "lit4", false));
		cartas.add(new Carta((short) 9, (short) 9, "OURO", '-', "limf5", "lit5", false));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(cartas);
		
		List<Carta> cartasDaMao = new ArrayList<>();
		cartasDaMao.add(new Carta((short) 11, (short) 11, "COPAS", 'J', "limf11", "lit11", true));
		cartasDaMao.add(new Carta((short) 12, (short) 12, "OURO", 'Q', "limf12", "lit12", true));
		
		Jogador jogador = new Jogador(1, "nome1");
		jogador.getMao().setCartas(cartasDaMao);
		
		ResultadoDeAnalise resultado = new CartasValoresDiferentesNaipesIguais().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 5);
		
		int quantidadeDeNaipesIguais = 0;
		for (Carta carta : resultado.getCartas()) {
			if (carta.getNaipe().equals("OURO")) {
				quantidadeDeNaipesIguais++;
			}
		}
		
		assertEquals(5, quantidadeDeNaipesIguais);
	}

}
