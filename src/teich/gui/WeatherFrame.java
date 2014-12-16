package teich.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WeatherFrame extends JFrame {

	public WeatherFrame() {
		setSize(800, 600);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();

		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);

		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer.add(new JLabel("Hello World"));
		northContainer.add(new JLabel("The World Says Hello"));

		JLabel label = new JLabel("Thanks for your silence");
		label.setBackground(Color.GREEN);
		label.setOpaque(true);

		add(northContainer, BorderLayout.NORTH);
		add(new JLabel("This class rocks!"), BorderLayout.SOUTH);
		add(label, BorderLayout.CENTER);
		add(new JLabel("Happy Birthday"), BorderLayout.EAST);
		add(new JLabel("Happy Tuesday"), BorderLayout.WEST);
	}

	public static void main(String[] args) {
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
}
