package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class LeerFicheroPeliculas extends LeerFichero{

	public LeerFicheroPeliculas(String pPath) {
		path = pPath;
	}
	
	public HashMap<Integer,String> leerFichero() {
		HashMap<Integer,String> lista = new HashMap<Integer, String>();
		try
		{
			String path = System.getProperty("user.dir")+super.path;
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String lectura = br.readLine();
			int idMayor = ListaPeliculas.getListaPeliculas().getIdMayor();
			while(lectura!=null)
			{	
				String[] str = lectura.split(";");
				String sr = str[1];
				int key = Integer.parseInt(str[0]);
				if(key>idMayor)
					idMayor=key;
				lista.put(key, sr);
				lectura=br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
}
