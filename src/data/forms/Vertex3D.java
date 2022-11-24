package data.forms;

import data.units.Vector3;

public class Vertex3D {
	
	private final int vertexNum;
	String name = "";
	
	Vector3 position;
	
	public Vertex3D(int num, Vector3 position) {
		vertexNum = num;
		this.position = position;
	}
	
	public int getNum() {return vertexNum;}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public double getX() { return position.getX(); }
	
	public double getY() { return position.getY(); }
	
	public double getZ() { return position.getZ(); }
	
}
