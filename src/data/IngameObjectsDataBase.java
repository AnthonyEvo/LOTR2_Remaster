package data;

import data.gameObjects.GameObject;

public class IngameObjectsDataBase {
	
	IngameObjectsSetID baseSetID;
	
	public IngameObjectsDataBase(IngameObjectsSetID setID) {
		baseSetID = setID;
	}
	
	public void search() {
		
	}
	
	public void place(GameObject gameObject) {
		
	}
	
	public void drop() {
		
	}
	
	public static void main(String args[]) {
		new OktreeDataBase(3);
	}
}