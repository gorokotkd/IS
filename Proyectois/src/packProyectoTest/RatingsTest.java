package packProyectoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.Gestor;
import packProyecto.Cos;
import packProyecto.FiltradoProductos;
import packProyecto.Ratings;
import packProyecto.Tupla;

public class RatingsTest {

	Ratings ratings;
	
	@Before
	public void setUp() throws Exception {
		FiltradoProductos fil = new FiltradoProductos();
		fil.setSimilitud(new Cos());
		Gestor.getBd().setFiltrado(fil);
		Gestor.getBd().cargarTodo();
		ratings = Gestor.getBd().getRatings();
	}

	@After
	public void tearDown() throws Exception {
		Gestor.getBd().eliminarRatingsPeliculas();
	}

	@Test
	public void testLeerFichero() {
		assertNotEquals(ratings.getLista(), null);
		System.out.println("La dimensi�n de la lista es: " + ratings.getLista().size());
	}

	@Test
	public void testCargarValoraciones() {
		assertNotEquals(ratings.getValoraciones(), null);
		System.out.println("La dimensi�n de la lista es: " + ratings.getValoraciones().size());
	}

	@Test
	public void testAnadirALista() {
		int size = ratings.getValoraciones().get(2024).size();
		assertEquals(size, ratings.getValoraciones().get(2024).size());
		ratings.anadirALista(2024, 2.0);
		assertNotEquals(size, ratings.getValoraciones().get(2024).size());
	}

	@Test
	public void testNormalizaryDesnormalizar() {
		System.out.println("Realizamos la prueba comparando un vector sin normalizar, normalizado y desnormalizado");
		System.out.println("Lista sin normalizar");
		ArrayList<Tupla<Integer,Double>> array = new ArrayList<Tupla<Integer,Double>>();
		Tupla<Integer, Double> t1 = new Tupla<Integer, Double>(2024, 4.0);
		Tupla<Integer, Double> t2 = new Tupla<Integer, Double>(1597, 3.5);
		Tupla<Integer, Double> t3 = new Tupla<Integer, Double>(640, 3.5);
		Tupla<Integer, Double> t4 = new Tupla<Integer, Double>(581, 4.0);
		Tupla<Integer, Double> t5 = new Tupla<Integer, Double>(607, 3.0);
		Tupla<Integer, Double> t6 = new Tupla<Integer, Double>(1422, 3.5);
		Tupla<Integer, Double> t7 = new Tupla<Integer, Double>(671, 4.0);
		
		array.add(t1);
		array.add(t2);
		array.add(t3);
		array.add(t4);
		array.add(t5);
		array.add(t6);
		array.add(t7);
		
		this.imprimirArrayTupla(array);
		
		System.out.println("\nLista normalizada");
		this.imprimirArrayTupla(ratings.getLista().get(2));
		
		System.out.println("\nLista desnormalizada");
		this.imprimirArrayTupla(ratings.desnormalizar(2));
		System.out.println();
		
	}


	@Test
	public void testGetMedia() {
		assertEquals(3.75,ratings.getMedia(1),2);
		assertEquals(3.64,ratings.getMedia(2), 2);
		assertEquals(3.4, ratings.getMedia(3),1);
	}


	@Test
	public void testObtenerNota() {
		ArrayList<Tupla<Integer,Double>> lista = ratings.getLista().get(1);
		for (int i = 0; i < lista.size(); i++) {
			assertEquals(lista.get(i).getY(), ratings.obtenerNota(1, lista.get(i).getX()),2);
		}
	}

	public void imprimirArrayTupla(ArrayList<Tupla<Integer,Double>> lista) {
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).imprimir();
		}
	}
}
