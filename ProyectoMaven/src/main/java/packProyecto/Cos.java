package packProyecto;

import java.util.ArrayList;

public class Cos implements SimilitudStrategy{

	public Double calcularSimilitud(ArrayList<Double> pV1, ArrayList<Double> pV2) {
		int diff = pV1.size()-pV2.size();
		if (diff<0) { //pV2 m�s grande
			diff = diff*(-1);
			pV1 = rellenarCon0(pV1,diff);
		}else if (diff>0) {
			pV2 = rellenarCon0(pV2,diff);  
		}
		Double sumatorio = this.sumatorio(pV1, pV2);
		Double modulo1 = this.modulo(pV1);
		Double modulo2 = this.modulo(pV2);
		return sumatorio/(modulo1*modulo2);
	}
	
	public Double sumatorio(ArrayList<Double> pV1, ArrayList<Double> pV2) {
		Double sumatorio = 0.0;
		for (int i = 0; i < pV1.size(); i++) {
			sumatorio = sumatorio + (pV1.get(i)*pV2.get(i));
		}
		return sumatorio;
	}
	
	public Double modulo(ArrayList<Double> list)
	{
		double resul = 0.0;
		for(int i=0; i<list.size();i++)
			resul = resul + Math.pow(list.get(i), 2.0);
		return Math.sqrt(resul);
		
		
	}

	
	public ArrayList<Double> rellenarCon0(ArrayList<Double> pV, int pCant) {
		for (int i = 0; i < pCant; i++) {
			pV.add(0.0);
		}
		return pV;
	}
}
