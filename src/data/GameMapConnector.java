package data;

import UI.UIMapRenderTest;
import edu.Lab1Drawer;

public class GameMapConnector {
	
	public static void main(String args[]) {
		new Lab1Drawer().uploadContainers(new GlobalMap(8).getMapTiles());
	}
	
	GameMapConnector() {
		
	}
}

interface Habitable {
	
}

interface Productionable {
	
}