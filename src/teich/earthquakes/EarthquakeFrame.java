package teich.earthquakes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import com.google.gson.Gson;

public class EarthquakeFrame extends JFrame {

	public EarthquakeFrame() throws IOException {
		setSize(300, 200);
		setTitle("Earthquakes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		URL url = new URL(
				"http://earthquake.usgs.gov/earthquakes/feed/v0.1/summary/significant_month.geojson");

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

		Earthquake earthquake = gson.fromJson(json, Earthquake.class);

		Features[] features = earthquake.getFeatures();

		DefaultListModel model = new DefaultListModel();
		for (Features f : features) {
			model.addElement(f.getProperties().getPlace());
		}

		JList list = new JList(model);

		add(list);
	}

	public static void main(String[] args) throws IOException {
		EarthquakeFrame frame = new EarthquakeFrame();
		frame.setVisible(true);
	}
}
