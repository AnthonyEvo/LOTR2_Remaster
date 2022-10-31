package data;

import java.awt.Color;
import UI.UIMapTest;
import UI.TileContainer;

public class GameMapConnector {
	
	/* Карта в виде четырехугольных тайлов с горизонтальным сдвигом.
	 * Размер карты опциональный. Перемещение расчитывать в 8-ми направлениях
	 * Карты 2-х типов, созданные и сгенерированные. 
	 * 
	 * */
	
	public static void main(String args[]) {
		new UIMapTest().uploadContainers(new GlobalMap(8).getMapTiles());
	}
	
	GameMapConnector() {
		
	}
}

class GlobalMap {
	final int mapWidth, mapHeight;
	
	GlobalMapTile[][] globalMapTileGrid;

	GlobalMap(int side) {
		mapWidth = side;
		mapHeight = side;
		
		globalMapTileGrid = new GlobalMapTile[mapWidth][mapHeight];
		
		fillMap();
	}
	
	private void fillMap() {
		for(int i = 0, j = 0; j < globalMapTileGrid[0].length; i++) {
			globalMapTileGrid[i][j] = new GlobalMapTile(GlobalMapTileType.forest);
			
			System.out.print(globalMapTileGrid[i][j].getTileTag() + "\t");
			
			if(i + 1 == globalMapTileGrid.length) {
				i = -1;
				j++;
				System.out.println();
			}
		}
	}
	
	public TileContainer[][] getMapTiles() {
		
		TileContainer[][] temp = new TileContainer[mapWidth][mapHeight];
		
		for(int i = 0, j = 0; j < globalMapTileGrid[0].length; i++) {
			temp[i][j] = new TileContainer(i,j, globalMapTileGrid[i][j].getTileColor());
			
			
			if(i + 1 == globalMapTileGrid.length) {
				i = -1;
				j++;
			}
		}
		
		return temp;
	}
}

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

class LocalMap {
	
}

class LocalMapTile {
	
}

class Building {
	
}

enum GlobalMapTileType{
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

interface Habitable {
	
}

interface Productionable {
	
}