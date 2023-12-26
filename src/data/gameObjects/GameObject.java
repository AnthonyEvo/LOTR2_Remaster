package data.gameObjects;

import data.forms.WireFrame3D;
import data.units.Origin3;

import data.units.Vector3;

public class GameObject {
	
	String goID;
	
	double mass;
	
	public GameObject(String goID) {
		this.goID = goID;
	}
}