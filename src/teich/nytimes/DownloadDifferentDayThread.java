package teich.nytimes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class DownloadDifferentDayThread extends Thread {

	private URL url;
	private JLabel[] headline;
	private JLabel[] paragraph;

	public DownloadDifferentDayThread(URL url, JLabel[] headline, JLabel[] paragraph) {
		this.url = url;
		this.headline = headline;
		this.paragraph = paragraph;
	}

	public void run() {

		try {
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();

			String json = IOUtils.toString(in);

			Gson gson = new Gson();

			NYTimes times = gson.fromJson(json, NYTimes.class);

			for (int i = 0; i < 10; i++) {
				headline[i].setText(times.getResponse().getDocs()[i].getHeadline().getMain());
				paragraph[i].setText(times.getResponse().getDocs()[i].getLead_paragraph());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
