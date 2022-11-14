package UI.InputHandlers.Commands;

import java.util.ArrayList;

import data.forms.WireFrame2D;
import data.shapes.WireTriangle;
import data.units.Vector2D;

public class DrawTriangle extends BasicCommand{
	
	public DrawTriangle() {
				
		super("draw_triangle", new BasicParameter[] {
			new BasicParameter("-eb", 1, 4),
			new BasicParameter("-ec", 1, 4)
		});
	}
	
	@Override
	protected String setCommandID() { return "drawTriangle"; }
		
	@Override
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 1, 6);
	}
	
	@Override
	public void makeAction(ArrayList<WireFrame2D> shapes) {
		System.out.println("Making actions");
		
		if(registeredParameters.get(1).isActive()) {
			double[] facing = registeredParameters.get(1).getValues();
			shapes.add(new WireTriangle(new Vector2D(facing[0], facing[1], facing[2], facing[3])));
		}
		
		super.makeAction(shapes);
	}
	
	/*
	 * buildTriangle -ec <x,y,R,f> - строит равносторонний треуголник из центра
	 * с указанным радиусом
	 * buildTriangle -eb <x,y,x1,y1> - строит равносторонний треугольник перпендикулярно основанию 
	 * buildTriangle <x,y,x1,y1,x2,y2> - строит треугольник по точкам
	 */
}
