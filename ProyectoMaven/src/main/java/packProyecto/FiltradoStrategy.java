package packProyecto;

public abstract class FiltradoStrategy {

	SimilitudStrategy similitud ;
	

	public SimilitudStrategy getSimilitud() {
		return similitud;
	}

	public void setSimilitud(SimilitudStrategy similitud) {
		this.similitud = similitud;
	}
	
}
