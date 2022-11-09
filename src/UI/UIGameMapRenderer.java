package UI;

import javax.swing.*;
import data.spaceUnits.*;


public class UIGameMapRenderer extends JPanel {
	int panelWidth, panelHeights;
	Shape2D shape;
	
	UIGameMapRenderer() {
		shape = new Shape2D("triangle", 0, true);
		shape.setPosition(new Vector2(50, 50));
		shape.addVertex(new Vector2(-20, -1));
		shape.addVertex(new Vector2(21, 1));
		shape.addVertex(new Vector2(0, 45));
		
		shape.createLink(0, 1);
		shape.createLink(1, 2);
		shape.createLink(0, 2);
		
		for(int i = 0; i < shape.getVertexList().size(); i++) {
			
			System.out.println(shape.getVertex(i).getSubVertexList().size());
			for(Vector2D pos : shape.getVertex(i).getSubVertexList()) {
				System.out.println((int)pos.getX() + "," + (int)pos.getY() + "; " + (int)pos.getEnd().getX() + "," + (int)pos.getEnd().getX());
				
			}
		}
	}
	
	public static void main(String args[]) {
		new UIGameMapRenderer();
	}
}
