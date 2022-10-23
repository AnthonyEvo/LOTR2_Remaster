package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class UIMapTest extends JPanel {
	JFrame mainWindow;
	TileContainer[][] mapTiles;
	
	double stepSizeWidth, stepSizeHeight, rawNum, rawMiddle;
	
	public UIMapTest() {
		mainWindow = new JFrame();
		mainWindow.setSize(500, 500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainWindow.add(this);
		mainWindow.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		drawMap(G);
	}
	
	public void drawMap(Graphics G) {
		try {
			stepSizeWidth = this.getWidth() / mapTiles.length/* * Math.sqrt(2)*/;
			rawMiddle = (this.getWidth() / 2) - (stepSizeWidth / 2);
			stepSizeHeight = this.getHeight() / rawNum;
			rawNum = mapTiles.length * 2 - 1;
			
			for(int i = 0, j = 0; j < mapTiles[0].length; i++) {
				
				G.setColor(mapTiles[i][j].getColor());
				G.fillOval((int)((rawMiddle - stepSizeWidth / 2 * j) + i * stepSizeWidth / 2), (int)((stepSizeHeight * j) + stepSizeHeight * i), (int)(stepSizeWidth - 1), (int)(stepSizeHeight - 1));
				G.setColor(Color.black);
				G.drawString((i + 1) + "." + (j + 1) ,(int)((rawMiddle - stepSizeWidth / 2 * j) + i * stepSizeWidth / 2) + 10, (int)((stepSizeHeight * j) + stepSizeHeight * i) + 10);
				//G.fillOval((int)(1 + stepSizeWidth * i), (int)(1 + stepSizeHeight * j), (int)(stepSizeWidth - 1), (int)(stepSizeHeight - 1));
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
	
}