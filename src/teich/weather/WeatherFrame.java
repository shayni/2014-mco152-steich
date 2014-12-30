package teich.weather;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherFrame extends JFrame {

	private JLabel label;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;

	public WeatherFrame() throws IOException {
		setSize(800, 600);
		setTitle("Weather");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		WeatherDownloadThread thread = new WeatherDownloadThread(this);

		thread.start();

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5, 1));

		label = new JLabel();
		l2 = new JLabel();
		l3 = new JLabel();
		l4 = new JLabel();
		l5 = new JLabel();
		p.add(label);
		p.add(l2);
		p.add(l3);
		p.add(l4);
		p.add(l5);

		l6 = new JLabel();
		add(p, BorderLayout.EAST);
		add(l6, BorderLayout.WEST);

	}

	public void displayWeather(WeatherNow now) throws MalformedURLException {
		label.setText("Temp: 	               "
				+ String.valueOf(now.getMain().getTemp()));
		l2.setText("Min Temp: 	           "
				+ String.valueOf(now.getMain().getTempMin()));
		l3.setText("Max Temp: 	           "
				+ String.valueOf(now.getMain().getTempMax()));
		l4.setText(now.getWeather()[0].getMain());
		l5.setText(now.getWeather()[0].getDescription());

		URL url = new URL("http://openweathermap.org/img/w/"
				+ now.getWeather()[0].getIcon() + ".png");
		ImageDownloadThread thread = new ImageDownloadThread(url, l6);
		thread.start();

	}

	public static void main(String[] args) throws IOException {
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
}
