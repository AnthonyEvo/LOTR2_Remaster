package data.forms;

import java.util.ArrayList;

import data.units.Vector2;
import data.units.Vector2D;

public class WireFrame2D {
	
	protected Vector2 axis = new Vector2(0, 0);
	protected ArrayList<Vertex2D> vertexList = new ArrayList<Vertex2D>();
	
	protected String shapeName;
	protected Double angleRad, angleDegr;
	protected Integer vertexNum = 0;
	
	public WireFrame2D(String name, double angle, boolean isRad) {
		shapeName = name;
		if(isRad) {
			angleRad = angle; angleDegr = angle / Math.PI * 180;
		}
		else {
			angleRad = angle * Math.PI / 180; angleDegr = angle;
		}
	}
	
	protected void buildWireFrame(double direction, boolean isRad) {}
	
	public void setPosition(Vector2 position) { axis = position; }
	
	public void setAngle(double angle, boolean inRad) {
		if(inRad) { angleRad = angle; angleDegr = angle / Math.PI * 180; }
		else { angleRad = angle * Math.PI / 180; angleDegr = angle; }
	}
	
	public Vector2 getAxis() {
		return axis;
	}
	
	public double getAngle(boolean inRad) {
		if(inRad) { return angleRad ; }
		else { return angleDegr; }
	}
	
	public void addVertex(Vector2 position) {
		vertexList.add(new Vertex2D(vertexNum, position));
		vertexNum++;
	}
		
	public void addVertex(double distance, double angle, boolean isRad) {
		double globalX = 0, globalY = 0, vertexAngle;
		if(isRad) vertexAngle = angle; else vertexAngle = angle * Math.PI / 180;
		
		globalX = Math.cos(vertexAngle) * distance; 
		if(Double.isNaN(globalX)) globalX = 0;
		
		globalY = Math.sin(vertexAngle) * distance;	
		if(Double.isNaN(globalY)) globalY = 0;
		
		vertexList.add(new Vertex2D(vertexNum, new Vector2(globalX, globalY)));
		vertexNum++;
	}
	
	public void addVertex(Vector2 position, double distance, double angle, boolean isRad) {
		double globalX = 0, globalY = 0, vertexAngle;
		if(isRad) vertexAngle = angle; else vertexAngle = angle * Math.PI / 180;
		
		globalX = Math.cos(vertexAngle) * distance; 
		if(Double.isNaN(globalX)) globalX = 0; 
		globalX += position.getX();
		
		globalY = Math.sin(vertexAngle) * distance;	
		if(Double.isNaN(globalY)) globalY = 0; 
		globalY += position.getY();
		
		vertexList.add(new Vertex2D(vertexNum, new Vector2(globalX, globalY)));
		vertexNum++;
	}
	
	public void createLink(int vert1, int vert2) {
		vertexList.stream().forEach(vertex -> {
			if(vertex.getNum() == vertexList.get(vert1).getNum()) vertex.buildLink(vertexList.get(vert2));
			if(vertex.getNum() == vertexList.get(vert2).getNum()) vertex.buildLink(vertexList.get(vert1));
		});
	}
	
	public ArrayList<Vertex2D> getVertexList() {
		return vertexList;
	}
	
	public Vertex2D getVertex(int num) {		
		return vertexList.get(num);
	}
	
	protected double getVertexASin(int vertNum) {
		return Math.asin(getVertex(vertNum).getPosition().getY() / getVertex(vertNum).getPosition().getRadius());
	}
	
	protected double getVertexASin(Vector2 subVert) {
		return Math.asin(subVert.getY() / subVert.getRadius());
	}
	
	protected double getVertexACos(int vertNum) {	
		return Math.acos(getVertex(vertNum).getPosition().getX() / getVertex(vertNum).getPosition().getRadius());
	}
	
	protected double getVertexACos(Vector2 subVert) {
		return Math.acos(subVert.getX() / subVert.getRadius());
	}
	
	public Vector2 getVertexPosition(int num) {
		return getVertexPosition(this.axis, this.getVertex(num).getPosition());
	}
	
	public Vector2 getVertexPosition(Vector2 axis, Vector2 pos) {
		double globalX = 0, globalY = 0,
		localX = pos.getX(), 
		localY = pos.getY();
		
		if(localX >= 0 && localY >= 0) { 
			double shiftX = (Math.cos(getVertexASin(pos) + angleRad) * pos.getRadius()); if(Double.isNaN(shiftX)) shiftX = 0;
			double shiftY = (Math.sin(getVertexASin(pos) + angleRad) * pos.getRadius()); if(Double.isNaN(shiftY)) shiftY = 0;
			
			globalX = axis.getX() + shiftX;
			globalY = axis.getY() + shiftY;
//			System.out.println("aX: " + Math.cos(getVertexASin(pos) + angleRad)  + " X: " + globalX  + " aY: " + Math.sin(getVertexASin(pos) + angleRad)  + " Y: " + globalY );
		}
		if(localX >= 0 && localY < 0) { 
			globalX = axis.getX() + (Math.cos(getVertexACos(pos) - angleRad) * pos.getRadius());
			globalY = axis.getY() + (Math.sin(getVertexASin(pos) + angleRad) * pos.getRadius());
		}
		if(localX < 0 && localY >= 0) { 
			globalX = axis.getX() + (Math.cos(getVertexACos(pos) + angleRad) * pos.getRadius());
			globalY = axis.getY() + (Math.sin(getVertexACos(pos) + angleRad) * pos.getRadius());
		}
		if(localX < 0 && localY < 0) { 
			globalX = axis.getX() + (Math.cos(getVertexACos(pos) - angleRad) * pos.getRadius());
			globalY = axis.getY() + (Math.sin(getVertexASin(pos) - angleRad) * pos.getRadius());
		}
		return new Vector2 (globalX, globalY);
	}
	
	public double getVertexAngle(Vector2 pos, boolean isRad) {
		double angle = 0,
		localX = pos.getX(),
		localY = pos.getY();
		
		if(localX >= 0 && localY >= 0) { 
			angle = Math.sin(getVertexASin(pos) + angleRad); if(Double.isNaN(angle)) angle = 0;
//			System.out.println("aX: " + Math.cos(getVertexASin(pos) + angleRad)  + " X: " + globalX  + " aY: " + Math.sin(getVertexASin(pos) + angleRad)  + " Y: " + globalY );
		}
		if(localX >= 0 && localY < 0) { 
			angle = Math.PI * 2 - Math.sin(getVertexASin(pos) + angleRad) * pos.getRadius();
		}
		if(localX < 0 && localY >= 0) { 
			angle = Math.cos(getVertexACos(pos) - angleRad);
		}
		if(localX < 0 && localY < 0) { 
			angle = Math.PI * 2 - Math.cos(getVertexACos(pos) - angleRad);
		}
		if(isRad) return angle;
		else return angle / Math.PI * 180;
	}
	
	public ArrayList<Vector2D> getVertexLinksPositions(int num) {
		ArrayList<Vector2D> temp = new ArrayList<Vector2D>();
		for(Vector2D subVertex : vertexList.get(num).getSubVertexList()) {
//			System.out.println("Vertex " + num + ": link created from " + subVertex.getX() + ", " + subVertex.getY());
//			System.out.println("Vertex " + num + ": link created to " + subVertex.getEnd().getX() + ", " + subVertex.getEnd().getY());						
//			System.out.println(getVertexACos(subVertex.getEnd()) / Math.PI * 180);
//			System.out.println(getVertexASin(subVertex.getEnd()) / Math.PI * 180);
			
			temp.add(new Vector2D(getVertexPosition(this.axis, subVertex), getVertexPosition(this.axis, subVertex.getEnd())));
		}
		return temp;
	}
	
	public void logMessage() {
		System.out.println(getVertexPosition(0).getX() + " " + getVertexPosition(0).getY());
	}
}
