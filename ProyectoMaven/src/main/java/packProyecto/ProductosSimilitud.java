package packProyecto;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductosSimilitud {

	private HashMap<Integer,HashMap<Integer,Double>> lista;
	
	public ProductosSimilitud() {
		lista = new HashMap<Integer, HashMap<Integer,Double>>();
	}
	
	public void anadir(int pId, HashMap<Integer,Double> pHash) {
		lista.put(pId, pHash);
	}
	
	public HashMap<Integer,Double> getSimilitudesPorId(int pId){
		return lista.get(pId);
	}
	
	public Double getSimilitud(int pPro1, int pPro2) {
		return lista.get(pPro1).get(pPro2);
	}
	
}
