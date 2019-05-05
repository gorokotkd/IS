package packProyecto;

import java.util.*;
public abstract class NormalizarStrategy {

	HashMap<Integer,Double> listaMedias;
	HashMap<Integer, ArrayList<Tupla<Integer,Double>>> listaValoraciones;
	

	public abstract HashMap<Integer, ArrayList<Tupla<Integer,Double>>> normalizar();
	public abstract double desnormalizar(int usu, double valor);
	
	public NormalizarStrategy()
	{
		
	}
	
	public void setLista(HashMap<Integer, ArrayList<Tupla<Integer,Double>>> pValoraciones) {
		listaValoraciones = pValoraciones;
	}
}
