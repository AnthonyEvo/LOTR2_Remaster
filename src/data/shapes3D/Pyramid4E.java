package data.shapes3D;

import data.forms.WireFrame3D;
import data.units.Vector3;

public class Pyramid4E extends WireFrame3D{
	public Pyramid4E(double modX, double modY, double modZ, double radius) {
		super("Pyramid");
		
		setPosition(new Vector3(modX, modY, modZ));
		
		this.createVertex(new Vector3(radius, radius, 0));
		this.createVertex(new Vector3(-radius, radius, 0));
		this.createVertex(new Vector3(-radius, -radius, 0));
		this.createVertex(new Vector3(radius, -radius, 0));
		this.createVertex(new Vector3(0, 0, radius));
				
		this.createEdge(0, 1); this.createEdge(1, 2); this.createEdge(2, 3); this.createEdge(3, 0);
		this.createEdge(0, 4); this.createEdge(1, 4); this.createEdge(2, 4); this.createEdge(3, 4);
		
	}
}
