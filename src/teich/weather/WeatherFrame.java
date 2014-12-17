package teich.weather;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WeatherFrame extends JFrame {

	public WeatherFrame() throws IOException {
		setSize(800, 600);
		setTitle("Weather");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		WeatherDownloadThread thread = new WeatherDownloadThread();
		thread.start();

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5, 1));
		/*
		 * JLabel label = new JLabel("Temp: 	               " +
		 * String.valueOf(now.getMain().getTemp())); p.add(label); JLabel l2 =
		 * new JLabel("Min Temp: 	           " +
		 * String.valueOf(now.getMain().getTempMin())); p.add(l2); JLabel l3 =
		 * new JLabel("Max Temp: 	           " +
		 * String.valueOf(now.getMain().getTempMax())); p.add(l3); JLabel l4 =
		 * new JLabel(now.getWeather()[0].getMain()); p.add(l4); JLabel l5 = new
		 * JLabel(now.getWeather()[0].getDescription()); p.add(l5); URL imageURL
		 * = new URL("http://openweathermap.org/img/w/" +
		 * now.getWeather()[0].getIcon() + ".png"); ImageIcon icon = new
		 * ImageIcon(imageURL); JLabel l6 = new JLabel(icon); add(p,
		 * BorderLayout.EAST); add(l6, BorderLayout.WEST);
		 */

	}

	public static void main(String[] args) throws IOException {
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
}
