package teste.strategy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.CartaMaisAlta;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class CartaMaisAltaTeste {
	private Jogador jogador;
	private List<Carta> cartas;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@Before
	public void setUp() throws Exception {
		List<Carta> cartasDaMao = new ArrayList<>();
		cartasDaMao.add(new Carta((short) 5, (short) 5, "OURO", 'K', "li1", "lit1", false));
		cartasDaMao.add(new Carta((short) 1, (short) 14, "ESPADA", 'A', "li1", "lit1", false));
		
		cartas = new ArrayList<>();
		cartas.add(cartasDaMao.get(1));
		
		jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(cartasDaMao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<Carta> listaCartasMesa = new ArrayList<>();
		listaCartasMesa.add(new Carta((short) 2, (short) 2, "PAUS", '-', "li1", "lit1", false));
		listaCartasMesa.add(new Carta((short) 6, (short) 6, "OURO", '-', "li1", "lit1", false));
		listaCartasMesa.add(new Carta((short) 9, (short) 9, "COPAS", '-', "li1", "lit1", false));
		listaCartasMesa.add(new Carta((short) 4, (short) 4, "PAUS", '-', "li1", "lit1", false));
		listaCartasMesa.add(new Carta((short) 11, (short) 11, "ESPADA", 'J', "li1", "lit1", false));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(listaCartasMesa);
		
		ResultadoDeAnalise resultadoDeAnalise = new CartaMaisAlta().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 1);
		assertEquals(cartas, resultadoDeAnalise.getCartas());
	}

}
