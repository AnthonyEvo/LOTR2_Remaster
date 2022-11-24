package UI.InputHandlers.Commands;

import java.util.ArrayList;

import data.forms.WireFrame2D;
import data.shapes2D.Triangle;
import data.units.Vector2D;

public class Remove extends BasicCommand{

	public Remove() {
		super("remove",  new BasicParameter[] {
				new BasicParameter("-all", 1, 0)
			});
	}
	@Override
	protected String setCommandID() { return "remove"; }
	
	@Override
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 1, 1);
	}
	
	@Override
	public void makeAction(ArrayList<WireFrame2D> shapes) {
		System.out.println("Making actions");
		
		if(registeredParameters.get(0).isActive()) {
			shapes.remove((int)(registeredParameters.get(0).getValues()[0]));
		} 
		else if(registeredParameters.get(1).isActive()) {
			shapes.clear();
		}
		super.makeAction(shapes);
	}
}
