package prelim;

public class Matrix {

	private int n;
	private int[][] entries;
	
	public Matrix(int n) {
		this.n = n;
		entries = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				entries[i][j] = 0;
			}
		}
	}
	
	public int size() {
		return n;
	}
	
	public int get(int row, int col) {
		return entries[row][col];
	}
	
	public void addEdge(int i, int j) {
		entries[i][j] = 1;
		entries[j][i] = 1;
	}
	
	public void removeEdge(int i, int j) {
		entries[i][j] = 0;
		entries[j][i] = 0;
	}
	
	public boolean isGraph() {
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				if(entries[i][j] > 1 || entries[j][i] < 0)
					return false;
				else if(i == j && entries[i][j] != 0)
					return false;
				else if(entries[i][j] != entries[j][i])
					return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String result = "";
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				result += entries[i][j];
			}
			result += "\n";
		}
		return result;
	}
	
}
