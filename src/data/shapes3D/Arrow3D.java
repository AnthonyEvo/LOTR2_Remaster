package data.shapes3D;

import data.forms.WireFrame3D;
import data.units.Direction;
import data.units.Vector3;
import java.awt.Color;

public class Arrow3D extends WireFrame3D {
	public Arrow3D(Direction direction) {
		super("Arrow");
		switch(direction) {
		
		case right:
			setColor(Color.green);
			
			this.createVertex(new Vector3(0, 0, 0));
			this.createVertex(new Vector3(50, 0, 0));
			this.createVertex(new Vector3(45, 5, 0));
			this.createVertex(new Vector3(45, -5, 0));
			
			vertexList.get(1).setName("Y");
			
			this.createEdge(0, 1);
			this.createEdge(1, 2);
			this.createEdge(1, 3);
			
			break;
			
		case front:
			setColor(Color.red);
			
			this.createVertex(new Vector3(0, 0, 0));
			this.createVertex(new Vector3(0, 50, 0));
			this.createVertex(new Vector3(5, 45, 0));
			this.createVertex(new Vector3(-5, 45, 0));
			
			vertexList.get(1).setName("X");
			
			this.createEdge(0, 1);
			this.createEdge(1, 2);
			this.createEdge(1, 3);
			
			break;
			
		case up:
			setColor(Color.blue);
			
			this.createVertex(new Vector3(0, 0, 0));
			this.createVertex(new Vector3(0, 0, 50));
			this.createVertex(new Vector3(5, 0, 45));
			this.createVertex(new Vector3(-5, 0, 45));
			
			vertexList.get(1).setName("Z");
			
			this.createEdge(0, 1);
			this.createEdge(1, 2);
			this.createEdge(1, 3);
			
			break;
		}	
	}
}
