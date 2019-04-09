package packProyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagsPorPeli {

	private HashMap<Integer,ArrayList<Tupla<String, Integer>>> lista;
	private double[][] modeloProductos;
	private double[][] modeladoPersona;
	
	public TagsPorPeli()
	{
		lista = new HashMap<Integer,ArrayList<Tupla<String, Integer>>>();
		modeloProductos = new double[BaseDatos.getBd().idMayorPelicula()+1][ListaTags.getListaTags().tamano()];
		modeladoPersona = new double[ListaUsuarios.getListaUsuarios().size()][ListaTags.getListaTags().tamano()];
	}
	
	public void leerFichero()
	{
		String path = System.getProperty("user.dir")+"/movie-tags.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura = " ";
			int cont = 0;
			ArrayList<Tupla<String, Integer>> aux = new ArrayList<Tupla<String, Integer>>();
			lectura = br.readLine();
			String[] str = lectura.split(";");
			String tagAct = str[1];
			int idAct = Integer.parseInt(str[0]);
			while(lectura!=null)
			{
				if(Integer.parseInt(str[0])==idAct)
				{
					if(tagAct.equals(str[1]))
						cont++;
					else
					{
						aux.add(new Tupla(tagAct,cont));
						tagAct=str[1];
						cont = 1;
					}
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
					else
					{
						aux.add(new Tupla(str[1],cont));
						lista.put(Integer.parseInt(str[0]), aux);
					}
				}
				else
				{
					aux.add(new Tupla(tagAct,cont));
					cont=1;
					lista.put(idAct, aux);
					aux = new ArrayList<Tupla<String, Integer>>();
					tagAct=str[1];
					idAct=Integer.parseInt(str[0]);
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
					else
					{
						aux.add(new Tupla(str[1],cont));
						lista.put(Integer.parseInt(str[0]), aux);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public ArrayList<Integer> tagsDevolKeys() {
		return new ArrayList<>(lista.keySet()); 
	}
	
	public ArrayList<Tupla<String, Integer>> getTagsPorId(Integer pId) {
		return lista.get(pId);
	}
	
	public void modeloPersona()
	{
		int usu = 1;
		while(usu<ListaUsuarios.getListaUsuarios().size())
		{
			ArrayList<Tupla<Integer,Double>> ratingsDelUsu=BaseDatos.getBd().getRatingsPorId(usu);
			ArrayList<Integer> pelisPorEncimaDelUmbral = calcularPelisPorEncimaDelUmbral(ratingsDelUsu,3.5);
			if(pelisPorEncimaDelUmbral!=null)
			{
				Iterator<Integer> itr = pelisPorEncimaDelUmbral.iterator();
				
				while(itr.hasNext())
				{
					int peliAux = itr.next();
					Iterator<String> itr2 = ListaTags.getListaTags().keySet().iterator();
					while(itr2.hasNext())
					{
						String tag = itr2.next();
						modeladoPersona[usu][ListaTags.getListaTags().getIdTag(tag)] = modeladoPersona[usu][ListaTags.getListaTags().getIdTag(tag)] + getTfidfDe(peliAux, tag);
					}
				}
			}
			usu++;
		}
		
	}

	
	private ArrayList<Double> getFilaPersona(int idUsu)
	{
		ArrayList<Double> resul = new ArrayList<Double>();
		
		for(int i=0;i<ListaTags.getListaTags().tamano()-1;i++)
			resul.add(modeladoPersona[idUsu][i]);
		return resul;
	}
	
	private ArrayList<Double> getFilaProducto(int idPeli)
	{
		ArrayList<Double> resul = new ArrayList<Double>();
		
		for(int i=0;i<ListaTags.getListaTags().tamano()-1;i++)
			resul.add(modeloProductos[idPeli][i]);
		return resul;
	}
	public double getIdoneidad(int idUsu, int idPeli)
	{
		
		Cos sim = new Cos();
		
		ArrayList<Double> vectorPersona = getFilaPersona(idUsu);
		ArrayList<Double> vectorPelicula = getFilaProducto(idPeli);
		
		return sim.calcularSimilitud(vectorPersona, vectorPelicula);
	}
	
/**	public void recomendarNPeliculas(int idUsu)
	{
		HashMap<Integer,Double> list = peliculasIdoneasParaElUsuario(idUsu);
		list=sortByValues(list);
		int N = 10;
		int i = 0;
		System.out.println("Las mejores peliculas para el usuario: " + idUsu+" son: \n");
		while(i<N-1)
		{
			System.out.println(i+": "+list.get(i)+"\n");
			i++;
		}
		
	}
	
	private HashMap<Integer,Double> peliculasIdoneasParaElUsuario(int idUsu)
	{
		int i = 0;
		HashMap<Integer,Double> list = new HashMap<Integer,Double>();
		
		while(i<BaseDatos.getBd().cuantasPelis())
			list.put(i, getIdoneidad(idUsu, i));
		return list;
	}
	
	*/
/*	
	private double idoneidadEntreUsuarios(int usu1, int usu2)
	{
		Cos sim = new Cos();
		
		ArrayList<Double> vectorPersona = getFilaPersona(usu1);
		ArrayList<Double> vectorPersona2 = getFilaPersona(usu2);
		
		return sim.calcularSimilitud(vectorPersona, vectorPersona2);
	}
	
	private HashMap<Integer,Double> idoneidadDeUsuarioParaTodasLasPelis(int idUsu)
	{
		int i=0;
		HashMap<Integer,Double> lista = new HashMap<Integer,Double>();
		while(i<BaseDatos.getBd().idMayorPelicula())
		{
			if(BaseDatos.getBd().estaPeli(i))
			{
				lista.put(i, getIdoneidad(idUsu,i));
			}
			i++;
		}
		lista = sortByValues(lista);
		return lista;
	}
	
	private HashMap<Integer,Double> idoneidadDeUsuarioConLosDemas(int idUsu)
	{
		HashMap<Integer,Double> lista = new HashMap<Integer,Double>();
		int i = 0;
		while(i<ListaUsuarios.getListaUsuarios().size())
		{
			if(ListaUsuarios.getListaUsuarios().contains(i))
				lista.put(i, idoneidadEntreUsuarios(idUsu, i));
			i++;
		}
		return lista;
	}
	*/
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
	/*
	private double numeradorEstimarValoracion(int idUsu, int idPeli, int N)
	{
		int cont = 0;
		double sumatorio = 0.0;
		HashMap<Integer,Double> listz = idoneidadDeUsuarioParaTodasLasPelis(idUsu);
		listz = sortByValues(listz);
		ArrayList<Integer> keys = new ArrayList<Integer>(listz.keySet());//ids de peliculas
		Iterator<Integer> itr = keys.iterator();
		
		while(itr.hasNext()&&cont<N-1)
		{
			int aux = itr.next();
			BaseDatos.getBd().obtenerNotaPeliculas(idUsu, aux);
			sumatorio = sumatorio+(listz.get(aux)*BaseDatos.getBd().obtenerNotaPeliculas(idUsu, aux));
			cont++;
		}
		return sumatorio;
		
		
	}
	
	private double denominadorEstimarValoraciones(int idUsu, int N)
	{
		int cont = 0;
		double sumatorio = 0.0;
		HashMap<Integer,Double> lista = idoneidadDeUsuarioParaTodasLasPelis(idUsu);
		lista = sortByValues(lista);
		ArrayList<Integer> keys = new ArrayList<Integer>(lista.keySet());
		
		Iterator<Integer> itr = keys.iterator();
		
		while(itr.hasNext()&&cont<N-1)
		{
			sumatorio = sumatorio+lista.get(itr.next());
			cont++;
		}
		return sumatorio;
	}
	public double estimarValoracion(int idUsu,int idPeli,int N)
	{
		double numera = numeradorEstimarValoracion(idUsu,idPeli,N);
		double denom = denominadorEstimarValoraciones(idUsu,N);
		return numera/denom;
	}
	*/
	private double getTfidfDe(int pIdPeli, String pTag)
	{
		return modeloProductos[pIdPeli][ListaTags.getListaTags().getIdTag(pTag)];
	}
	
	private ArrayList<Integer> calcularPelisPorEncimaDelUmbral(ArrayList<Tupla<Integer,Double>> list, Double umbral)
	{
		ArrayList<Integer> resul = new ArrayList<>();
		if(list!=null)
		{
			Iterator<Tupla<Integer,Double>> itr = list.iterator();
			while(itr.hasNext())
			{
				Tupla<Integer,Double> aux = itr.next();
				if(aux.getY()>=umbral)
					resul.add(aux.getX());
			}
			return resul;
		}
		else
			return null;
	}

	
	public void generarModeladoDeProductos()
	{
		Iterator<Integer> itr = tagsDevolKeys().iterator();
		while(itr.hasNext())
		{
			int i = itr.next();
			Iterator<Tupla<String,Integer>> itr2 = lista.get(i).iterator();
			while(itr2.hasNext())
			{
				Tupla<String,Integer> tupla = itr2.next();
				double resul = tfidf(i, tupla.getX());
				int idTag = ListaTags.getListaTags().getIdTag(tupla.getX());
				modeloProductos[i][idTag] = resul;
			}
			modeloProductos[i] = vectorUnitario(modeloProductos[i]);
		}
	//	vectorUnitarioMatriz();
	
	}
	
	private void vectorUnitarioMatriz()
	{
		int i = 0;
		while(i<modeloProductos[0].length)
		{
			double[] list = columnaArray(i);
			list=vectorUnitario(list);
			arrayAColumna(i,list);
			i++;
		}
	}
	
	private void arrayAColumna(int col, double[] list)
	{
		for(int i=0;i<list.length;i++)
			modeloProductos[i][col]=list[i];
	}
	
	private double[] columnaArray(int col)
	{
		double[] list = new double[modeloProductos.length];
		for(int i=0; i<list.length;i++)
			list[i] = modeloProductos[i][col];
		return list;
	}
	
	private double[] vectorUnitario(double[] list)
	{

		double modulo = moduloVector(list);

		for(int i=0;i<list.length;i++)
		{
			double aux = list[i]/modulo;
			list[i]=aux;
		}
		return list;	
	}
	
	private double moduloVector(double[] list)
	{
		double resul = 0.0;
		for (int i=0;i<list.length;i++)
			resul = resul+Math.pow((list[i]),2.0);
		return Math.sqrt(resul);
	}
	
	private double tfidf(Integer pPeli, String pTag)
	{
		double tf = (double) cuantasVecesTieneTag(pPeli,pTag);
		double nt = (double) cuantasConEseTag(pTag);
	//	double n = (double) lista.size();
		double n = (double) BaseDatos.getBd().cuantasPelis();
		return tf*Math.log10(n/nt);
		
	}
	private int cuantasVecesTieneTag(Integer pPeli, String pTag)
	{
		boolean salir = false;
		int resul =0;
		ArrayList<Tupla<String,Integer>> aux = this.getTagsPorId(pPeli);
		Iterator<Tupla<String,Integer>> itr = aux.iterator();
		while(itr.hasNext()&&!salir)
		{
			Tupla<String,Integer> tupla = itr.next();
			if(tupla.getX().equals(pTag))
			{
				salir = true;
				resul = tupla.getY();
			}
		}
		return resul;
	}
	
	private int cuantasConEseTag(String tag)
	{
		
		int i = 0;
		int cont = 0;
		Iterator<Integer> itr2 = tagsDevolKeys().iterator();
		while(itr2.hasNext())
		{
			i=itr2.next();
			boolean salir = false;
			ArrayList<Tupla<String,Integer>> aux = this.getTagsPorId(i);
			Iterator<Tupla<String,Integer>> itr = aux.iterator();
			while(itr.hasNext()&&!salir)
			{
				Tupla<String,Integer> tupla = itr.next();
				if(tupla.getX().equals(tag))
				{
					salir = true;
					cont++;
				}
			}
		}
		return cont;
	}
	
	public void eliminar()
	{
		lista.clear();
		//modeloProductos.clear();
	}
	
	public void anadirEntrada(int key, ArrayList<Tupla<String,Integer>> entrada)
	{
		if(!lista.containsKey(key))
			lista.put(key, entrada);
	}
	
}