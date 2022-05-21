package teste.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.DoisPares;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class DoisParesTeste {

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
	public void testeDoisPares() {
		List<Carta> mesa = new ArrayList<>();
		mesa.add(new Carta((short) 11, (short) 11, "OURO", 'J', "lif1", "lit1", true));
		mesa.add(new Carta((short) 5, (short) 5, "PAUS", '-', "lif2", "lit2", true));
		mesa.add(new Carta((short) 2, (short) 2, "COPAS", '-', "lif3", "lit3", true));
		mesa.add(new Carta((short) 7, (short) 7, "ESPADA", '-', "lif4", "lit4", true));
		mesa.add(new Carta((short) 5, (short) 5, "COPAS", '-', "lif5", "lit5", true));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(mesa);
		
		List<Carta> mao = new ArrayList<>();
		mao.add(new Carta((short) 11, (short) 11, "ESPADA", 'J', "lif6", "lit6", true));
		mao.add(new Carta((short) 12, (short) 12, "COPAS", 'Q', "lif7", "lit7", true));
		
		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(mao);
		
		ResultadoDeAnalise resultado = new DoisPares().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 4);
		
		int somatorio = 0;
		for (Carta carta : resultado.getCartas()) {
			somatorio += carta.getValorAlternativo();
		}
		
		assertEquals(32, somatorio);
	}

}
