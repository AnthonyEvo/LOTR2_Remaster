package data.units;

public class Vector3D extends Vector3 {	// A bit more complicated vector with beginning not in 0,0,0
	
	Vector3 end;	// For vector end
	
	public Vector3D(Vector3 vector1, Vector3 vector2) { // Default constructor
		super(new Vector3(vector1));
		end = new Vector3(vector2);
	}
	
	@Override
	public Vector3 getVector3() {	// Get simple vector from vector3D (needed in mathematical operations)
		return new Vector3(end.getX() - this.getX(), end.getY() - this.getY(),end.getZ() - this.getZ());
	}
	
	@Override
	public Vector3 normalize() {	// Vector normalization 
		return this.getVector3().normalize();
	}
	
	public Vector3 getBegin() {return super.getVector3();}	// Return vector beginning as Vector3
	
	public Vector3 getEnd() { return end; } // Return vector ending as Vector3
	
	public double getDistance() {	// Return distance betweenn begin and end of vector
		return getVector3().getRadius();
	}
	
	public static Vector3D multiplyVectors(Vector3D operand1, Vector3D operand2) { // Mathematical operations with Vector3
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
	
	public static Vector3D combineVectors(Vector3D operand1, Vector3D operand2) {	//...
		return new Vector3D(
			operand1.getBegin(),
			Vector3.combineVectors(operand1.getEnd(), operand2.getVector3())
		);
	}
	
	public static Vector3D getSimilarVector(Vector3D vector, double lengthProcent) { // Getting similar to existing vector
		return new Vector3D(vector.getBegin(), Vector3.combineVectors(vector.getBegin(), Vector3.multiplyVectors(vector.normalize(), vector.getDistance() * lengthProcent)));
	}
}
