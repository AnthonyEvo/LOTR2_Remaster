package data;

import java.util.TreeMap;

import data.units.Vector3;
import data.forms.BoundBox;
import data.gameObjects.GameObject;

class OktreeDataBase extends IngameObjectsDataBase{
	
	TreeMap<OktreeDataCell, Vector3> gameSpace = new TreeMap<OktreeDataCell, Vector3>();
	
	OktreeDataCell mainCell;
	
	double layerDepth, sectorMinSize = 10;
	public static int ODBdepth;
	
	OktreeDataBase(int dbDepth) {
		super(IngameObjectsSetID.octoTreeToolSet);
		ODBdepth = dbDepth;
		mainCell = new OktreeDataCell(dbDepth, new Vector3(), sectorMinSize);
	}
	
	@Override
	public void place(GameObject gameObject) {
		
	}
	
}