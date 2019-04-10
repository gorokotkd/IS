package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Comparator;



public class Peliculas {

	private HashMap<Integer,String> lista; //idPeli + NombrePelicula
	private HashMap<Integer,HashMap<Integer,Double>> similiProductos; //idProducto + (idProducto+similitud)
	private int idMayor;
	
	public Peliculas()
	{
		lista = new HashMap<Integer,String>();
		idMayor=-1;
		similiProductos = new HashMap<Integer, HashMap<Integer,Double>>();
	}
	
	public HashMap<Integer,String> getLista(){
		return lista;
	}
	
	public int getIdMayor()
	{
		return idMayor;
	}

	public void leerFichero(){
		try
		{
			String path = System.getProperty("user.dir")+"/movie-titles.csv";
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String lectura = br.readLine();
			while(lectura!=null)
			{	
				String[] str = lectura.split(";");
				String sr = str[1];
				int key = Integer.parseInt(str[0]);
				if(key>idMayor)
					idMayor=key;
				lista.put(key, sr);
				lectura=br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void initMatrizSimilitudes() {
		Set<Map.Entry<Integer,String>> mapaEntrada = lista.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		HashMap<Integer,Double> hashAux = new HashMap<Integer, Double>();

		while (itr.hasNext()) { // recorremos el HashMap de idPeli + NombrePelicula
			Map.Entry<Integer, String> entrada = itr.next();
			hashAux = getFilaSimilitudes(entrada);
			if (!hashAux.isEmpty()) {
				similiProductos.put(entrada.getKey(), hashAux);
			}
		}		 
	}
	
	private HashMap<Integer,Double> getFilaSimilitudes(Map.Entry<Integer, String> entrada){
		HashMap<Integer,Double> hashAux = new HashMap<Integer, Double>();
		Set<Map.Entry<Integer,String>> mapaEntrada2 = lista.entrySet();
		Iterator<Map.Entry<Integer, String>> itr2 = mapaEntrada2.iterator();
		Ratings ratings = BaseDatos.getBd().getRatings();
		Double simil = 0.0;
		ArrayList<Double> aux1,aux2;
		
		while (itr2.hasNext()) { //recorremos el resto de peliculas, para rellenar la tabla de similiProductos
			Map.Entry<Integer, String> entrada2 = itr2.next();
			if (entrada.getKey()!=entrada2.getKey()) {	
				aux1 = ratings.getValoraciones(entrada.getKey());
				aux2 = ratings.getValoraciones(entrada2.getKey());
				if (aux1!=null && aux2!=null) {
					if (entrada.getKey()>entrada2.getKey()) {simil = BaseDatos.getBd().getFiltrado().getSimilitud().calcularSimilitud(aux1,aux2);
					}else {simil = BaseDatos.getBd().getFiltrado().getSimilitud().calcularSimilitud(aux2,aux1);}
					
					hashAux.put(entrada2.getKey(), simil);
				}
			}
		}
		return hashAux;
	}
	
	public Double calcularIdoneidad(int pUsuario, int pProducto) {		
		Double rdo = this.calcularFormula(this.similiProductos.get(pProducto), pUsuario, pProducto);
		rdo = rdo + BaseDatos.getBd().getRatings().getMedia(pUsuario);
		
		System.out.println("El resultado de la similitud entre el producto :" +pProducto+ " del usuario: "+pUsuario+" es de --> "+rdo);
		return rdo;
	}
	
	private Double calcularFormula(HashMap<Integer,Double> pSimilares, int pUsuario, int pProducto) {
		if (pSimilares==null) {
			return 0.0;
		}else {
			pSimilares = sortByValues(pSimilares); 
			Set<Map.Entry<Integer,Double>> mapaEntrada = pSimilares.entrySet();
			Iterator<Map.Entry<Integer, Double>> itr = mapaEntrada.iterator();
			Ratings rating = BaseDatos.getBd().ge8tRatings();
			Double nota = 0.0;
			Double sumaNumerador = 0.0;
			Double sumaDenominador = 0.0;
			Double simil = 0.0;
			int i = 0;
			while(itr.hasNext() && i< 20) {
				Map.Entry<Integer, Double> entrada = itr.next();
				nota = rating.obtenerNota(pUsuario, entrada.getKey());
				if (nota<0) {
					nota = 0.0;
				}
				simil = this.buscarSimilitud(pProducto, entrada.getKey());
				sumaNumerador = sumaNumerador +(nota*simil);
				sumaDenominador = sumaDenominador + simil;
			}
			return sumaNumerador/sumaDenominador;
		}
	}
	
	private static HashMap<Integer, Double> sortByValues(HashMap<Integer, Double> map) { 
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
	
	public Set<Entry<Integer,String>> entrySet() {
		return lista.entrySet();
	}	
	
	private Double buscarSimilitud(int pPro1, int pPro2) {
		return this.similiProductos.get(pPro1).get(pPro2);
	}
	
	private void imprimir(ArrayList<Integer> listOfKeys) {
		int i =0;
		for (i = 0; i < listOfKeys.size(); i++) {
			System.out.println(listOfKeys.get(i) +" :=> " );
			System.out.println(similiProductos.get(listOfKeys.get(i)));
		}
		System.out.println(i);
	}
	
	public boolean estaId(int id)
	{
		return lista.containsKey(id);
	}
	
	public ArrayList<Integer> getKeys()
	{
		return new ArrayList<>(lista.keySet());
	}
	
	//TODO A partir de aqui solo hay metodos de los jUnit
	
	public int size()
	{
		return lista.size();
	}
	
	public void eliminar()
	{
		lista.clear();
	}
}
