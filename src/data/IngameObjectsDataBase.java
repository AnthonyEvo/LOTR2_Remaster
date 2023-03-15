package data;

public class IngameObjectsDataBase {
	
	IngameObjectsSetID baseSetID;
	
	public IngameObjectsDataBase(IngameObjectsSetID setID) {
		baseSetID = setID;
	}
	
	public void volumetricSearch() {
		
	}
}

class OktreeDataBase {
	double layerDepth, sectorMinSize = 10;
	
	OktreeDataBase() {
		
	}
}