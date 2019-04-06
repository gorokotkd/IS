
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
			for (int i = 0; i < diff; i++) {
				pVPeli1.add(0.0);
			}
		}else if (diff>0) {
			for (int i = 0; i < diff; i++) {
				pVPeli2.add(0.0);
			}  
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
}