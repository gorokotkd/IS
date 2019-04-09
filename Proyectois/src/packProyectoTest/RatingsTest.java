package packProyectoTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import packProyecto.BaseDatos;
import packProyecto.Cos;
import packProyecto.FiltradoProductos;
import packProyecto.Ratings;

class RatingsTest {

	@BeforeEach
	void setUp() throws Exception {
		Cos cos = new Cos();
		BaseDatos.getBd().setFiltrado(new FiltradoProductos());
		BaseDatos.getBd().getFiltrado().setSimilitud(cos);
		BaseDatos.getBd().cargarBd();
	}

	@AfterEach
	void tearDown() throws Exception {
		BaseDatos.getBd().eliminarBd();
	}

	@Test
	void normalizar() {
		Ratings ratings = BaseDatos.getBd().getRatings();
		ratings.imprimir();
	}

}
