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
}
