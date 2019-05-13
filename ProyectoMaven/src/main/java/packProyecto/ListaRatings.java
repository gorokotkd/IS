package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ListaRatings implements LeerFichero{

	private HashMap<Integer, ArrayList<Tupla<Integer,Double>>> lista; //usuario + lista(peliculas+nota)
	private ListaValoracionesPorPeli valoraciones;
	private NormalizarStrategy norm;
	private static ListaRatings mRatings;
	
	
	private ListaRatings()
	{
		lista = new HashMap<Integer,ArrayList<Tupla<Integer,Double>>>();
		valoraciones = new ListaValoracionesPorPeli();
		
		//PASOS PARA INICIALIZACION
		//1 - instanciar
		//2 - setFichero
		//3 - normalizar 
	}

	public void leerFichero(String pPath) {
	//	lista = new HashMap<Integer, ArrayList<Tupla<Integer,Double>>>();
		try
		{
			String path = System.getProperty("user.dir")+pPath;
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
				
				if(lectura == null){
					lista.put(usuAct, aux);
				}
				
			}
			
			/**
			 * Leo aqui la lista de usuarios y de valoraciones para poder pasarles el path del fichero a leer
			 * que pude ser el de pruebas o el normal.
			 */
			ListaUsuarios.getListaUsuarios().leerFichero(pPath);
			valoraciones.leerFichero(pPath);
		}
		catch (Exception e)
		{
			System.out.println("Se ha producido un error al leer el fichero de ratings");
			e.printStackTrace();
		}
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
	
	public double desnormalizar(int pUsu, double pValor)
	{
		return norm.desnormalizar(pUsu, pValor);
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

	/**
	 * METODOS INTERFACES
	 */
	
	public ArrayList<Integer> ratingsDevolKeys()
	{
		return new ArrayList<Integer>(lista.keySet());
	}
	
	public void anadir(int pUsu,int pPeli, double pnota) {
		ArrayList aux = lista.get(pUsu);
		aux.add(new Tupla(pPeli, pnota));
	}
}
	
