package problem2;

import java.util.Random;

public class HillClimbing {
	
	private int numberOfQueens;
	private int heuristic;

	public HillClimbing() {
	}
	
	Queen [] generateBoard(int number) {
		this.numberOfQueens = number;
		
		Queen [] board = new Queen[this.numberOfQueens];
		
		Random random = new Random();
		
		for(int i = 0; i < this.numberOfQueens; i++) {
			board[i] = new Queen(random.nextInt(this.numberOfQueens), i, this.numberOfQueens);
		}
		return board;
	}
	
	void print(Queen[] q) {
		
		int [][] tempboard = new int[this.numberOfQueens][this.numberOfQueens];
		
		for(int i = 0; i < this.numberOfQueens; i++) {
			tempboard[q[i].getRow()][q[i].getColumn()] = 1;
		}
		
		for(int i = 0; i < this.numberOfQueens; i++) {
			for(int j = 0; j < this.numberOfQueens; j++) {
				System.out.print(tempboard[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	int findHeuristic(Queen[] q) {
		this.heuristic = 0;
		
		for(int i =0; i < this.numberOfQueens; i++) {
			for(int j = i + 1; j <this.numberOfQueens; j++) {
				if(q[i].isConflict(q[j])) {
					this.heuristic++;
				}
			}
		}
		return this.heuristic;
	}
	
	Queen[] getChildren(Queen[] currentboard) {
		
		Queen[] newboard = new Queen[this.numberOfQueens];
		Queen[] tempboard = new Queen[this.numberOfQueens];
		
		int currentboardheuristic = findHeuristic(currentboard);
		int bestHeuristic = currentboardheuristic;
		int newBoardHeuristic;
		
		for(int i = 0; i < this.numberOfQueens; i++) {
			newboard[i] = new Queen(currentboard[i].getRow(), currentboard[i].getColumn(), this.numberOfQueens);
			tempboard[i] = newboard[i];
		}
		
		for(int i = 0; i < this.numberOfQueens; i++) {
			
			if(i>0) {
				tempboard[i] = new Queen(newboard[i-1].getRow(), newboard[i-1].getColumn(), this.numberOfQueens);
			}
			tempboard[i] = new Queen(0, newboard[i].getColumn(), this.numberOfQueens);
			
			for(int j = 0; j < this.numberOfQueens; j++) {
				
				newBoardHeuristic = findHeuristic(tempboard);
				
				if(newBoardHeuristic < bestHeuristic) {
					bestHeuristic = newBoardHeuristic;
					
					for(int k = 0; k < this.numberOfQueens; k++) {
						newboard[k] = new Queen(tempboard[k].getRow(), tempboard[k].getColumn(), this.numberOfQueens);
					}
					
					if(tempboard[i].getColumn() != this.numberOfQueens - 1) {
						tempboard[i].move();
					}
				}
			}
		}
		
		if(currentboardheuristic == bestHeuristic) {
			newboard = generateBoard(this.numberOfQueens);
			this.heuristic = findHeuristic(newboard);
		}else {
			this.heuristic = bestHeuristic;
		}
		return newboard;
	}
}
