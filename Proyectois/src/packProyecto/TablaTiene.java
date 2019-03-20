package packProyecto;

public class TablaTiene {

	
	public TablaTiene()
	{
		generarTabla();
	}
	
	private void generarTabla()
	{
		crearTabla();
		introducirDatos();
	}
	private void crearTabla()
	{
		String comando = "create table tiene(idpeli int not null, nombretag varchar(300) not null, cuantos int not null, primary key(idpeli,nombretag), foreign key(idpeli) references peliculas(idpeli) on update cascade, foreign key(nombretag) references tag(nombretag) on update cascade);";
		BaseDatos.getBd().actualizar(comando);
	}
	private void introducirDatos()
	{

		String comando = "insert into tiene(idpeli,nombretag,cuantos) select idpeli, nombretag, count(nombretag) from tag_aux group by idpeli, nombretag;";
		BaseDatos.getBd().actualizar(comando);
		comando = "drop table tag_aux;";
		BaseDatos.getBd().actualizar(comando);

	}
}
