
public interface GraphInterface<E> {
	public boolean isEdge(int source, int target);
	
	public E getLabel(int vertex);
		
	public void addEdge (int vertex, int target);
	
	public Vertex[] neighbors(int vertex);
	
	public void removeEdge (int source, int target);
	
	public void setLabel (int vertex, E newLabel);
	
	public int size();
	
}
