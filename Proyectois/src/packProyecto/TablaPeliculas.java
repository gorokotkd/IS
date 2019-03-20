package packProyecto;

public class TablaPeliculas {
		
	private void generarTabla()
	{
		crearTabla();
		introducirDatos();
	}
	public TablaPeliculas() {
		generarTabla();
	}
	private void crearTabla()
	{
		String comando = "create table peliculas(idpeli int not null, nombre varchar(300), primary key(idpeli));";
		BaseDatos.getBd().actualizar(comando);
	}
	private void introducirDatos()
	{
<<<<<<< HEAD
		try {
			String path = System.getProperty("user.dir");
			path = path.replace("\\", "/");
			path = path+"/movie-titles.csv";
			String comando = "load data infile"+"'"+path+"' into table peliculas fields terminated by ';' enclosed by '\"' lines terminated by '\n' (idpeli, nombre);";
			st.executeUpdate(comando);
		}catch(Exception e) {
			e.printStackTrace();
		}
=======

		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		path = path+"/movie-titles.csv";
		String comando = "load data infile"+"'"+path+"' into table peliculas fields terminated by ';' enclosed by '\"' lines terminated by '\r\n' (idpeli, nombre);";
		BaseDatos.getBd().actualizar(comando);
		Peliculas.getPeliculas();
>>>>>>> 18db53e19c85521820aaff354e7ffdba5edbd763
	}
}
