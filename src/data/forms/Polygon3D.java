package data.forms;

import java.util.ArrayList;
import java.awt.Color;

import data.units.Vector3;
import data.units.Vector3D;

public class Polygon3D {
	Color polygonColor;
	Vector3 faceDirection;
	Vertex3D vertex1, vertex2, vertex3;
	
	public Polygon3D(Vertex3D vertex1, Vertex3D vertex2, Vertex3D vertex3, Color color) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.vertex3 = vertex3;
		polygonColor = color;
	}
	
	public Vector3 getDirection() {
		return Vector3.multiplyVectors(
			new Vector3D(vertex1.getPosition(), vertex2.getPosition()).normalize(),
			new Vector3D(vertex2.getPosition(), vertex3.getPosition()).normalize()
		);
	}
	
	public ArrayList<Vector3> getFaceVertexes() {
		ArrayList<Vector3> vectorList = new ArrayList<Vector3>();
		vectorList.add(vertex1.getPosition());
		vectorList.add(vertex2.getPosition());
		vectorList.add(vertex3.getPosition());
		return vectorList;
	}
	
	public Color getColibratedColor(double angle) {
		
		return new Color(
			(int)(polygonColor.getRed() - angle / Math.PI * 150), 
			(int)(polygonColor.getGreen() - angle / Math.PI * 150), 
			(int)(polygonColor.getBlue() - angle / Math.PI * 150)
		);
	}
	
	public Vector3D getNormal() {
		Vector3D base = new Vector3D(vertex1.getPosition(), vertex2.getPosition());
		Vector3D midline = new Vector3D(vertex3.getPosition(), Vector3D.getSimilarVector(base, 0.5).getEnd());
		
		return Vector3D.multiplyVectors( new Vector3D(Vector3D.getSimilarVector(midline, 0.5).getEnd(), 
			Vector3.combineVectors(Vector3D.getSimilarVector(midline, 0.5).getEnd(), getDirection())), 20);
	}
	
	public boolean isUnderPoligon(Vertex3D vertex) {
				
		return false;
	}
}