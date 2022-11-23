package edu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import UI.UITestMapRender;
import data.forms.WireFrame2D;
import data.forms.WireFrame3D;

public class Lab2to4Drawer extends UITestMapRender {

	double scaleMod;

	protected ArrayList<WireFrame3D> renderList = new ArrayList<WireFrame3D>();
	
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

