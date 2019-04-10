package packProyecto;

public abstract class Filtrado {

	private SimilitudStrategy similitud;
	
	public Filtrado() {
		
	}
	
	public void setSimilitud(SimilitudStrategy pSimilitud) {
		similitud = pSimilitud;
	}
	
	public SimilitudStrategy getSimilitud() {
		return similitud;
	}

	public abstract Double filtrar(int parametro1, int parametro2);
}
