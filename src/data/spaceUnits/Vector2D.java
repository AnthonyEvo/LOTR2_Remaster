package data.spaceUnits;

public class Vector2D extends Vector2 {

	Vector2 end;
	
	public Vector2D(Vector2 pos1, Vector2 pos2) {
		super(pos1);
		end = pos2;
	}
	
	@Override
	public Vector2 normalize() {
		Vector2 temp = getVector2();
		return new Vector2(temp.getX() / temp.getRadius(), temp.getY() / temp.getRadius());
	}
	
	public Vector2 getVector2() {
		return new Vector2(end.getX() - this.getX(), end.getY() - this.getY());
	}
	
	public double getDistance() {
		return this.getVector2().getRadius();
	}
	
	public Vector2 getMiddle() {
		return new Vector2(this.getX() + normalize().getX() * getDistance() / 2, 
							this.getY() + normalize().getY() * getDistance() / 2);
	}
	
	public Vector2 getEnd() { return end.getPosition(); }
}
