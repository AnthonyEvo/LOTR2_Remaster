package data.forms;

import java.awt.Color;
import java.util.ArrayList;

import data.units.EulerSequence;
import data.units.Origin3;
import data.units.Vector3;

public class WireFrame3D {
	
	protected String shapeName = "";
	
	protected ArrayList<Vertex3D> vertexList = new ArrayList<Vertex3D>();
	protected ArrayList<Edge3D> edgeList = new ArrayList<Edge3D>();
	protected ArrayList<Polygon3D> polygonList = new ArrayList<Polygon3D>();
	
	protected EulerSequence currentSeq;
	protected Vector3 axis;
	protected Origin3 origin;
	protected int vertexNum = 0, edgeNum = 0, polygonNum = 0;
	protected Color facesColor = Color.lightGray, edgesColor = Color.black;
	protected boolean wireframe = true, polygons = true;
	
	public WireFrame3D(String name) {
		shapeName = name;
		axis = new Vector3();
		origin = new Origin3();
	}
	
	public boolean isWireframeNeeded() { return wireframe; }
	public boolean isPolyNeeded() { return polygons; }
	
	public String getName() { return shapeName; }
	
	public void setPosition(Vector3 axis) { this.axis = axis; }
	
	public void setOrientation(Origin3 origin) { this.origin = origin; }
	
	public Origin3 getOrientation() { return origin; }
	
	public void setColor(Color color) { facesColor = color; edgesColor = color;}
	
	public Color getFacesColor() {return facesColor;}
	
	public Color getEdgesColor() {return edgesColor;}
	
	public Vector3 getPointPosition(Vector3 vertex, boolean isGlobal) {
		
		double rM[][] = new double[3][];
		
		double a = origin.getAlpha(), b = origin.getBeta(), g = origin.getGamma();
		double x = 0, y = 0, z = 0;
		switch(origin.getOriginSequence()) {
		case XYZ:
			rM[0] = new double[] { cos(b),  sin(b) * sin(g), cos(g) * sin(b) };
			rM[1] = new double[] { sin(a) * sin(b), cos(a) * cos(g) - cos(b) * sin(a) * sin(g), -cos(a) * sin(g) - cos(b) * cos(g) * sin(a) };
			rM[2] = new double[] { -cos(a) * sin(b), cos(g) * sin(a) + cos(a) * cos(b) * sin(g), cos(a) * cos(b) * cos(g) - sin(a) * sin(g) };
			break;
		default:
			break;
		}
		
		x = rM[0][0] * vertex.getX()  + rM[0][1] * vertex.getY() + rM[0][2] * vertex.getZ();
		y = rM[1][0] * vertex.getX()  + rM[1][1] * vertex.getY() + rM[1][2] * vertex.getZ();
		z = rM[2][0] * vertex.getX()  + rM[2][1] * vertex.getY() + rM[2][2] * vertex.getZ();
		
		if(isGlobal) return Vector3.combineVectors(new Vector3(x, y, z), axis);
		else return new Vector3(x, y, z);
	}
	
	public static double cos(double operand) { return Math.cos(operand); }
	
	public static double sin(double operand) { return Math.sin(operand); }
	
	public void createVertex(Vector3 position) {
		vertexList.add(new Vertex3D(vertexNum, position));
		vertexNum++;
	}
	
	public ArrayList<Vector3> getVertexPositionsListAsVector() {
		ArrayList<Vector3> temp = new ArrayList<Vector3>();
		vertexList.stream().forEach(item -> temp.add(item.getPosition()));
		return temp;
	}
	
	public ArrayList<Vertex3D> getVertexPositionsList() { return vertexList; }
	
	public void createEdge(int vertex1, int vertex2) {
		edgeList.add(new Edge3D(edgeNum, vertexList.get(vertex1), vertexList.get(vertex2)));
		edgeNum++;
	}
	
	public ArrayList<Edge3D> getEdges() { return edgeList; }
	
	public static double getAngleBetweenVectors(Vector3 operand1, Vector3 operand2) {
		return Math.acos((operand1.getX() * operand2.getX() + operand1.getY() * operand2.getY() + operand1.getZ() * operand2.getZ()) /
				(Math.sqrt(Math.pow(operand1.getX(), 2) + Math.pow(operand1.getY(), 2) + Math.pow(operand1.getZ(), 2)) * 
				Math.sqrt(Math.pow(operand2.getX(), 2) + Math.pow(operand2.getY(), 2) + Math.pow(operand2.getZ(), 2))));
	}
	
	public void createPolygon(int vertex1, int vertex2, int vertex3) {
		polygonList.add(new Polygon3D(vertexList.get(vertex1), vertexList.get(vertex2), vertexList.get(vertex3), facesColor, polygonNum));
		polygonNum++;
	}
	
	public Vector3 getPolygonOrientation(Polygon3D polygon) {
		return getPointPosition(polygon.getDirection(), false);
	}
	
	public ArrayList<Polygon3D> getFaces() {
		return polygonList;
	}
}
