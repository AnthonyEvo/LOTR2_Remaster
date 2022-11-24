package UI.InputHandlers;

import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import UI.InputHandlers.Commands.*;
import UI.Listeners.CommandPromptListener;
import data.forms.WireFrame2D;
import data.forms.WireFrame3D;

public class CommandPromptInputHandler {

	CommandPromptListener attachedListener;
	JTextPane attachedTextPane;
	ArrayList<WireFrame2D> attached2DShapes;
	ArrayList<WireFrame3D> attached3DShapes;
	
	ArrayList<BasicCommand> registredCommands = new ArrayList<BasicCommand>();
	
	boolean isCommandHandled = false;
	
	public CommandPromptInputHandler(ArrayList<WireFrame2D> shapes2D, ArrayList<WireFrame3D> shapes3D, CommandPromptListener listener, JTextPane textPane) {
		attachedListener = listener;
		attachedTextPane = textPane;
		attached2DShapes = shapes2D;
		attached3DShapes = shapes3D;
		
		registredCommands.add(new DrawTriangle());
		registredCommands.add(new Remove());
		registredCommands.add(new SetElementColor());
		registredCommands.add(new DrawPyramid());
		registredCommands.add(new SetAngle());
	}
	
	public void checkListeners() {
		if(attachedListener.getEnterState()) {
			handleCommand(attachedTextPane.getText());
			attachedTextPane.setText("");
		}
	}
	
	private RecognizedParameter[] parceCommand(String command) {
		int parts = (int) command.chars().filter(ch -> ch == ' ').count();
		String temp = command;
		RecognizedParameter[] parms = new RecognizedParameter[parts + 1];
		
		for(int i = 0; i < parms.length; i++) {
			System.out.print("Part #" + i + " ");
			if(temp.contains(" ")) { 
				parms[i] = parceParameter(temp.substring(0, temp.indexOf(" ")), ':', ',');
				temp = temp.substring(temp.indexOf(" ") + 1);
			}
			else if(temp.length() > 0) parms[i] = parceParameter(temp, ':', ',');
			
		}
		
		return parms;
	}
	
	public void handleCommand(String command) {
		RecognizedParameter[] recognizedCommand = parceCommand(command);
		String commandName = recognizedCommand[0].getName();
		recognizedCommand[0].setName("default");
		
		
		registredCommands.stream().forEach(item -> { 
			if(item.getName().equals(commandName)) {
				for(RecognizedParameter param : recognizedCommand) {
					item.activateParameter(param);
				}
				item.makeAction(attached2DShapes);
				item.makeAction(attached3DShapes, 0);
			} 
		});
		isCommandHandled = true;
		System.out.println("Command used: " + commandName + " ");
		
	}
	
	public boolean commandUsed() {
		boolean temp = isCommandHandled;
		isCommandHandled = false;
		return temp;
	}
	
	private RecognizedParameter parceParameter(String message, char paramToValueDevider, char valueDevider) {
		
		
		
		RecognizedParameter temp = new RecognizedParameter("NaN", null);
		String paramName = "";
			if(message.contains(paramToValueDevider + "")) {
				paramName = message.substring(0, message.indexOf(paramToValueDevider));
				message = message.substring(message.indexOf(paramToValueDevider) + 1);
				if(message.length() > 0) {
					double[] values = new double[(int) message.chars().filter(ch -> ch == valueDevider).count() + 1];
					System.out.println(message);
					for(int i = 0; i < values.length; i++) {
						if(message.contains(valueDevider + "")) {
							try {
								values[i] = Double.parseDouble(message.substring(0, message.indexOf(valueDevider)));
								System.out.println("Value " + i + ": " + values[i] + " ");
								message = message.substring(message.indexOf(valueDevider) + 1);
							} catch (Exception Ex) {
								values[i] = 0; System.out.println(Ex.getMessage());
							}
						} 
						else {
							try {
								values[i] = Double.parseDouble(message);
							} catch (Exception Ex) {
								values[i] = 0;
							}
						}
					}
					
					temp = new RecognizedParameter(paramName, values);
				}				
			} else {
				paramName = message;
				temp = new RecognizedParameter(paramName, null);
			}
			
		System.out.println("Param name: " + temp.getName() + " param value: " + temp.getValuesAsString(',') + "\n");
		return temp;
	}
}
