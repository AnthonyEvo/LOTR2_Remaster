package UI;

import javax.swing.*;
import data.spaceUnits.*;


public class UIGameMapRenderer extends JPanel {
	int panelWidth, panelHeights;
	
	Shape2D shape;
	
	UIGameMapRenderer() {
		shape = new Shape2D("Dot", 180, false);
		shape.addVertex(new Vector2(-12d, -17d));
		
		System.out.println(Math.cos(shape.getVertexCos(0)) * shape.getVertex(0).getRadius());
		System.out.println(Math.sin(shape.getVertexSin(0)) * shape.getVertex(0).getRadius());
		shape.logAngles();
	}
	
	public static void main(String args[]) {
		new UIGameMapRenderer();
	}
}
