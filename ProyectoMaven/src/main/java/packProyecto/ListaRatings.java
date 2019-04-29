package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ListaRatings implements LeerFichero {

	private HashMap<Integer, ArrayList<Tupla<Integer,Double>>> lista;
	private static ListaRatings mRatings;
	
	public void leerFichero() {
		try
		{
			String path = System.getProperty("user.dir")+"/src/main/resources/movie-ratings.csv";
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
				if(lectura == null)
				{
					lista.put(usuAct, aux);
				}
				
			}
		}
		catch (Exception e)
		{
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		}

	}
	
	private ListaRatings()
	{
		lista = new HashMap<Integer,ArrayList<Tupla<Integer,Double>>>();
		leerFichero();
	}
	
	public static ListaRatings getListaRatings()
	{
		if(mRatings==null)
			mRatings = new ListaRatings();
		return mRatings;
	}
	
	public ArrayList<Tupla<Integer,Double>> getRatingsPorId(Integer pId) {
		return lista.get(pId);
	}
	
	public double obtenerNota(int pIdUsu, int pIdPeli)
	{
		double nota = -1;
		if (lista.get(pIdUsu)!=null && ListaPeliculas.getListaPeliculas().size()!=0) {
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
	
	public boolean haValoradoLaPelicula(int pIdUsu, int pIdPeli)
	{
		double nota = -1;
		boolean salir = false;
		if (lista.get(pIdUsu)!=null && ListaPeliculas.getListaPeliculas().size()!=0) {
			ArrayList<Tupla<Integer,Double>> listaAux = lista.get(pIdUsu);
			Iterator<Tupla<Integer,Double>> itr = listaAux.iterator();
			
			
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
		return salir;
	
	}

	public HashMap<Integer, ArrayList<Tupla<Integer,Double>>> obtenerLista()
	{
		return lista;
	}
	public ArrayList<Integer> ratingsDevolKeys() {
		ArrayList<Integer> keys = new ArrayList<Integer>(lista.keySet());
		return keys;
	}
}
