package packProyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Ratings {
	
	private HashMap<Integer, ArrayList<PeliRating>> lista;
	private static Ratings mRating;
	
	private Ratings()
	{
		lista = new HashMap<Integer, ArrayList<PeliRating>>();
		try
		{
			int i = 1;
			String sql="select max(idusu) from resena;";
			ResultSet consulta = BaseDatos.getBd().hacerConsulta(sql);
			int max;
			if(consulta.next())
				max = Integer.parseInt(consulta.getString("max(idusu)"));
			else 
				max = 0;
			System.out.println("Empieza lo bueno...\n");
			while(i<max+1)
			{
				//lista.put(i, new ArrayList<PeliRating>());
				sql = "select idpeli,nota from resena where idusu="+i+";";
				consulta = BaseDatos.getBd().hacerConsulta(sql);
				ArrayList<PeliRating> aux = new ArrayList<PeliRating>();
				while(consulta.next())
				{
					aux.add(new PeliRating(Integer.parseInt(consulta.getString("idpeli")),Double.parseDouble(consulta.getString("nota"))));
				}
				lista.put(i, aux);
				i++;
			}
			System.out.println("Terminado");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Ratings getRatings()
	{
		if(mRating==null)
			mRating = new Ratings();
		return mRating;
	}
	
	public void imprimir(int i)
	{
		System.out.println(i+"-->"+lista.get(i));
		Iterator<PeliRating> itr = lista.get(i).iterator();
		while(itr.hasNext())
		{
			itr.next().imprimir();
		}
	}
}