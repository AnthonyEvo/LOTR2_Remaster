package edu;

import java.awt.Graphics;

import UI.UIMapRenderTest;
import data.forms.Poly;
import data.shapes.Arrow;
import data.units.*;

import java.awt.Color;
import java.awt.Font;

public class Lab1Drawer extends UIMapRenderTest {
	
	Poly shape;
	double scaleMod;
	
	public Lab1Drawer() {
		super();
		
		this.setBackground(Color.white);
		minViewportAngle = -180;
		maxViewportAngle = 180;
		
		shape = new Poly("triangle", 0, true);
		shape.setPosition(new Vector2(50, 50));
		shape.addVertex(50, 0, false);
		shape.addVertex(50, 120, false);
		shape.addVertex(50, 240, false);
		
		shape.createLink(0, 1);
		shape.createLink(1, 2);
		shape.createLink(2, 0);
	}
	
	@Override
	public void paintComponent(Graphics G) {
		scaleMod = mapTestMouseListener.getScaleModifier();
		super.paintComponent(G);
		drawTwoLayerGrid(G);
		drawPoly(G, shape);
		drawPoly(G, new Arrow(Math.PI/2, true), Direction.front.getDirectionColor());
		drawPoly(G, new Arrow(0, true), Direction.right.getDirectionColor());
		drawMarking(G, shape, false);
	}
	
	public void drawPoly(Graphics g, Poly shape) {
		Color defaultColor = Color.black;
		drawPoly(g, shape, defaultColor);
	}
	
	public void drawPoly(Graphics g, Poly shape, Color color) {
		
		shape.setAngle(tempViewportAngle, false);
		
		for(int i = 0; i < shape.getVertexList().size(); i++) {
			for(Vector2D pos : shape.getVertexLinksPositions(i)) {
				g.setColor(color);
				g.drawLine((int)(pos.getPosition().getX() * scaleMod) + tempHorizontalMapShift, 
						(int)(pos.getPosition().getY() * scaleMod) + tempVerticalMapShift, 
						(int)(pos.getEnd().getX() * scaleMod) + tempHorizontalMapShift, 
						(int)(pos.getEnd().getY() * scaleMod) + tempVerticalMapShift);
			}
		}
	}
	
	protected void drawMarking(Graphics g, Poly shape, boolean showStrings) {
		for(int i = 0; i < shape.getVertexList().size(); i++) {
			for(Vector2D pos : shape.getVertexLinksPositions(i)) {
				g.setColor(Color.orange);
				g.drawLine((int)(pos.getEnd().getX() * scaleMod) - 2 + tempHorizontalMapShift, 
						(int)(pos.getEnd().getY() * scaleMod) + tempVerticalMapShift, 
						(int)(pos.getEnd().getX() * scaleMod) + 2 + tempHorizontalMapShift, 
						(int)(pos.getEnd().getY() * scaleMod) + tempVerticalMapShift);
				
				g.drawLine((int)(pos.getEnd().getX() * scaleMod) + tempHorizontalMapShift, 
						(int)(pos.getEnd().getY() * scaleMod) - 2 + tempVerticalMapShift, 
						(int)(pos.getEnd().getX() * scaleMod) + tempHorizontalMapShift, 
						(int)(pos.getEnd().getY() * scaleMod) + 2 + tempVerticalMapShift);
			}
			
						
			g.setColor(Color.red);
			g.drawOval((int)(shape.getVertexPosition(i).getX() * scaleMod) - 2 + tempHorizontalMapShift, 
				(int)(shape.getVertexPosition(i).getY() * scaleMod) - 2 + tempVerticalMapShift, 5, 5);
						
			if(showStrings) {
				g.setColor(Color.red);
				g.setFont(new Font("Times New Roman", 0, 18));
				g.drawString( "V" + i + ": (" + (double)(Math.round(shape.getVertexPosition(i).getX() * 100) / 100) + "; " + 
				(double)Math.round(shape.getVertexPosition(i).getY() * 100) / 100 + ";"/* + 
				(double)Math.round(shape.getVertexASin(i) * 100) / 100  + "; " +
				(double)Math.round(shape.getVertexACos(i) * 100) / 100*/ + ")",
				(int)(shape.getVertexPosition(i).getX() * scaleMod) + tempHorizontalMapShift, 
				(int)(shape.getVertexPosition(i).getY() * scaleMod) + 14 + tempVerticalMapShift);
			}
		}
		
		g.setColor(Color.green);
		g.drawLine((int)(shape.getAxis().getX() * scaleMod) - 4 + tempHorizontalMapShift, 
				(int)(shape.getAxis().getY() * scaleMod) + tempVerticalMapShift, 
				(int)(shape.getAxis().getX() * scaleMod) + 4 + tempHorizontalMapShift, 
				(int)(shape.getAxis().getY() * scaleMod) + tempVerticalMapShift);
		
		g.drawLine((int)(shape.getAxis().getX() * scaleMod) + tempHorizontalMapShift, 
				(int)(shape.getAxis().getY() * scaleMod) - 4 + tempVerticalMapShift, 
				(int)(shape.getAxis().getX() * scaleMod) + tempHorizontalMapShift, 
				(int)(shape.getAxis().getY() * scaleMod) + 4 + tempVerticalMapShift);
		if(showStrings) {
				g.drawString( "Ax" + ": (" + (double)(Math.round(shape.getAxis().getX() * 100) / 100) 
						+ ";" + (double)Math.round(shape.getAxis().getY() * 100) / 100 + ")",
				(int)(shape.getAxis().getX() * scaleMod) + tempHorizontalMapShift, 
				(int)(shape.getAxis().getY() * scaleMod) + 14 + tempVerticalMapShift);
				
				g.setColor(Color.black);
				g.drawString( "Shape angle: degrees:" + shape.getAngle(false) + " radians: " + shape.getAngle(true), 2, 14);
		}
	}
	
	protected void drawTwoLayerGrid(Graphics g) {
		if(scaleMod > 6) drawSimpleGrid(g, 1, new Color(245, 245, 245));
		drawSimpleGrid(g, 10, new Color(235, 235, 235));
	}
	
	
	protected void drawSimpleGrid(Graphics g, int stepSize, Color color) {
		g.setColor(color);
		for(int i = 0 ; i < Math.abs(this.getWidth() / (stepSize * scaleMod)) + 1; i++) {
			g.drawLine((int) (((tempHorizontalMapShift) % (stepSize * scaleMod)) + (stepSize * scaleMod * i)), 
					(int) 0, 
					(int) (((tempHorizontalMapShift) % (stepSize * scaleMod)) + (stepSize * scaleMod * i)), 
					(int) this.getHeight());
		}
		
		for(int i = 0 ; i < this.getWidth() / (stepSize * scaleMod); i++) {
			g.drawLine((int) 0, 
					(int) (((tempVerticalMapShift) % (stepSize * scaleMod)) + (stepSize * scaleMod * i)), 
					(int) this.getWidth(), 
					(int) (((tempVerticalMapShift) % (stepSize * scaleMod)) + (stepSize * scaleMod * i)));
		}
		
		
		
	}
}
