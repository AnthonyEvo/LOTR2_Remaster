package data;

import UI.UIMapRenderTest;

public class GameMapConnector {
	
	public static void main(String args[]) {
		new UIMapRenderTest().uploadContainers(new GlobalMap(8).getMapTiles());
	}
	
	GameMapConnector() {
		
	}
}

interface Habitable {
	
}

interface Productionable {
	
}