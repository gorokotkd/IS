package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TagsPorPeli implements LeerFichero{

	private HashMap<Integer,ArrayList<Tupla<String, Integer>>> lista;
	private static TagsPorPeli mTags;
	
	private TagsPorPeli()
	{
		lista=new HashMap<Integer,ArrayList<Tupla<String, Integer>>>();
	}
	
	public static TagsPorPeli getTagsPorPeli()
	{
		if(mTags==null)
			mTags=new TagsPorPeli();
		return mTags;
	
	}
	
	public void leerFichero(String pPath)
	{
		String path = System.getProperty("user.dir")+pPath;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura = " ";
			int cont = 0;
			ArrayList<Tupla<String, Integer>> aux = new ArrayList<Tupla<String, Integer>>();
			lectura = br.readLine();
			String[] str = lectura.split(";");
			String tagAct = str[1];
			int idAct = Integer.parseInt(str[0]);
			while(lectura!=null)
			{
				if(Integer.parseInt(str[0])==idAct)
				{
					if(tagAct.equals(str[1]))
						cont++;
					else
					{
						aux.add(new Tupla(tagAct,cont));
						tagAct=str[1];
						cont = 1;
					}
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
					else
					{
						aux.add(new Tupla(str[1],cont));
						lista.put(Integer.parseInt(str[0]), aux);
					}
				}
				else
				{
					aux.add(new Tupla(tagAct,cont));
					cont=1;
					lista.put(idAct, aux);
					aux = new ArrayList<Tupla<String, Integer>>();
					tagAct=str[1];
					idAct=Integer.parseInt(str[0]);
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
					else
					{
						aux.add(new Tupla(str[1],cont));
						lista.put(Integer.parseInt(str[0]), aux);
					}
				}
			}
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		/**
		 * Leo aqui la lista auxiliar de tags para poder pasarle el path del fichero a leer.
		 */
		ListaTags.getListaTags().leerFichero(pPath);
	}
	
	public ArrayList<Tupla<String,Integer>> get(int i)
	{
		return lista.get(i);
	}
	
	public ArrayList<Integer> tagsDevolKeys() {
		return new ArrayList<Integer>(lista.keySet()); 
	}
	
	public ArrayList<Tupla<String, Integer>> getTagsPorId(Integer pId) {
		return lista.get(pId);
	}
	
	/**Metodos Para Los JUnits
	 * 
	 * 
	 * 
	 * */
	
	public void eliminar()
	{
		lista.clear();
		//modeloProductos.clear();
	}
	
	public void anadirEntrada(int key, ArrayList<Tupla<String,Integer>> entrada)
	{
		if(!lista.containsKey(key))
			lista.put(key, entrada);
}
	public boolean estoyVacia() {
		return lista.isEmpty();
	}
	public void imprimirlista() {
		Iterator<Integer> itr = tagsDevolKeys().iterator();
		while(itr.hasNext())
		{
			int i = itr.next();
			Iterator<Tupla<String,Integer>> itr2 = lista.get(i).iterator();
			while(itr2.hasNext())
			{
				Tupla<String,Integer> tupla = itr2.next();
				System.out.println(i+","+tupla.getX()+","+tupla.getY());

			}
			System.out.println(" ");
		}
	}
}
