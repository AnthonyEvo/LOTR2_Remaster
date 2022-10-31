package UI.Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import UI.Listeners.MapTestMouseListener.Listener;

public class MapTestKeyListener implements Runnable{
	
	Thread keyInputListener;
	public Listener listener;
	
	public void run() {
		listener = new Listener();
	}
	
	public Listener attachListener() {
		return listener;
	}
	
	class Listener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Автоматически созданная заглушка метода
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Автоматически созданная заглушка метода
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Автоматически созданная заглушка метода
			
		}
		
	}

}