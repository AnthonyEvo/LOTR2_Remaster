package data.units;

public class Vector3 {
	private double x, y, z;
	
	public Vector3(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
	
	public Vector3(Vector3 vector) {
		this.x = vector.getX(); this.y = vector.getY(); this.z = vector.getZ();
	}
	
	public Vector3() { this.x = 0; this.y = 0; this.z = 0; }
	
	public double getX() { return x; }
	
	public double getY() { return y; }
	
	public double getZ() { return z; }
	
	public Vector3 getVector3() {return this;}
	
	public double getRadius() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	public Vector3 normalize() {
		return new Vector3(
			this.getX() / this.getRadius(), 
			this.getY() / this.getRadius(),
			this.getZ() / this.getRadius()
		);
	}
	
	public static Vector3 multiplyVectors(Vector3 operand1, Vector3 operand2) {
		return new Vector3(
			operand1.getZ() * operand2.getY() - operand1.getY() * operand2.getZ(),
			operand1.getX() * operand2.getZ() - operand1.getZ() * operand2.getX(),
			operand1.getY() * operand2.getX() - operand1.getX() * operand2.getY()
		);
	}
	
	public static Vector3 combineVectors(Vector3 operand1, Vector3 operand2) {
		return new Vector3(
				operand1.getX() + operand2.getX(),
				operand1.getY() + operand2.getY(),
				operand1.getZ() + operand2.getZ()
			);
		
		
	}
}
