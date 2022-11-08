package data.spaceUnits;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Shape2D {
	
	Vector2 axis;
	ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	
	String shapeName;
	Double angleRad, angleDegr;
	private Integer vertexNum = 0;
	
	public Shape2D(String name, double angle, boolean isRad) {
		shapeName = name;
		if(isRad) {
			angleRad = angle; angleDegr = angle / Math.PI * 180;
		}
		else {
			angleRad = angle * Math.PI / 180; angleDegr = angle;
		}
	}
	
	public void addVertex(Vector2 position) {
		vertexList.add(new Vertex(vertexNum, position));
		vertexNum++;
	}
	
	public ArrayList<Vertex> getVertexList() {
		return vertexList;
	}
	
	public Vertex getVertex(int num) {		
		return vertexList.get(num);
	}
	
	public double getVertexSin(int vertNum) {
		return Math.asin(getVertex(vertNum).getPosition().getY() / getVertex(vertNum).getPosition().getRadius());
	}
	
	public double getVertexCos(int vertNum) {		
		return Math.acos(getVertex(vertNum).getPosition().getX() / getVertex(vertNum).getPosition().getRadius());
	}
	
	public void logAngles() {
		System.out.println(angleRad + " " + angleDegr);
	}
}
