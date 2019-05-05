package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TagsPorPeli {

	private HashMap<Integer,ArrayList<Tupla<String, Integer>>> lista;
	private static TagsPorPeli mTags;
	private LeerFicheroTags fich;
	
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
	
	public void setFichero(String pPath)
	{
		fich = new LeerFicheroTags(pPath);
		lista = fich.leerFichero();
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
