package packProyectoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.*;

public class TagsPorPeliTestMalo {

	TagsPorPeli tags;
	ArrayList<Tupla<String,Integer>> aux;
	@Before
	public void setUp() throws Exception {
		tags = new TagsPorPeli();
		aux = new ArrayList<Tupla<String,Integer>>();
	}

	@After
	public void tearDown() throws Exception {
		tags.eliminar();
		aux.clear();
	}

	@Test
	public void testTagsPorPeli() {
		fail("Not yet implemented");
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
		 fail("Faltan mas");
	}

	@Test
	public void testGenerarModeladoDeProductos() {
		fail("Not yet implemented");
	}

}
