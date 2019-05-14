package packProyectoTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.ListaTags;

public class ListTagsTest {

	@Before
	public void setUp() throws Exception {
		ListaTags.getListaTags().leerFichero("testTags.csv");;
	}

	@After
	public void tearDown() throws Exception {
		ListaTags.getListaTags();
	}

	@Test
	public void testTamano() {
		assertEquals(ListaTags.getListaTags().tamano(),8);
	}

	@Test
	public void testGetIdTag() {
		assertEquals(ListaTags.getListaTags().getIdTag("divertida"),5);
		assertEquals(ListaTags.getListaTags().getIdTag("dinamica"),4);
	}


}
