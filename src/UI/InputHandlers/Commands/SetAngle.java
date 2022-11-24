package UI.InputHandlers.Commands;

import java.util.ArrayList;

import data.forms.WireFrame3D;
import data.shapes3D.Pyramid4E;
import data.units.Origin3;

public class SetAngle extends BasicCommand {
	
	public SetAngle() {
		super("set_angle", new BasicParameter[] {
			new BasicParameter("-all", 1, 0)
		});
	}
	
	@Override
	protected String setCommandID() { return "setAngle"; }
		
	@Override
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 1, 3);
	}
	
	@Override
	public void makeAction(ArrayList<WireFrame3D> shapes, int a) {
		
		double[] facing = registeredParameters.get(0).getValues();
		System.out.println("Turning to: " + facing[0] + " " + facing[1]);
		shapes.stream().forEach(item -> item.setOrientation(new Origin3(facing[0], facing[1], facing[2], false)));
		
		super.makeAction(shapes, 0);
	}
}
