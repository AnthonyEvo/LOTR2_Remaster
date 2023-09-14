package data;

import java.util.ArrayList;

import data.forms.WireFrame3D;

public class IngameDataBaseRecord {
	
	ArrayList<String> recordID = new ArrayList<String>(1);
	
	WireFrame3D volumetricForm;
	
	protected IngameDataBaseRecord(WireFrame3D volumetricForm) {
		this.volumetricForm = volumetricForm;
	}
	
	public WireFrame3D getVolumetricForm() { return volumetricForm; }
	
	public ArrayList<String> getRecordIDList() { return recordID; }
}
