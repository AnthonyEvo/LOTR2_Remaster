package UI.InputHandlers.Commands;

import java.awt.Color;
import java.util.ArrayList;

import data.forms.WireFrame2D;
import data.shapes2D.Triangle;
import data.units.Vector2D;

public class SetElementColor extends BasicCommand{

	public SetElementColor() {
		super("set_color", null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String setCommandID() { return "setElementColor"; }
		
	@Override
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 1, 3);
	}
	
	@Override
	public void makeAction(ArrayList<WireFrame2D> shapes) {
		System.out.println("Making actions");
		
		if(registeredParameters.get(0).isActive()) {
			shapes.stream().forEach(item -> {
				item.setColor(new Color(
					(int)registeredParameters.get(0).getValues()[0] % 256,
					(int)registeredParameters.get(0).getValues()[1] % 256,
					(int)registeredParameters.get(0).getValues()[2] % 256
				));
			});
		}
		
		super.makeAction(shapes);
	}
}
