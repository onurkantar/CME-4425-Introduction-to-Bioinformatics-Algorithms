import java.util.ArrayList;

public class  DPNode<V> {
	
	public enum From {
		UP,LEFT,DIAGONAL
	}
	
	private V value;
	
	private ArrayList<From> from = new ArrayList<>();
	
	public DPNode() {
		// TODO Auto-generated constructor stub
	}
	
	public DPNode(V value,From fromOut) {
		// TODO Auto-generated constructor stub
		
		this.value = value;
		from.add(fromOut);
		
	}

	public V getValue() {
		return value;
	}

	public ArrayList<From> getFrom() {
		return from;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void addFrom(From fromOut) {
		from.add(fromOut);
	}
	


}
