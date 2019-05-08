package packProyectoInterfaces;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaUsuarioPass {

	private HashMap<String,Integer> lista;
	private static ListaUsuarioPass mLista = new ListaUsuarioPass();
	
	private ListaUsuarioPass()
	{
		lista = new HashMap<String,Integer>();
		lista.put("Admin", 0);
		lista.put("Ander", 1);
		lista.put("Lekanda", 2);
	}
	
	public static ListaUsuarioPass getListaUsuarioPass()
	{
		return mLista;
	}
	
	public boolean contains(String arg0)
	{
		return lista.containsKey(arg0);
	}
	
	public Integer getValue(String arg0)	{
		return lista.get(arg0);
	}
	
	public void insertarNuevo(String arg0) {
		lista.put(arg0, nuevoId());
	}
	
	private Integer nuevoId() {
		Integer max=0;
		ArrayList<Integer> aux = new ArrayList<Integer>(lista.values());
		for (int i=0; i<aux.size(); i++) {
			if (aux.get(i)<max) {
				max=aux.get(i);
			}
		}
		return max;
	}
}