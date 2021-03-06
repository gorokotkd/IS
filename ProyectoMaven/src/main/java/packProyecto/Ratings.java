package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ratings {
	
	private HashMap<Integer, ArrayList<Tupla<Integer,Double>>> lista;
	
	public Ratings()
	{
		lista = new HashMap<Integer, ArrayList<Tupla<Integer,Double>>>();
		try
		{
			String path = System.getProperty("user.dir")+"/movie-ratings.csv";
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
				
			}
		}
		catch (Exception e)
		{
			System.out.println("Peto");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> devolKeys() {
		return new ArrayList<Integer>(lista.keySet()); 
	}
	
	public ArrayList<Tupla<Integer,Double>> getRatingsPorId(Integer pId) {
		return lista.get(pId);
	}
}