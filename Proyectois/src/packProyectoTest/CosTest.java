package packProyectoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.Cos;

public class CosTest {

	Cos aux;
	ArrayList<Double> lista1,lista2;
	@Before
	public void setUp() throws Exception {
		aux = new Cos();
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
		System.out.println(aux.calcularSimilitud(lista1, lista2));
		assertEquals(aux.calcularSimilitud(lista1, lista2),14/Math.sqrt(273),0.00001);//tomamos 14/raiz(273) con calculadora como valido//
		
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
		assertEquals(aux.sumatorio(lista1, lista2),14,0.0001);//suma de multiplicaiones de todas las posiciones de ambas listas//
		
		lista1.remove(2);
		assertEquals(aux.sumatorio(lista1, lista2),12,0.0001);
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

	@Test
	public void testModulo() {
		lista1.add(3.0);
		lista1.add(0.0);
		lista1.add(2.0);
		lista2.add(4.0);
		lista2.add(2.0);
		lista2.add(1.0);

		assertEquals(aux.modulo(lista1, lista2),Math.sqrt(273),0.00001);//calculado con calculadoa raiz de 273//
		
		lista2.remove(1);
		aux.rellenarCon0(lista2, 1);
		
		assertEquals(aux.modulo(lista1, lista2),Math.sqrt(221),0.00001);//calculado raiz de 221//
	}

}
