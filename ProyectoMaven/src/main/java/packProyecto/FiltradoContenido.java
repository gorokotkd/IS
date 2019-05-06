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

public class FiltradoContenido extends FiltradoStrategy {

	private double[][] modeloProductos;
	private double[][] modeladoPersona;

	
	public FiltradoContenido(SimilitudStrategy sim)
	{
		modeloProductos = new double[ListaPeliculas.getListaPeliculas().getIdMayor()+1][ListaTags.getListaTags().tamano()];
		System.out.println(ListaPeliculas.getListaPeliculas().getIdMayor()+1);
		System.out.println(ListaTags.getListaTags().tamano());
		modeladoPersona = new double[ListaUsuarios.getListaUsuarios().size()][ListaTags.getListaTags().tamano()];
		super.similitud=sim;
		inicializarFiltradoContenido();/**Inicializo lo necesario para el filtrado*/
	}
	
	
	private void modeloPersona()
	{
		int usu = 1;
		while(usu<ListaUsuarios.getListaUsuarios().size())
		{
			ArrayList<Tupla<Integer,Double>> ratingsDelUsu=ListaRatings.getListaRatings().getRatingsPorId(usu);
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
	
	private void inicializarFiltradoContenido()
	{
		this.generarModeladoDeProductos();
		System.out.println("Modelo De productos Generado.");
		this.modeloPersona();
		System.out.println("Modelo De personas Generado.");
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
	private double getIdoneidad(int idUsu, int idPeli)
	{
		ArrayList<Double> vectorPersona = getFilaPersona(idUsu);
		ArrayList<Double> vectorPelicula = getFilaProducto(idPeli);
		
		return similitud.calcularSimilitud(vectorPersona, vectorPelicula);
	}
	
	public HashMap<String, Double> recomendarNPeliculas(int idUsu)
	{
		HashMap<Integer,Double> list = peliculasIdoneasParaElUsuario(idUsu);
		list=super.sortByValues(list);
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
		return null;
		
		/**
		 * GORKKKAAAAAA CAMBIALO PUTO
		 */
		
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
			list.put(i, getIdoneidad(idUsu, i));
		}
			
		return list;
	}
	
	
	
	
	
	
	private double getTfidfDe(int pIdPeli, String pTag)
	{
		return modeloProductos[pIdPeli][ListaTags.getListaTags().getIdTag(pTag)];
	}
	
	private ArrayList<Integer> calcularPelisPorEncimaDelUmbral(ArrayList<Tupla<Integer,Double>> list, Double umbral)
	{
		ArrayList<Integer> resul = new ArrayList<Integer>();
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

	
	private void generarModeladoDeProductos()
	{
		Iterator<Integer> itr = TagsPorPeli.getTagsPorPeli().tagsDevolKeys().iterator();
		while(itr.hasNext())
		{
			int i = itr.next();
			Iterator<Tupla<String,Integer>> itr2 = TagsPorPeli.getTagsPorPeli().get(i).iterator();
			while(itr2.hasNext())
			{
				Tupla<String,Integer> tupla = itr2.next();
				double resul = tfidf(i, tupla.getX());
				int idTag = ListaTags.getListaTags().getIdTag(tupla.getX());
				modeloProductos[i][idTag] = resul;
			}
			modeloProductos[i] = vectorUnitario(modeloProductos[i]);
		}
	
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
		double n = (double) ListaPeliculas.getListaPeliculas().size();
		return tf*Math.log10(n/nt);
		
	}
	private int cuantasVecesTieneTag(Integer pPeli, String pTag)//numero de veces que tiene una peli un determinado tag
	{
		boolean salir = false;
		int resul =0;
		ArrayList<Tupla<String,Integer>> aux = TagsPorPeli.getTagsPorPeli().getTagsPorId(pPeli);
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
	
	private int cuantasConEseTag(String tag)//Numero de pelis con un mismo tag
	{
		
		int i = 0;
		int cont = 0;
		Iterator<Integer> itr2 = TagsPorPeli.getTagsPorPeli().tagsDevolKeys().iterator();
		while(itr2.hasNext())
		{
			i=itr2.next();
			boolean salir = false;
			ArrayList<Tupla<String,Integer>> aux = TagsPorPeli.getTagsPorPeli().getTagsPorId(i);
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
}
