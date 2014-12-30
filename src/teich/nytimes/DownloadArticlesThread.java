package teich.nytimes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class DownloadArticlesThread extends Thread {

	private NYTFrame frame;

	public DownloadArticlesThread(NYTFrame frame) {
		this.frame = frame;
	}

	public void run() {
		URL url;

		try {
			url = new URL(
					"http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&api-key=ba6ceae674fe79db68a0b95e1c9c0c40:3:70543319");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();

			String json = IOUtils.toString(in);

			Gson gson = new Gson();

			NYTimes times = gson.fromJson(json, NYTimes.class);

			frame.display(times);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
