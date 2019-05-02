package packProyecto;

import java.util.*;
public abstract class NormalizarStrategy {

	protected HashMap<Integer, ArrayList<Tupla<Integer,Double>>> listaValoraciones;
	

	public abstract HashMap<Integer, ArrayList<Tupla<Integer,Double>>> normalizar();
	public abstract double desnormalizar(int usu, double valor);
	
	public NormalizarStrategy(HashMap<Integer, ArrayList<Tupla<Integer,Double>>> pValoraciones)
	{
		listaValoraciones = pValoraciones;
		//ulul
	}

}
