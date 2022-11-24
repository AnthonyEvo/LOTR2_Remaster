package data.forms;

import java.util.ArrayList;

import data.units.Vector3;

public class Face3D {
	ArrayList<Vertex3D> vertexList = new ArrayList<Vertex3D>();
	Vector3 faceDirection;
	
	Face3D(Vector3 direction) {
		faceDirection = direction;
	}
	
	public void addVertex(Vertex3D vertex) {
		vertexList.add(vertex);
	}
	
}