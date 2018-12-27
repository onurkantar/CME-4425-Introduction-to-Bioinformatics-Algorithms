
public class DPTable {
	
	private DPNode[][] Table ;
	
	public DPTable(int rows , int columns) {
		// TODO Auto-generated constructor stub
		
		Table = new DPNode[rows+1][columns+1];
		
	}

	public DPNode[][] getTable() {
		return Table;
	}

	public void setTableRC(int row , int column , DPNode theNode) {
		Table[row][column] = theNode;
	}
	
	public DPNode getDpNode(int row , int column) {
		
		return Table[row][column];	
		}
	
	
	

}
