package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class UIMapTest extends JPanel {
	JFrame mainWindow;
	TileContainer[][] mapTiles;
	
	float stepSizeWidth, stepSizeHeight;
	
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
			stepSizeWidth = this.getWidth() / mapTiles.length;
			stepSizeHeight = this.getHeight() / mapTiles[0].length;
			
			for(int i = 0, j = 0; j < mapTiles[0].length; i++) {
				
				G.setColor(mapTiles[i][j].getColor());
				G.fillOval((int)(1 + stepSizeWidth * i), (int)(1 + stepSizeHeight * j), (int)(stepSizeWidth - 1), (int)(stepSizeHeight - 1));
				
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