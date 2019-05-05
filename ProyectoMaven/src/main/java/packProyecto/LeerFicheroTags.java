package packProyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LeerFicheroTags extends LeerFichero {

	
	
	public LeerFicheroTags(String pPath) {
		super(pPath);

	}

	public HashMap<Integer,ArrayList<Tupla<String, Integer>>> leerFichero()
	{
		String path = System.getProperty("user.dir")+super.path;
		try {
			
			HashMap<Integer,ArrayList<Tupla<String, Integer>>> lista = new HashMap<Integer,ArrayList<Tupla<String, Integer>>>();
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
					else
					{
						aux.add(new Tupla(str[1],cont));
						lista.put(Integer.parseInt(str[0]), aux);
					}
				}
				else
				{
					aux.add(new Tupla(tagAct,cont));
					cont=1;
					lista.put(idAct, aux);
					aux = new ArrayList<Tupla<String, Integer>>();
					tagAct=str[1];
					idAct=Integer.parseInt(str[0]);
					lectura = br.readLine();
					if(lectura!=null)
						str = lectura.split(";");
					else
					{
						aux.add(new Tupla(str[1],cont));
						lista.put(Integer.parseInt(str[0]), aux);
					}
				}
			}
			return lista;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}

}
