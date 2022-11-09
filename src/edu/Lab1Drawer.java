package edu;

import java.awt.Graphics;

import UI.UIMapRenderTest;
import data.spaceUnits.*;

public class Lab1Drawer extends UIMapRenderTest {
	
	Shape2D shape;
	
	public Lab1Drawer() {
		super();
		
		shape = new Shape2D("triangle", 0, true);
		shape.setPosition(new Vector2(50, 50));
		shape.addVertex(new Vector2(-20, 0));
		shape.addVertex(new Vector2(20, 0));
		shape.addVertex(new Vector2(0, 45));
		
		shape.createLink(0, 1);
		shape.createLink(1, 2);
		shape.createLink(0, 2);
	}
	
	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		drawShape(G);
	}
	
	public void drawShape(Graphics g) {
		
		for(int i = 0; i < shape.getVertexList().size(); i++) {
			for(Vector2D pos : shape.getVertex(i).getSubVertexList()) {
				g.drawLine((int)pos.getPosition().getX() + tempHorizontalMapShift, 
						(int)pos.getPosition().getY() + tempVerticalMapShift, 
						(int)pos.getEnd().getX() + tempHorizontalMapShift, 
						(int)pos.getEnd().getY() + tempVerticalMapShift);
				
			}
		}
	}
}
