package teste;

import java.util.ArrayList;
import java.util.List;

import poker.Ficha;
import poker.Mesa;

public class Teste {

	public static void main(String[] args) {
		List<Ficha> fichasEmtregues = new ArrayList<>();
		try {
			Mesa mesa = new Mesa();
			fichasEmtregues = mesa.entregarStack(1720);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Ficha> fichas1 = new ArrayList<>();
		fichasEmtregues.stream()
		.filter(ficha -> ficha.getValor() == 1)
		.forEach(ficha -> fichas1.add(ficha));
		
		System.out.println(fichasEmtregues);
	}

}
