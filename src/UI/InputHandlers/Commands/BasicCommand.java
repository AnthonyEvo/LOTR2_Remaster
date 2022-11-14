package UI.InputHandlers.Commands;

import java.util.ArrayList;

import UI.InputHandlers.RecognizedParameter;
import data.forms.WireFrame2D;

public abstract class BasicCommand {
	
	protected final String commandID;
	
	protected String commandName = "";
	protected String helpMessage = "default help message";
		
	protected ArrayList<BasicParameter> registeredParameters = new ArrayList<BasicParameter>();
	
	BasicCommand(String name, BasicParameter[] parameters) {
		commandID = setCommandID();
		registeredParameters.add(setDefaultParameter());
		commandName = name;
		
		for(BasicParameter parameter : parameters) {
			registeredParameters.add(parameter);
		}
	}
	
	protected String setCommandID() { return "default"; }
	
	protected BasicParameter setDefaultParameter() { 
		return new BasicParameter("default", 0, 1); 
	}
	
	public String getCommandID() { return commandID; }
	
	public String getName() {
		return commandName;
	}
		
	public void activateParameter(RecognizedParameter parameter) {
		
		System.out.println("Activating: " + parameter.getName());
		
		registeredParameters.stream().forEach(item -> {
			if(item.getParameterName().equals(parameter.getName()) && parameter.getValues() != null) {
				for(int i = 0; i < parameter.getValues().length; i++) {
					try {
						item.setParameter(i, parameter.getValues()[i]);
						item.activate();
					}
					catch(Exception Ex) { break; }  
				}
			} else { System.out.println("Activation of: " + parameter.getName() + " skiped");}
			System.out.println(item.getParameterName() + " activation state: " + item.isActive());
		});
		
		
	}
	
	public void setHelpMessage(String helpMessage) {
		this.helpMessage = helpMessage;
	}
	
	public void makeAction(ArrayList<WireFrame2D> shapes) { 
		registeredParameters.stream().forEach(item -> item.disactivate());
	}
	
	public String getHelp() { return helpMessage; }
}
