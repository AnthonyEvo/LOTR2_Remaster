package data.forms;

import java.util.ArrayList;

import data.units.Vector2;
import data.units.Vector2D;

public class Vertex {
	private final int vertexNum;
	private Vector2 vertexPosition;
	private ArrayList<SubVertex> subvertexList = new ArrayList<SubVertex>();
	
	public Vertex(int num, Vector2 position) {
		vertexNum = num;
		vertexPosition = position;
	}
	
	public int getNum() {return vertexNum;}
	
	public Vector2 getPosition() {return vertexPosition; }
			
	public double getRadius() {
		return vertexPosition.getRadius();
	}
	
	public void buildLink(Vertex linkingVertex) {
		if(!subvertexList.stream().anyMatch(link -> link.getMarker() == linkingVertex.getNum())) {
			Vector2D temp = new Vector2D(this.getPosition(), linkingVertex.getPosition());
			subvertexList.add(new SubVertex(this.getPosition(), temp.getMiddle(), linkingVertex.getNum()));
						
//			System.out.println("Vertex " + vertexNum + ": link created from " + temp.getPosition().getX() + ", " + temp.getPosition().getY());
//			System.out.println("Vertex " + vertexNum + ": link created to " + linkingVertex.getPosition().getX() + ", " + linkingVertex.getPosition().getY());
//			System.out.println("Vertex " + vertexNum + ": middle at: " + temp.getMiddle().getX() + ", " + temp.getMiddle().getY());
		}
	}
	
	public void dropLink(Vertex linkingVertex) {
		if(subvertexList.stream().anyMatch(link -> link.getMarker() == linkingVertex.getNum())) {
			subvertexList.removeIf(link -> link.getMarker() == linkingVertex.getNum());
		}
	}
	
	public ArrayList<Vector2D> getSubVertexList() {
		ArrayList<Vector2D> temp = new ArrayList<Vector2D>();
		for(SubVertex i : subvertexList) {
			temp.add(new Vector2D(i.getBegin(), i.getEnd()));
		}
		return temp;
	}
	
	class SubVertex extends Vector2 {
		
		final int linkMarker;
		Vector2 begin;
		
		SubVertex(Vector2 begin, Vector2 end, int num) {
			super(end);
			this.begin = begin;
			linkMarker = num;
		}
		
		public int getMarker() { return linkMarker; }
		
		public Vector2 getBegin() { return begin; }
		
		public Vector2 getEnd() { return super.getPosition(); }
	}
}
