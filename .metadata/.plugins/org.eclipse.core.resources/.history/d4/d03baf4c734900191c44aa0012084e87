package packProyecto;

import java.sql.SQLException;
import java.sql.Statement;

public class TablaTiene {

private static TablaTiene mTabla;
	
	private TablaTiene()
	{
	}
	public static TablaTiene getTablaTiene()
	{
		if(mTabla==null)
			mTabla=new TablaTiene();
		return mTabla;
	}
	
	public void generarTabla(Statement st)
	{
		crearTabla(st);
		introducirDatos(st);
	}
	private void crearTabla(Statement st)
	{
		String comando = "create table tiene(idpeli int not null, nombretag varchar(300) not null, cuantos int not null, primary key(idpeli,nombretag), foreign key(idpeli) references peliculas(idpeli) on update cascade, foreign key(nombretag) references tag(nombretag) on update cascade);";
		try {
			st.executeUpdate(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void introducirDatos(Statement st)
	{
		try {
			String comando = "insert into tiene(idpeli,nombretag,cuantos) select idpeli, nombretag, count(nombretag) from tag_aux group by idpeli, nombretag;";
			st.executeUpdate(comando);
			comando = "drop table tag_aux;";
			st.executeUpdate(comando);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
