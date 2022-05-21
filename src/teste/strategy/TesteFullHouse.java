package teste.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.FullHouse;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class TesteFullHouse {

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
	public void testeFullHouse() {
		List<Carta> mesa = new ArrayList<>();
		mesa.add(new Carta((short) 5, (short) 5, "COPAS", '-', "lif1", "lit1", false));
		mesa.add(new Carta((short) 9, (short) 9, "OURO", '-', "lif2", "lit2", false));
		mesa.add(new Carta((short) 5, (short) 5, "PAUS", '-', "lif3", "lit3", false));
		mesa.add(new Carta((short) 5, (short) 5, "ESPADA", '-', "lif4", "lit4", false));
		mesa.add(new Carta((short) 3, (short) 3, "ESPADA", '-', "lif5", "lit5", false));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(mesa);
		
		List<Carta> mao = new ArrayList<>();
		mao.add(new Carta((short) 9, (short) 9, "COPAS", '-', "lif6", "lit6", false));
		mao.add(new Carta((short) 7, (short) 7, "PAUS", '-', "lif6", "lit6", false));
		
		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(mao);
		
		ResultadoDeAnalise resultado = new FullHouse().encontrarMelhorCombinacaoDeCartas(jogador , cartasDaMesa, 5);
		
		int somatorio9 = 0;
		int somatorio5 = 0;
		
		List<Carta> cartas = resultado.getCartas();
		for (Carta carta : cartas) {
			if (carta.getValor() == 9) {
				somatorio9 += 9;
			}
			if (carta.getValor() == 5) {
				somatorio5 += 5;
			}
		}
		
		int somatorioFinal = somatorio5  + somatorio9 ;
		assertEquals(33, somatorioFinal);
	}

}
