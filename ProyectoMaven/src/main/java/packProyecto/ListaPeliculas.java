package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

public class ListaPeliculas {
	
	private HashMap<Integer,String> lista; //idPeli + NombrePelicula
	private ProductosSimilitud productosSimilitud;
	private LeerFicheroPeliculas fich;
	private int idMayor;
	private static ListaPeliculas mPelis;
	
	private ListaPeliculas()
	{
		lista = new HashMap<Integer,String>();
		productosSimilitud = new ProductosSimilitud();
		//PASOS PARA INICIALIZACION
		//1-Instanciar
		//2-Set fichero
		//3-Inicializar
	}
	

	public static ListaPeliculas getListaPeliculas()
	{
		if(mPelis==null)
			mPelis=new ListaPeliculas();
		return mPelis;
	}
	
	public void setFichero(String pPath) {
		fich = new LeerFicheroPeliculas(pPath);
		lista = fich.leerFichero();
	}

	private Iterator<Map.Entry<Integer, String>> getIterator(){
		Set<Map.Entry<Integer,String>> mapaEntrada = lista.entrySet();
		return mapaEntrada.iterator();
	}
	
	public int getIdMayor()
	{
		return idMayor;
	}
	public ArrayList<Integer> getKeys()
	{
		return new ArrayList<Integer>(lista.keySet());
	}
	public int size()
	{
		return lista.size();
	}
	public Set<Entry<Integer, String>> entrySet(){
		return lista.entrySet();
	}
	 
	public void inicializar() {
		Iterator<Map.Entry<Integer, String>> itr = getIterator();
		HashMap<Integer,Double> hashAux = new HashMap<Integer, Double>();

		while (itr.hasNext()) { // recorremos el HashMap de idPeli + NombrePelicula
			Map.Entry<Integer, String> entrada = itr.next();
			hashAux = getColeccionSimilitudes(entrada.getKey());
			if (!hashAux.isEmpty()) {
				productosSimilitud.añadir(entrada.getKey(), hashAux);
			}
		}
	}
	
	public HashMap<Integer,Double> getColeccionSimilitudes(int pId) {
		Set<Map.Entry<Integer,String>> mapaEntrada = lista.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = getIterator();
		ListaValoracionesPorPeli valoraciones = ListaRatings.getListaRatings().getValoraciones();
		HashMap<Integer,Double> hashAux = new HashMap<Integer, Double>();
		Double simil = 0.0;
		ArrayList<Double> aux1,aux2;
		
		while (itr.hasNext()) { //recorremos el resto de peliculas, para rellenar la tabla de similiProductos
			Map.Entry<Integer, String> entrada = itr.next();
			if (pId != entrada.getKey()) {	
				aux1 = valoraciones.getValoracionesPorId(pId);
				aux2 = valoraciones.getValoracionesPorId(entrada.getKey());
				if (aux1!=null && aux2!=null) {
					if (pId>entrada.getKey()) {simil = Gestor.getGestor().getFiltrado().getSimilitud().calcularSimilitud(aux1,aux2);
					}else {simil = Gestor.getGestor().getFiltrado().getSimilitud().calcularSimilitud(aux2,aux1);}
					
					hashAux.put(entrada.getKey(), simil);
				}
			}
		}
		return hashAux;
	}
	
	public Double calcularIdoneidad(int pUsuario, int pProducto) {		
		Double rdo = calcularFormula(productosSimilitud.getSimilitudesPorId(pProducto), pUsuario, pProducto);
		rdo = ListaRatings.getListaRatings().getNormalizar().desnormalizar(pUsuario, rdo);
		
		System.out.println("El resultado de la similitud entre el producto :" +pProducto+ " del usuario: "+pUsuario+" es de --> "+rdo);
		return rdo;
	}
	
	private Double calcularFormula(HashMap<Integer,Double> pSimilares, int pUsuario, int pProducto) {
		if (pSimilares==null) {
			return 0.0;
		}else {
			pSimilares = ordenar(pSimilares); 
			Set<Map.Entry<Integer,Double>> mapaEntrada = pSimilares.entrySet();
			Iterator<Map.Entry<Integer, Double>> itr = mapaEntrada.iterator();
			Double nota = 0.0;
			Double sumaNumerador = 0.0;
			Double sumaDenominador = 0.0;
			Double simil = 0.0;
			int i = 0;
			while(itr.hasNext() && i< 20) {
				Map.Entry<Integer, Double> entrada = itr.next();
				nota = ListaRatings.getListaRatings().obtenerNota(pUsuario, entrada.getKey());
				if (nota<0) {
					nota = 0.0;
				}
				simil = productosSimilitud.getSimilitud(pProducto, entrada.getKey());
				sumaNumerador = sumaNumerador +(nota*simil);
				sumaDenominador = sumaDenominador + simil;
			}
			return sumaNumerador/sumaDenominador;
		}
	}
	
	private HashMap<Integer, Double> ordenar(HashMap<Integer, Double> map) { 
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
