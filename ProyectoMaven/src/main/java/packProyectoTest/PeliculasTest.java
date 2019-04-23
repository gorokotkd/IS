package packProyectoTest;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.Gestor;
import packProyecto.Cos;
import packProyecto.FiltradoProductos;
import packProyecto.Peliculas;

public class PeliculasTest {

	Peliculas peliculas;
	@Before
	public void setUp() throws Exception {
		FiltradoProductos fil = new FiltradoProductos();
		fil.setSimilitud(new Cos());
		Gestor.getBd().setFiltrado(fil);
		Gestor.getBd().cargarTodo();
		peliculas = Gestor.getBd().getPeliculas();
	}

	@After
	public void tearDown() throws Exception {
		Gestor.getBd().eliminarRatingsPeliculas();
	}

	@Test
	public void testGetLista() {
		assertNotEquals(peliculas.getLista(), null);
	}

	@Test
	public void testLeerFichero() {
		assertNotEquals(peliculas.getLista(), null);
	}

	@Test
	public void testGetIdMayor() {
		int maxKey = Collections.max(peliculas.getLista().keySet());
		assertEquals(peliculas.getIdMayor(), maxKey,0);
		System.out.println("La id m�s grande de la lista es: "+maxKey);
	}

	@Test
	public void testInitMatrizSimilitudes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFilaSimilitudes() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcularIdoneidad() {
			
	}

}
