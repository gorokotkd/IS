package packProyecto;

import java.sql.SQLException;
import java.sql.Statement;

public class TablaResena {

	private static TablaResena mTabla;
	
	private TablaResena()
	{
	}
	public static TablaResena getTablaResena()
	{
		if(mTabla==null)
			mTabla=new TablaResena();
		return mTabla;
	}
	
	public void generarTabla(Statement st)
	{
		crearTabla(st);
		introducirDatos(st);
	}
	private void crearTabla(Statement st)
	{
		String comando = "create table resena(idusu int not null, idpeli int not null, nota dec(2,1), primary key(idusu,idpeli), foreign key(idpeli) references peliculas(idpeli) on update cascade);";
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
			path = path+"/movie-ratings.csv";
			String comando = "load data infile '"+path+"' into table resena fields terminated by ',' enclosed by '\"' lines terminated by '\n' (idusu, idpeli, nota);";
			st.executeUpdate(comando);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
