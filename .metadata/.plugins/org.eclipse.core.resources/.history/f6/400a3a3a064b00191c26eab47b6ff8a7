package packProyecto;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TablaTag {
	
	public TablaTag()
	{
		generarTabla();
	}
	
	public void generarTabla()
	{
		crearTabla();
		introducirDatos();
	}
	private void crearTabla()
	{
		String comando = "create table tag(nombreTag varchar(300) not null, primary key(nombreTag));";
		BaseDatos.getBd().actualizar(comando);
		comando = "create table tag_aux(idpeli int, nombreTag varchar(300));";
		BaseDatos.getBd().actualizar(comando);
	}
	private void introducirDatos()
	{
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path+"/movie-tags.csv";
		String comando = "load data infile"+ "'"+path+"' ignore into table tag fields terminated by ';' enclosed by '\"' lines terminated by '\r\n' (@dummy, nombreTag);";
		BaseDatos.getBd().actualizar(comando);
		comando="load data infile '"+path+"' into table tag_aux fields terminated by ';' enclosed by '\"' lines terminated by '\r\n' (idpeli,nombretag);";
		BaseDatos.getBd().actualizar(comando);
	}
	
	public ArrayList<String> tablaArray()
	{
		try
		{
			String comando = "select * from tag;";
			ResultSet sql = BaseDatos.getBd().hacerConsulta(comando);
			ArrayList<String> resul = new ArrayList<String>();
			while(sql.next())
			{
				resul.add(sql.getString(1));
			}
			return resul;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	
			
	}
}
