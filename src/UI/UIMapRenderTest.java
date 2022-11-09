package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

import UI.Listeners.*;
import data.spaceUnits.Shape2D;
import data.spaceUnits.Vector2;
import data.spaceUnits.Vector2D;
import edu.Lab1Drawer;

public class UIMapRenderTest extends JPanel implements Runnable{
	JFrame mainWindow;
	TileContainer[][] mapTiles;
	
	Thread uiMapTest = new Thread (this, "UI Map Test");
	
	double stepSizeWidth, stepSizeHeight, rawNum, rawMiddle;
	protected double viewportAngle = 45, tempViewportAngle = viewportAngle;
	
	protected int horizontalMapShift = 0, tempHorizontalMapShift = horizontalMapShift;
	protected int verticalMapShift = 0, tempVerticalMapShift = verticalMapShift;
		
	MapTestMouseListener mapTestMouseListener = new MapTestMouseListener();
	MapTestKeyListener mapTestKeyListener = new MapTestKeyListener();
	
	public UIMapRenderTest() {
		mainWindow = new JFrame();
		mainWindow.setSize(500, 500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addMouseListener(mapTestMouseListener.attachListener());
		this.addMouseMotionListener(mapTestMouseListener.attachListener());
		this.addMouseWheelListener(mapTestMouseListener.attachListener());
		mainWindow.addKeyListener(mapTestKeyListener.attachListener());
		
		mainWindow.add(this);
		mainWindow.setVisible(true);
		
		
		
		uiMapTest.start();
	}
	
	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
//		drawMap(G);
	}
	
	public void drawMap(Graphics G) {
		try {
			stepSizeWidth = (this.getWidth() / mapTiles[0].length) * mapTestMouseListener.getScaleModifier(); 
			stepSizeHeight = stepSizeWidth * Math.sin(tempViewportAngle / 180 * Math.PI) / 2;
			rawMiddle = (this.getWidth() / 2) - (stepSizeWidth / 2);
			
			rawNum = mapTiles.length * 2 - 1;
			
			for(int i = 0, j = 0; j < mapTiles[0].length; i++) {
				
				G.setColor(mapTiles[i][j].getColor());
				G.fillOval((int)((rawMiddle - stepSizeWidth / 2 * j) + i * stepSizeWidth / 2) + tempHorizontalMapShift,
						(int)((stepSizeHeight * j) + stepSizeHeight * i) + tempVerticalMapShift, 
						(int)(stepSizeWidth - 1),
						(int)(stepSizeHeight - 1));
				G.setColor(Color.black);
				G.drawString((i + 1) + "." + (j + 1) ,
						(int)((rawMiddle - stepSizeWidth / 2 * j) + i * stepSizeWidth / 2) + 10 + tempHorizontalMapShift, 
						(int)((stepSizeHeight * j) + stepSizeHeight * i) + 10 + tempVerticalMapShift);
				if(i + 1 == mapTiles.length) {
					i = -1;
					j++;
				}
			}
		} catch(Exception Ex) { }
	}
	
	public void uploadContainers(TileContainer[][] mapTiles) {
		this.mapTiles = mapTiles;
		this.repaint();
	}
	
	protected double setViewportAngle() {
		double tempDragDistance = mapTestMouseListener.listener.getDragVerticalDistance(true); 
		
		tempViewportAngle = viewportAngle;
		
		if(viewportAngle + tempDragDistance * 90 / this.getHeight() >= 0) {
			if(viewportAngle + tempDragDistance * 90 / this.getHeight() <= 90) {
				tempViewportAngle += tempDragDistance * 90 / this.getHeight() /*0.25*/;
			}
			else { tempViewportAngle = 90;} 
		} 
		else { tempViewportAngle = 0; }
		
		this.repaint();
		return tempViewportAngle;
	}
	
	protected void setFocusPosition() {
		tempHorizontalMapShift = horizontalMapShift + mapTestMouseListener.listener.getDragHorizontalDistance(false);
		tempVerticalMapShift = verticalMapShift + mapTestMouseListener.listener.getDragVerticalDistance(false);
		
		this.repaint();
	}
	
	public void run() {
		try {
			while(true) {
				Thread.sleep(16);
				if(mapTestMouseListener.isMapDraged() && mapTestKeyListener.isShiftPressed()) { setViewportAngle(); }
				if(mapTestMouseListener.isMapDraged() && !mapTestKeyListener.isShiftPressed()) setFocusPosition();
				if(mapTestMouseListener.isScaleChanged()) { /*setViewportAngle()*/this.repaint(); }
				if(!mapTestMouseListener.isM1Pressed()) { 
					viewportAngle = tempViewportAngle;
					horizontalMapShift = tempHorizontalMapShift;
					verticalMapShift = tempVerticalMapShift;
				}
			}
		} catch (InterruptedException Ex) { }
	}
	
}