package data.units;

public class Vector3D extends Vector3 {
	
	Vector3 end;
	
	public Vector3D(Vector3 vector1, Vector3 vector2) {
		super(new Vector3(vector1));
		end = new Vector3(vector2);
	}
	
	@Override
	public Vector3 getVector3() {
		return new Vector3(end.getX() - this.getX(), end.getY() - this.getY(),end.getZ() - this.getZ());
	}
	
	@Override
	public Vector3 normalize() {
		return this.getVector3().normalize();
	}
	
	public Vector3 getBegin() {return super.getVector3();}
	
	public Vector3 getEnd() { return end; }
	
	public double getDistance() {
		return getVector3().getRadius();
	}
	
	public static Vector3D multiplyVectors(Vector3D operand1, Vector3D operand2) {
		return new Vector3D(
			operand1.getBegin(),
			Vector3.combineVectors(operand1.getBegin(), Vector3.multiplyVectors(operand1.getVector3(), operand2.getVector3()))
		);
	}
	
	public static Vector3D multiplyVectors(Vector3D operand1, double operand2) {
		return new Vector3D(
			operand1.getBegin(),
			Vector3.combineVectors(operand1.getBegin(), Vector3.multiplyVectors(operand1.getVector3(), operand2))
		);
	}
	
	public static Vector3D combineVectors(Vector3D operand1, Vector3D operand2) {
		return new Vector3D(
			operand1.getBegin(),
			Vector3.combineVectors(operand1.getEnd(), operand2.getVector3())
		);
	}
	
	public static Vector3D getSimilarVector(Vector3D vector, double lengthProcent) {
		return new Vector3D(vector.getBegin(), Vector3.combineVectors(vector.getBegin(), Vector3.multiplyVectors(vector.normalize(), vector.getDistance() * lengthProcent)));
	}
}
