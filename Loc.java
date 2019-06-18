
public class Loc {
	private int x, y;
	
	public Loc (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Loc minus (Loc sub) {
		return new Loc(this.x - sub.getX(), this.y - sub.getY());
	}
	public boolean equals (Loc a) {
		return x == a.getX() && y == a.getY();
	}

	public Loc scaledBy(double i) {
		return new Loc((int)(this.x*i), (int)(this.y*i));
	}
	
	public String toString () {
		return "x: " + x + "  y: " + y;
	}
}
