package teich.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JList;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class DownloadGetLocationThread extends Thread {

	private String[] labels;
	private JList<String> list;
	private String address;

	public DownloadGetLocationThread(JList<String> list, String address) {
		this.list = list;
		this.address = address;
	}

	public void run() {
		StringBuilder build = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(address);
		while (tokenizer.hasMoreTokens()) {
			build.append(tokenizer.nextToken());
		}

		try {
			String formattedAddress = build.toString();
			URL url = new URL(
					"https://maps.googleapis.com/maps/api/geocode/json?address="
							+ formattedAddress
							+ "&components=country:US&key=AIzaSyCPzUBk7JYZNIemdamrP9j4PXkUEfCe37U");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();

			String json = IOUtils.toString(in);

			Gson gson = new Gson();

			ISS iss = gson.fromJson(json, ISS.class);
			double lat = iss.getResults()[0].getGeometry().getLocation()
					.getLat();
			double lng = iss.getResults()[0].getGeometry().getLocation()
					.getLng();

			URL url2 = new URL("http://api.open-notify.org/iss-pass.json?lat="
					+ lat + "&lon=" + lng);
			URLConnection connect = url2.openConnection();
			InputStream input = connect.getInputStream();
			String json2 = IOUtils.toString(input);
			Gson gson2 = new Gson();
			PassTimes times = gson2.fromJson(json2, PassTimes.class);
			int passes = times.getRequest().getPasses();
			labels = new String[passes];
			/*
			 * for (int j = 0; j < passes; j++) { labels[j] = new JLabel(); }
			 */
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss z");
			String formattedDate;

			for (int i = 0; i < passes; i++) {
				long risetime = times.getResponse()[i].getRisetime();
				Date date = new Date(risetime * 1000L);
				formattedDate = format.format(date);
				labels[i] = (formattedDate);
			}
			list.setListData(labels);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
