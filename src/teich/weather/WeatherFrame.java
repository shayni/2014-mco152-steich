package teich.weather;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;

public class WeatherFrame extends JFrame {

	public WeatherFrame() throws IOException {
		setSize(800, 600);
		setTitle("Weather");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");

		URLConnection connection = url.openConnection();

		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		StringBuilder build = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			build.append(new String(b, 0, n));
		}
		String json = build.toString();

		Gson gson = new Gson();

		WeatherNow now = gson.fromJson(json, WeatherNow.class);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5, 1));
		JLabel label = new JLabel("Temp: 	               " + String.valueOf(now.getMain().getTemp()));
		p.add(label);
		JLabel l2 = new JLabel("Min Temp: 	           " + String.valueOf(now.getMain().getTempMin()));
		p.add(l2);
		JLabel l3 = new JLabel("Max Temp: 	           " + String.valueOf(now.getMain().getTempMax()));
		p.add(l3);
		JLabel l4 = new JLabel(now.getWeather()[0].getMain());
		p.add(l4);
		JLabel l5 = new JLabel(now.getWeather()[0].getDescription());
		p.add(l5);
		URL imageURL = new URL("http://openweathermap.org/img/w/" + now.getWeather()[0].getIcon() + ".png");
		ImageIcon icon = new ImageIcon(imageURL);
		JLabel l6 = new JLabel(icon);
		add(p, BorderLayout.EAST);
		add(l6, BorderLayout.WEST);

	}

	public static void main(String[] args) throws IOException {
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
}
