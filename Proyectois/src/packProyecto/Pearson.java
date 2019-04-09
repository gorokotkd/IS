package packProyecto;

import java.util.ArrayList;

public class Pearson implements SimilitudStrategy {

	public Double calcularSimilitud(ArrayList<Double> list1, ArrayList<Double> list2)
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
		double numerador = sumatorio(list1, list2);
		double deno1 = modulo(list1);
		double deno2 = modulo(list2);
		
		return numerador/(deno1*deno2);
		
	}
	
	private Double modulo(ArrayList<Double> list)
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
	
	public Double sumatorio(ArrayList<Double> pV1, ArrayList<Double> pV2)
	{
		Double resul = 0.0;
		double media1 = mediaDeVector(pV1);
		double media2 = mediaDeVector(pV2);
		for(int i=0; i<pV1.size();i++)
			resul = resul + (pV1.get(i)-media1)*(pV2.get(i)-media2);
		return resul;
	}

	public ArrayList<Double> rellenarCon0(ArrayList<Double> pV, int pCant) {
		for (int i = 0; i < pCant; i++) {
			pV.add(0.0);
		}
		return pV;
	}

	@Override
	public Double modulo(ArrayList<Double> pV1, ArrayList<Double> pV2) {
		// TODO Auto-generated method stub
		return null;
	}
}
