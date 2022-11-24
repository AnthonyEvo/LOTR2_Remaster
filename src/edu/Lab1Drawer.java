package edu;

import java.awt.Graphics;

import UI.UITestMapRender;
import UI.InputHandlers.CommandPromptInputHandler;
import data.forms.WireFrame2D;
import data.forms.WireFrame3D;
import data.shapes2D.WireArrow;
import data.units.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;

public class Lab1Drawer extends UITestMapRender {
	
	double scaleMod;
	
	public Lab1Drawer() {
		super();
		this.setBackground(Color.white);
		minViewportAngle = -180;
		maxViewportAngle = 180;
	}
	
	@Override
	public void paintComponent(Graphics G) {
		scaleMod = mapTestMouseListener.getScaleModifier();
		super.paintComponent(G);
		drawTwoLayerGrid(G);
		draw2DShape(G, new WireArrow("Coordinate Arrow Y", Math.PI/2, true), Direction.front.getDirectionColor());
		draw2DShape(G, new WireArrow("Coordinate Arrow X", 0, true), Direction.right.getDirectionColor());
		renderList2D.stream().forEach(item -> { draw2DShape(G,item); });
		renderList2D.stream().forEach(item -> { drawMarking(G,item, false);});
		renderList3D.stream().forEach(item -> { draw3DShape(G,item); });
	}
	
	public void draw3DShape(Graphics g, WireFrame3D shape) {
		

		System.out.println("Face list length of " + shape.getName() + ": " + shape.getEdges().size());
		
/*		for(int i = 0; i < shape.getEdges().size(); i++) {
			g.setColor(shape.getColor());
			
			Polygon polygon = new Polygon();
			for(Vector3 pos : shape.getPoligonVertexesPositions(i)) {
				polygon.addPoint(
					(int)(pos.getX() * scaleMod  + tempHorizontalMapShift), 
					(int)(pos.getY() * scaleMod + tempVerticalMapShift)
				);
			}
			g.fillPolygon(polygon);
		}
		*/
		
		for(int i = 0; i < shape.getEdges().size(); i++) {
			
				g.setColor(shape.getColor());
		
				g.drawLine((int)(shape.getPointPosition(shape.getEdges().get(i).getBegin()).getX() * scaleMod) + tempHorizontalMapShift, 
						(int)(shape.getPointPosition(shape.getEdges().get(i).getBegin()).getY() * scaleMod) + tempVerticalMapShift, 
						(int)(shape.getPointPosition(shape.getEdges().get(i).getEnd()).getX() * scaleMod) + tempHorizontalMapShift, 
						(int)(shape.getPointPosition(shape.getEdges().get(i).getEnd()).getY() * scaleMod) + tempVerticalMapShift);
			
		}
	}
	
	public void draw2DShape(Graphics g, WireFrame2D shape) {
		Color defaultColor = Color.black;
		draw2DShape(g, shape, defaultColor);
	}
	
	public void draw2DShape(Graphics g, WireFrame2D shape, Color color) {
		
		System.out.println("Face list length of " + shape.getName() + ": " + shape.getFaceList().size());
		
		for(int i = 0; i < shape.getFaceList().size(); i++) {
			g.setColor(shape.getColor());
			
			Polygon polygon = new Polygon();
			for(Vector2 pos : shape.getPoligonVertexesPositions(i)) {
				polygon.addPoint(
					(int)(pos.getX() * scaleMod  + tempHorizontalMapShift), 
					(int)(pos.getY() * scaleMod + tempVerticalMapShift)
				);
			}
			g.fillPolygon(polygon);
		}
		
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
	
	protected void drawMarking(Graphics g, WireFrame2D shape, boolean showStrings) {
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
