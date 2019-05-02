package packProyecto;

public abstract class FiltradoStrategy {

	SimilitudStrategy similitud ;
	
	public abstract void recomendarNPeliculas(int pUsu);

	public SimilitudStrategy getSimilitud() {
		return similitud;
	}

	public void setSimilitud(SimilitudStrategy similitud) {
		this.similitud = similitud;
	}
	
}
