package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class ListaTags implements LeerFichero{

	private  HashMap<String,Integer> lista;
	private static ListaTags mLista;
	
	private ListaTags()
	{
		lista = new HashMap<String,Integer>();

	}
	
	public static ListaTags getListaTags()
	{
		if(mLista==null)
			mLista = new ListaTags();
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

	public void leerFichero(String pPath) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(pPath)));
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
	
	public void clear() {
		lista.clear();
	}


}
