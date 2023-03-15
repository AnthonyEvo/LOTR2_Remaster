package data;

import java.util.ArrayDeque;

import data.gameObjects.GameObject;
import data.units.Vector3;

class OktreeDataCell {
	
	final int cellLayer;
	Vector3 cellPosition;
	
	ArrayDeque<GameObject> celledGObjects;
	OktreeDataCell[] celles = new OktreeDataCell[8];
	
	OktreeDataCell(int  cellLayer, Vector3 cellPosition) {
		this.cellLayer = cellLayer;
		this.cellPosition = cellPosition;
	}
	
	public void addGObjectToCell(GameObject gObject) {
		
	}
	
	protected void buildCells() {
		
		for(int i = 0; i < 8; i++) {
			
		}
	}
}