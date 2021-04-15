package Games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JFrame {
//	static class MyFrame  {

	public SnakeGame() {
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initUI();// init UI
		makeSnakeList(); // make Snake Body
		startTimer();// start timer
		setKeyListener();// listen key event
		makeFruit(); // make fruit
	}

	static class XY {
		int x;
		int y;

		public XY(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	static JPanel panelNorth;
	static JPanel panelCenter;
	static JLabel labelTitle;
	static JLabel labelMessage;
	static JPanel[][] panels = new JPanel[20][20];
	static int[][] map = new int[20][20]; // Fruit 9, Bomb 8, 0 Blank
	static LinkedList<XY> snake = new LinkedList<XY>();
	static int dir = 3; // move direction 0 : up, 1: down, 2: left, 3: right
	static int score = 0;
	static int time = 0;// game time (unit 1 second)
	static int timeTickCount = 0; // per 200ms
	static Timer timer = null;

	public void makeFruit() {
		Random rand = new Random();
		// 0~19 x, 0~19 y
		int randX = rand.nextInt(19);
		int randY = rand.nextInt(19);
		map[randX][randY] = 9; // 9 is Fruit
	}

	public void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {// 0 : up, 1: down, 2: left, 3: right
					if (dir != 1)
						dir = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// 0 : up, 1: down, 2: left, 3: right
					if (dir != 0)
						dir = 1;

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// 0 : up, 1: down, 2: left, 3: right
					if (dir != 3)
						dir = 2;

				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 0 : up, 1: down, 2: left, 3: right
					if (dir != 2)
						dir = 3;
				}
			}
		});
	}// -------------------------------------------------------------------------------------------------------

	public void startTimer() {
		timer = new Timer(100, new ActionListener() {// 50ms per 1 second
			@Override
			public void actionPerformed(ActionEvent e) {
				timeTickCount += 1;

				if (timeTickCount % 5 == 0) {
					time++;// 1sec add
				}
				moveSnake(); // move Snake
				updateUI(); // update UI
			}
		});
		timer.start(); // Start Timer!
	}// ------------------------------------------------------------------------------------------------------------

	public void moveSnake() {
		XY headXY = snake.get(0); // get head
		int headX = headXY.x;
		int headY = headXY.y;

		if (dir == 0) {// 0 : up, 1: down, 2: left, 3: right
			boolean isColl = checkCollision(headX, headY - 1);
			if (isColl == true) {// GameOver
				labelMessage.setText("Game Over!");
				timer.stop();
				return;
			}
			snake.add(0, new XY(headX, headY - 1));// add head
			snake.remove(snake.size() - 1);// remove tail

		} else if (dir == 1) {// 0 : up, 1: down, 2: left, 3: right
			boolean isColl = checkCollision(headX, headY + 1);
			if (isColl == true) {// GameOver
				labelMessage.setText("Game Over!");
				timer.stop();
				return;
			}
			snake.add(0, new XY(headX, headY + 1));// add head
			snake.remove(snake.size() - 1);// remove tail

		} else if (dir == 2) {// 0 : up, 1: down, 2: left, 3: right
			boolean isColl = checkCollision(headX - 1, headY);
			if (isColl == true) {// GameOver
				labelMessage.setText("Game Over!");
				timer.stop();
				return;
			}
			snake.add(0, new XY(headX - 1, headY));// add head
			snake.remove(snake.size() - 1);// remove tail

		} else if (dir == 3) {// 0 : up, 1: down, 2: left, 3: right
			boolean isColl = checkCollision(headX + 1, headY);
			if (isColl == true) {// GameOver
				labelMessage.setText("Game Over!");
				timer.stop();
				return;
			}
			snake.add(0, new XY(headX + 1, headY));// add head
			snake.remove(snake.size() - 1);// remove tail
		}

	}// ------------------------------------------------------------------------------------------------------

	public boolean checkCollision(int headX, int headY) {
		if (headX < 0 || headX > 19 || headY < 0 || headY > 19) {// Collision on Wall
			return true;
		}
		// Collision to Snake Body
		for (XY xy : snake) {
			if (headX == xy.x && headY == xy.y) {
				return true;
			}
		}

		if (map[headY][headX] == 9) {// Collision on Fruit
			map[headY][headX] = 0;
			addTail();
			makeFruit();
			score += 100;
		}

		return false;
	}// -----------------------------------------------------------------------------------------------------------

	public void addTail() {
		int tailX = snake.get(snake.size() - 1).x;
		int tailY = snake.get(snake.size() - 1).y;
		int tailX2 = snake.get(snake.size() - 2).x;
		int tailY2 = snake.get(snake.size() - 2).y;

		if (tailX < tailX2) {// to Right : attach to Left
			snake.add(new XY(tailX - 1, tailY));
		} else if (tailX > tailX2) {// to Left : attach to Right
			snake.add(new XY(tailX + 1, tailY));
		} else if (tailY < tailY2) {// to Up : attach to Down
			snake.add(new XY(tailX, tailY - 1));
		} else if (tailY > tailY2) {// to Down : attach to Up
			snake.add(new XY(tailX - 1, tailY + 1));
		}

	}// --------------------------------------------------------------------------------------------------------------------

	public void updateUI() {
		labelTitle.setText("Score: " + "  " + score + "  " + "Time: " + time);

		// clear title(panel)
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (map[i][j] == 0) {// Blank
					panels[i][j].setBackground(Color.LIGHT_GRAY);
				} else if (map[i][j] == 9) {// Fruit
					panels[i][j].setBackground(Color.RED);

				}
			}
		}
		// draw Snake
		int index = 0;
		for (XY xy : snake) {
			if (index == 0) {// head
				panels[xy.y][xy.x].setBackground(Color.PINK);

			} else {// body(s), tail
				panels[xy.y][xy.x].setBackground(Color.magenta);
			}

			index++;
		}
	}// --------------------------------------------------------------------------------------------------------------------

	public void makeSnakeList() {
		snake.add(new XY(10, 10)); // Head of Snake
		snake.add(new XY(9, 10)); // Body of Snake
		snake.add(new XY(8, 10)); // Tail of Snake

	}// --------------------------------------------------------------------------------------------------------------------

	public void initUI() {
		this.setLayout(new BorderLayout());

		panelNorth = new JPanel();
		panelNorth.setPreferredSize(new Dimension(400, 100));
		panelNorth.setBackground(Color.PINK);
		panelNorth.setLayout(new FlowLayout());

		labelTitle = new JLabel("Score : 0,Time : 0Sec");
		labelTitle.setPreferredSize(new Dimension(400, 50));
		labelTitle.setFont(new Font("TimesRoman", Font.BOLD, 20));
		labelTitle.setForeground(Color.white);
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		panelNorth.add(labelTitle);

		labelMessage = new JLabel("Eat Fruit!");
		labelMessage.setPreferredSize(new Dimension(400, 20));
		labelMessage.setFont(new Font("TimesRoman", Font.BOLD, 20));
		labelMessage.setForeground(Color.YELLOW);
		labelMessage.setHorizontalAlignment(JLabel.CENTER);
		panelNorth.add(labelMessage);

		this.add("North", panelNorth);

		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(20, 20));
		for (int i = 0; i < 20; i++) {// i Loop : Row
			for (int j = 0; j < 20; j++) {// j Loop : Column
				map[i][j] = 0; // init 0 = Blank
				panels[i][j] = new JPanel();
				panels[i][j].setPreferredSize(new Dimension(20, 20));
				panels[i][j].setBackground(Color.PINK);
				panelCenter.add(panels[i][j]);

			}
		}
		this.add("Center", panelCenter);
		this.pack(); // Remove Empty Space

	}
//	}//------------------------------------------------------------------------------------------------------------------------------------- 

	public static void main(String[] args) {
//		new MyFrame("Snake Game");
		new SnakeGame();

	}

}// class-------------------------------------------------------------------------------------------------------------------------------------
