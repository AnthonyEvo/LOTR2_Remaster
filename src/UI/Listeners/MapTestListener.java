package UI.Listeners;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class MapTestListener implements Runnable{
	
	Thread mouseInputListener;
	public Listener listener;
	
	public MapTestListener() {
		mouseInputListener = new Thread(this, "Mouse Input Listener");
		mouseInputListener.start();
	}
	
	public Listener attachListener() {
		return listener;
	}
	
	public void run() {
		listener = new Listener();
	}
	
	public int getDragVerticalDistance() {
		return listener.getDragVerticalDistance();
	}
	
	public boolean isMapDraged() {
		return listener.isMapDraged();
	}
	
	class Listener implements MouseInputListener {
		
		boolean mapDraged = false;
		
		int verticalDraggDistance = 0;
		int hitY = 0;
		
		public boolean isMapDraged() {
			return mapDraged;
		}
		
		public int getDragVerticalDistance() {
			mapDraged = false;
			return verticalDraggDistance;
		}
		
		public int getHitHighness() {
			return hitY;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Mouce clic at: " + e.getPoint().x + "." + e.getPoint().y);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("M1 Pressed");
			hitY = e.getPoint().y;
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("M1 Released");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			verticalDraggDistance = (e.getPoint().y - hitY);
			mapDraged = true;
			System.out.println("y = " + verticalDraggDistance);
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
