package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FourInARowTest {
	
	FourInARow game;
	int board[][];
	
	private final int yellow = 1, red = -1;
	
	@Before
	public void setup(){
		 game = new FourInARow();
		 board = new int[6][7];
	}

	@Test
	public void testGettingEmptyBoardState() {		
		assertArrayEquals(board, game.getBoardState());
	}
	
	@Test
	public void testAddASingleChecker(){
		game.addChecker(0);
		
		board[0][0] = yellow;
		
		assertArrayEquals(board, game.getBoardState());		
	}
	
	@Test
	public void testAddTwoCheckersDifferentColumns(){
		game.addChecker(0);
		game.addChecker(1);
		
		board[0][0] = yellow;
		board[0][1] = red;
		
		assertArrayEquals(board, game.getBoardState());
	}
	
	@Test
	public void testAddTwoCheckersSameColumns(){
		game.addChecker(2);
		game.addChecker(2);
		
		board[0][2] = yellow;
		board[1][2] = red;
		
		assertArrayEquals(board, game.getBoardState());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddCheckerToColumnOutsideBoard(){
		game.addChecker(8);
	}
}
