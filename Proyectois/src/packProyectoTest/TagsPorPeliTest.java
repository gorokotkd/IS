package packProyectoTest;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.BaseDatos;
import packProyecto.TagsPorPeli;
import packProyecto.Tupla;

public class TagsPorPeliTest {

	TagsPorPeli tags;
	ArrayList<Tupla<String,Integer>> aux;
	@Before
	public void setUp() throws Exception {
		BaseDatos.getBd().cargarBd();
		tags = new TagsPorPeli();
		aux = new ArrayList<Tupla<String,Integer>>();
	}

	@After
	public void tearDown() throws Exception {
		tags.eliminar();
		aux.clear();
	}
	@Test
	public void testLeerFichero() {
		tags.leerFichero();
		assertNotNull(tags.getTagsPorId(11));//comprobamos que se ha llenado el hashmap con  nuestros datos//
		assertNotNull(tags.getTagsPorId(24));
	}

	@Test
	public void testTagsDevolKeys() {
		 aux.add(new Tupla<String,Integer>("Peli1",1));
		 aux.add(new Tupla<String,Integer>("Peli2",2));
		 aux.add(new Tupla<String,Integer>("Peli3",3));
		 aux.add(new Tupla<String,Integer>("Peli4",4));
		 
		 tags.anadirEntrada(1, aux);
		 tags.anadirEntrada(2, aux);
		 tags.anadirEntrada(3, aux);
		 
		 assertTrue(tags.tagsDevolKeys().get(0)==1);
		 assertTrue(tags.tagsDevolKeys().get(1)==2);
		 assertTrue(tags.tagsDevolKeys().get(2)==3);
	}

	@Test
	public void testGetTagsPorId() {
		 aux.add(new Tupla<String,Integer>("Peli1",1));
		 aux.add(new Tupla<String,Integer>("Peli2",2));
		 aux.add(new Tupla<String,Integer>("Peli3",3));
		 aux.add(new Tupla<String,Integer>("Peli4",4));
		 
		 tags.anadirEntrada(1, aux);
		 tags.anadirEntrada(2, aux);
		 
		 assertTrue(tags.getTagsPorId(1).get(1).getX().equals("Peli2"));
		 assertTrue(tags.getTagsPorId(1)==aux);
		 assertTrue(tags.getTagsPorId(2)==aux);
	}

	@Test
	public void testInicializarFiltradoContenido() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecomendarNPeliculas() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminar() {
		 aux.add(new Tupla<String,Integer>("Peli1",1));
		 aux.add(new Tupla<String,Integer>("Peli2",2));
		 aux.add(new Tupla<String,Integer>("Peli3",3));
		 aux.add(new Tupla<String,Integer>("Peli4",4));
		 
		 tags.anadirEntrada(1, aux);
		 
		 assertTrue(tags.getTagsPorId(1)==aux);
		 
		 tags.eliminar();
		 
		 assertTrue(tags.estoyVacia());
	}

	@Test
	public void testAnadirEntrada() {
		 aux.add(new Tupla<String,Integer>("Peli1",1));
		 aux.add(new Tupla<String,Integer>("Peli2",2));
		 
		 tags.anadirEntrada(1, aux);
		 
		 assertTrue(tags.getTagsPorId(1)==aux);
		 
		 aux.add(new Tupla<String,Integer>("Peli3",3));
		 aux.add(new Tupla<String,Integer>("Peli4",4));
		 
		 tags.anadirEntrada(1, aux);
		 
		 assertTrue(tags.getTagsPorId(1)==aux);
	}

	@Test
	public void testGetFilaModeloProductos() {
		fail("Not yet implemented");
	}
}