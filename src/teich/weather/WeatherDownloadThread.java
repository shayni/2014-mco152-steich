package teich.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {

	public void run() {
		URL url;
		try {
			url = new URL(
					"http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
