package packProyecto;

public class Tupla<X, Y> {

	private final X x;
	private final Y y;
	
	public Tupla(X x, Y y)
	{
		this.x = x;
		this.y = y;
	}
	
	public X getX()
	{return x;}
	
	public Y getY()
	{return y;}
}