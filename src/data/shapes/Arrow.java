package data.shapes;

import data.forms.Poly;
import data.units.Direction;

public class Arrow extends Poly {
	
	Direction direction;
	
	public Arrow(double direction, boolean isRad) {
		super("Coordinate Arrow", 0, isRad);
		buildArrow(direction, isRad);
	}
	
	private void buildArrow(double direction, boolean isRad) {
		addVertex(0, 0, isRad);
		addVertex(50, direction, isRad); this.createLink(0, 1);
		addVertex(getVertex(1).getPosition(), 8 , Math.toDegrees(direction) - 155, false);
		createLink(1, 2);
		addVertex(getVertex(1).getPosition(), 8 , Math.toDegrees(direction) - 205, false);
		createLink(1, 3);
	}
}