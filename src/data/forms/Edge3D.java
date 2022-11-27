package data.forms;

import data.units.Vector3;

public class Edge3D {	// Edge of 3D shape needed for wireframe drawing
	
	final int edgeNum;
	
	Vertex3D vertexBegin, vertexEnd;
	
	Edge3D(int edgeNum, Vertex3D vertexBegin, Vertex3D vertexEnd) {
		this.edgeNum = edgeNum;
		this.vertexBegin = vertexBegin;
		this.vertexEnd = vertexEnd;
	}
	
	public Vector3 getBegin() { return vertexBegin.getPosition(); }

	public Vector3 getEnd() { return vertexEnd.getPosition(); }
}
