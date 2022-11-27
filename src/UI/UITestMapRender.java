package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

import UI.InputHandlers.CommandPromptInputHandler;
import UI.Listeners.*;
import data.forms.WireFrame2D;
import data.forms.WireFrame3D;
import data.units.Origin3;

public class UITestMapRender extends JPanel implements Runnable{
	protected JFrame mainWindow;
	protected JTextPane inputPane;
	
	TileContainer[][] mapTiles;
	
	Thread uiMapTest = new Thread (this, "UI Map Test");
	
	double stepSizeWidth, stepSizeHeight, rawNum, rawMiddle;
	protected double viewportAngle = 0, tempViewportAngle = viewportAngle;
	protected double minViewportAngle = 0, maxViewportAngle = 90;
	protected Origin3 positionModifier = new Origin3(), tempPositionModifier = new Origin3();
	
	protected int horizontalMapShift = 20, tempHorizontalMapShift = horizontalMapShift;
	protected int verticalMapShift = 10, tempVerticalMapShift = verticalMapShift;
	
	protected MapTestMouseListener mapTestMouseListener = new MapTestMouseListener();
	protected MapTestKeyListener mapTestKeyListener = new MapTestKeyListener();
	protected CommandPromptListener CPKeyListener = new CommandPromptListener();
	
	protected CommandPromptInputHandler cpInputHandler;
	
	protected ArrayList<WireFrame2D> renderList2D = new ArrayList<WireFrame2D>();
	protected ArrayList<WireFrame3D> renderList3D = new ArrayList<WireFrame3D>();
	
	public UITestMapRender() {
		
		mainWindow = new JFrame();
		mainWindow.setSize(600, 550);
		mainWindow.setLayout(new FlowLayout());
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		
		inputPane = new JTextPane();
		inputPane.setPreferredSize(new Dimension(mainWindow.getContentPane().getWidth(), 35));
		inputPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setPreferredSize(new Dimension(mainWindow.getWidth(), mainWindow.getContentPane().getHeight() - 35));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.addMouseListener(mapTestMouseListener.attachListener());
		this.addMouseMotionListener(mapTestMouseListener.attachListener());
		this.addMouseWheelListener(mapTestMouseListener.attachListener());
		this.addKeyListener(mapTestKeyListener.attachListener());
		inputPane.addKeyListener(CPKeyListener.attachListener());
		
		cpInputHandler = new CommandPromptInputHandler(renderList2D, renderList3D, CPKeyListener, inputPane);
		
		mainWindow.add(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		mainWindow.add(inputPane);
		mainWindow.revalidate();
		
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
		double tempDragDistance = mapTestMouseListener.listener.getDragVerticalDistance(false); 
		
		tempViewportAngle = viewportAngle;
		
		tempPositionModifier.setAlpha(mapTestMouseListener.listener.getDragHorizontalDistance(false) / 3, false);
		tempPositionModifier.setBeta(mapTestMouseListener.listener.getDragVerticalDistance(false) / 3, false);
		
		mapTestMouseListener.listener.getDragVerticalDistance(true);
		mapTestMouseListener.listener.getDragHorizontalDistance(true);
		
		tempViewportAngle += tempDragDistance * 90 / this.getHeight() /*0.25*/;
			
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
				if(mapTestMouseListener.isMapDraged() && mapTestKeyListener.isShiftPressed()) { 
					setViewportAngle();
				}
				
				if(mapTestMouseListener.isMapDraged() && !mapTestKeyListener.isShiftPressed()) { setFocusPosition(); }
				if(mapTestMouseListener.isScaleChanged()) { this.repaint(); }
				if(!mapTestMouseListener.isM1Pressed()) { 
					viewportAngle = tempViewportAngle;
					horizontalMapShift = tempHorizontalMapShift;
					verticalMapShift = tempVerticalMapShift;
					positionModifier.setOriginAngle(tempPositionModifier);
				} else {
					repaint();
					this.requestFocusInWindow();
				}
				
				cpInputHandler.checkListeners();
				if(cpInputHandler.commandUsed()) repaint();
				renderList2D.stream().forEach(item -> item.setAngle(tempViewportAngle, false));
				
			}
		} catch (InterruptedException Ex) { }
	}
	
}