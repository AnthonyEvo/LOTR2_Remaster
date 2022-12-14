package edu;

import java.awt.Graphics;

import UI.UITestMapRender;
import UI.InputHandlers.CommandPromptInputHandler;
import data.forms.WireFrame2D;
import data.forms.WireFrame3D;
import data.shapes2D.WireArrow;
import data.shapes3D.Arrow3D;
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

		renderList3D.add(new Arrow3D(Direction.up));
		renderList3D.add(new Arrow3D(Direction.right));
		renderList3D.add(new Arrow3D(Direction.front));
	}
	
	@Override
	public void paintComponent(Graphics G) {
		scaleMod = mapTestMouseListener.getScaleModifier();
		super.paintComponent(G);
		drawTwoLayerGrid(G);
//		draw2DShape(G, new WireArrow("Coordinate Arrow Y", Math.PI/2, true), Direction.front.getDirectionColor());
//		draw2DShape(G, new WireArrow("Coordinate Arrow X", 0, true), Direction.right.getDirectionColor());
		
		
		renderList2D.stream().forEach(item -> { draw2DShape(G,item); });
		renderList2D.stream().forEach(item -> { drawMarking(G,item, false);});
		renderList3D.stream().forEach(item -> { draw3DShape(G,item); });
		renderList3D.stream().forEach(item -> { draw3DMarking(G,item); });
	}
	
	public void draw3DShape(Graphics g, WireFrame3D shape) {
				
		if(shape.isPolyNeeded()) {
			for(int i = 0; i < shape.getFaces().size(); i++) {
				double faceSlope = WireFrame3D.getAngleBetweenVectors(new Vector3(0, 0, 1), shape.getPointPosition(shape.getFaces().get(i).getNormal().normalize(), false));
				
				g.setColor(shape.getFaces().get(i).getColibratedColor(faceSlope));
				
				if(faceSlope < Math.PI / 2) {
	//				System.out.println("Polygon " + i + " drawn. " + WireFrame3D.getAngleBetweenVectors(new Vector3(0,0,1), shape.getPolygonOrientation(shape.getFaces().get(i))));
					Polygon polygon = new Polygon();
					for(Vector3 pos : shape.getFaces().get(i).getFaceVertexes()) {
						polygon.addPoint(
							(int)((shape.getPointPosition(pos, true)).getX() * scaleMod  + tempHorizontalMapShift), 
							(int)((shape.getPointPosition(pos, true)).getY() * scaleMod + tempVerticalMapShift)
						);
					}
					g.fillPolygon(polygon);
				}
	//			else { System.out.println("Polygon " + i + " drawing skiped " + faceSlope); }
			}
		}
		
		if(shape.isWireframeNeeded()) {
			for(int i = 0; i < shape.getEdges().size(); i++) {
				
					g.setColor(shape.getEdgesColor());
			
					g.drawLine((int)(shape.getPointPosition(shape.getEdges().get(i).getBegin(), true).getX() * scaleMod) + tempHorizontalMapShift, 
							(int)(shape.getPointPosition(shape.getEdges().get(i).getBegin(), true).getY() * scaleMod) + tempVerticalMapShift, 
							(int)(shape.getPointPosition(shape.getEdges().get(i).getEnd(), true).getX() * scaleMod) + tempHorizontalMapShift, 
							(int)(shape.getPointPosition(shape.getEdges().get(i).getEnd(), true).getY() * scaleMod) + tempVerticalMapShift);
				
			}
		}
	}
	
	public void draw3DMarking(Graphics g, WireFrame3D shape) {
		for(int i = 0; i < shape.getFaces().size(); i++) {
			
			g.setColor(Color.green);
			
			g.drawOval(
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getBegin(), true).getX() * scaleMod) + tempHorizontalMapShift - 2,
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getBegin(), true).getY() * scaleMod) + tempVerticalMapShift - 2, 
				3,3
			);
			
			g.drawLine(
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getBegin(), true).getX() * scaleMod) + tempHorizontalMapShift, 
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getBegin(), true).getY() * scaleMod) + tempVerticalMapShift, 
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getEnd(), true).getX() * scaleMod) + tempHorizontalMapShift, 
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getEnd(), true).getY() * scaleMod) + tempVerticalMapShift
			);
			
			g.drawString(
					shape.getFaces().get(i).getPolygonNum() + "",
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getBegin(), true).getX() * scaleMod) + tempHorizontalMapShift + 1,
				(int)(shape.getPointPosition(shape.getFaces().get(i).getNormal().getBegin(), true).getY() * scaleMod) + tempVerticalMapShift + 1
			);
		}
		
		for(int i = 0; i < shape.getVertexPositionsList().size(); i++) {
			g.setColor(shape.getEdgesColor());
			g.drawString(
				shape.getVertexPositionsList().get(i).getName(),
				(int)(shape.getPointPosition(shape.getVertexPositionsListAsVector().get(i), true).getX() * scaleMod) + tempHorizontalMapShift + 1,
				(int)(shape.getPointPosition(shape.getVertexPositionsListAsVector().get(i), true).getY() * scaleMod) + tempVerticalMapShift + 12
			);
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
