package packProyecto;

import java.util.*;
public abstract class NormalizarStrategy {

	HashMap<Integer,Double> listaMedias;
	HashMap<Integer, ArrayList<Tupla<Integer,Double>>> listaValoraciones;
	

	public abstract HashMap<Integer, ArrayList<Tupla<Integer,Double>>> normalizar();
	public abstract double desnormalizar(int usu, double valor);
	
	public NormalizarStrategy()
	{
		listaMedias = new HashMap<Integer,Double>();
	}
	
	public void setLista(HashMap<Integer, ArrayList<Tupla<Integer,Double>>> pValoraciones) {
		listaValoraciones = pValoraciones;
	}
	
	public double mediaVector(ArrayList<Tupla<Integer,Double>> list)
	{
		double resul = 0.0;
		for(int i = 0; i<list.size();i++)
			resul = resul+list.get(i).getY();
		return resul/list.size();
	}
}
