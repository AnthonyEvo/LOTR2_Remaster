package data.gameObjects;

import java.awt.Color;

public class GlobalMapTile {
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