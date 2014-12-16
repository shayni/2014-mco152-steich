package teich.connect4;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connect4Frame extends JFrame {

	public Connect4Frame() {
		setSize(800, 600);
		setTitle("Connect Four");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(6, 7));
		final JButton button = new JButton();
		button.setBackground(Color.GREEN);
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if (button.getBackground() == Color.GREEN) {
					button.setBackground(Color.PINK);
				} else {
					button.setBackground(Color.GREEN);
				}
			}

		});

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				add(button);
			}
		}

	}

	public static void main(String[] args) {
		Connect4Frame frame = new Connect4Frame();
		frame.setVisible(true);
	}
}
