package packProyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ZScore extends NormalizarStrategy {
	
	private HashMap<Integer,Double> listaDesviaciones;

	public ZScore() {
		super();
		listaDesviaciones = new HashMap<Integer,Double>();
	}

	@Override
	public HashMap<Integer, ArrayList<Tupla<Integer, Double>>> normalizar() {
		Set<Map.Entry<Integer,ArrayList<Tupla<Integer,Double>>>> mapaEntrada = listaValoraciones.entrySet();
		Iterator<Map.Entry<Integer, ArrayList<Tupla<Integer,Double>>>> itr = mapaEntrada.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry<Integer, ArrayList<Tupla<Integer,Double>>> entrada = itr.next();
			double media = mediaVector(entrada.getValue());
			listaMedias.put(entrada.getKey(),media);
			double desvTipica = desvTipica(entrada.getValue(), media);
			
			ArrayList<Tupla<Integer,Double>> aux = new ArrayList<Tupla<Integer,Double>>();
			for (int i = 0; i < entrada.getValue().size(); i++) {
				aux.add(new Tupla<Integer, Double>(entrada.getValue().get(i).getX(), (entrada.getValue().get(i).getY()-media)/desvTipica));
			}
			listaValoraciones.put(entrada.getKey(), aux);
		}
		return listaValoraciones;
	}

	@Override
	public double desnormalizar(int usu, double valor) {
		double media = listaMedias.get(usu);
		double desv = listaDesviaciones.get(usu);
		return valor*desv + media;
	}

	private double mediaVector(ArrayList<Tupla<Integer,Double>> list)
	{
		double resul = 0.0;
		for(int i = 0; i<list.size();i++)
			resul = resul+list.get(i).getY();
		return resul/list.size();
	}
	
	private double desvTipica(ArrayList<Tupla<Integer,Double>> list,double media)
	{
		double resul = 0.0;
		for(int i=0; i<list.size();i++)
			resul = resul + Math.pow(list.get(i).getY()-media, 2);
		return Math.sqrt(resul/list.size());
		
	}
}
