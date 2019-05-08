package packProyecto;

import java.util.*;

public class FiltradoProductos extends FiltradoStrategy {
	
	private boolean normalizado;

	
	public FiltradoProductos(SimilitudStrategy pSimil) {
		// TODO Auto-generated constructor stub
		this.similitud = pSimil;
		ListaPeliculas.getListaPeliculas().inicializar();
	}
	
	
	public Double recomendarPelicula(int pUsu, int pProducto) {
		
		Double rdo = calcularIdoneidad(pUsu, pProducto);
		
		return rdo;
	}


	@Override
	public HashMap<String, Double> recomendarNPeliculas(int idUsu) {
		
		HashMap<String, Double> rdo = new HashMap<String, Double>();
		HashMap<Integer, Double> aux = peliculasIdoneasParaElUsuario(idUsu);
		super.sortByValues(aux);
		Set<Map.Entry<Integer ,Double>> mapaEntrada = aux.entrySet();
		Iterator<Map.Entry<Integer ,Double>> itr = mapaEntrada.iterator();
		int i=0;
		
		while (itr.hasNext() && i<10) {
			Map.Entry<Integer, Double> entrada = itr.next();
			rdo.put(ListaPeliculas.getListaPeliculas().idPeliAString(entrada.getKey()), entrada.getValue());
			i++;
		}
		return rdo;
		
	}
	
	private HashMap<Integer,Double> peliculasIdoneasParaElUsuario(int idUsu){
		
		HashMap<Integer,Double> aux = new HashMap<Integer, Double>();
		HashMap<Integer, String> listaPeliculas = ListaPeliculas.getListaPeliculas().getLista();
		Set<Map.Entry<Integer,String>> mapaEntrada = listaPeliculas.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		
		while (itr.hasNext()) {
			Map.Entry<Integer, String> entrada = itr.next();
			aux.put(entrada.getKey(), calcularIdoneidad(idUsu, entrada.getKey()));
		}
		return aux;
	}
	
	public void seHaNormalizado(boolean norm)
	{
		normalizado = norm;
		
	}
	
	public Double calcularIdoneidad(int pUsuario, int pProducto) {		
		Double rdo = calcularFormula(ListaPeliculas.getListaPeliculas().getProductosSimilitud().getSimilitudesPorId(pProducto), pUsuario, pProducto);
		if(normalizado)
			return ListaRatings.getListaRatings().getNormalizar().desnormalizar(pUsuario, rdo);
		else
			return rdo;
	}
	
	private Double calcularFormula(HashMap<Integer,Double> pSimilares, int pUsuario, int pProducto) {
		if (pSimilares==null) {
			return 0.0;
		}else {
			pSimilares = super.sortByValues(pSimilares); 
			Set<Map.Entry<Integer,Double>> mapaEntrada = pSimilares.entrySet();
			Iterator<Map.Entry<Integer, Double>> itr = mapaEntrada.iterator();
			Double nota = 0.0;
			Double sumaNumerador = 0.0;
			Double sumaDenominador = 0.0;
			Double simil = 0.0;
			int i = 0;
			while(itr.hasNext() && i< 20) {
				Map.Entry<Integer, Double> entrada = itr.next();
				nota = ListaRatings.getListaRatings().obtenerNota(pUsuario, entrada.getKey());
				if (nota<0) {
					nota = 0.0;
				}
				simil = ListaPeliculas.getListaPeliculas().getProductosSimilitud().getSimilitud(pProducto, entrada.getKey());
				sumaNumerador = sumaNumerador +(nota*simil);
				sumaDenominador = sumaDenominador + simil;
			}
			return sumaNumerador/sumaDenominador;
		}
	}
}
