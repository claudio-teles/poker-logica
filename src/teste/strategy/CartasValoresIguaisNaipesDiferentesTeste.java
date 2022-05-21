package teste.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desingpatters.strategy.CartasValoresIguaisNaipesDiferentes;
import desingpatters.strategy.ResultadoDeAnalise;
import poker.Carta;
import poker.CartasDaMesa;
import poker.Jogador;

public class CartasValoresIguaisNaipesDiferentesTeste {

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
	public void testeCombinacaoPar() {
		List<Carta> cartasComunitaria = new ArrayList<>();
		cartasComunitaria.add(new Carta((short) 2, (short) 2, "COPAS", '-', "limf2", "lit2", false));
		cartasComunitaria.add(new Carta((short) 5, (short) 5, "OURO", '-', "limf5", "lit5", false));
		cartasComunitaria.add(new Carta((short) 7, (short) 7, "PAUS", '-', "limf7", "lit7", false));
		cartasComunitaria.add(new Carta((short) 4, (short) 4, "ESPADA", '-', "limf4", "lit4", false));
		cartasComunitaria.add(new Carta((short) 9, (short) 9, "OURO", '-', "limf8", "lit9", false));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(cartasComunitaria);
		
		List<Carta> cartasDaMao = new ArrayList<>();
		cartasDaMao.add(new Carta((short) 2, (short) 2, "ESPADA", '-', "limf1", "lit1", false));
		cartasDaMao.add(new Carta((short) 11, (short) 11, "PAUS", 'J', "limf11", "lit11", false));
		
		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(cartasDaMao);
		
		ResultadoDeAnalise resultadoDeAnalise = new CartasValoresIguaisNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 2);
		
		short quantidadeDeValoresIguais = 0;
		for (Carta carta: resultadoDeAnalise.getCartas()) {
			if (carta.getValorAlternativo() == (short) 2) quantidadeDeValoresIguais++;
		}
		
		assertEquals((short) 2, quantidadeDeValoresIguais);
	}
	
	@Test
	public void testeCombinacaoTrinca() {
		List<Carta> cartasComunitaria = new ArrayList<>();
		cartasComunitaria.add(new Carta((short) 7, (short) 7, "PAUS", '-', "limf7", "lit7", false));
		cartasComunitaria.add(new Carta((short) 2, (short) 2, "COPAS", '-', "limf2", "lit2", false));
		cartasComunitaria.add(new Carta((short) 5, (short) 5, "OURO", '-', "limf5", "lit5", false));
		cartasComunitaria.add(new Carta((short) 4, (short) 4, "ESPADA", '-', "limf4", "lit4", false));
		cartasComunitaria.add(new Carta((short) 7, (short) 7, "COPAS", '-', "limf8", "lit8", false));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(cartasComunitaria);
		
		List<Carta> cartasDaMao = new ArrayList<>();
		cartasDaMao.add(new Carta((short) 7, (short) 7, "OURO", '-', "limf7", "lit7", false));
		cartasDaMao.add(new Carta((short) 2, (short) 2, "ESPADA", '-', "limf1", "lit1", false));
		
		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(cartasDaMao);
		
		ResultadoDeAnalise resultadoDeAnalise = new CartasValoresIguaisNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 3);
		
		short quantidadeDeValoresIguais = 0;
		for (Carta carta: resultadoDeAnalise.getCartas()) {
			if (carta.getValorAlternativo() == (short) 7) quantidadeDeValoresIguais++;
		}
		
		assertEquals((short) 3, quantidadeDeValoresIguais);
	}

	@Test
	public void testeCombinacaoQuadra() {
		List<Carta> cartasComunitaria = new ArrayList<>();
		cartasComunitaria.add(new Carta((short) 7, (short) 7, "PAUS", '-', "limf7", "lit7", false));
		cartasComunitaria.add(new Carta((short) 2, (short) 2, "COPAS", '-', "limf2", "lit2", false));
		cartasComunitaria.add(new Carta((short) 5, (short) 5, "OURO", '-', "limf5", "lit5", false));
		cartasComunitaria.add(new Carta((short) 4, (short) 4, "ESPADA", '-', "limf4", "lit4", false));
		cartasComunitaria.add(new Carta((short) 7, (short) 7, "COPAS", '-', "limf8", "lit8", false));
		
		CartasDaMesa cartasDaMesa = new CartasDaMesa();
		cartasDaMesa.setCartas(cartasComunitaria);
		
		List<Carta> cartasDaMao = new ArrayList<>();
		cartasDaMao.add(new Carta((short) 7, (short) 7, "OURO", '-', "limf1", "lit1", false));
		cartasDaMao.add(new Carta((short) 7, (short) 7, "ESPADA", '-', "limf1", "lit1", false));
		
		Jogador jogador = new Jogador(1, "jogador1");
		jogador.getMao().setCartas(cartasDaMao);
		
		ResultadoDeAnalise resultadoDeAnalise = new CartasValoresIguaisNaipesDiferentes().encontrarMelhorCombinacaoDeCartas(jogador, cartasDaMesa, 4);
		
		short quantidadeDeValoresIguais = 0;
		for (Carta carta: resultadoDeAnalise.getCartas()) {
			if (carta.getValorAlternativo() == (short) 7) quantidadeDeValoresIguais++;
		}
		
		assertEquals((short) 4, quantidadeDeValoresIguais);
	}

}
