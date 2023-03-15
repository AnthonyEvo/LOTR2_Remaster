package data.forms;

import java.util.*;

import data.units.Vector3;
import data.units.Vector3D;

public class BoundBox {
		
	SortedMap<Double, Vector3> widthMap, lengthMap, heightMap;
	double collisionSphereRadius = 0;
	
	protected Vector3 midPoint = new Vector3();
	
	public BoundBox() {
		widthMap =  new TreeMap<Double, Vector3>();
		lengthMap =  new TreeMap<Double, Vector3>();
		heightMap =  new TreeMap<Double, Vector3>();
	}
	
	public void addVertex(Vector3 vertex, int mapCount) {
		
		calculateMidPoint(vertex, mapCount);
		
		widthMap.put(vertex.getX(), vertex);
		lengthMap.put(vertex.getY(), vertex);
		heightMap.put(vertex.getZ(), vertex);
		
		getCollisionSphereRadius();
	}
	
	public double getWidth() { return Math.abs(widthMap.lastKey() - widthMap.firstKey()); }
	
	public double getLength() { return Math.abs(lengthMap.lastKey() - lengthMap.firstKey()); }
	
	public double getHeight() { return Math.abs(heightMap.lastKey() - heightMap.firstKey()); }
	
	public Vector3 getMidPoint() { return midPoint; }
	
	public double getCollisionRadius() { return collisionSphereRadius; }
	
	public void calculateMidPoint(Vector3 position, int pointCount) {
		System.out.println(pointCount);
		if(pointCount < 1) {
			midPoint = position;
		}
		else {
			midPoint = new Vector3(
				(midPoint.getX() * pointCount + position.getX()) / (pointCount + 1),
				(midPoint.getY() * pointCount + position.getY()) / (pointCount + 1),
				(midPoint.getZ() * pointCount + position.getZ()) / (pointCount + 1)
			);
		}
	}
	
	public void getCollisionSphereRadius() {
		Vector3[] corners = new Vector3[] { 
			new Vector3( widthMap.firstKey(), lengthMap.lastKey(), heightMap.firstKey()), 
			new Vector3( widthMap.firstKey(), lengthMap.lastKey(), heightMap.lastKey()),
			new Vector3( widthMap.firstKey(), lengthMap.firstKey(), heightMap.firstKey()),
			new Vector3( widthMap.firstKey(), lengthMap.firstKey(), heightMap.lastKey()),
			new Vector3( widthMap.lastKey(), lengthMap.firstKey(), heightMap.firstKey()),
			new Vector3( widthMap.lastKey(), lengthMap.firstKey(), heightMap.lastKey()),
			new Vector3( widthMap.lastKey(), lengthMap.lastKey(), heightMap.firstKey()),
			new Vector3( widthMap.lastKey(), lengthMap.lastKey(), heightMap.lastKey())
		};
		
		double temp = 0;
		
		for(Vector3 x : corners) {
			if(temp < new Vector3D(midPoint, x).getDistance()) temp = new Vector3D(midPoint, x).getDistance();
		}
		collisionSphereRadius = temp;
		System.out.println(" Collision radius: " + collisionSphereRadius);
	}
}