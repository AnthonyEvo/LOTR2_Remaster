package UI.Listeners;

import java.awt.event.*;
import java.util.ArrayList;

public class CommandPromptListener implements Runnable{
	
	Thread keyInputListener;
	public Listener listener;
	
	ArrayList<String> commandsPool;
	
	public CommandPromptListener() {
		commandsPool = new ArrayList<String>();
		
		keyInputListener = new Thread(this, "Command prompt Key Input Listener");
		keyInputListener.start();
	}
	
	public void run() {
		listener = new Listener();
	}
	
	public Listener attachListener() {
		return listener;
	}
	
	public boolean getEnterState() { return listener.isEnterPressed(); }
	
	class Listener implements KeyListener, ItemListener {
		
		boolean isCommandCongirmed = false;
		
		@Override
		public void keyTyped(KeyEvent e) {
			//System.out.println(e.getKeyCode());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 10) {
				isCommandCongirmed = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == 10) {
				
			}
		}
		
		public boolean isEnterPressed() {
			boolean temp = isCommandCongirmed;
			isCommandCongirmed = false;
			return temp;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			
		}
	}
}
