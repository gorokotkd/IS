package packProyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Peliculas {

	private HashMap<Integer,String> lista;
	private static Peliculas mPeliculas;
	
	private Peliculas()
	{
		try
		{
			lista = new HashMap<Integer, String>();
			String sql = "select * from peliculas;";
			ResultSet consulta = BaseDatos.getBd().hacerConsulta(sql);
			while(consulta.next())
			{
				lista.put(consulta.getInt(1), consulta.getString(2));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Peliculas getPeliculas()
	{
		if(mPeliculas==null)
			mPeliculas = new Peliculas();
		return mPeliculas;
	}

	public Set<Entry<Integer,String>> entrySet() {
		return lista.entrySet();
	}	
}
