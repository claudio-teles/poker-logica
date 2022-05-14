package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.Baralho;
import poker.Carta;
import poker.Ficha;
import poker.Jogador;
import poker.Mesa;

public class JogadorTeste {
	
	List<Jogador> jogadores;
	Mesa mesa;
	Baralho baralho;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		jogadores = new ArrayList<>();
		
		jogadores.add(new Jogador(1, "J1"));
		jogadores.add(new Jogador(2, "J2"));
		jogadores.add(new Jogador(3, "J3"));
		jogadores.add(new Jogador(4, "J4"));
		jogadores.add(new Jogador(5, "J5"));
		jogadores.add(new Jogador(6, "J6"));
		jogadores.add(new Jogador(7, "J7"));
		jogadores.add(new Jogador(8, "J8"));
		
		mesa = new Mesa();
		mesa.setDealerDefinido(false);
		mesa.setJogadoresDoTorneio(jogadores);
		
		baralho = new Baralho();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testeDistribuicaoDeCartasDoDealer() {
		Jogador distribuidor = new Jogador(10, "distribuidor");
		assertEquals(8, distribuidor.distribuirCartasDoDealer(baralho, jogadores, mesa).size());
	}
	
	@Test
	public void testeApostarSmallBlind() {
		List<Ficha> lista = new ArrayList<>();
		lista.add(new Ficha(1, "branco", "li1"));
		lista.add(new Ficha(1, "branco", "li2"));
		
		mesa.setBlind(lista);
		mesa.setBlindDefinido(true);
		mesa.setSmallBlindDefinidoDefinido(true);
		mesa.setBigBlindDefinido(true);
		mesa.setSmallBlindApostado(false);
		mesa.setIntencaoEscolherDealer(false);
		
		Ficha ficha1 = new Ficha(5, "vermelho", "li1");
		Ficha ficha2 = new Ficha(1, "branco", "li2");
		
		List<Ficha> blind = new ArrayList<>();
		blind.add(ficha1);
		blind.add(ficha2);
		
		Jogador jogador = new Jogador(1, "j1");
		List<Ficha> fichas = jogador.apostarSmallBlind(blind, mesa);
		
		boolean testeFicha = (fichas.get(0).getValor() == 3 && fichas.get(0).getCor().equals("vermelho"));
		assertTrue(testeFicha);
	}
	
	@Test
	public void testeApostarBigBlind() {
		mesa.setBlindDefinido(true);
		mesa.setSmallBlindDefinidoDefinido(true);
		mesa.setBigBlindDefinido(true);
		mesa.setSmallBlindApostado(false);
		mesa.setIntencaoEscolherDealer(false);
		
		Ficha ficha1 = new Ficha(1, "branco", "li1");
		Ficha ficha2 = new Ficha(1, "branco", "li2");
		
		List<Ficha> blind = new ArrayList<>();
		blind.add(ficha1);
		blind.add(ficha2);
		
		assertEquals(2, blind.size());
	}
	
	@Test
	public void testeDistribuirCartasDaPartida() {
		mesa.setBigBlindApostado(true);
		
		Jogador dealer = new Jogador(10, "j10");
		dealer.setVezDeSerDealer(true);
		
		dealer.distribuirCartasDaPartida(baralho, jogadores, mesa);
		
		int quantidadeDeCartasNulas = 0;
		for (Carta carta : baralho.getCartas()) {
			if (carta == null) {
				quantidadeDeCartasNulas++;
			}
		}
		
		assertEquals(16, quantidadeDeCartasNulas);
	}

}
