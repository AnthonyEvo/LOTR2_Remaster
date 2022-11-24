package UI;

import javax.swing.*;
import java.awt.Color;

import data.forms.Polygon3D;
import data.forms.Vertex3D;
import data.forms.WireFrame2D;
import data.forms.WireFrame3D;
import data.shapes3D.*;
import data.units.*;


public class UIGameMapRenderer extends JPanel {
		
	UIGameMapRenderer() {
		
		Polygon3D polygon = new Polygon3D(new Vertex3D(0, new Vector3(10,10,0)), new Vertex3D(1, new Vector3(-10,-10,0)), new Vertex3D(2, new Vector3(0,0,10)), Color.gray);
		
		System.out.println(polygon.getNormal().getBegin().getX() + " " + polygon.getNormal().getBegin().getY() + " " + polygon.getNormal().getBegin().getZ() );
		System.out.println(polygon.getNormal().getEnd().getX() + " " + polygon.getNormal().getEnd().getY() + " " + polygon.getNormal().getEnd().getZ() );
	}
	
	public static void main(String args[]) {
		new UIGameMapRenderer();	
	}
}
