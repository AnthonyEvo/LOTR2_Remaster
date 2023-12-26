package data;

import java.util.ArrayList;

import data.forms.WireFrame3D;
import data.gameObjects.GameObject;

public class IngameDataBaseRecord {
	
	ArrayList<String> recordID = new ArrayList<String>(1);
	
	WireFrame3D volumetricForm;
	GameObject recordData;
	
	protected IngameDataBaseRecord(WireFrame3D volumetricForm, GameObject data) {
		this.volumetricForm = volumetricForm;
		recordData = data;
	}
	
	public WireFrame3D getVolumetricForm() { return volumetricForm; }
	
	public ArrayList<String> getRecordIDList() { return recordID; }
}
