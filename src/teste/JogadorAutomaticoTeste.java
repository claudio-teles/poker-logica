package teste;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.Carta;
import poker.JogadorAutomatico;

public class JogadorAutomaticoTeste {

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
	public void testeConfiancaDuasCartasVisiveis01() {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta((short) 5, (short) 5, "OURO", '-', "lif1", "lit1", true));
		cartas.add(new Carta((short) 5, (short) 5, "PAUS", '-', "lif2", "lit2", true));
		cartas.add(new Carta((short) 7, (short) 7, "ESPADA", '-', "lif3", "lit3", false));
		cartas.add(new Carta((short) 9, (short) 9, "COPAS", '-', "lif4", "lit4", false));
		cartas.add(new Carta((short) 11, (short) 11, "OURO", 'J', "lif5", "lit5", false));
		
		JogadorAutomatico ja1 = new JogadorAutomatico();
		ja1.setIndice(1);
		ja1.setNome("ja1");
		ja1.getMao().setCartas(cartas);
		
		assertEquals("0.3332", String.valueOf(ja1.calcularNivelConfianca(cartas)));
	}
	
	@Test
	public void testeConfiancaCincoCartasVisiveis01() {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta((short) 3, (short) 3, "OURO", '-', "lif1", "lit1", true));
		cartas.add(new Carta((short) 3, (short) 3, "PAUS", '-', "lif2", "lit2", true));
		cartas.add(new Carta((short) 3, (short) 3, "ESPADA", '-', "lif3", "lit3", true));
		cartas.add(new Carta((short) 10, (short) 10, "COPAS", '-', "lif4", "lit4", true));
		cartas.add(new Carta((short) 1, (short) 14, "OURO", 'J', "lif5", "lit5", true));
		
		JogadorAutomatico ja1 = new JogadorAutomatico();
		ja1.setIndice(2);
		ja1.setNome("ja2");
		
		assertEquals("0.9163", String.valueOf(ja1.calcularNivelConfianca(cartas)));
	}

	@Test
	public void testeConfiancaSeteCartasVisiveis01() {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta((short) 3, (short) 3, "OURO", '-', "lif1", "lit1", true));
		cartas.add(new Carta((short) 3, (short) 3, "PAUS", '-', "lif2", "lit2", true));
		cartas.add(new Carta((short) 3, (short) 3, "ESPADA", '-', "lif3", "lit3", true));
		cartas.add(new Carta((short) 10, (short) 10, "OURO", '-', "lif4", "lit4", true));
		cartas.add(new Carta((short) 1, (short) 14, "OURO", 'A', "lif5", "lit5", true));
		
		cartas.add(new Carta((short) 12, (short) 12, "OURO", 'Q', "lif4", "lit4", true));
		cartas.add(new Carta((short) 13, (short) 13, "OURO", 'K', "lif5", "lit5", true));
		
		JogadorAutomatico ja1 = new JogadorAutomatico();
		ja1.setIndice(2);
		ja1.setNome("ja2");
		
		assertEquals("0.99960005", String.valueOf(ja1.calcularNivelConfianca(cartas)));
	}

}
