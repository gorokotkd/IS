package packProyecto;

import java.util.*;

public abstract class FiltradoStrategy {

	SimilitudStrategy similitud ;
	

	public SimilitudStrategy getSimilitud() {
		return similitud;
	}

	public void setSimilitud(SimilitudStrategy similitud) {
		this.similitud = similitud;
	}
	
	public abstract HashMap<Integer,Double> recomendarNPeliculas(int idUsu);
	
}
