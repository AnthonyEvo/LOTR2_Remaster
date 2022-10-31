package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import UI.Listeners.*;

public class UIMapTest extends JPanel implements Runnable{
	JFrame mainWindow;
	TileContainer[][] mapTiles;
	
	Thread uiMapTest = new Thread (this, "UI Map Test");
	
	double stepSizeWidth, stepSizeHeight, rawNum, rawMiddle;
	double viewportAngle = 45;
	
	
	MapTestMouseListener mapTestMouseListener = new MapTestMouseListener();
	MapTestKeyListener mapTestKeyListener = new MapTestKeyListener();
	
	public UIMapTest() {
		mainWindow = new JFrame();
		mainWindow.setSize(500, 500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addMouseListener(mapTestMouseListener.attachListener());
		this.addMouseMotionListener(mapTestMouseListener.attachListener());
		mainWindow.addKeyListener(mapTestKeyListener.attachListener());
		
		mainWindow.add(this);
		mainWindow.setVisible(true);
		
		uiMapTest.start();
	}
	
	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		drawMap(G);
	}
	
	public void drawMap(Graphics G) {
		try {
			rawMiddle = (this.getWidth() / 2) - (stepSizeWidth / 2);
			stepSizeWidth = this.getWidth() / mapTiles[0].length; 
			stepSizeHeight = stepSizeWidth * Math.sin(viewportAngle / 180 * Math.PI) / 2;
			
			rawNum = mapTiles.length * 2 - 1;
			
			for(int i = 0, j = 0; j < mapTiles[0].length; i++) {
				
				G.setColor(mapTiles[i][j].getColor());
				G.fillOval((int)((rawMiddle - stepSizeWidth / 2 * j) + i * stepSizeWidth / 2), (int)((stepSizeHeight * j) + stepSizeHeight * i), (int)(stepSizeWidth - 1), (int)(stepSizeHeight - 1));
				G.setColor(Color.black);
				G.drawString((i + 1) + "." + (j + 1) ,(int)((rawMiddle - stepSizeWidth / 2 * j) + i * stepSizeWidth / 2) + 10, (int)((stepSizeHeight * j) + stepSizeHeight * i) + 10);
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
	
	private void getAngleChange() {
		int dragDistance = mapTestMouseListener.getDragVerticalDistance();
		if(viewportAngle + dragDistance * 90 / this.getHeight() >= 0) {
			if(viewportAngle + dragDistance * 90 / this.getHeight() <= 90) {
				viewportAngle += dragDistance * 90 / this.getHeight() * 0.25;
			}
			else { viewportAngle = 90;} 
		} 
		else {
			viewportAngle = 0;
		}
		
		this.repaint();
	}
	
	public void run() {
		try {
			while(true) {
				Thread.sleep(16);
				if(mapTestMouseListener.isMapDraged() && mapTestKeyListener.isShiftPressed()) getAngleChange();
			
			}
		} catch (InterruptedException Ex) { }
	}
	
}