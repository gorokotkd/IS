package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ListaRatings {

	private HashMap<Integer, ArrayList<Tupla<Integer,Double>>> lista; //usuario + lista(peliculas+nota)
	private ListaValoracionesPorPeli valoraciones;
	private NormalizarStrategy norm;
	private LeerFicheroRatings fich;
	private static ListaRatings mRatings;
	
	private ListaRatings()
	{
		try {
			lista = new HashMap<Integer,ArrayList<Tupla<Integer,Double>>>();
			valoraciones = new ListaValoracionesPorPeli();
			System.out.println("asodnosaDJ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//PASOS PARA INICIALIZACION
		//1 - instanciar
		//2 - setFichero
		//3 - normalizar
	}

	public void setFichero(String pPath) {
		fich = new LeerFicheroRatings(pPath);
		lista = fich.leerFichero();
	}
	
	public static ListaRatings getListaRatings()
	{
		if(mRatings==null)
			mRatings = new ListaRatings();
		return mRatings;
	}
	
	public ListaValoracionesPorPeli getValoraciones() {
		return valoraciones;
	}
	
	public NormalizarStrategy getNormalizar() {
		return norm;
	}

	public void normalizar(NormalizarStrategy pNorm) {
		norm = pNorm;
		norm.setLista(lista);
		lista = norm.normalizar();
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

	public HashMap<Integer, ArrayList<Tupla<Integer,Double>>> getLista()
	{
		return lista;
	}
}
	
