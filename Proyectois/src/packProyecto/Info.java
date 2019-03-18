package packProyecto;

import java.util.HashMap;

public class Info {

	//ESTA CLASE ALMACENA LAS PELIS DE LAS QUE HA OPINADO UN USUARIO.
	private int usuario;
	private HashMap<Integer,Double> lista;
	
	public Info(int pUsu)
	{
		usuario = pUsu;
		lista = new HashMap<Integer,Double>();
	}
	
	public void anadir(int pPeli, double pNota)
	{
		lista.put(pPeli, pNota);
	}
	
	public double buscarNotaPeli(int pPeli)
	{
		return lista.get(pPeli);
	}
	
	public int getUsuario()
	{
		return usuario;
	}
}
