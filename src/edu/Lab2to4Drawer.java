package edu;

import java.awt.Color;
import java.awt.Graphics;

import UI.UIMapRenderTest;

public class Lab2to4Drawer extends UIMapRenderTest {

	double scaleMod;
	
	public Lab2to4Drawer() {
		super();
		this.setBackground(Color.white);
		minViewportAngle = -180;
		maxViewportAngle = 180;
	}
	
	@Override
	public void paintComponent(Graphics G) {
		
	}
}

