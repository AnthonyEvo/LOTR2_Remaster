package data;

import java.util.ArrayList;

import data.gameObjects.GameObject;
import data.oktreeDB.OktreeDataBase;

abstract public class IngameObjectsDataBase {
	
	IngameObjectsSetID baseSetID;
	long baseSincCounter;
	
	public IngameObjectsDataBase(IngameObjectsSetID setID) {
		baseSetID = setID;
	}
	
	public GameObject search(String objectID) { return null; }
	
	public ArrayList<GameObject> searchFew(String objectID) { return null; }
	
	public void place(GameObject gameObject) {  }
	
	public void drop(String objectID) { }
	
	public static void main(String args[]) {
		new OktreeDataBase(3);
	}
}