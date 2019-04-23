package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

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

}
