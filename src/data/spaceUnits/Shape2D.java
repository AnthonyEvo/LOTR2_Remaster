package data.spaceUnits;

import java.util.ArrayList;

public class Shape2D {
	
	Vector2 axis = new Vector2(0, 0);
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
	
	public void setPosition(Vector2 position) { axis = position; }
	
	public void addVertex(Vector2 position) {
		vertexList.add(new Vertex(vertexNum, position));
		vertexNum++;
	}
		
	public void createLink(int vert1, int vert2) {
		vertexList.stream().forEach(vertex -> {
			if(vertex.getNum() == vertexList.get(vert1).getNum()) vertex.buildLink(vertexList.get(vert2));
			if(vertex.getNum() == vertexList.get(vert2).getNum()) vertex.buildLink(vertexList.get(vert1));
		});
	}
	
	public ArrayList<Vertex> getVertexList() {
		return vertexList;
	}
	
	public Vertex getVertex(int num) {		
		return vertexList.get(num);
	}
	
	private double getVertexASin(int vertNum) {
		return Math.asin(getVertex(vertNum).getPosition().getY() / getVertex(vertNum).getPosition().getRadius());
	}
	
	private double getVertexASin(Vector2 subVert) {
		return Math.asin(subVert.getPosition().getY() / subVert.getPosition().getRadius());
	}
	
	private double getVertexACos(int vertNum) {		
		return Math.acos(getVertex(vertNum).getPosition().getX() / getVertex(vertNum).getPosition().getRadius());
	}
	
	private double getVertexACos(Vector2 subVert) {		
		return Math.acos(subVert.getPosition().getX() / subVert.getPosition().getRadius());
	}
	
	public Vector2 getVertexPosition(int num) {
		return new Vector2(axis.getX() + Math.cos(getVertexACos(num) + angleRad) * getVertex(num).getRadius(), 
							axis.getY() + Math.sin(getVertexASin(num) + angleRad) * getVertex(num).getRadius());
	}
	
	public ArrayList<Vector2D> getVertexLinksPositions(int num) {
		ArrayList<Vector2D> temp = new ArrayList<Vector2D>();
		
		return temp;
	}
	
	public void logMessage() {
		System.out.println(getVertexPosition(0).getX() + " " + getVertexPosition(0).getY());
	}
}
