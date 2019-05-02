package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ListaValoracionesPorPeli implements LeerFichero {

	private HashMap<Integer, ArrayList<Double>> lista; //pelicula + lista de valoraciones
	
	public ListaValoracionesPorPeli() {
		leerFichero();
	}

	public void leerFichero() {
		try
		{
			String path = System.getProperty("user.dir")+"/movie-ratings.csv";
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura=" ";
			ArrayList<Double> aux = new ArrayList<Double>();
			lectura = br.readLine();
			String[] str = null;
			while(lectura!=null){
				str = lectura.split(",");
				int pelicula = Integer.parseInt(str[1]);
				Double puntuacion = Double.parseDouble(str[2]);
				if (!lista.containsKey(pelicula)) { //Contiene la pelicula
					aux.add(puntuacion);
					lista.put(pelicula,aux);
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
		ArrayList<Double> aux = lista.get(pKey);
		if (!(aux==null)) {
			aux.add(pPuntuacion);
			lista.put(pKey, aux);
		}
	}
	
	public ArrayList<Double> getValoracionesPorId(int pId){
		return lista.get(pId);
	}
}
