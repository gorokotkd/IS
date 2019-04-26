package packProyecto;

import java.util.*;
public abstract class NormalizarStrategy {

	private ArrayList<Double> listaDiferencias;	
	

	public abstract ArrayList<Double> normalizar(ArrayList<Double> pV);
	public abstract ArrayList<Double> desnormalizar(ArrayList<Double> pV);
	
	public ArrayList<Double> getListaDiferencias()
	{
		return listaDiferencias;
	}
	
	public void setListaDiferencias(ArrayList<Double> pV)
	{
		listaDiferencias=pV;
	}
}
