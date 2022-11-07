package data;

import java.awt.Color;

public enum GlobalMapTileType{
	forest,
	mountain,
	field,
	sea;
	
	public Color getColor() {
		switch(this) {
			case forest: return Color.green;
			case mountain: return Color.darkGray;
			case field: return Color.lightGray;
			case sea: return Color.cyan;
			default: return Color.white;
		}
	}
	
	public String getTag() {
		switch(this) {
			case forest: return "[FRT]";
			case mountain: return "[MTN]";
			case field: return "[FLD]";
			case sea: return "[SEA]";
			default: return "[EPT]";
		}
	}
}