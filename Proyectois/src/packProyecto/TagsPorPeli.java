package packProyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class TagsPorPeli {

	private HashMap<Integer,ArrayList<Tupla<String, Integer>>> lista;
	private double[][] modeloProductos;
	private double[][] modeladoPersona;
	
	public TagsPorPeli()
	{
		lista = new HashMap<Integer,ArrayList<Tupla<String, Integer>>>();
		modeloProductos = new double[BaseDatos.getBd().idMayorPelicula()+1][ListaTags.getListaTags().tamano()];
		modeladoPersona = new double[ListaUsuarios.getListaUsuarios().size()][ListaTags.getListaTags().tamano()];
	}
	
	public void leerFichero()
	{
		String path = System.getProperty("user.dir")+"/testTags.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura = " ";
			int cont = 0;
			ArrayList<Tupla<String, Integer>> aux = new ArrayList<Tupla<String, Integer>>();
			lectura = br.readLine();
			String[] str = lectura.split(";");
			String tagAct = str[1];
			int idAct = Integer.parseInt(str[0]);
			while(lectura!=null)
			{
				if(Integer.parseInt(str[0])==idAct)
				{
					if(tagAct.equals(str[1]))
						cont++;
					else
					{
						aux.add(new Tupla(tagAct,cont));
						tagAct=str[1];
						cont = 1;
					}
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
				}
				else
				{
					if(tagAct.equals(str[1]))
						cont++;
					aux.add(new Tupla(tagAct,cont));
					tagAct=str[1];
					cont = 1;
					lista.put(idAct, aux);
					aux = new ArrayList<Tupla<String, Integer>>();
					idAct=Integer.parseInt(str[0]);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Integer> tagsDevolKeys() {
		return new ArrayList<>(lista.keySet()); 
	}
	
	public ArrayList<Tupla<String, Integer>> getTagsPorId(Integer pId) {
		return lista.get(pId);
	}
	
	public void modeloPersona()
	{
		int usu = 1;
		while(usu<ListaUsuarios.getListaUsuarios().size()-1)
		{
			ArrayList<Tupla<Integer,Double>> ratingsDelUsu=BaseDatos.getBd().getRatingsPorId(usu);
			ArrayList<Integer> pelisPorEncimaDelUmbral = calcularPelisPorEncimaDelUmbral(ratingsDelUsu,3.5);
			if(pelisPorEncimaDelUmbral!=null)
			{
				Iterator<Integer> itr = pelisPorEncimaDelUmbral.iterator();
				
				while(itr.hasNext())
				{
					int peliAux = itr.next();
					Iterator<String> itr2 = ListaTags.getListaTags().keySet().iterator();
					while(itr2.hasNext())
					{
						String tag = itr2.next();
						modeladoPersona[usu][ListaTags.getListaTags().getIdTag(tag)] = modeladoPersona[usu][ListaTags.getListaTags().getIdTag(tag)] + getTfidfDe(peliAux, tag);
					}
				}
			}
			usu++;
		}
		
	}

	
	private ArrayList<Double> getFilaPersona(int idUsu)
	{
		ArrayList<Double> resul = new ArrayList<Double>();
		
		for(int i=0;i<ListaTags.getListaTags().tamano()-1;i++)
			resul.add(modeladoPersona[idUsu][i]);
		return resul;
	}
	
	private ArrayList<Double> getFilaProducto(int idPeli)
	{
		ArrayList<Double> resul = new ArrayList<Double>();
		
		for(int i=0;i<ListaTags.getListaTags().tamano()-1;i++)
			resul.add(modeloProductos[idPeli][i]);
		return resul;
	}
	public double getIdoneidad(int idUsu, int idPeli)
	{
		Similitud sim = new Similitud();
		
		ArrayList<Double> vectorPersona = getFilaPersona(idUsu);
		ArrayList<Double> vectorPelicula = getFilaProducto(idPeli);
		return sim.correlacionDePearson(vectorPersona, vectorPelicula);
	}
	
	private double getTfidfDe(int pIdPeli, String pTag)
	{
		return modeloProductos[pIdPeli][ListaTags.getListaTags().getIdTag(pTag)];
	}
	
	private ArrayList<Integer> calcularPelisPorEncimaDelUmbral(ArrayList<Tupla<Integer,Double>> list, Double umbral)
	{
		ArrayList<Integer> resul = new ArrayList<>();
		if(list!=null)
		{
			Iterator<Tupla<Integer,Double>> itr = list.iterator();
			while(itr.hasNext())
			{
				Tupla<Integer,Double> aux = itr.next();
				if(aux.getY()>=umbral)
					resul.add(aux.getX());
			}
			return resul;
		}
		else
			return null;
	}

	
	public void generarModeladoDeProductos()
	{
		Iterator<Integer> itr = tagsDevolKeys().iterator();
		while(itr.hasNext())
		{
			int i = itr.next();
			Iterator<Tupla<String,Integer>> itr2 = lista.get(i).iterator();
			while(itr2.hasNext())
			{
				Tupla<String,Integer> tupla = itr2.next();
				double resul = tfidf(i, tupla.getX());
				int idTag = ListaTags.getListaTags().getIdTag(tupla.getX());
				modeloProductos[i][idTag] = resul;
			}
			modeloProductos[i] = vectorUnitario(modeloProductos[i]);
			
		}
	
	}
	
	private double[] vectorUnitario(double[] list)
	{

		double modulo = moduloVector(list);

		for(int i=0;i<list.length;i++)
		{
			double aux = list[i]/modulo;
			list[i]=aux;
		}
		return list;	
	}
	
	private double moduloVector(double[] list)
	{
		double resul = 0.0;
		for (int i=1;i<list.length;i++)
			resul = Math.sqrt(resul+Math.pow((list[i]),2.0));
		return resul;
	}
	
	private double tfidf(Integer pPeli, String pTag)
	{
		double tf = (double) cuantasVecesTieneTag(pPeli,pTag);
		double nt = (double) cuantasConEseTag(pTag);
	//	double n = (double) lista.size();
		double n = (double) BaseDatos.getBd().cuantasPelis();
		return tf*Math.log10(n/nt);
		
	}
	private int cuantasVecesTieneTag(Integer pPeli, String pTag)
	{
		boolean salir = false;
		int resul =0;
		ArrayList<Tupla<String,Integer>> aux = this.getTagsPorId(pPeli);
		Iterator<Tupla<String,Integer>> itr = aux.iterator();
		while(itr.hasNext()&&!salir)
		{
			Tupla<String,Integer> tupla = itr.next();
			if(tupla.getX().equals(pTag))
			{
				salir = true;
				resul = tupla.getY();
			}
		}
		return resul;
	}
	
	private int cuantasConEseTag(String tag)
	{
		
		int i = 0;
		int cont = 0;
		Iterator<Integer> itr2 = tagsDevolKeys().iterator();
		while(itr2.hasNext())
		{
			i=itr2.next();
			boolean salir = false;
			ArrayList<Tupla<String,Integer>> aux = this.getTagsPorId(i);
			Iterator<Tupla<String,Integer>> itr = aux.iterator();
			while(itr.hasNext()&&!salir)
			{
				Tupla<String,Integer> tupla = itr.next();
				if(tupla.getX().equals(tag))
				{
					salir = true;
					cont++;
				}
			}
		}
		return cont;
	}
	
	public void eliminar()
	{
		lista.clear();
		modeloProductos = new double[BaseDatos.getBd().idMayorPelicula()+1][ListaTags.getListaTags().tamano()];
		modeladoPersona = new double[ListaUsuarios.getListaUsuarios().size()][ListaTags.getListaTags().tamano()];
	}
	
	public void anadirEntrada(int key, ArrayList<Tupla<String,Integer>> entrada)
	{
		if(!lista.containsKey(key))
			lista.put(key, entrada);
	}
	public boolean estoyVacia() {
		return lista.isEmpty();
	}
	public void imprimirlista() {
		Iterator<Integer> itr = tagsDevolKeys().iterator();
		while(itr.hasNext())
		{
			int i = itr.next();
			Iterator<Tupla<String,Integer>> itr2 = lista.get(i).iterator();
			while(itr2.hasNext())
			{
				Tupla<String,Integer> tupla = itr2.next();
				System.out.println(i+","+tupla.getX()+","+tupla.getY());

			}
			System.out.println(" ");
		}
	}
	public void imprimirModeloProducto() {
		for (int x=0; x < modeloProductos.length; x++) {
			  for (int y=0; y < modeloProductos[x].length; y++) {
			    System.out.print ("|"+modeloProductos[x][y]+"|");
			  }
			  System.out.println("Fila " + x);
			}
	}
	public double[] getFilaModeloProductos(int pFila) {
		return this.modeloProductos[pFila];
	}
	
}