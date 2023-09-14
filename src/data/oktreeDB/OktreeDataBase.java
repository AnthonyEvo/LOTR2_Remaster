package data.oktreeDB;

import java.util.TreeMap;

import data.units.Vector3;
import data.IngameObjectsDataBase;
import data.IngameObjectsSetID;
import data.gameObjects.GameObject;

public class OktreeDataBase extends IngameObjectsDataBase{
	
	TreeMap<OktreeDataCell, Vector3> gameSpace = new TreeMap<OktreeDataCell, Vector3>();
	
	OktreeDataCell mainCell;
	
	double layerDepth, sectorMinSize = 10;
	public static int ODBdepth;
	
	public OktreeDataBase(int dbDepth) {
		super(IngameObjectsSetID.octoTreeToolSet);
		ODBdepth = dbDepth;
		mainCell = new OktreeDataCell(dbDepth, new Vector3(), sectorMinSize);
	}
	
	@Override
	synchronized public void place(GameObject gameObject) {
		
	}
	
}