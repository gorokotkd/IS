package packProyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Peliculas {

	private HashMap<Integer,String> lista; //idPeli + NombrePelicula
	private Double[][] similiProductos; 
	
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
	
	public void initMatrizSimilitudes() {
		similiProductos = new Double[lista.size()][lista.size()];
		Set<Map.Entry<Integer,String>> mapaEntrada = lista.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		ArrayList<Double> aux1;
		ArrayList<Double> aux2;
		BaseDatos bd = BaseDatos.getBd();
		Ratings ratings = bd.getRatings();
		while (itr.hasNext()) { // recorremos el HashMap de idPeli + NombrePelicula
			Map.Entry<Integer, String> entrada = itr.next();
			Set<Map.Entry<Integer,String>> mapaEntrada2 = lista.entrySet();
			Iterator<Map.Entry<Integer, String>> itr2 = mapaEntrada.iterator();
			while (itr.hasNext()) { //recorremos el resto de peliculas, para rellenar la tabla de similiProductos
				Map.Entry<Integer, String> entrada2 = itr.next();
				aux1 = ratings.getValoraciones(entrada.getKey());
				aux2 = ratings.getValoraciones(entrada2.getKey());
				if (entrada.getKey()!=entrada2.getKey()) {
					similiProductos[entrada.getKey()][entrada2.getKey()] = BaseDatos.getBd().getSimilitud().calcularSimilitud(aux1,aux2);
				}
			}
		}
		System.out.println("Se ha creado la matriz de similitud de productos");
	}
}
