package packProyectoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.Similitud;

public class SimilitudTest {

	ArrayList<Double> lista1,lista2;
	Similitud aux;
	@Before
	public void setUp() throws Exception {
		lista1 = new ArrayList<Double>();
		lista2 = new ArrayList<Double>();
		aux = new Similitud();
	}

	@After
	public void tearDown() throws Exception {
		lista1.clear();
		lista2.clear();
	}

	@Test
	public void testCorrelacionDePearson() {
		lista1.add(5.0);
		lista2.add(2.5);
		double tmp = 0.6;
		assertTrue(aux.calcularSimilitud(lista1, lista2)==tmp);
		
		lista1.clear();
		lista2.clear();
		
		lista1.add(2.5);
		lista2.add(2.0);
		tmp = 0.9;
		assertTrue(aux.calcularSimilitud(lista1, lista2)==tmp);
		
		lista1.clear();
		lista2.clear();
		
		lista1.add(5.0);
		lista2.add(2.5);
		lista1.add(2.5);
		lista2.add(2.0);
		tmp=0.6704930839387951;
		assertTrue(aux.calcularSimilitud(lista1, lista2)==tmp);
	}

}
