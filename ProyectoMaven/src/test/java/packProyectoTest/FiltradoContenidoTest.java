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

		/**
		 * Realizando los calculos a mano con los ficheros de prueba los resultados son los siguientes:
		 * 
		 * USUARIO 1:
		 * 	American Beauty (1999) : 0.0
		 * 	Pirates of the Caribbean: The Curse of the Black Pearl (2003) : 0.0
		 * 	Kill Bill: Vol. 1 (2003) : 0.27321
		 * 
		 * USUARIO 2:
		 * 	Star Wars: Episode IV - A New Hope (1977) : 0.14238
		 * 	Kill Bill: Vol. 1 (2003) : 0.19321
		 * 
		 * USUARIO 3:
		 * 	Forrest Gump (1994) : 0.0
		 * 
		 * USUARIO 4:
		 * 	Kill Bill: Vol. 1 (2003) : 0.08876
		 * 
		 * USUARIO 5:
		 * 	Forrest Gump (1994) : 0.14812
		 * 	American Beauty (1999) : 0.53038
		 * 	Pirates of the Caribbean: The Curse of the Black Pearl (2003) : 0.37356
		 * 
		 */
		HashMap<String,Double> resul = new HashMap<String,Double>();
		
		//CASOS PARA EL USUARIO 1
		
		resul = filtr.recomendarNPeliculas(1);

		assertTrue(resul.get("\"American Beauty (1999)\"")==0.0);
		assertTrue(resul.get("\"Pirates of the Caribbean: The Curse of the Black Pearl (2003)\"")==0.0);
		assertEquals(resul.get("\"Kill Bill: Vol. 1 (2003)\""),0.27321,0.001);
		
		//CASOS PARA EL USUARIO 2
		
		resul = filtr.recomendarNPeliculas(2);

		assertEquals(resul.get("\"Kill Bill: Vol. 1 (2003)\""),0.19321,0.001);
		assertEquals(resul.get("\"Star Wars: Episode IV - A New Hope (1977)\""),0.14238,0.001);
		
		//CASOS PARA EL USUARIO 3
		
		resul = filtr.recomendarNPeliculas(3);

		assertTrue(resul.get("\"Forrest Gump (1994)\"")==0.0);
		
		//CASOS PARA EL USUARIO 4
		
		resul = filtr.recomendarNPeliculas(4);

		assertEquals(resul.get("\"Kill Bill: Vol. 1 (2003)\""),0.08876,0.001);
		
		//CASOS PARA EL USUARIO 5
		
		resul = filtr.recomendarNPeliculas(5);
		assertEquals(resul.get("\"American Beauty (1999)\""),0.53038,0.001);
		assertEquals(resul.get("\"Forrest Gump (1994)\""),0.14812,0.001);
		assertEquals(resul.get("\"Pirates of the Caribbean: The Curse of the Black Pearl (2003)\""),0.37356,0.001);

	}

	@Test
	public void testFiltradoContenido() {
		assertNotNull(filtr);
	}

}
