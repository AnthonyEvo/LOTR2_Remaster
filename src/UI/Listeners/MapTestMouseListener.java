package UI.Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseWheelListener;

public class MapTestMouseListener implements Runnable{
	
	Thread mouseInputListener;
	public Listener listener;
	
	public MapTestMouseListener() {
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
	
	public boolean isM1Pressed() {
		return listener.getM1State();
	}
	
	public double getScaleModifier() {
		return listener.getScaleModifier();
	}
	
	public boolean isScaleChanged() {
		return listener.getScaleState();
	}
	
	class Listener implements MouseInputListener, MouseWheelListener {
		
		boolean mapDraged = false, isM1Pressed = false, isScaleChanged = false;
		
		private int verticalDraggDistance = 0;
		private int hitY = 0;
		
		private double scaleModifier = 1;
		
		public boolean getM1State() {
			return isM1Pressed;
		}
		
		public boolean isMapDraged() {
			return mapDraged;
		}
		
		public boolean getScaleState() {
			return isScaleChanged;
		}
		
		public int getDragVerticalDistance() {
			mapDraged = false;
			return verticalDraggDistance;
		}
		
		public int getHitHighness() {
			return hitY;
		}
		
		public double getScaleModifier() {
			isScaleChanged = false;
			return scaleModifier;
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
			if(e.getButton() == MouseEvent.BUTTON1) {
				isM1Pressed = true;
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("M1 Released");
			if(e.getButton() == MouseEvent.BUTTON1) {
				isM1Pressed = false;
				
			}
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

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			
			if(e.getWheelRotation() > 0) {
				scaleModifier /= e.getScrollAmount() / 3 * 1.1;
			}
			else {
				scaleModifier *= e.getScrollAmount() / 3 * 1.1;
			}
			System.out.println(scaleModifier);
			
			isScaleChanged = true;
		}
	}
}

