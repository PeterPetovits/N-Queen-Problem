package problem2;

public class Queen {
	
	private int row;
	private int column;
	private int numberOfQueens;
	
	Queen(int row, int column, int numberOfQueens) {
		this.row = row;
		this.column = column;
		this.numberOfQueens = numberOfQueens;
	}
	
	void move() {
		if(row == this.numberOfQueens - 1) {
			row--;
		}
		row++;
	}
	
	int getRow() {
		return this.row;
	}
	
	int getColumn() {
		return this.column;
	}
	
	boolean isConflict(Queen q) {
		
		if(q.getRow() == row || q.getColumn() == column) {
			return true;
		}else if(Math.abs(column - q.getColumn()) == Math.abs(row - q.getRow())) {
			return true;
		}
		return false;
	}
}
