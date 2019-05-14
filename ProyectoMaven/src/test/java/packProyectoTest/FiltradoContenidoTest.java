package packProyectoTest;
import packProyecto.*;
import static org.junit.Assert.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FiltradoContenidoTest {
	FiltradoContenido filtr ;
	@Before
	public void setUp() throws Exception {
		ListaPeliculas.getListaPeliculas().leerFichero("testMovies.csv");
		ListaRatings.getListaRatings().leerFichero("testRatings.csv");
		TagsPorPeli.getTagsPorPeli().leerFichero("testTags.csv");
		filtr = new FiltradoContenido(new Cos());

	}

	@After
	public void tearDown() throws Exception {
		filtr = null;
	}


	@Test
	public void testRecomendarNPeliculas() {

		HashMap<String,Double> resul = new HashMap<String,Double>();
		resul = filtr.recomendarNPeliculas(1);
		System.out.println(resul);
				

	}

	@Test
	public void testFiltradoContenido() {
		assertNotNull(filtr);
	}

	@Test
	public void testSoloLasNPrimeras() {
		
	}

}
