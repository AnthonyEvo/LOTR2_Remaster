package data;

import java.util.TreeMap;

import data.units.Vector3;

public class IngameObjectsDataBase {
	
	IngameObjectsSetID baseSetID;
	
	public IngameObjectsDataBase(IngameObjectsSetID setID) {
		baseSetID = setID;
	}
	
	public void volumetricSearch() {
		
	}
	
	public static void main(String args[]) {
		new OktreeDataBase(3);
	}
}

class OktreeDataBase {
	
	TreeMap<OktreeDataCell, Vector3> gameSpace = new TreeMap<OktreeDataCell, Vector3>();
	
	OktreeDataCell mainCell;
	
	double layerDepth, sectorMinSize = 10;
	public static int ODBdepth;
	
	OktreeDataBase(int dbDepth) {
		ODBdepth = dbDepth;
		mainCell = new OktreeDataCell(dbDepth, new Vector3(), sectorMinSize);
	}
	
	
	
}