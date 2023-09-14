package data.gameObjects;

import data.forms.WireFrame3D;
import data.units.Origin3;

import data.units.Vector3;

public class GameObject {
	
	String goID;
	
	Origin3 origin; Vector3 axis;	
	WireFrame3D shapes; Vector3 impulse;
	
	double mass;
	
	public GameObject(String goID) {
		this.goID = goID;
	}
}