package teich.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife extends JFrame {

	private static final int COL = 20;
	private static final int ROW = 20;

	private static final long serialVersionUID = 1L;
	private JButton cells[][];

	public GameOfLife() {
		setSize(800, 600);
		setTitle("game of life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ActionListener listen = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextGeneration();
			}
		};

		JButton newGen = new JButton("Next Generation");
		newGen.addActionListener(listen);

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(COL, ROW));

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				button.setBackground(Color.GREEN);

			}
		};

		Random random = new Random();

		cells = new JButton[COL][ROW];
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {

				final JButton button = new JButton();
				button.setOpaque(true);
				button.setBorderPainted(false);
				cells[i][j] = button;
				container.add(button);
				button.addActionListener(listener);// interface

				int n = random.nextInt(100);
				if (n < 30) {
					button.setBackground(Color.GREEN);
				} else {
					button.setBackground(Color.BLACK);

				}

			}
		}

		panel.add(newGen, BorderLayout.WEST);
		panel.add(container, BorderLayout.CENTER);
		add(panel);

	}

	public void nextGeneration() {
		Color[][] nextGen = new Color[COL][ROW];
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				int neighbors = getNumAliveNeighbors(i, j);
				switch (neighbors) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					nextGen[i][j] = Color.BLACK;
					break;

				case 2:
					break;
				case 3:
					break;

				}
			}
		}

		for (int s = 0; s < COL; s++) {
			for (int t = 0; t < ROW; t++) {
				if (nextGen[s][t] != null) {
					cells[s][t].setBackground(nextGen[s][t]);
				}
			}
		}

	}

	public int getNumAliveNeighbors(int i, int j) {
		int numAlive = 0;

		if (isAlive(i - 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i, j - 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i - 1, j)) {
			numAlive++;
		}
		if (isAlive(i + 1, j)) {
			numAlive++;
		}
		if (isAlive(i - 1, j + 1)) {
			numAlive++;
		}
		if (isAlive(i, j + 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j + 1)) {
			numAlive++;
		}

		return numAlive;
	}

	private boolean isAlive(int i, int j) {
		try {
			return cells[i][j].getBackground() == Color.GREEN;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		GameOfLife frame = new GameOfLife();
		frame.setVisible(true);
	}

}
