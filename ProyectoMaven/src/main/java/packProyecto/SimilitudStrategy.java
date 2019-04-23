
package packProyecto;

import java.util.ArrayList;
import java.lang.*;

public interface SimilitudStrategy {

	Double calcularSimilitud(ArrayList<Double> pV1, ArrayList<Double> pV2);
	Double sumatorio(ArrayList<Double> pV1, ArrayList<Double> pV2);
	Double modulo(ArrayList<Double> pV1);
	ArrayList<Double> rellenarCon0(ArrayList<Double> pV, int pCant);
}