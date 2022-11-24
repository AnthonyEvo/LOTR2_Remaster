package UI;

import javax.swing.*;

import data.forms.WireFrame2D;
import data.forms.WireFrame3D;
import data.units.*;


public class UIGameMapRenderer extends JPanel {
		
	UIGameMapRenderer() {
		System.out.println(WireFrame3D.getAngleBetweenVectors(new Vector3(10,0,0), new Vector3(-10,0,0)));
	}
	
	public static void main(String args[]) {
		new UIGameMapRenderer();
	}
}
