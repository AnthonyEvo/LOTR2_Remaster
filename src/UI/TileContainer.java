package UI;

import java.awt.Color;

public class TileContainer {
	
	int width, height;
	Color color;
	
	public TileContainer(int width, int height, Color color) {
		this.width = width; this.height = height; this.color = color;
	}
	
	public int getWidth() { return width; }
	
	public int getHeight( ) { return height; }
	
	public Color getColor() { return color; }
} 