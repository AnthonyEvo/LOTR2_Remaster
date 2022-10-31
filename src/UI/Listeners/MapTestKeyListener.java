package UI.Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import UI.Listeners.MapTestMouseListener.Listener;

public class MapTestKeyListener implements Runnable{
	
	Thread keyInputListener;
	public Listener listener;
	
	public MapTestKeyListener() {
		keyInputListener = new Thread(this, "Key Input Listener");
		keyInputListener.start();
	}
	
	public void run() {
		listener = new Listener();
	}
	
	public Listener attachListener() {
		return listener;
	}
	
	public boolean isShiftPressed() {
		return listener.getShiftState();
	}
	
	class Listener implements KeyListener {
		
		boolean isShiftPressed = false;
		
		public boolean getShiftState() {
			return isShiftPressed;
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Автоматически созданная заглушка метода
			//System.out.println(e.getKeyCode());
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 16) {
				isShiftPressed = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == 16) {
				isShiftPressed = false;
			}
		}
		
	}

}