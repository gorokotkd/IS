package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ratings {
	
	private HashMap<Integer, ArrayList<Tupla<Integer,Double>>> lista;
	//key del hashmap es el usuario
	//ArrayList --> Pelis calificadas por el usuario
	//La tupla --> nota a una peli
	private HashMap<Integer,ArrayList<Double>> valoraciones; //pelicula + lista de valoraciones
	private HashMap<Integer,Double> medias;
	
	public Ratings()
	{
		lista = new HashMap<Integer, ArrayList<Tupla<Integer,Double>>>();
	}
	
	public HashMap<Integer, ArrayList<Tupla<Integer,Double>>> getLista(){
		if (lista==null) {
			lista = new HashMap<Integer, ArrayList<Tupla<Integer,Double>>>();
		}
		return lista;
	}
	
	public HashMap<Integer,ArrayList<Double>> getValoraciones(){
		if (valoraciones==null) {
			valoraciones = new HashMap<Integer,ArrayList<Double>>();
		}
		return valoraciones;
	}
	
	public void leerFichero()
	{
		try
		{
			String path = System.getProperty("user.dir")+"/testRatings.csv";
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura=" ";
			ArrayList<Tupla<Integer,Double>> aux = new ArrayList<Tupla<Integer,Double>>();
			lectura = br.readLine();
			String[] str = lectura.split(",");
			int usuAct = Integer.parseInt(str[0]);
			while(lectura!=null)
			{
				
				if(Integer.parseInt(str[0])==usuAct)
				{	
					aux.add(new Tupla(Integer.parseInt(str[1]),Double.parseDouble(str[2])));
					lectura = br.readLine();
					if(lectura != null)
						str = lectura.split(",");
				}
				else
				{
					lista.put(usuAct, aux);
					aux = new ArrayList<Tupla<Integer,Double>>();
					usuAct = Integer.parseInt(str[0]);
				}
				
			}
		}
		catch (Exception e)
		{
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		}
	}

	
	public void cargarValoraciones() {
		valoraciones = new HashMap();
		try
		{
			String path = System.getProperty("user.dir")+"/movie-ratings.csv";
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura=" ";
			ArrayList<Double> aux = new ArrayList<>();
			lectura = br.readLine();
			String[] str = null;
			while(lectura!=null){
				str = lectura.split(",");
				int pelicula = Integer.parseInt(str[1]);
				Double puntuacion = Double.parseDouble(str[2]);
				if (!valoraciones.containsKey(pelicula)) { //Contiene la pelicula
					aux.add(puntuacion);
					valoraciones.put(pelicula,aux);
					aux = new ArrayList<Double>();
				}else {this.anadirALista(pelicula, puntuacion);}
				lectura = br.readLine();
			}
		}
		catch (Exception e)
		{
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		}
	}
	
	public synchronized void anadirALista(int pKey, Double pPuntuacion) {
		ArrayList<Double> lista = valoraciones.get(pKey);
		if (!(lista==null)) {
			lista.add(pPuntuacion);
			valoraciones.put(pKey, lista);
		}
	}
	
	public void normalizar() { //normalizaci�n de las valoraciones
		if (lista.size()!=0) {
			medias = new HashMap<Integer,Double>();
			Set<Map.Entry<Integer,ArrayList<Tupla<Integer,Double>>>> mapaEntrada = lista.entrySet();
			Iterator<Map.Entry<Integer, ArrayList<Tupla<Integer,Double>>>> itr = mapaEntrada.iterator();
			while (itr.hasNext()) {
				Map.Entry<Integer, ArrayList<Tupla<Integer,Double>>> entrada = itr.next();
				double aux = 0;
				for (int i = 0; i < entrada.getValue().size(); i++) {
					aux = aux + entrada.getValue().get(i).getY();
				}
				double media = (float) (aux/entrada.getValue().size());
				medias.put(entrada.getKey(),media);
				ArrayList<Tupla<Integer,Double>> aux2 = new ArrayList<Tupla<Integer,Double>>();
				for (int j = 0; j < entrada.getValue().size(); j++) {
					aux2.add(new Tupla<Integer, Double>(entrada.getValue().get(j).getX(), entrada.getValue().get(j).getY()-media));
				}
				lista.put(entrada.getKey(), aux2);
			}
		}
	}
	
	public ArrayList<Tupla<Integer,Double>> desnormalizar(Integer pUsuario) { //desnormalizaci�n de las valoraciones
		Double media = this.medias.get(pUsuario);
		ArrayList<Tupla<Integer,Double>> aux = lista.get(pUsuario);
		ArrayList<Tupla<Integer,Double>> aux1 = new ArrayList<Tupla<Integer,Double>>();
		for (int i = 0; i < aux.size(); i++) {
			aux1.add(new Tupla(this.lista.get(pUsuario).get(i).getX(),(this.lista.get(pUsuario).get(i).getY())+media));
		}
		return aux1;
	}
	
	public Double getMedia(int pUsuario) {
		return this.medias.get(pUsuario);
	}
	
	public ArrayList<Integer> devolKeys() {
		return new ArrayList<>(lista.keySet()); 
	}
	
	public ArrayList<Tupla<Integer,Double>> getRatingsPorId(Integer pId) {
		return lista.get(pId);
	}
	
	public ArrayList<Double> getValoraciones(Integer pPeli){
		return this.valoraciones.get(pPeli);
	}
	
	public double obtenerNota(int pIdUsu, int pIdPeli)
	{
		double nota = -1;
		if (lista.get(pIdUsu)!=null && BaseDatos.getBd().getPeliculas().getLista()!=null) {
			ArrayList<Tupla<Integer,Double>> listaAux = lista.get(pIdUsu);
			Iterator<Tupla<Integer,Double>> itr = listaAux.iterator();
			boolean salir = false;
			
			while(!salir && itr.hasNext())
			{
				Tupla<Integer,Double> tAux = itr.next();
				if(tAux.getX()==pIdPeli)
				{
					nota=tAux.getY();
					salir = true;
				}
			}
		}
		return nota;
	
	}
	/**
	 METODOS RELACIONADOS CON EL FILTRADO COLABORATIVO BASADO EN PERSONAS
	 */
	
/*	private void generarMatrizDeMedias()
	{
		medias = new HashMap<Integer,Double>();
		double mediaDelUsu=0.0;
		ArrayList<Integer> keysUsus = new ArrayList<Integer>(lista.keySet());
		Iterator<Integer> itr = keysUsus.iterator();
		
		while(itr.hasNext())
		{
			int usuAct = itr.next();
			mediaDelUsu=mediaDeArray(valoraciones.get(usuAct));
			
		}
		
		
		
	}
	
	private double mediaDeArray(ArrayList<Double> list)
	{
		double resul = 0.0;
		
		Iterator<Double> itr = list.iterator();
		
		while(itr.hasNext())
			resul = resul +itr.next();
		return resul/list.size();
	}
	*/
	
	public double valoracionEstimada(int usu, int peli)
	{
		HashMap<Integer,Double> similitudes = obtenerLasNMasSimiliares(usu, 10);
		double numerador = numeradorValoracion(peli, similitudes);
		double denom = denomValoracion(similitudes);
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
			sumatorio = sumatorio + (obtenerNota(usuAct, peli)*similitudes.get(usuAct));
		}
		return sumatorio;
	}
	
	private double denomValoracion(HashMap<Integer,Double> similitudes)
	{
		Double sumatorio = 0.0;
		ArrayList<Integer> keys = new ArrayList<Integer>(similitudes.keySet());
		Iterator<Integer> itr = keys.iterator();
		
		while(itr.hasNext())
			sumatorio = sumatorio + itr.next();
		
		return sumatorio;
		
		
	}
	
	private HashMap<Integer,Double> obtenerLasNMasSimiliares(int usu, int N)
	{
		SimilitudStrategy sim = BaseDatos.getBd().getSimilitud();
		ArrayList<Double> valoracionesDelUsu = valoraciones.get(usu);
		int i = 1;
		
		HashMap<Integer,Double> similitudes = new HashMap<>();
		
		while(i<valoraciones.size())
		{
			if(i!=usu)
			{
				ArrayList<Double> aux = valoraciones.get(i);
				if(aux!=null)
					similitudes.put(i,sim.calcularSimilitud(valoracionesDelUsu, aux));
				
			}
		}
		
		similitudes=sortByValues(similitudes);
		return soloLasNPrimeras(N, similitudes);
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
		}
		return aux;
	}
	
	
	private boolean haCalificadoLaPeli(int usu, int peli)
	{
		boolean salir = false;
		ArrayList<Integer> usuarios = new ArrayList<Integer>(lista.keySet());
		Iterator<Integer> itr = usuarios.iterator();
		
		while(itr.hasNext()&&!salir)
		{
			int usuAct = itr.next();
			if(usuAct==usu)
			{
				ArrayList<Tupla<Integer,Double>> susRatings = lista.get(usuAct);
				Iterator<Tupla<Integer,Double>> itr2 = susRatings.iterator();
				while(itr2.hasNext()&&!salir)
				{
					Tupla<Integer,Double> tupla = itr2.next();
					if(tupla.getX()==peli)
						salir=true;
				}
			}
		}
		return salir;
		
	}
	
	//TODO A partir de aqui solo hay metodos de los jUnit
	public void eliminar()
	{
		lista.clear();
		valoraciones.clear();
		medias.clear();
	}
	
	public void imprimir() {
		Set<Integer> keySet = lista.keySet();  
		ArrayList<Integer> listOfKeys = new ArrayList<Integer>(keySet);
		for (int i = 0; i < listOfKeys.size(); i++) {
			System.out.println("USUARIO: " + listOfKeys.get(i));
			ArrayList<Tupla<Integer,Double>> aux = lista.get(listOfKeys.get(i));
			for (int j = 0; j < aux.size(); j++) {
				System.out.print(" -> <" + aux.get(j).getX() + " " +aux.get(j).getY());
			}
			System.out.println("\n");
		}
	}
}