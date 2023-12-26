package data.oktreeDB;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import data.units.Vector3;
import data.IngameObjectsDataBase;
import data.IngameObjectsSetID;
import data.gameObjects.GameObject;

public class OktreeDataBase extends IngameObjectsDataBase {
	
	ArrayList<OktreeDataCell> gameSpace = new ArrayList<OktreeDataCell>();
	
	double layerDepth, sectorMinSize = 10;
	protected static int ODBdepth;
	double a;
	
	public OktreeDataBase(int dbDepth) {
		super(IngameObjectsSetID.octoTreeToolSet);
		ODBdepth = dbDepth;
		a = sectorMinSize * Math.pow(2, ODBdepth - 1);
		gameSpace.add(new OktreeDataCell(ODBdepth, new Vector3(), sectorMinSize));
	}
	
	@Override
	synchronized public void place(GameObject gameObject) {
		
	}
	
	@Override 
	public GameObject search(String objectID) {
		return null;
	}
}