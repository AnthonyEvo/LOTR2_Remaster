package data.oktreeDB;

import java.util.ArrayDeque;
import java.util.ArrayList;

import data.gameObjects.GameObject;
import data.units.Vector3;
import data.units.Vector3D;

class OktreeDataCell {
	
	final int cellLayer;
	final Vector3 cellPosition;
	
	OktreeDataBase attachedBase;
	
	ArrayList<Vector3> cellCorners;
	
	double cellSize, minimalCellSize;   
	
	ArrayDeque<GameObject> celledGObjects;
	OktreeDataCell[] celles = new OktreeDataCell[8];  
	
	OktreeDataCell(int cellLayer, Vector3 cellPosition, double minimalCellSize) {
		this.cellLayer = cellLayer;
		this.cellPosition = cellPosition;
		this.minimalCellSize = minimalCellSize;
		
		cellSize = minimalCellSize * Math.pow(2, cellLayer - 1);
		
		cellCorners = calculateCorners();
		
		for(int i = cellLayer; i < OktreeDataBase.ODBdepth; i++ ) { System.out.print(":   "); }
		System.out.println("x: " + cellPosition.getX() + ";y: " + cellPosition.getY() + ";x: " + cellPosition.getZ() + "; " + cellSize);
		
		if(cellLayer > 1) buildCells();
	}
	
	public void addGameObjectToCell(GameObject gObject) {
		
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
	
	protected ArrayList<Vector3> calculateCorners() {
		ArrayList<Vector3> corners = new ArrayList<Vector3>(8);
		corners.add( new Vector3(cellPosition.getX() + cellSize / 2, cellPosition.getY() + cellSize / 2, cellPosition.getZ() + cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() + cellSize / 2, cellPosition.getY() - cellSize / 2, cellPosition.getZ() + cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() - cellSize / 2, cellPosition.getY() - cellSize / 2, cellPosition.getZ() + cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() - cellSize / 2, cellPosition.getY() + cellSize / 2, cellPosition.getZ() + cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() + cellSize / 2, cellPosition.getY() + cellSize / 2, cellPosition.getZ() - cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() + cellSize / 2, cellPosition.getY() - cellSize / 2, cellPosition.getZ() - cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() - cellSize / 2, cellPosition.getY() - cellSize / 2, cellPosition.getZ() - cellSize / 2));
		corners.add( new Vector3(cellPosition.getX() - cellSize / 2, cellPosition.getY() + cellSize / 2, cellPosition.getZ() - cellSize / 2));	
		return corners;
	}
	
	public ArrayList<Vector3> getCornerCoords() { return cellCorners; }
	
	public CellIntersection getCellIntersection(Vector3 requestPos,  double sychRadius) {
		
		if(cellCorners.stream().allMatch(corner -> new Vector3D(corner, requestPos).getDistance() < sychRadius)) { 
			return CellIntersection.fullIntersection; 
		}
		else if(!cellCorners.stream().allMatch(corner -> new Vector3D(corner, requestPos).getDistance() < sychRadius)) {
			return CellIntersection.fullIntersection;
		}
		else {
			return CellIntersection.fullIntersection;
		}
	}
	
	
}