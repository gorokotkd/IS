package packProyecto;

import java.util.ArrayList;

public class Cos implements SimilitudStrategy{

	public Double calcularSimilitud(ArrayList<Double> pV1, ArrayList<Double> pV2) {
		int diff = pV1.size()-pV2.size();
		if (diff<0) { //pV2 más grande
			diff = diff*(-1);
			pV1 = rellenarCon0(pV1,diff);
		}else if (diff>0) {
			pV2 = rellenarCon0(pV2,diff);  
		}
		Double sumatorio = this.sumatorio(pV1, pV2);
		Double modulo = this.modulo(pV1, pV2);
		return sumatorio/modulo;
	}
	
	public Double sumatorio(ArrayList<Double> pV1, ArrayList<Double> pV2) {
		Double sumatorio = 0.0;
		for (int i = 0; i < pV1.size(); i++) {
			sumatorio = sumatorio + (pV1.get(i)*pV2.get(i));
		}
		return sumatorio;
	}
	
	public Double modulo(ArrayList<Double> pV1, ArrayList<Double> pV2) {
		Double aux1 = 0.0;
		Double aux2 = 0.0;
		for (int i = 0; i < pV1.size(); i++) {
			aux1 = aux1 + Math.pow(pV1.get(i),2.0);
			aux2 = aux2 + Math.pow(pV2.get(i),2.0); 
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
}
