package data.forms;

import data.units.Vector3;

public class Vertex3D {		// Vertex of shape
	
	private final int vertexNum;
	String name = "";
	
	Vector3 position;
	
	public Vertex3D(int num, Vector3 position) {	// Default constructor
		vertexNum = num;
		this.position = position;
	}
	
	public int getNum() {return vertexNum;}		// Getting number of vertex setted while creating
	
	public Vector3 getPosition() {	// Get vertex poisition as Vector3
		return position;
	}
	
	public void setName(String name) {	// Setting name of vertex in case of need
		this.name = name;
	}
	
	public String getName() {	// Getting name
		return name;
	}
	
	public double getX() { return position.getX(); }	// Access to separate coordinate of vertex position
	
	public double getY() { return position.getY(); }
	
	public double getZ() { return position.getZ(); }
	
}
