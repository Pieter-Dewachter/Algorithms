package algorithms.FourInARow;

import java.util.Scanner;

/**
 * 
 * Provides a board to play four in a row add checkers in turn with
 * addChecker(player, atColumn)
 * 
 */
public class FourInARow {
	private int rows = 6, columns = 7;
	private final int yellow = 1, red = -1, empty = 0;
	private int board[][] = new int[rows][columns];
	private int currentPlayer = yellow;

	/**
	 * Start with an empty board
	 */
	public FourInARow() {
		clearBoard();
	}

	private void clearBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board[i][j] = empty;
			}
		}
	}

	/**
	 * the current player adds a checker to the column of his choice, then the
	 * current player is switched
	 * 
	 * @param column
	 *            between 0 and 6
	 */
	public void addChecker(int column) {
		if (column < 0 || column > 6)
			throw new IllegalArgumentException("Invalid board position");
		int row = nextEmptySpot(0, column);
		board[row][column] = currentPlayer;
		currentPlayer = -currentPlayer;
		if (doWeHaveAWinner() != 0) {
			System.out.print(this.toString());
			if (doWeHaveAWinner() == yellow)
				System.out.println("Yellow player won!");
			else if (doWeHaveAWinner() == red)
				System.out.println("Red player won!");
			clearBoard();
			System.out.println("New game started");
		}
	}

	/**
	 * checks the board for the first empty row of a given column
	 * 
	 * @param row
	 *            to check
	 * @param column
	 *            to check
	 * @return row index of first empty spot in a column
	 */
	private int nextEmptySpot(int row, int column) {
		if (board[row][column] == empty)
			return row;
		else
			return nextEmptySpot(row + 1, column);
	}
	
	/**
	 * Gets the current board state
	 * @return the current state
	 */
	public int[][] getBoardState(){
		return board;
	}
	
	public int doWeHaveAWinner() {
		// Horizontal
		for (int[] aBoard : board) {
			int horizontalAmount = 1,
					lastPlayer = 0;
			for (int anABoard : aBoard) {
				if (anABoard == lastPlayer
						&& lastPlayer != 0) {
					horizontalAmount++;
					if (horizontalAmount == 4)
						return lastPlayer;
				} else {
					lastPlayer = anABoard;
					horizontalAmount = 1;
				}
			}
		}
		// Vertical
		for (int column = 0; column < board[0].length; column++) {
			int verticalAmount = 1,
					lastPlayer = 0;
			for (int[] aBoard : board) {
				if (aBoard[column] == lastPlayer
						&& lastPlayer != 0) {
					verticalAmount++;
					if (verticalAmount == 4)
						return lastPlayer;
				} else {
					lastPlayer = aBoard[column];
					verticalAmount = 1;
				}
			}
		}
		// Diagonal
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				int tempRow = row,
						tempColumn = column;
				int diagonalAmount = 1;

				while (tempRow+1 < board.length && tempColumn+1 < board[row].length) {
					if (board[tempRow][tempColumn] == board[tempRow+1][tempColumn+1]
							&& board[tempRow][tempColumn] != 0) {
						diagonalAmount++;
						if (diagonalAmount == 4)
							return board[tempRow][tempColumn];
					}
					else
						diagonalAmount = 1;
					tempRow++; tempColumn++;
				}
				
				tempRow = row;
				tempColumn = column;
				diagonalAmount = 1;

				while (tempRow+1 < board.length && tempColumn-1 > 0) {
					if (board[tempRow][tempColumn] == board[tempRow+1][tempColumn-1]
							&& board[tempRow][tempColumn] != 0) {
						diagonalAmount ++;
						if (diagonalAmount == 4)
							return board[tempRow][tempColumn];
					}
					else
						diagonalAmount = 1;
					tempRow++; tempColumn--;
				}
			}
		}
		return 0;
	}

	public String toString() {
		StringBuilder b = new StringBuilder("\n---------------\n|");
		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < columns; j++) {
				switch (board[i][j]) {
				case yellow:
					b.append("Y");
					break;
				case red:
					b.append("R");
					break;
				case empty:
					b.append(" ");
					break;
				}
				b.append("|");
			}
			b.append("\n---------------\n");
			if (i != 0)
				b.append("|");
		}

		return b.toString();
	}

	public static void main(String[] args) {
		FourInARow game = new FourInARow();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("*** Four in a row ***");
		System.out.println("Enter column number between 0 and 6 to insert checker");
		System.out.print(game.toString());
		
		while(true){
			int column = scanner.nextInt();
			game.addChecker(column);
			System.out.print(game.toString());
		}
	}

}
