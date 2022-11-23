package UI;

import javax.swing.*;

import data.forms.WireFrame2D;
import data.forms.WireFrame3D;
import data.units.*;


public class UIGameMapRenderer extends JPanel {
		
	UIGameMapRenderer() {
		WireFrame3D shape = new WireFrame3D("test");
		shape.createVertex(new Vector3(10, 10, 10));
		shape.createVertex(new Vector3(-10, 10, 10));
		shape.createVertex(new Vector3(-10, -10, 10));
		shape.createVertex(new Vector3(10, -10, 10));
		shape.createVertex(new Vector3(10, 10, -10));
		shape.createVertex(new Vector3(-10, 10, -10));
		shape.createVertex(new Vector3(-10, -10, -10));
		shape.createVertex(new Vector3(10, -10, -10));
		
		Origin3 origin = shape.getOrientation();
		origin.setAlpha(Math.PI / 3);
		origin.setBeta(Math.PI / 4);
		
		shape.getVertexPositionsList().stream().forEach(item -> 
			{
				Vector3 temp = WireFrame3D.getPointPosition(item, origin);
				System.out.println(temp.getX() + " " + temp.getY() + " " + temp.getZ());
			}
		);
	}
	
	public static void main(String args[]) {
		new UIGameMapRenderer();
	}
}
