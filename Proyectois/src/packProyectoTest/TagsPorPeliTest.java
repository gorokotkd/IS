package packProyectoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.BaseDatos;
import packProyecto.ListaTags;
import packProyecto.ListaUsuarios;
import packProyecto.Similitud;
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
		fail("mirar alguna posicion");
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
	public void testModeloPersona() {
		fail("-------------");
	}

	@Test
	public void testGetIdoneidad() {
		fail();
	}

	@Test
	public void testGenerarModeladoDeProductos() {
		BaseDatos.getBd().cargarBd();
		ListaTags.getListaTags();
		ListaUsuarios.getListaUsuarios();
		tags.leerFichero();
		tags.imprimirlista();
		System.out.println("Arriba el hashmap^^");
		tags.inicializarFiltradoContenido();
		tags.imprimirModeloProducto();
		
		double[] aux = new double[8];
		aux[0] = 0.7758209126545023; //mas o menos 0.7781512504 con calculadora//
		aux[1] = 0.30012849797165825; //mas o menos 0.30102995 con calculadora//
		aux[2] = 0.9513848293656879; //mas o menos 0.952425094 con calculadora//
		for (int i=0; i<aux.length; i++) {
			assertTrue(aux[i]==tags.getFilaModeloProductos(11)[i]);//prueba para la fila 11//
		}
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

}
