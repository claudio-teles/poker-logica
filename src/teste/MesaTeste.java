package teste;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.Ficha;
import poker.Mesa;

public class MesaTeste {
	Mesa mesa;
	Mesa mesaComErro;
	List<Ficha> fichasEmtregues;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mesaComErro = new Mesa();
		
		mesa = new Mesa();
		
		fichasEmtregues = new ArrayList<>();
		try {
			fichasEmtregues.addAll(mesa.entregarStack(1780));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = Exception.class)
	public void testeEntregarStackLancaExcecao() throws Exception {
		mesaComErro.entregarStack(5);
	}
	
	@Test
	public void testeDaQuantidadeDeFicha1() {
		List<Ficha> fichas1 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("branco"))
		.forEach(ficha -> fichas1.add(ficha));
		
		assertEquals(15, fichas1.size());
	}
	
	@Test
	public void testeDaQuantidadeDeFicha5() {
		List<Ficha> fichas5 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("vermelho"))
		.forEach(ficha -> fichas5.add(ficha));
		
		assertEquals(10, fichas5.size());
	}
	
	@Test
	public void testeDaQuantidadeDeFicha10() {
		List<Ficha> fichas10 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("laranja"))
		.forEach(ficha -> fichas10.add(ficha));
		
		assertEquals(5, fichas10.size());
	}
	
	@Test
	public void testeDaQuantidadeDeFicha20() {
		List<Ficha> fichas20 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("amarelo"))
		.forEach(ficha -> fichas20.add(ficha));
		
		assertEquals(4, fichas20.size());
	}
	
	@Test
	public void testeDaQuantidadeDeFicha25() {
		List<Ficha> fichas25 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("verde"))
		.forEach(ficha -> fichas25.add(ficha));
		
		assertEquals(3, fichas25.size());
	}
	
	@Test
	public void testeDaQuantidadeDeFicha100() {
		List<Ficha> fichas100 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("preto"))
		.forEach(ficha -> fichas100.add(ficha));
		
		assertEquals(5, fichas100.size());
	}
	
	@Test
	public void testeDaQuantidadeDeFicha500() {
		List<Ficha> fichas500 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getCor().equals("roxo"))
		.forEach(ficha -> fichas500.add(ficha));
		
		assertEquals(2, fichas500.size());
	}

}
