package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.Baralho;
import poker.CartasDoDealer;
import poker.Ficha;
import poker.Jogador;
import poker.Mesa;

public class MesaTeste {
	Mesa mesa;
	Mesa mesaComErro;
	List<Ficha> fichasEmtregues;
	List<Jogador> jogadores;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {
		mesa = new Mesa();
		jogadores = new ArrayList<>();
		
		try {
			jogadores.add(new Jogador(1, "J1", mesa.entregarStack(800)));
			jogadores.add(new Jogador(2, "J2", mesa.entregarStack(1250)));
			jogadores.add(new Jogador(3, "J3", mesa.entregarStack(1300)));
			jogadores.add(new Jogador(4, "J4", mesa.entregarStack(900)));
			jogadores.add(new Jogador(5, "J5", mesa.entregarStack(1100)));
			jogadores.add(new Jogador(6, "J6", mesa.entregarStack(1480)));
			jogadores.add(new Jogador(7, "J7", mesa.entregarStack(1780)));
			jogadores.add(new Jogador(8, "J8", mesa.entregarStack(2000)));
			
			jogadores.add(new Jogador(9, "J9", mesa.entregarStack(19)));
			jogadores.add(new Jogador(10, "J10", mesa.entregarStack(8)));
		} catch (Exception e1) {
			
		}
		mesaComErro = new Mesa();
		
		
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
	
	@Test
	public void testeDefinirDealerRetornaUmJogadorComCartaMaisAltaRetornaNull() {
		mesa.setIntencaoEscolherDealer(false);
		Jogador j = new Jogador(15, "j15");
		CartasDoDealer cartasDoDealer = new CartasDoDealer();
		cartasDoDealer.setCartasDoDealer(j.distribuirCartasDoDealer(new Baralho(), jogadores, mesa));
		assertNull(mesa.definirDealer(jogadores, cartasDoDealer));
	}
	
	@Test
	public void testeDefinirDealerRetornaUmJogadorComCartaMaisAlta() {
		mesa.setIntencaoEscolherDealer(true);
		Jogador j = new Jogador(15, "j15");
		CartasDoDealer cartasDoDealer = new CartasDoDealer();
		cartasDoDealer.setCartasDoDealer(j.distribuirCartasDoDealer(new Baralho(), jogadores, mesa));
		assertNotNull(mesa.definirDealer(jogadores, cartasDoDealer));
	}
	
	@Test
	public void testeBlindDefinido() {
		mesa.setDealerDefinido(true);
		mesa.setBlind(new Ficha(25, "verde", "li1"));
		assertEquals(25, mesa.getBlind().getValor());
	}

}
