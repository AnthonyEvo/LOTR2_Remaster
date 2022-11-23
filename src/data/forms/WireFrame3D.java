package data.forms;

import java.util.ArrayList;

import data.units.EulerSequence;
import data.units.Origin3;
import data.units.Vector3;

public class WireFrame3D {
	
	String shapeName = "";
	
	ArrayList<Vertex3D> vertexList = new ArrayList<Vertex3D>();
	ArrayList<Edge3D> edgeList = new ArrayList<Edge3D>();
	
	EulerSequence currentSeq;
	Vector3 axis;
	Origin3 origin;
	int vertexNum, edgeNum;
	
	public WireFrame3D(String name) {
		shapeName = name;
		axis = new Vector3();
		origin = new Origin3();
	}
	
	public void setPosition(Vector3 axis) { this.axis = axis; }
	
	public void setOrientation(Origin3 origin) { this.origin = origin; }
	
	public Origin3 getOrientation() { return origin; }
	
	public static Vector3 getPointPosition(Vector3 vertex, Origin3 origin) {
		
		double rM[][] = new double[3][];
		
		double a = origin.getAlpha(), b = origin.getBeta(), g = origin.getGamma();
		double x = 0, y = 0, z = 0;
		switch(origin.getOriginSequence()) {
		case XYZ:
			rM[0] = new double[] {  cos(a) * cos(b) - cos(b) * sin(a) * sin(g),  -cos(g) * sin(a) - cos(a) * cos(b) * sin(g), sin(b) * sin(g) };
			rM[1] = new double[] {  cos(b) * cos(g) * sin(a) + cos(a) * sin(g), cos(a) * cos(b) * cos(g) - sin(a) * sin(g), -cos(g) * sin(b) };
			rM[2] = new double[] {  sin(a) * sin(b), cos(a) * sin(b), cos(b) };
			break;
		default:
			break;
		}
		
		x = rM[0][0] * vertex.getX()  + rM[0][1] * vertex.getY() + rM[0][2] * vertex.getZ();
		y = rM[1][0] * vertex.getX()  + rM[1][1] * vertex.getY() + rM[1][2] * vertex.getZ();
		z = rM[2][0] * vertex.getX()  + rM[2][1] * vertex.getY() + rM[2][2] * vertex.getZ();
		
		return new Vector3(x, y, z);
	}
	
	public static double cos(double operand) { return Math.cos(operand); }
	
	public static double sin(double operand) { return Math.sin(operand); }
	
	public void createVertex(Vector3 position) {
		vertexList.add(new Vertex3D(vertexNum, position));
		vertexNum++;
	}
	
	public ArrayList<Vector3> getVertexPositionsList() {
		ArrayList<Vector3> temp = new ArrayList<Vector3>();
		vertexList.stream().forEach(item -> temp.add(item.getPosition()));
		return temp;
	}
	
	public void createEdge(int vertex1, int vertex2) {
		edgeList.add(new Edge3D(edgeNum, vertexList.get(vertex1), vertexList.get(vertex2)));
		edgeNum++;
	}
}
