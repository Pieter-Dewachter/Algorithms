package algorithms.Snake;
import algorithms.LinkedList.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Snake {
	private final int rows = 10, columns = 10;
	private final int emptySign = 0, bodySign = 1, headSign = 2, foodSign = -1;
	private int foodCollected = 0;
	private String direction = "right";
	
	private int maze[][] = new int[rows][columns];
	
	private Coords head = new Coords(rows/2, columns/2);
	private List<Coords> snake = new List<Coords>(head);
	
	private Coords food = new Coords(1, 1);
	
	public Snake() {
		maze[head.y][head.x] = 2;
		maze[food.y][food.x] = -1;
	}
	
	public void left() {
		if(head.x > 1) {
			head.x--;
			if(head.x == snake.head().get().x && head.y == snake.head().get().y) {
				updateSnake(snake.head().get().x+1, head.y);
			}
			else {
				updateSnake(snake.head().get().x, snake.head().get().y);
			}
		}
		else {
			death("you hit the left wall!");
		}
	}
	
	public void right() {
		if(head.x < columns-2) {
			head.x++;
			if(head.x == snake.head().get().x && head.y == snake.head().get().y) {
				updateSnake(snake.head().get().x-1, head.y);
			}
			else {
				updateSnake(snake.head().get().x, snake.head().get().y);
			}
		}
		else {
			death("you hit the right wall!");
		}
	}
	
	public void up() {
		if(head.y > 1) {
			head.y--;
			if(head.x == snake.head().get().x && head.y == snake.head().get().y) {
				updateSnake(snake.head().get().x, head.y+1);
			}
			else {
				updateSnake(snake.head().get().x, snake.head().get().y);
			}
		}
		else {
			death("you hit the upper wall!");
		}
	}
	
	public void down() {
		if(head.y < rows-2) {
			head.y++;
			if(head.x == snake.head().get().x && head.y == snake.head().get().y) {
				updateSnake(snake.head().get().x, head.y-1);
			}
			else {
				updateSnake(snake.head().get().x, snake.head().get().y);
			}
		}
		else {
			death("you hit the lower wall!");
		}
	}
	
	public void updateSnake(int oldX, int oldY) {
		if(head.x == food.x && head.y == food.y) {
			foodCollected = 1;
			do {
				food.x = (int) (1+Math.random()*(columns-3));
				food.y = (int) (1+Math.random()*(rows-3));
			} while(snake.contains(new Coords(food.x, food.y))
					|| (Math.abs(head.x-food.x) < 2 && Math.abs(head.y-food.y) < 2));
			maze[food.y][food.x] = foodSign;
		}
		else if(snake.contains(new Coords(head.x, head.y))) {
			death("you hit yourself!");
			return;
		}
		Node<Coords> cursor = snake.head();
		if(foodCollected == 0) {
			maze[oldY][oldX] = emptySign;
		}
		else if(foodCollected == 1) {
			maze[oldY][oldX] = emptySign;
			foodCollected++;
		}
		else if(foodCollected == 2) {
			snake.prepend(new Node<Coords>(new Coords(oldX, oldY)));
			maze[oldY][oldX] = bodySign;
			foodCollected = 0;
		}
		while(cursor.next() != null) {
			cursor.set(cursor.next().get());
			cursor = cursor.next();
			maze[cursor.get().y][cursor.get().x] = bodySign;
		}
		Coords newCoords = new Coords(head.x, head.y);
		maze[head.y][head.x] = headSign;
		cursor.set(newCoords);
	}
	
	public void death(String reason) {
		System.out.print("Game over: " + reason);
		System.exit(0);
	}
	
	public String toString() {
		StringBuilder mazeString = new StringBuilder("\n");
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if(i == 0 || i == rows-1) {
					mazeString.append("---");
				}
				else if(j == 0 || j == columns-1) {
					mazeString.append(" | ");
				}
				else {
					switch(maze[i][j]) {
						case emptySign:
							mazeString.append("   ");
							break;
						case bodySign:
							mazeString.append(" * ");
							break;
						case headSign:
							mazeString.append(" # ");
							break;
						case foodSign:
							mazeString.append(" + ");
							break;
						default:
							break;
					}
				}
			}
			mazeString.append("\n");
		}
		return mazeString.toString();
	}
	
	public static void main(String[] args) {
		Snake game = new Snake();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				switch(game.direction) {
					case "left":
						game.left();
						break;
					case "right":
						game.right();
						break;
					case "up":
						game.up();
						break;
					case "down":
						game.down();
						break;
				}
				System.out.print(game.toString());
			}
		}, 1000, 1000);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("*** Snake ***");
		System.out.println("Use the zqsd to move the snake");
		System.out.print(game.toString());
		
		while(true) {
			String input = scanner.next();
			switch(input) {
				case "q":
					game.direction = "left";
					break;
				case "d":
					game.direction = "right";
					break;
				case "z":
					game.direction = "up";
					break;
				case "s":
					game.direction = "down";
					break;
			}
		}
	}
}
