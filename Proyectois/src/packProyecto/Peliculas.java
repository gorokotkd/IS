package packProyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Peliculas {

	private HashMap<Integer,String> lista;
	
	public Peliculas()
	{
		lista = new HashMap<Integer,String>();
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
				lista.put(key, sr);
				lectura=br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public Set<Entry<Integer,String>> entrySet() {
		return lista.entrySet();
	}	
<<<<<<< HEAD
=======
	
	public void initMatrizSimilitudes() {
		Set<Map.Entry<Integer,String>> mapaEntrada = lista.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		ArrayList<Double> aux1;
		ArrayList<Double> aux2;
		BaseDatos bd = BaseDatos.getBd();
		Ratings ratings = bd.getRatings();
		HashMap<Integer,Double> hashAux = new HashMap<Integer, Double>();
		Double simil = 0.0;
		while (itr.hasNext()) { // recorremos el HashMap de idPeli + NombrePelicula
			Map.Entry<Integer, String> entrada = itr.next();
			Set<Map.Entry<Integer,String>> mapaEntrada2 = lista.entrySet();
			Iterator<Map.Entry<Integer, String>> itr2 = mapaEntrada.iterator();
			hashAux = new HashMap<Integer, Double>();
			while (itr.hasNext()) { //recorremos el resto de peliculas, para rellenar la tabla de similiProductos
				Map.Entry<Integer, String> entrada2 = itr.next();
				aux1 = ratings.getValoraciones(entrada.getKey());
				aux2 = ratings.getValoraciones(entrada2.getKey());
				if (entrada.getKey()!=entrada2.getKey()) {
					 simil = BaseDatos.getBd().getSimilitud().calcularSimilitud(aux1,aux2);
					 hashAux.put(entrada2.getKey(), simil);
				}
			}
			if (!hashAux.isEmpty()) {
				similiProductos.put(entrada.getKey(), hashAux);
			}
		}
		System.out.println("Se ha creado la matriz de similitud de productos");
	}
	
	public int size()
	{
		return lista.size();
	}
	
	public void eliminar()
	{
		lista.clear();
	}
>>>>>>> parent of 5179f82... Todo Bien solo quedan JUnits
}
