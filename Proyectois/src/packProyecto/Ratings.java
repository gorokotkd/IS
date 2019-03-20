package packProyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ratings {
	
	private HashMap<Integer, PeliRating[]> lista;
	private static Ratings mRating;
	
	private Ratings()
	{
		lista = new HashMap<Integer, PeliRating[]>();
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
			while(i<max+1)
			{
				sql = "select count(*) from resena where idusu="+i+";";
				consulta = BaseDatos.getBd().hacerConsulta(sql);
				int tam = 0;
				int j = 0;
				if(consulta.next())
					tam = Integer.parseInt(consulta.getString("count(*)"));
				PeliRating[] aux = new PeliRating[tam];
				sql = "select idpeli,nota from resena where idusu="+i+";";
				consulta = BaseDatos.getBd().hacerConsulta(sql);
				while(consulta.next())
				{
					aux[j]=new PeliRating(Integer.parseInt(consulta.getString("idpeli")),Double.parseDouble(consulta.getString("nota")));
					j++;
				}
				lista.put(i, aux);
				i++;
			}
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
<<<<<<< HEAD
	
	public void imprimir(int i)
	{
		//System.out.println(i+"-->"+lista.get(i));
		//lista.get(i)[8].imprimir();

	}
	
=======
>>>>>>> 1398a65c048d45ccdef2be145f0406416fe291a5
	public ArrayList<Integer> devolKeys() {
		return new ArrayList<>(lista.keySet()); 
	}
	
	public PeliRating[] getRatingsPorId(Integer pId) {
		return lista.get(pId);
	}
}