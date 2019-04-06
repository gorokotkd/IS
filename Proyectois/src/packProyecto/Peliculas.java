package packProyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Peliculas {

	private HashMap<Integer,String> lista; //idPeli + NombrePelicula
	private HashMap<Integer,HashMap<Integer,Double>> similiProductos; //idProducto + HashMap<idProducto + similitudes>
	
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
	
	public Double calcularIdoneidad(int pUsuario, int pProducto) {
		this.similiProductos.get(key)
		
	}
	
	public ArrayList<Double> obtenerNProductos(int pProducto){
		HashMap<Integer,Double> aux = this.similiProductos.get(pProducto);
		Collection<Double> values = aux.values();
		ArrayList<Double> lista = new ArrayList<Double>(values);
		return
	}
	
	public ArrayList<String> ordenarHash(){
		ArrayList<String> arrayHash = this.hash2Array();
		ArrayList<String> ordenado = this.mergeSort(arrayHash);
		return ordenado;
	}
	
	private ArrayList<Double> hash2Array(){
		HashMap<Integer,Double> aux = this.similiProductos.get(pProducto);
		ArrayList<Double> lista = new ArrayList<Double>(aux.values());
		return lista;
	}
	
	private ArrayList<String> mergeSort(ArrayList<String> entero){
		ArrayList<String> izq = new ArrayList<String>();
		ArrayList<String> der = new ArrayList<String>();
		int ind;
		if(entero.size() == 1) {
			return entero;
		}else {
			ind = entero.size()/2;
			for(int i=0; i<ind; i++) {
				izq.add(entero.get(i));
			}
			for(int i=ind;i<entero.size();i++) {
				der.add(entero.get(i));
			}
			izq = mergeSort(izq);
			der = mergeSort(der);
			mezcla(izq,der,entero);
		}
		return entero;
	}
	
	private void mezcla(ArrayList<String> izq,ArrayList<String> der, ArrayList<String> entero) {
		int izqIndex = 0;
		int derIndex = 0;
		int enteroIndex = 0;
		while(izqIndex<izq.size() && derIndex<der.size()) {
			if( (izq.get(izqIndex).compareTo(der.get(derIndex))) <0){
				entero.set(enteroIndex, izq.get(izqIndex));
				izqIndex++;
			}else{
				entero.set(enteroIndex, der.get(derIndex));
				derIndex++;
			}
			enteroIndex++;
		} 
		ArrayList<String> resto;
		int restoIndex = 0;
		if(izqIndex >= izq.size()) {
			resto = der;
			restoIndex = derIndex;
		}else {
			resto = izq;
			restoIndex = izqIndex;
		}
		
		for(int i=restoIndex; i<resto.size();i++) {
			entero.set(enteroIndex, resto.get(i));
			enteroIndex++;
		}
	}
	
	
}
