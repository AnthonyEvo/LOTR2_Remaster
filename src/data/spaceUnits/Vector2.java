package data.spaceUnits;

public class Vector2 {
	double posX, posY;
	
	public Vector2(double posX, double posY) { 
		this.posX = posX; 
		this.posY = posY; 
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
	
	public double getRadius() {
		return Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
	}
}
