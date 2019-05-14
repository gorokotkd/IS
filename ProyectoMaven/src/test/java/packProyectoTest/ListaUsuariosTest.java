package packProyectoTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.ListaUsuarios;

public class ListaUsuariosTest {

	@Before
	public void setUp() throws Exception {
		ListaUsuarios.getListaUsuarios().leerFichero("testRatings.csv");;
	}

	@After
	public void tearDown() throws Exception {
		ListaUsuarios.getListaUsuarios();
	}

	@Test
	public void testContains() {
		assertTrue(ListaUsuarios.getListaUsuarios().contains(1));
		assertFalse(ListaUsuarios.getListaUsuarios().contains(10));
	}

	@Test
	public void testSize() {
		assertEquals(ListaUsuarios.getListaUsuarios().size(),5);		
	}

}