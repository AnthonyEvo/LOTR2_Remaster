package data.objects;

import data.forms.WireFrame3D;
import data.gameObjects.GameObject;
import data.units.Origin3;
import data.units.Vector3;

public class IngameCamera extends GameObject{
	
	Vector3 focus;
	int resolutionX, resolutionY;
	
	double cameraViewDistance;
	
	IngameCamera(String cameraID) {
		super(cameraID);
	}
}