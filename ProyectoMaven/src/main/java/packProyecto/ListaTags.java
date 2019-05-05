package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ListaTags extends LeerFichero {

	private  HashMap<String,Integer> lista;
	private static ListaTags mLista = new ListaTags();
	
	private ListaTags()
	{
		lista = new HashMap<String,Integer>();

	}
	
	public static ListaTags getListaTags()
	{
		return mLista;
	}
	
	public int tamano()
	{
		return lista.size();
	}
	
	public int getIdTag(String pTag)
	{
		return lista.get(pTag);
	}
	
	public ArrayList<String> keySet()
	{
		return new ArrayList<String>(lista.keySet());
	}

	public void leerFichero() {
		
		String path = System.getProperty("user.dir")+"/src/main/resources/movie-tags.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura = br.readLine();
			int i = 0;
			while(lectura != null)
			{
				String[] str = lectura.split(";");
				if(!lista.containsKey(str[1]))
				{
					lista.put(str[1], i);
					i++;
				}
						
				lectura = br.readLine();
				
				
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void leerFicheroTest() {
		
		String path = System.getProperty("user.dir")+"/src/main/resources/testTags.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura = br.readLine();
			int i = 0;
			while(lectura != null)
			{
				String[] str = lectura.split(";");
				if(!lista.containsKey(str[1]))
				{
					lista.put(str[1], i);
					i++;
				}
						
				lectura = br.readLine();
				
				
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> tagsDevolKeys() {
		ArrayList<String> keys = new ArrayList<String>(lista.keySet());
		return keys;
	}


}
