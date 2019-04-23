package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ListaPeliculas implements LeerFichero {
	
	private HashMap<Integer,String> lista; //idPeli + NombrePelicula
	private int idMayor;
	private static ListaPeliculas mPelis;
	
	public void leerFichero() {
		try
		{
			String path = System.getProperty("user.dir")+"/src/main/resources/movie-titles.csv";
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String lectura = br.readLine();
			while(lectura!=null)
			{	
				String[] str = lectura.split(";");
				String sr = str[1];
				int key = Integer.parseInt(str[0]);
				if(key>idMayor)
					idMayor=key;
				lista.put(key, sr);
				lectura=br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	private ListaPeliculas()
	{
		lista = new HashMap<Integer,String>();
		leerFichero();
	}
	
	public static ListaPeliculas getListaPeliculas()
	{
		if(mPelis==null)
			mPelis=new ListaPeliculas();
		return mPelis;
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
	

}
