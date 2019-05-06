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
	
	public abstract HashMap<String,Double> recomendarNPeliculas(int idUsu);
	
	public HashMap<Integer, Double> sortByValues(HashMap<Integer, Double> map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return (-1)*((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	
}
