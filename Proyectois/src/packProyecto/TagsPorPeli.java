package packProyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class TagsPorPeli {

	private HashMap<Integer,ArrayList<Tupla<String, Integer>>> lista;
	
	public TagsPorPeli()
	{
		String path = System.getProperty("user.dir")+"/movie-tags.csv";
		lista = new HashMap<Integer,ArrayList<Tupla<String, Integer>>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lectura = " ";
			int cont = 0;
			ArrayList<Tupla<String, Integer>> aux = new ArrayList<Tupla<String, Integer>>();
			lectura = br.readLine();
			String[] str = lectura.split(";");
			String tagAct = str[1];
			int idAct = Integer.parseInt(str[0]);
			while(lectura!=null)
			{
				if(Integer.parseInt(str[0])==idAct)
				{
					if(tagAct.equals(str[1]))
						cont++;
					else
					{
						aux.add(new Tupla(tagAct,cont));
						tagAct=str[1];
						cont = 1;
					}
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
				}
				else
				{
					if(tagAct.equals(str[1]))
						cont++;
					aux.add(new Tupla(tagAct,cont));
					tagAct=str[1];
					cont = 1;
					lista.put(idAct, aux);
					aux = new ArrayList<Tupla<String, Integer>>();
					idAct=Integer.parseInt(str[0]);
				}
			}
			System.out.println("Terminado");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> tagsDevolKeys() {
		return new ArrayList<>(lista.keySet()); 
	}
	
	public ArrayList<Tupla<String, Integer>> getTagsPorId(Integer pId) {
		return lista.get(pId);
	}
}
