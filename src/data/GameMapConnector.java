package data;

import UI.UIMapTest;

public class GameMapConnector {
	
	public static void main(String args[]) {
		new UIMapTest().uploadContainers(new GlobalMap(8).getMapTiles());
	}
	
	GameMapConnector() {
		
	}
}

interface Habitable {
	
}

interface Productionable {
	
}