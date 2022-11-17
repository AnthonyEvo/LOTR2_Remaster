package UI;

import javax.swing.*;

import data.forms.WireFrame2D;
import data.units.*;


public class UIGameMapRenderer extends JPanel {
		
	UIGameMapRenderer() {
		Vector3D vect1 = new Vector3D(new Vector3(1,5,1), new Vector3(11,5,11));
		Vector3D vect2 = new Vector3D(new Vector3(14,-4,25), new Vector3(14,16,25));
		
		Vector3D vect3 = Vector3D.multiplyVectors(vect1, vect2);
		
		System.out.println(
			"X1: " + vect3.getBegin().getX() + " Y1: " + vect3.getBegin().getY() + " Z1: " + vect3.getBegin().getZ() + "\n" +
			"X2: " + vect3.getEnd().getX() + " Y2: " + vect3.getEnd().getY() + " Z2: " + vect3.getEnd().getZ()
		);
	}
	
	public static void main(String args[]) {
		new UIGameMapRenderer();
	}
}
