package data.shapes;

import data.forms.WireFrame2D;
import data.units.Vector2;
import data.units.Vector2D;

public class WireTriangle extends WireFrame2D{
	public WireTriangle(Vector2 position, double direction, boolean isRad) {
		super("Trangle", 0, isRad);
	}
	
	protected void buildWireFrame(Vector2D line) {
		addVertex(line.getPosition());
		addVertex(line.getEnd());
		addVertex(line.getPosition(), line.getDistance(), Math.PI * 2 / 3, true);
	}
}
