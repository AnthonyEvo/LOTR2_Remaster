package UI.InputHandlers.Commands;

import java.util.ArrayList;

import data.forms.WireFrame3D;
import data.shapes3D.SpecialPrism;

public class DrawSPrism extends BasicCommand {
	public DrawSPrism() {
		super("draw_sprism", new BasicParameter[] {
				new BasicParameter("-b", 1, 4)
			});
	}

	@Override
	protected String setCommandID() { return "drawSpecialPrism"; }
		
	@Override
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 1, 4);
	}
	
	@Override
	public void makeAction(ArrayList<WireFrame3D> shapes, int a) {
		System.out.println("Drawing prism");
		
		registeredParameters.stream().forEach(item -> System.out.println(item.isActive()));
		
		System.out.println("Adding prism");
		double[] facing = registeredParameters.get(0).getValues();
		shapes.add(new SpecialPrism(facing[0], facing[1], facing[2], facing[3]));
		
		System.out.println("3D forms in list: " + shapes.size());
		super.makeAction(shapes, 0);
	}
	
}
