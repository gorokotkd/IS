package packProyecto;

import java.sql.SQLException;
import java.sql.Statement;

public class TablaTag {

private static TablaTag mTabla;
	
	private TablaTag()
	{
	}
	public static TablaTag getTablaTag()
	{
		if(mTabla==null)
			mTabla=new TablaTag();
		return mTabla;
	}
	
	public void generarTabla(Statement st)
	{
		crearTabla(st);
		introducirDatos(st);
	}
	private void crearTabla(Statement st)
	{
		String comando = "create table tag(nombreTag varchar(300) not null, primary key(nombreTag));";
		try {
			st.executeUpdate(comando);
			comando = "create table tag_aux(idpeli int, nombreTag varchar(300));";
			st.executeUpdate(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void introducirDatos(Statement st)
	{
		try {
			String comando = "load data infile 'D:/IngenierIa_Software/Proyecto/movie-tags.csv' ignore into table tag fields terminated by ';' enclosed by '\"' lines terminated by '\r\n' (@dummy, nombreTag);";
			st.executeUpdate(comando);
			comando="load data infile 'D:/IngenierIa_Software/Proyecto/movie-tags.csv' into table tag_aux fields terminated by ';' enclosed by '\"' lines terminated by '\r\n' (idpeli,nombretag);";
			st.executeUpdate(comando);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
