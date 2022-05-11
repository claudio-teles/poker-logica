package teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.Baralho;
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
		assertEquals(8, distribuidor.distribuirCartasDaPartida(baralho, jogadores, mesa).size());
	}

}
