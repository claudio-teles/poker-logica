package teste.strategy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.CartasValoresSequenciaNaipesDiferentes;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class CartasValoresSequenciaNaipesDiferentesTeste {

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
	public void testeCartasValoresSequenciaNaipesDiferentes() {
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		List<Carta> _cartasDaMesa = new ArrayList<>();
		_cartasDaMesa.add(new Carta((short) 3, (short) 3, "OURO", '-', "lif1", "lit1", true));
		_cartasDaMesa.add(new Carta((short) 4, (short) 4, "ESPADA", '-', "lif2", "lit2", true));
		_cartasDaMesa.add(new Carta((short) 12, (short) 12, "ESPADA", '-', "lif3", "lit3", true));
		_cartasDaMesa.add(new Carta((short) 9, (short) 9, "PAUS", '-', "lif4", "lit4", true));
		_cartasDaMesa.add(new Carta((short) 7, (short) 7, "COPAS", '-', "lif5", "lit5", true));
		
		cartasDaMesa.setCartas(_cartasDaMesa );
		
		List<Carta> cartasDaMao = new ArrayList<>();
		cartasDaMao.add(new Carta((short) 5, (short) 5, "OURO", '-', "lif7", "lit7", true));
		cartasDaMao.add(new Carta((short) 6, (short) 6, "PAUS", '-', "lif6", "lit6", true));
		
		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(cartasDaMao);
		
		ResultadoDeAnalise resultadoDeAnalise = new CartasValoresSequenciaNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 5);
		
		int somatorioDeValorDasCartas = 0;
		for (Carta carta : resultadoDeAnalise.getCartas()) {
			if (carta != null) {
				somatorioDeValorDasCartas += carta.getValorAlternativo();
			}
		}
		
		assertEquals(25, somatorioDeValorDasCartas);
	}

}
