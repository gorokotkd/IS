package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

public class ListaPeliculas implements LeerFichero{
	
	private HashMap<Integer,String> lista; //idPeli + NombrePelicula
	private ProductosSimilitud productosSimilitud;
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
	
	public ProductosSimilitud getProductosSimilitud() {
		return productosSimilitud;
	}
	
	public HashMap<Integer, String> getLista(){
		return lista;
	}
	
	public void leerFichero(String pPath) {
		lista = new HashMap<Integer, String>();
			try
			{
				String path = System.getProperty("user.dir")+pPath;
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String lectura = br.readLine();
				while(lectura!=null)
				{	
					String[] str = lectura.split(";");
					String sr = str[1];
					int key = Integer.parseInt(str[0]);
					lista.put(key, sr);
					lectura=br.readLine();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			cualEsLaIdMayor();
	}

	private Iterator<Map.Entry<Integer, String>> getIterator(){
		Set<Map.Entry<Integer,String>> mapaEntrada = lista.entrySet();
		return mapaEntrada.iterator();
	}
	
	private void cualEsLaIdMayor()
	{
		ArrayList<Integer> keys = new ArrayList<Integer>(lista.keySet());
		Iterator<Integer> itr = keys.iterator();
		int aux = 0;
		
		while(itr.hasNext())
		{
			int key = itr.next();
			if(key>aux)
				aux=key;
		}
		idMayor=aux;
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
			int aux = entrada.getKey();
			hashAux = getColeccionSimilitudes(aux);
			if (!hashAux.isEmpty()) {
				productosSimilitud.anadir(entrada.getKey(), hashAux);
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
	
	
	
	public String idPeliAString(int pId)
	{
		return lista.get(pId);
	}
}
