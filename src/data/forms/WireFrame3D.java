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
	
	// --- Wireframe Bound Box ---
	
	protected BoundBox boundBox  = new BoundBox();
	
	public double getBoundBoxWidth() { return boundBox.getWidth(); }

	public double getBoundBoxRight() { return boundBox.getRight(); }
	public double getBoundBoxLeft() {return boundBox.getLeft(); }
	
	public double getBoundBoxLength() { return boundBox.getLength(); }
	
	public double getBoundBoxFront() {return boundBox.getFront(); }
	public double getBoundBoxReare() {return boundBox.getReare(); }
	
	public double getBoundBoxHeight() { return boundBox.getHeight(); }
	
	public double getBoundBoxTop() { return boundBox.getTop(); }
	public double getBoundBoxBottom() { return boundBox.getBottom(); }
	
	// --- /Wireframe Bound Box ---
	
	protected Vector3 axis, midPoint = new Vector3();
	
	protected Origin3 origin;
	protected int vertexNum = 0, edgeNum = 0, polygonNum = 0;
	protected Color facesColor = Color.lightGray, edgesColor = Color.black;
	protected boolean wireframe = true, polygons = true;
	
	public WireFrame3D(String name) {	// Default constructor
		shapeName = name;
		axis = new Vector3();
		origin = new Origin3();
	}
	
	public boolean isWireframeNeeded() { return wireframe; }	// Wireframe flag for renderer, turning on/off render of edges
	
	public boolean isPolyNeeded() { return polygons; }		// Polygon flag for renderer, same as previous
	
	public String getName() { return shapeName; }	// Interior name of shape
	
	public void setPosition(Vector3 axis) { this.axis = axis; }	// Sets position of shape axis
	
	public void setOrientation(Origin3 origin) { this.origin = origin; }	// Sets orientation of shape through Origin3
	
	public Origin3 getOrientation() { return origin; }	// Gets orientation
	
	public void setColor(Color color) { facesColor = color; edgesColor = color;}	// Setting color of adges and polygons
	
	public Color getFacesColor() {return facesColor;}	// ...
	
	public Color getEdgesColor() {return edgesColor;} 	// ...
	
	public Vector3 getPointPosition(Vector3 vertex, boolean isGlobal) {	// returns position of point according to origin orientation of shape
		
		double rM[][] = new double[3][];
		
		double a = origin.getAlpha(), b = origin.getBeta(), g = origin.getGamma();
		double x = 0, y = 0, z = 0;
		switch(origin.getOriginSequence()) {
		
		case  XZX:
			rM[0] = new double[] {cos(b), -cos(g) * sin(b), sin(b) * sin(g)};
			rM[1] = new double[] {cos(a) * sin(b), cos(a) * cos(b) * cos(g) - sin(a) * sin(g), -cos(g) * sin(a) - cos(a) * cos(b) * sin(g)};
			rM[2] = new double[] {sin(a) * sin(b), cos(a) * sin(g) + cos(b) * cos(g) * sin(a), cos(a) * cos(g) - cos(b) * sin(a) * sin(g)};
			break;
		case XYX:
			rM[0] = new double[] {cos(b),  sin(b) * sin(g), cos(g) * sin(b) };
			rM[1] = new double[] {sin(a) * sin(b), cos(a) * cos(g) - cos(b) * sin(a) * sin(g), -cos(a) * sin(g) - cos(b) * cos(g) * sin(a)};
			rM[2] = new double[] {-cos(a) * sin(b), cos(g) * sin(a) + cos(a) * cos(b) * sin(g), cos(a) * cos(b) * cos(g) - sin(a) * sin(g)};
			break;
		default:
			break;
		}
		
		x = rM[0][0] * vertex.getX() + rM[0][1] * vertex.getY() + rM[0][2] * vertex.getZ();
		y = rM[1][0] * vertex.getX() + rM[1][1] * vertex.getY() + rM[1][2] * vertex.getZ();
		z = rM[2][0] * vertex.getX() + rM[2][1] * vertex.getY() + rM[2][2] * vertex.getZ();
		
		if(isGlobal) return Vector3.combineVectors(new Vector3(x, y, z), axis);
		else return new Vector3(x, y, z);
	}
	
	public static double cos(double operand) { return Math.cos(operand); }	// Simplification of original method
	
	public static double sin(double operand) { return Math.sin(operand); }	// ... same as previous 
	
	public void createVertex(Vector3 position) {	// Adding vertex to shape
		
		boundBox.addVertex(position, vertexList.size());
		vertexList.add(new Vertex3D(vertexNum, position));
		vertexNum++;
	}
	
	public Vector3 getMidPoint() { return boundBox.getMidPoint(); }
	
	public double getCollisionRadius() { return boundBox.getCollisionRadius(); }
	
	public ArrayList<Vector3> getVertexPositionsListAsVector() {	// Return vertices as Vector3 list
		ArrayList<Vector3> temp = new ArrayList<Vector3>();
		vertexList.stream().forEach(item -> temp.add(item.getPosition()));
		return temp;
	}
	
	public ArrayList<Vertex3D> getVertexPositionsList() { return vertexList; }	// Return vertexes as Vertex3D list 
	
	public void createEdge(int vertex1, int vertex2) {		// Creating edge between existing vertices
		edgeList.add(new Edge3D(edgeNum, vertexList.get(vertex1), vertexList.get(vertex2)));
		edgeNum++;
	}
	
	public ArrayList<Edge3D> getEdges() { return edgeList; }	// Return edges as list
	
	public static double getAngleBetweenVectors(Vector3 operand1, Vector3 operand2) {	// Calculate angle between couple of vectors
		return Math.acos((operand1.getX() * operand2.getX() + operand1.getY() * operand2.getY() + operand1.getZ() * operand2.getZ()) /
				operand1.getRadius() * operand2.getRadius());
	}
	
	public void createPolygon(int vertex1, int vertex2, int vertex3) {	// Creating polygon between existing vertices
		polygonList.add(new Polygon3D(vertexList.get(vertex1), vertexList.get(vertex2), vertexList.get(vertex3), facesColor, polygonNum));
		polygonNum++;
	}
	
	public Vector3 getPolygonOrientation(Polygon3D polygon) {	// Return an end of normal located on a polygon surface
		return getPointPosition(polygon.getDirection(), false);
	}
	
	public ArrayList<Polygon3D> getFaces() { return polygonList; }	// Getting list of polygons
}