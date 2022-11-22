package data.shapes2D;

import data.forms.WireFrame2D;

public class WireArrow extends WireFrame2D {
		
	public WireArrow(String name, double direction, boolean isRad) {
		super(name, 0, isRad);
		buildWireFrame(direction, isRad);
	}
	
	protected void buildWireFrame(double direction, boolean isRad) {
		addVertex(0, 0, isRad);
		addVertex(50, direction, isRad); this.createLink(0, 1);
		addVertex(getVertex(1).getPosition(), 8 , Math.toDegrees(direction) - 155, false);
		createLink(1, 2);
		addVertex(getVertex(1).getPosition(), 8 , Math.toDegrees(direction) - 205, false);
		createLink(1, 3);
	}
}