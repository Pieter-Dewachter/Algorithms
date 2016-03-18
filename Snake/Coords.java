package algorithms.Snake;

public class Coords {
	public int x;
	public int y;
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int hashCode() {
		return 41 * (41 * x + y);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Coords) {
			Coords that = (Coords) obj;
			return (that.x == this.x && that.y == this.y);
		}
		return false;
	}
}
