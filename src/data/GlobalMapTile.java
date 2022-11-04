package data;

import java.awt.Color;

class GlobalMapTile {
	GlobalMapTileType tileType;
	
	GlobalMapTile (GlobalMapTileType tileType) {
		this.tileType = tileType;
	}
	
	public Color getTileColor() {
		return tileType.getColor();
	}
	
	public String getTileTag() {
		return tileType.getTag();
	}
}