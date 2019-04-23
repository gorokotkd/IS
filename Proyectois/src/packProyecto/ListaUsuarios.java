package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ListaUsuarios {

	private ArrayList<Integer> lista;
	private static ListaUsuarios mLista = new ListaUsuarios();
	
	private ListaUsuarios()
	{
		lista = new ArrayList<>();
		
		try
		{
			String path = System.getProperty("user.dir")+"/testRatings.csv";
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String bfread;
			
			
			while((bfread = bf.readLine()) != null)
			{				
				String[] str = bfread.split(",");
				if(lista.isEmpty())
					lista.add(Integer.parseInt(str[0]));
				else if(!lista.contains(Integer.parseInt(str[0])))
					lista.add(Integer.parseInt(str[0]));
				
			}
			bf.close();
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static ListaUsuarios getListaUsuarios()
	{
		return mLista;
	}
	
	public boolean contains(int pUsu)
	{
		return lista.contains(pUsu);
	}
	
	public int size()
	{
		return lista.size();
	}
	
	
}
