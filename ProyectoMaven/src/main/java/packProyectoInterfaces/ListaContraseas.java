package packProyectoInterfaces;

import java.util.HashMap;

public class ListaContraseas {

	private HashMap<String,String> lista;
	private static ListaContraseas mLista = new ListaContraseas();
	
	private ListaContraseas()
	{
		lista = new HashMap<String,String>();
		lista.put("Admin", "123456");
		lista.put("Ander", "456789");
		lista.put("Lekanda", "123789");
	}
	
	public static ListaContraseas getListaContraseas()
	{
		return mLista;
	}
	
	public boolean contains(String arg0)
	{
		return lista.containsKey(arg0);
	}
	
	public String getValue(String arg0)	{
		return lista.get(arg0);
	}
	
	public void insertarNuevo(String arg0,String arg1) {
		lista.put(arg0, arg1);
	}
}
