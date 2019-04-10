
package packProyecto;

import java.util.ArrayList;
import java.lang.*;

public class Similitud {

	
	public Similitud() {
	}
	
	public Double calcularSimilitud(ArrayList<Double> pVPeli1, ArrayList<Double> pVPeli2) {
		int diff = pVPeli1.size()-pVPeli2.size();
		if (diff<0) {
			diff = diff*(-1);
			pVPeli1 = rellenarCon0(pVPeli1,diff);
		}else if (diff>0) {
			pVPeli2 = rellenarCon0(pVPeli2,diff);  
		}
		Double sumatorio = this.sumatorio(pVPeli1, pVPeli2);
		Double modulo = this.modulo(pVPeli1, pVPeli2);
		return sumatorio/modulo;
	}
	
	public Double sumatorio(ArrayList<Double> pVPeli1, ArrayList<Double> pVPeli2) {
		Double sumatorio = 0.0;
		for (int i = 0; i < pVPeli1.size(); i++) {
			sumatorio = sumatorio + pVPeli1.get(i) + pVPeli2.get(i);
		}
		return sumatorio;
	}
	
	public Double modulo(ArrayList<Double> pVPeli1, ArrayList<Double> pVPeli2) {
		int diff = pVPeli1.size()-pVPeli2.size();
		if (diff<0) {
			diff = diff*(-1);
			pVPeli1 = rellenarCon0(pVPeli1,diff);
		}else if (diff>0) {
			pVPeli2 = rellenarCon0(pVPeli2,diff);  
		}
		Double aux1 = 0.0;
		Double aux2 = 0.0;
		for (int i = 0; i < pVPeli1.size(); i++) {
			aux1 = aux1 + Math.pow(pVPeli1.get(i),2.0);
			aux2 = aux2 + Math.pow(pVPeli2.get(i),2.0); 
		}
		aux1 = Math.sqrt(aux1);
		aux2 = Math.sqrt(aux2);
		return aux1*aux2;
	}
	
	public ArrayList<Double> rellenarCon0(ArrayList<Double> pV, int pCant) {
		for (int i = 0; i < pCant; i++) {
			pV.add(0.0);
		}
		return pV;
	}
	
	
	public Double correlacionDePearson(ArrayList<Double> list1, ArrayList<Double> list2)
	{
		int diff = list1.size()-list2.size();
		if (diff<0) {
			diff = diff*(-1);
			for (int i = 0; i < diff; i++) {
				list1.add(0.0);
			}
		}else if (diff>0) {
			for (int i = 0; i < diff; i++) {
				list2.add(0.0);
			}  
		}
		double numerador = sumatorioPearson(list1, list2);
		double deno1 = moduloPearson(list1);
		double deno2 = moduloPearson(list2);
		
		return numerador/(deno1*deno2);
		
	}
	
	private Double moduloPearson(ArrayList<Double> list)
	{
		double resul = 0.0;
		double media = mediaDeVector(list);
		for(int i=0; i<list.size();i++)
			resul = resul + Math.pow(list.get(i)-media, 2.0);
		return Math.sqrt(resul);
		
		
	}
	
	private Double mediaDeVector(ArrayList<Double> list)
	{
		Double sum=0.0;
		for(int i=0;i<list.size();i++)
			sum = sum+list.get(i);
		return sum/list.size();
	}
	
	private Double sumatorioPearson(ArrayList<Double> list1, ArrayList<Double> list2)
	{
		Double resul = 0.0;
		double media1 = mediaDeVector(list1);
		double media2 = mediaDeVector(list2);
		for(int i=0; i<list1.size();i++)
			resul = resul + (list1.get(i)-media1)*(list2.get(i)-media2);
		return resul;
	}
}