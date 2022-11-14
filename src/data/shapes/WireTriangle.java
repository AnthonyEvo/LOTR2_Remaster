package data.shapes;

import data.forms.WireFrame2D;
import data.units.Vector2;
import data.units.Vector2D;

public class WireTriangle extends WireFrame2D{
	public WireTriangle(Vector2 position, double direction, boolean isRad) {
		super("Triangle", 0, isRad);
	}
	
	public WireTriangle(Vector2D line) {
		super("Triangle", 0, false);
		setPosition(line.getMiddle());
		buildWireFrame(new Vector2D(Vector2.subtractVector2(new Vector2(0, 0), Vector2.divideVector2(line.getVector2(), 2)), Vector2.divideVector2(line.getVector2(), 2)));
	}
	
	protected void buildWireFrame(Vector2D line) {
		addVertex(line.getPosition());
		addVertex(line.getEnd());
		addVertex(line.getPosition(), line.getDistance(), /*Math.PI / 3 + */this.getVertexAngle(line.normalize(), true) + angleRad, true);
		
		this.createLink(0, 1); this.createLink(1, 2); this.createLink(2, 0);
	}
}
