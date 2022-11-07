package data;

import UI.TileContainer;

public class GlobalMap {
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