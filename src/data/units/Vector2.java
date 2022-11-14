package data.units;

public class Vector2 {
	double posX, posY;
	
	public Vector2(double posX, double posY) { 
		this.posX = posX; this.posY = posY; 
	}
	
	public Vector2(Vector2 vector2) { 
		posX = vector2.getX(); posY = vector2.getY(); 
	}
	
	public void setPosition(double posX, double posY) { 
		this.posX = posX; 
		this.posY = posY; 
	}
	
	public void setPosition(Vector2 vector2) {
		this.posX = vector2.getX(); 
		this.posY = vector2.getY();
	}
	
	public double getX() { return posX; }
	
	public double getY() { return posY; }
	
	public Vector2 getPosition() { return this; }
	
	public Vector2 normalize() {
		return new Vector2(this.getX() / this.getRadius(), this.getY() / this.getRadius());
	}
	
	public double getRadius() {
		return Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
	}
	
	public static Vector2 subtractVector2(Vector2 operand1, Vector2 operand2) {
		return new Vector2(
			operand1.getX() - operand2.getX(), 
			operand1.getY() - operand2.getY()
		);
	}
	
	public static Vector2 divideVector2(Vector2 operand1, double operand2) {
		return new Vector2(
				operand1.getX() / operand2, 
				operand1.getY() / operand2
			);
	}
}
