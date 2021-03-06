package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
			String path = System.getProperty("user.dir")+"/movie-ratings.csv";
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
	
	public void normalizar() { //normalización de las valoraciones
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
	
	public ArrayList<Tupla<Integer,Double>> desnormalizar(Integer pUsuario) { //desnormalización de las valoraciones
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