package UI.InputHandlers;

import javax.swing.JTextPane;

import UI.Listeners.CommandPromptListener;

public class CommandPromptInputHandler {

	CommandPromptListener attachedListener;
	JTextPane attachedTextPane;
	
	public CommandPromptInputHandler(CommandPromptListener listener, JTextPane textPane) {
		attachedListener = listener;
		attachedTextPane = textPane;
	}
	
	public void checkListeners() {
		if(attachedListener.getEnterState()) {
			parceCommand(attachedTextPane.getText());
			attachedTextPane.setText("");
		}
	}
	
	String[] commandList = { "drawTriangle", "drawLine" };
	
	private void parceCommand(String command) {
		
	}
}
