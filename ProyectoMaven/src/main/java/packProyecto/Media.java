package packProyecto;

import java.util.*;


public class Media extends NormalizarStrategy {

	
	@Override
	public HashMap<Integer, ArrayList<Tupla<Integer,Double>>> normalizar() {
		
		listaMedias = new HashMap<Integer,Double>();
		Set<Map.Entry<Integer,ArrayList<Tupla<Integer,Double>>>> mapaEntrada = listaValoraciones.entrySet();
		Iterator<Map.Entry<Integer, ArrayList<Tupla<Integer,Double>>>> itr = mapaEntrada.iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, ArrayList<Tupla<Integer,Double>>> entrada = itr.next();
			double aux = 0;
			for (int i = 0; i < entrada.getValue().size(); i++) {
				aux = aux + entrada.getValue().get(i).getY();
			}
			double media = (float) (aux/entrada.getValue().size());
			listaMedias.put(entrada.getKey(),media);
			ArrayList<Tupla<Integer,Double>> aux2 = new ArrayList<Tupla<Integer,Double>>();
			for (int j = 0; j < entrada.getValue().size(); j++) {
				aux2.add(new Tupla<Integer, Double>(entrada.getValue().get(j).getX(), entrada.getValue().get(j).getY()-media));
			}
			listaValoraciones.put(entrada.getKey(), aux2);
		}
		
		return listaValoraciones;
		
	}
	

	@Override
	public double desnormalizar(int usu, double valor) {
		
		return valor+listaMedias.get(usu);
	}

}
