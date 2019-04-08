package packProyecto;

import java.util.ArrayList;

public class Pearson implements SimilitudStrategy {

	public Double calcularSimilitud(ArrayList<Double> pV1, ArrayList<Double> pV2)
	{
		int diff = pV1.size()-pV2.size();
		if (diff<0) {
			diff = diff*(-1);
			for (int i = 0; i < diff; i++) {
				pV1.add(0.0);
			}
		}else if (diff>0) {
			for (int i = 0; i < diff; i++) {
				pV2.add(0.0);
			}  
		}
		double numerador = sumatorio(pV1, pV2);
		double modulo = modulo(pV1,pV2);
		
		return numerador/modulo;
		
	}
	
	public Double modulo(ArrayList<Double> pV1, ArrayList<Double> pV2){
		double resul = 0.0;
		double resul2 = 0.0;
		double media = mediaDeVector(pV1);
		for(int i=0; i<pV1.size();i++) {
			resul = resul + Math.pow(pV1.get(i)-media, 2.0);
			resul2 = resul2 + Math.pow(pV2.get(i)-media, 2.0);
		}
		return Math.sqrt(resul)*Math.sqrt(resul2);
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

	@Override
	public ArrayList<Double> rellenarCon0(ArrayList<Double> pV, int pCant) {
		// TODO Auto-generated method stub
		return null;
	}
}
