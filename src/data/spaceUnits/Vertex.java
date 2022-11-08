package data.spaceUnits;

import java.util.ArrayDeque;

public class Vertex {
	int vertexNum;
	Vector2 vertexPosition;
	ArrayDeque<Integer> linkedVertexNamesList = new ArrayDeque<Integer>(4);
	
	public Vertex(int num, Vector2 position) {
		vertexNum = num;
		vertexPosition = position;
	}
	
	public int getNum() {return vertexNum;}
	
	public Vector2 getPosition() {return vertexPosition; }
	
	public void addLinkedVertex(Integer vertexNum) {
		linkedVertexNamesList.add(vertexNum);
	}
	
	public ArrayDeque<Integer> getLinksList() {
		return linkedVertexNamesList;
	}
	
	public double getRadius() {
		return vertexPosition.getRadius();
	}
}
