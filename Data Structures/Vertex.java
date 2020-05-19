
public class Vertex { // vertex class created to aid in finding DFT, marks the vertex as visited or not
	public boolean visit;
	Vertex (boolean visit) {
		this.visit=visit;
	}// constructor
	public Vertex() {
		visit=false;
	}
	public void visited () {
		visit=true;
	}
}
