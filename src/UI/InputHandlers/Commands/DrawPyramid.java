package UI.InputHandlers.Commands;

import java.util.ArrayList;

import data.forms.WireFrame3D;
import data.shapes3D.Pyramid4E;

public class DrawPyramid extends BasicCommand {	// Building a pyramid
	public DrawPyramid() {
		super("draw_pyramid", new BasicParameter[] {
				new BasicParameter("-b", 1, 4)
			});
	}
	
	@Override
	protected String setCommandID() { return "drawPyramid"; }
		
	@Override
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 1, 4);
	}
	
	@Override
	public void makeAction(ArrayList<WireFrame3D> shapes, int a) {
		System.out.println("Drawing pyramid");
		
		registeredParameters.stream().forEach(item -> System.out.println(item.isActive()));
		
		System.out.println("Adding pyramid");
		double[] facing = registeredParameters.get(0).getValues();
		shapes.add(new Pyramid4E(facing[0], facing[1], facing[2], facing[3]));
		
		System.out.println("3D forms in list: " + shapes.size());
		super.makeAction(shapes, 0);
	}
}
