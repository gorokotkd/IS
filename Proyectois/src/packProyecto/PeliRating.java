package packProyecto;

public class PeliRating {
	
	private int idPeli;
	private double nota;
	
	public PeliRating(int pIdpeli, double pNota)
	{
		idPeli = pIdpeli;
		nota = pNota;
	}
	
	public int getPeli()
	{return idPeli;}
	
	public double getNota()
	{return nota;}
<<<<<<< HEAD

	
	public void imprimir()
	{
		System.out.println("("+idPeli+" --> "+nota+")");
	}
=======
>>>>>>> 1398a65c048d45ccdef2be145f0406416fe291a5
}