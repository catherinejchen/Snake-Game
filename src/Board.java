import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Board extends JFrame{
	public Board() {
		setTitle("Snake");
		setSize(319, 448);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		GameControl game = new GameControl();
		
		board.add(game);
		board.setVisible(true);
	}
}