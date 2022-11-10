package data.units;

import java.awt.Color;

public enum Direction {
	up,
	down,
	right,
	left,
	front,
	back;
	
	public Color getDirectionColor() {
		switch(this) {
			case up: 
			case down: return Color.blue;
			case right:
			case left: return Color.red;
			case front:
			case back: return Color.green;
			default: return Color.white;
		}
	}
}