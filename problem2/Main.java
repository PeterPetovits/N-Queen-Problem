package problem2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner myObj = new Scanner(System.in);
		System.out.println("Please enter the number of Queens / Dimension of the board");
		int numberOfQueens = myObj.nextInt();
		myObj.close();
		
		HillClimbing initialState = new HillClimbing();
		
		Queen[] presentBoard = initialState.generateBoard(numberOfQueens);
		int presentHeuristic = initialState.findHeuristic(presentBoard);
		
		long start = System.currentTimeMillis();
		
		while(presentHeuristic != 0) {
			presentBoard = initialState.getChildren(presentBoard);
			presentHeuristic = initialState.findHeuristic(presentBoard);
		}
		long end = System.currentTimeMillis();
		
		System.out.println("Search time:" + (double)(end-start) / 1000 + "sec.");
		initialState.print(presentBoard);
	}
}
