package data.oktreeDB;

import java.util.ArrayDeque;

import data.gameObjects.GameObject;
import data.units.Vector3;

class OktreeDataCell {
	
	final int cellLayer;
	final Vector3 cellPosition;
	
	double cellSize, minimalCellSize;   
	
	ArrayDeque<GameObject> celledGObjects;
	OktreeDataCell[] celles = new OktreeDataCell[8];  
	
	OktreeDataCell(int cellLayer, Vector3 cellPosition, double minimalCellSize) {
		this.cellLayer = cellLayer;
		this.cellPosition = cellPosition;
		this.minimalCellSize = minimalCellSize;
		cellSize = minimalCellSize * cellLayer;
		
		for(int i = cellLayer; i < OktreeDataBase.ODBdepth; i++ ) { System.out.print(":   "); }
		System.out.println("x: " + cellPosition.getX() + ";y: " + cellPosition.getY() + ";x: " + cellPosition.getZ() + "; " + cellSize);
		
		if(cellLayer > 1) buildCells();
	}
	
	public void addGObjectToCell(GameObject gObject) {
		
	}
	
	protected void buildCells() {
		for(int i = 0; i < 8; i++) {
			double x, y, z;
			
			if(i < 4) { 
				z = cellPosition.getZ() + cellSize / 4;
				if(i < 2) { x = cellPosition.getX() + cellSize / 4; } 
				else { x = cellPosition.getX() - cellSize / 4; }
			} 
			else { 	
				z = cellPosition.getZ() - cellSize / 4;
				if(i < 6) { x = cellPosition.getX() + cellSize / 4; }
				else { x = cellPosition.getX() - cellSize / 4; }
			}
			
			if(i == 0 || i == 3 || i == 4 || i == 7) { y = cellPosition.getY() + cellSize / 4; }
			else { y = cellPosition.getY() - cellSize / 4; }
			
			celles[i] = new OktreeDataCell(cellLayer - 1, new Vector3(x,y, z), minimalCellSize);
		}
	}
}