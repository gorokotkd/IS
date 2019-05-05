package packProyecto;

import java.util.*;


public class Media extends NormalizarStrategy {

	
	@Override
	public HashMap<Integer, ArrayList<Tupla<Integer,Double>>> normalizar() {
		
		/*HashMap<Integer, ArrayList<Tupla<Integer, Double>>> matrizNormalizada = new HashMap<Integer, ArrayList<Tupla<Integer, Double>>>();
		ArrayList<Integer> listaUsus = ListaUsuarios.getListaUsuarios().todosLosUsus();
		Iterator<Integer> itr = listaUsus.iterator();		
		HashMap<Integer, ArrayList<Double>> valoraciones = generarListaValoraciones();
		
		while(itr.hasNext())
		{
			int usuAct = itr.next();
			if(valoraciones.get(usuAct)==null)
				System.out.println("jaja");
			double mediaUsu = calcularMediaDeArray(valoraciones.get(usuAct));
			listaMedias.put(usuAct, mediaUsu);
			
			Iterator<Tupla<Integer,Double>> itr2 = listaValoraciones.get(usuAct).iterator();
			
			ArrayList<Tupla<Integer,Double>> listaAux = new ArrayList<Tupla<Integer,Double>>();
			while(itr2.hasNext())
			{
				Tupla<Integer,Double> laTupla = itr2.next();
				listaAux.add(new Tupla(laTupla.getX(),laTupla.getY()-mediaUsu));
			}
			matrizNormalizada.put(usuAct, listaAux);
		}
		
		return matrizNormalizada;*/
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
	
	private HashMap<Integer, ArrayList<Double>> generarListaValoraciones()
	{
		HashMap<Integer, ArrayList<Double>> valoraciones = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Integer> listaUsus = ListaUsuarios.getListaUsuarios().todosLosUsus();
		Iterator<Integer> itr = listaUsus.iterator();
		
		while(itr.hasNext())
		{
			int usuAct = itr.next();
			ArrayList<Tupla<Integer,Double>> listaValoraciones = ListaRatings.getListaRatings().getRatingsPorId(usuAct);
			if(listaValoraciones != null)
			{
				Iterator<Tupla<Integer,Double>> itr2 = listaValoraciones.iterator();
				ArrayList<Double> listaAux = new ArrayList<Double>();
				while(itr2.hasNext())
					listaAux.add(itr2.next().getY());
				valoraciones.put(usuAct, listaAux);
			}
		
		}
		
		return valoraciones;
		
	}
	
	private Double calcularMediaDeArray(ArrayList<Double> pV)
	{
		Double sum=0.0;
		for(int i=0;i<pV.size();i++)
			sum = sum+pV.get(i);
		return sum/pV.size();
	}
	

	@Override
	public double desnormalizar(int usu, double valor) {
		
		return valor+listaMedias.get(usu);
	}

}
