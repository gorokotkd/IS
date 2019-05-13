package packProyecto;

import java.util.*;

public class FiltradoProductos extends FiltradoStrategy {
	
	private boolean normalizado;

	
	public FiltradoProductos(SimilitudStrategy pSimil) {
		// TODO Auto-generated constructor stub
		this.similitud = pSimil;
		ListaPeliculas.getListaPeliculas().inicializarMatrizSimilitudes();
	}
	
	
	


	@Override
	public HashMap<String, Double> recomendarNPeliculas(int idUsu) {
		
		HashMap<String, Double> rdo = new HashMap<String, Double>();
		HashMap<Integer, Double> aux = peliculasIdoneasParaElUsuario(idUsu);
		aux = super.sortByValues(aux);
		aux = super.soloLasNPrimeras(10, aux);
		Set<Map.Entry<Integer ,Double>> mapaEntrada = aux.entrySet();
		Iterator<Map.Entry<Integer ,Double>> itr = mapaEntrada.iterator();
		aux = super.sortByValues(aux);

		while (itr.hasNext()) {
			Map.Entry<Integer, Double> entrada = itr.next();
			rdo.put(ListaPeliculas.getListaPeliculas().idPeliAString(entrada.getKey()), entrada.getValue());
		}
		return rdo;
		
	}
	
	public HashMap<Integer,Double> peliculasIdoneasParaElUsuario(int idUsu){
		
		HashMap<Integer,Double> aux = new HashMap<Integer, Double>();
		HashMap<Integer, String> listaPeliculas = ListaPeliculas.getListaPeliculas().getLista();
		Set<Map.Entry<Integer,String>> mapaEntrada = listaPeliculas.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		Double idoneidad = 0.0;
		while (itr.hasNext()) {
			Map.Entry<Integer, String> entrada = itr.next();
			idoneidad = calcularIdoneidad(idUsu, entrada.getKey());
			if (idoneidad != 0.0) {
				aux.put(entrada.getKey(),idoneidad);
			}
		}
		return aux;
	}
	
	public void cambiarEstadoNormalizar(boolean norm)
	{
		normalizado = norm;
	}
	
	public Double calcularIdoneidad(int pUsuario, int pProducto) {		
		if (ListaRatings.getListaRatings().haValoradoLaPelicula(pUsuario, pProducto)) {
			return 0.0;
		}else {
		Double rdo = calcularFormula(ListaPeliculas.getListaPeliculas().getProductosSimilitud().getSimilitudesPorId(pProducto), pUsuario, pProducto);
		if(normalizado)
			return ListaRatings.getListaRatings().getNormalizar().desnormalizar(pUsuario, rdo);
		else
			return rdo;
		}
	}
	
	private Double calcularFormula(HashMap<Integer,Double> pSimilares, int pUsuario, int pProducto) {
		/**
		 * A pesar de que en el Filtrado por persona tenemos dos métodos para calcular el denominador y el numerador, aqui 
		 * lo hemos juntado ya que sino se recorre el mismo vector 2 veces más.
		 */
		if (pSimilares==null) {
			return 0.0;
		}else {
			pSimilares = super.sortByValues(pSimilares); 
			pSimilares = super.soloLasNPrimeras(20, pSimilares);
			Set<Map.Entry<Integer,Double>> mapaEntrada = pSimilares.entrySet();
			Iterator<Map.Entry<Integer, Double>> itr = mapaEntrada.iterator();
			Double nota = 0.0;
			Double sumaNumerador = 0.0;
			Double sumaDenominador = 0.0;
			Double simil = 0.0;
			while(itr.hasNext()) {
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
