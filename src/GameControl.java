import javax.swing.*;
import java.math.*;

import java.awt.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class GameControl extends JPanel implements ActionListener, KeyListener {
	int width = 300, height = 400;
	int avail_space = 1200;
	int snake_len;
	int speed;
	char dir = 'd';
	int x[] = new int[avail_space]; //x-coord snake-head
	int y[] = new int[avail_space]; //y-coord snake-head
	int food_x, food_y;
	boolean in_game = true;
	boolean food_used = false;
	boolean timer_on = true;
	
//	Timer time = new Timer(speed, this);
	
	public GameControl() {
		startGame();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void gameCheck() {
		if (in_game == false) {
			snake_len = 0;
			food_x = 1000;
//			time.stop();
		}
	}
	
	public void startGame() {
		snake_len = 3;
		speed = 75;
		dir = 's';
		food_used = false;
		
		for (int i = 0; i < snake_len; i++) {
	        x[i] = 50 - i * 10;
	        y[i] = 50;
	    }
		
		placeFood();
	
		if (timer_on == true) {
			Timer time = new Timer(speed, this);
			time.start();
			timer_on = false;
		}
	}
	
	public void checkFood() {
		if (x[0] == food_x && y[0] == food_y) {
			snake_len++;
			food_used = false;
			placeFood();
		}
	}
	
	public void placeFood() {
        while (food_used == false) {
        	int new_width = width / 10;
        	int new_height = height / 10;
        	food_x = (int) (Math.random() * new_width);
        	food_x *= 10;
    		food_y = (int) (Math.random() * new_height);
        	food_y *= 10;
    		food_used = true;
        }
	}
	
	public void collisionCheck() {
		if (x[0] < 0 || x[0] > 300 || y[0] < 0 || y[0] > 400 ) {
			in_game = false;
		}
		for (int i = 1; i < snake_len; i++) {
			if (x[0] == x[i] && y[0] == y[i]) {
				in_game = false;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < snake_len; i++) {
        	g2.setColor(Color.black);
        	g2.fill(new Ellipse2D.Double(x[i], y[i], 10, 10));
        }
        g2.setColor(Color.red);
    	g2.fill(new Ellipse2D.Double(food_x, food_y, 10, 10));
    	g2.setColor(Color.black);
    	g2.drawRect(0, 0, 300, 400);
    	if (in_game == false) {
    		g2.drawString("GAME OVER", 115, 150);
    		g2.drawString("hit the down arrow to restart", 80, 200);
    	}
	}
	
	public void actionPerformed(ActionEvent e) {
		move();
		checkFood();
		repaint();
		collisionCheck();
		gameCheck();
	}
	
	public void move() {
		for (int i = snake_len; i > 0; i--) {
			x[i] = x[(i - 1)];
			y[i] = y[(i - 1)];
		}	
		if (dir == 'w') {
			y[0] -= 10;		
		}
		
		if (dir == 's') {
			y[0] += 10;	
		}
		
		if (dir == 'a') {
			x[0] -= 10;
		}
		
		if (dir == 'd') {
			x[0] += 10;
		}
	
	}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_DOWN && in_game == false) {
			in_game = true;
			startGame();
		}
		if(code == KeyEvent.VK_UP && dir != 's') {
			dir = 'w';
		}
		if(code == KeyEvent.VK_RIGHT && dir != 'a') {
			dir = 'd';
		}
		if(code == KeyEvent.VK_DOWN && dir != 'w') {
			dir = 's';
		}
		if(code == KeyEvent.VK_LEFT && dir != 'd') {
			dir = 'a';
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}