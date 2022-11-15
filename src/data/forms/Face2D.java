package data.forms;

public class Face2D {
	
	Vertex2D[] baseVertexes;
	final int faceId;
	
	public Face2D(Vertex2D vrt1, Vertex2D vrt2, Vertex2D vrt3, int id) {
		baseVertexes = new Vertex2D[] {vrt1, vrt2, vrt3};
		faceId = id;
	}
	
	public Vertex2D getBaseVertex(int vertNum) {
		if(vertNum >= baseVertexes.length) return null;
		else return baseVertexes[vertNum];
	}
	
	public int getId() {return faceId;}
}