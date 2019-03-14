package packProyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFileChooser;

public class ListaPeliculas {

	private HashMap<Integer,String> lista;
	private Integer[] keys;
	private static ListaPeliculas mLista;
	
	private ListaPeliculas()
	{
		lista = new HashMap<Integer,String>();
	
	}
	
	public static ListaPeliculas getListaPeliculas()
	{
		if(mLista==null)
		{
			mLista = new ListaPeliculas();
			return mLista;
		}
		else
			return mLista;
	}
	
	public void leerFichero(File fl)
	{
		try
		{
			String lectura;
			FileReader f =new FileReader(fl);
			BufferedReader b = new BufferedReader(f);
			while((lectura=b.readLine())!=null)
			{
				String[] str = lectura.split(";");
				lista.put(Integer.parseInt(str[0]), str[1]);
			}
			b.close();
			f.close();
			keys = new Integer[lista.size()];
			//keys = (Integer[]) lista.keySet().toArray();
		}
		catch(Exception e)
		{
			System.out.println("La has cagado");
		}
	}
	
	public void imprimir()
	{
		System.out.println(lista.get(36955));
		
	}
	
	public void jja()
	{
		FileChooser fc = new FileChooser();
		leerFichero(fc.leerFichero());
		imprimir();
	}
	
	public void prueba()
	{
		String j = "36955;True Lies (1994)";
		String[] str = j.split(";");
		System.out.println(str[0]);
		System.out.println(str[1]);
	}
	
}
