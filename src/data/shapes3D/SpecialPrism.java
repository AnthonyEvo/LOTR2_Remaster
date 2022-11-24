package data.shapes3D;

import data.forms.WireFrame3D;
import data.units.Vector3;

public class SpecialPrism extends WireFrame3D {
	
	public SpecialPrism(double modX, double modY, double modZ, double radius) {
		super("Special prism");
		
		setPosition(new Vector3(modX, modY, modZ));
		this.createVertex(new Vector3(radius, radius, -radius));
		this.createVertex(new Vector3(-radius, radius, -radius));
		this.createVertex(new Vector3(-radius, -radius, -radius));
		this.createVertex(new Vector3(radius, -radius, -radius));
		this.createVertex(new Vector3(radius, radius, radius));
		this.createVertex(new Vector3(-radius, radius, radius));
		this.createVertex(new Vector3(-radius, -radius, radius));
		this.createVertex(new Vector3(radius, -radius, radius));
		this.createVertex(new Vector3(0, 0, radius * 2));
				
		this.createEdge(0, 1); this.createEdge(1, 2); this.createEdge(2, 3); this.createEdge(3, 0);
		this.createEdge(0, 4); this.createEdge(1, 5); this.createEdge(2, 6); this.createEdge(3, 7);
		this.createEdge(4, 5); this.createEdge(5, 6); this.createEdge(6, 7); this.createEdge(7, 4);
		this.createEdge(4, 8); this.createEdge(5, 8); this.createEdge(6, 8); this.createEdge(7, 8);
		
		this.createPolygon(0, 1, 2);
		this.createPolygon(2, 3, 0);
		
		this.createPolygon(0, 1, 4);
		this.createPolygon(4, 5, 1);
		
		this.createPolygon(2, 1, 5);
		this.createPolygon(5, 6, 2);
		
		this.createPolygon(2, 3, 6);
		this.createPolygon(7, 6, 2);
		
		this.createPolygon(0, 3, 7);
		this.createPolygon(7, 4, 3);
		
		this.createPolygon(5, 4, 8);
		this.createPolygon(6, 5, 8);
		this.createPolygon(7, 6, 8);
		this.createPolygon(4, 7, 8);
		
	}
}	
