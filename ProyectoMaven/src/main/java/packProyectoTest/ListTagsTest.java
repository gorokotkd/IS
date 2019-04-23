package packProyectoTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packProyecto.ListaTags;

public class ListTagsTest {

	@Before
	public void setUp() throws Exception {
		ListaTags.getListaTags();
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
		//assertNull(ListaTags.getListaTags().getIdTag("Jamon de york"));
		assertEquals(ListaTags.getListaTags().getIdTag("dinamica"),4);
	}

	@Test
	public void testKeySet() {
		fail("Not yet implemented");
	}

}
