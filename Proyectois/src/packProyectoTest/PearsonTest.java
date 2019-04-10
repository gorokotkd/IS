package packProyectoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.Pearson;

public class PearsonTest {

	Pearson aux;
	ArrayList<Double> lista1,lista2;
	@Before
	public void setUp() throws Exception {
		aux = new Pearson();
		lista1 = new ArrayList <Double>();
		lista2 = new ArrayList <Double>();
	}

	@After
	public void tearDown() throws Exception {
		lista1.clear();
		lista2.clear();
	}

	@Test
	public void testCalcularSimilitud() {
		lista1.add(3.0);
		lista1.add(0.0);
		lista1.add(2.0);
		lista2.add(4.0);
		lista2.add(2.0);
		lista2.add(1.0);
		assertEquals(aux.calcularSimilitud(lista1, lista2),0.5,0.00001);//tomamos 1/2 con calculadora como valido//
		
		lista1.clear();
		lista2.clear();
		
		lista1.add(4.0);//misma prueba pero con un array de diferente dimension//
		lista1.add(2.0);
		lista2.add(3.0);
		lista2.add(1.0);
		lista2.add(3.0);
		assertEquals(aux.calcularSimilitud(lista1, lista2),0.0,00001);//con calculadora da justo 0//
	}

	@Test
	public void testSumatorio() {
		lista1.add(3.0);
		lista1.add(0.0);
		lista1.add(2.0);
		lista2.add(4.0);
		lista2.add(2.0);
		lista2.add(1.0);
		System.out.println(aux.sumatorio(lista1, lista2));
		assertEquals(aux.sumatorio(lista1, lista2),(7/3.0),0.00001);//tomamos 7/3 con calculadora como valido//
		
		lista1.clear();
		lista2.clear();
		
		lista1.add(4.0);//misma prueba pero valores distintos//
		lista1.add(2.0);
		lista1.add(0.0);
		lista2.add(3.0);
		lista2.add(1.0);
		lista2.add(3.0);
		assertEquals(aux.sumatorio(lista1, lista2),0.0,00001);//con calculadora da justo 0//
	}

	@Test
	public void testRellenarCon0() {
		lista1.add(1.0);
		lista2.add(1.0);
		assertEquals(lista1,lista2);
		
		lista2.add(0.0);//añadimos 3 ceros manualmente//
		lista2.add(0.0);
		lista2.add(0.0);
		
		aux.rellenarCon0(lista1, 3);//añadimos 3 ceros con el metodo//
		assertEquals(lista1,lista2);
	}


}
