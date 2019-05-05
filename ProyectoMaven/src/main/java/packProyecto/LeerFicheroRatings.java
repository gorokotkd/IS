package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LeerFicheroRatings extends LeerFichero{

	public LeerFicheroRatings(String pPath) {
		super.path = pPath;
	}
	
	public HashMap<Integer, ArrayList<Tupla<Integer,Double>>> leerFichero() {
		HashMap<Integer, ArrayList<Tupla<Integer,Double>>> lista = new HashMap<Integer, ArrayList<Tupla<Integer,Double>>>();
		try
		{
			String path = System.getProperty("user.dir")+super.path;
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura=" ";
			ArrayList<Tupla<Integer,Double>> aux = new ArrayList<Tupla<Integer,Double>>();
			lectura = br.readLine();
			String[] str = lectura.split(",");
			int usuAct = Integer.parseInt(str[0]);
			while(lectura!=null)
			{
				
				if(Integer.parseInt(str[0])==usuAct)
				{	
					aux.add(new Tupla(Integer.parseInt(str[1]),Double.parseDouble(str[2])));
					lectura = br.readLine();
					if(lectura != null)
						str = lectura.split(",");
				}
				else
				{
					lista.put(usuAct, aux);
					aux = new ArrayList<Tupla<Integer,Double>>();
					usuAct = Integer.parseInt(str[0]);
				}
				
				if(lectura == null){
					lista.put(usuAct, aux);
				}
				
			}
		}
		catch (Exception e)
		{
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		}
		return lista;
	}
	
}
