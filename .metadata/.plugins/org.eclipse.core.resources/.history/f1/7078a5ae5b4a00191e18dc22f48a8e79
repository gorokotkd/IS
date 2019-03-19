package packProyecto;

import java.sql.SQLException;
import java.sql.Statement;

public class TablaPeliculas {

	private static TablaPeliculas mTabla;
	
	private TablaPeliculas()
	{
	}
	public static TablaPeliculas getTablaPeliculas()
	{
		if(mTabla==null)
			mTabla=new TablaPeliculas();
		return mTabla;
	}
	
	public void generarTabla(Statement st)
	{
		crearTabla(st);
		introducirDatos(st);
	}
	private void crearTabla(Statement st)
	{
		String comando = "create table peliculas(idpeli int not null, nombre varchar(300), primary key(idpeli));";
		try {
			st.executeUpdate(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void introducirDatos(Statement st)
	{
		try {
			String path = System.getProperty("user.dir");
			path = path.replace("\\", "/");
			path = path+"/movie-titles.csv";
			String comando = "load data infile"+"'"+path+"' into table peliculas fields terminated by ';' enclosed by '\"' lines terminated by '\n' (idpeli, nombre);";
			st.executeUpdate(comando);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
