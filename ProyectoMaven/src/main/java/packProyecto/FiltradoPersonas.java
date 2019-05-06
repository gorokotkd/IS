package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FiltradoPersonas extends FiltradoStrategy {
	
//	private NormalizarStrategy norm;
	private HashMap<Integer,ArrayList<Double>> valoraciones; //usuario + lista de valoraciones
//	private HashMap<Integer, ArrayList<Tupla<Integer,Double>>> matrizNormalizada; //METER EN LISTA RATINGS
	boolean normalizado; //PREGUNTAR


	public FiltradoPersonas(SimilitudStrategy pSim)
	{
		similitud = pSim;
		//this.normalizar();
		this.generarListaValoraciones();
	}
	
	public void seHaNormalizado(boolean pBool)
	{
		normalizado=pBool;
	}
	
	public HashMap<Integer,Double> recomendarNPeliculas(int pUsu) {
		
		HashMap<Integer,Double> list = peliculasIdoneasParaElUsuario(pUsu);
		list=sortByValues(list);
		int N = 10;
		int i = 0;
		ArrayList<Integer> keys = new ArrayList<Integer>(list.keySet());
		Iterator<Integer> itr = keys.iterator();
		HashMap<Integer,Double> listAux = new HashMap<Integer,Double>();
		while(i<N && itr.hasNext())
		{
			int id = itr.next();
			listAux.put(id, list.get(id));
			i++;
		}
		return listAux;
	}
	
	private HashMap<Integer,Double> peliculasIdoneasParaElUsuario(int idUsu)
	{
		int i = 0;
		HashMap<Integer,Double> list = new HashMap<Integer,Double>();
		ArrayList<Integer> keys = ListaPeliculas.getListaPeliculas().getKeys();
		Iterator<Integer> itr = keys.iterator();
		
		while(itr.hasNext())
		{
			i=itr.next();
			if(!ListaRatings.getListaRatings().haValoradoLaPelicula(idUsu, i))
				list.put(i, valoracionEstimada(idUsu, i));
			
		}
			
		return list;
	}
	
	
	
	private double valoracionEstimada(int usu, int peli)
	{
		
		HashMap<Integer,Double> similitudes = obtenerLasNMasSimiliares(usu, 30, peli);
		double numerador = numeradorValoracion(peli, similitudes);
		double denom = denomValoracion(similitudes);
		
		if(normalizado)
			return ListaRatings.getListaRatings().desnormalizar(usu, numerador/denom);
		else
			return numerador/denom;
	}
	
	private double numeradorValoracion(int peli, HashMap<Integer,Double> similitudes)
	{
		double sumatorio = 0.0;
		ArrayList<Integer> keys = new ArrayList<Integer>(similitudes.keySet());
		Iterator<Integer> itr = keys.iterator();
		while(itr.hasNext())
		{
			int usuAct = itr.next();
			sumatorio = sumatorio + (ListaRatings.getListaRatings().obtenerNota(usuAct, peli)*similitudes.get(usuAct));
		}
		return sumatorio;
	}
	
	
	private double denomValoracion(HashMap<Integer,Double> similitudes)
	{
		Double sumatorio = 0.0;
		ArrayList<Integer> keys = new ArrayList<Integer>(similitudes.keySet());
		Iterator<Integer> itr = keys.iterator();
		
		while(itr.hasNext())
			sumatorio = sumatorio + similitudes.get(itr.next());
		
		return sumatorio;
		
		
	}
	
	private HashMap<Integer,Double> obtenerLasNMasSimiliares(int usu, int N, int pPeli)
	{
		
		ArrayList<Double> valoracionesDelUsu = valoraciones.get(usu);
		HashMap<Integer,Double> similitudes = new HashMap<Integer,Double>();
		ArrayList<Integer> listaUsus = ListaUsuarios.getListaUsuarios().todosLosUsus();
		Iterator<Integer> itr = listaUsus.iterator();
		
		while(itr.hasNext())
		{
			int usuAct = itr.next();
			if(ListaRatings.getListaRatings().haValoradoLaPelicula(usuAct, pPeli))
			{
				if(usuAct != usu)
				{
					ArrayList<Double> aux = valoraciones.get(usuAct);
					if(aux!=null)
						similitudes.put(usuAct,similitud.calcularSimilitud(valoracionesDelUsu, aux));
				}
			}
		}
		
		similitudes=sortByValues(similitudes);
		return soloLasNPrimeras(N, similitudes);
		
	}
	
	private void generarListaValoraciones()
	{
		valoraciones = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> listaUsus = ListaUsuarios.getListaUsuarios().todosLosUsus();
		Iterator<Integer> itr = listaUsus.iterator();
		
		while(itr.hasNext())
		{
			int usuAct = itr.next();
			ArrayList<Tupla<Integer,Double>> listaValoraciones = ListaRatings.getListaRatings().getRatingsPorId(usuAct);
			if(listaValoraciones != null)
			{
				Iterator<Tupla<Integer,Double>> itr2 = listaValoraciones.iterator();
				ArrayList<Double> listaAux = new ArrayList<Double>();
				while(itr2.hasNext())
					listaAux.add(itr2.next().getY());
				valoraciones.put(usuAct, listaAux);
			}
		
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
	
	private HashMap<Integer,Double>  soloLasNPrimeras(int N, HashMap<Integer,Double> list)
	{
		HashMap<Integer,Double> aux = new HashMap<Integer,Double> ();
		ArrayList<Integer> keys = new ArrayList<Integer>(list.keySet());
		Iterator<Integer> itr = keys.iterator();
		int i = 0;
		while(i<N&&itr.hasNext())
		{
			int j = itr.next();
			aux.put(j,list.get(j));
			i++;
		}
		return aux;
	}


}
