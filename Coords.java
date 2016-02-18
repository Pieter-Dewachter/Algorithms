package algorithms;

public class Coords<A, B> {
	public A x;
	public B y;
	
	public Coords(A x, B y) {
		this.x = x;
		this.y = y;
	}
	
	public int hashCode() {
		return 41 * (41 * x.hashCode()) + y.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Coords<?, ?>) {
			Coords<A, B> that = (Coords<A, B>) obj;
			return (that.x == this.x && that.y == this.y);
		}
		return false;
	}
}
