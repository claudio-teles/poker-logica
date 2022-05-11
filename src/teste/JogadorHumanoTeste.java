package teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.JogadorHumano;
import poker.Mesa;

public class JogadorHumanoTeste {
	
	Mesa mesa;
	JogadorHumano jh;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 mesa = new Mesa();
		 jh = new JogadorHumano();
		jh.comprarFichas(1780, mesa);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testeCompraFichas() {
		assertEquals(1780, jh.getStack());
	}

}
