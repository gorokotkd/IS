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
	
	public void imprimir()
	{
		System.out.println("("+idPeli+" --> "+nota+")");
	}
	
}