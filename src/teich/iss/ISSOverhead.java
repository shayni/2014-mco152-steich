package teich.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverhead {

	public static void main(String[] args) {
		URL url;
		try {
			url = new URL(
					"https://maps.googleapis.com/maps/api/geocode/json?address=1602+Avenue+J+Brooklyn,+NY+11230&components=country:US&key=AIzaSyCPzUBk7JYZNIemdamrP9j4PXkUEfCe37U");
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
			PassTimes times = gson.fromJson(json2, PassTimes.class);
			int passes = times.getRequest().getPasses();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss z");
			String formattedDate;
			for (int i = 0; i < passes; i++) {
				long risetime = times.getResponse()[i].getRisetime();
				Date date = new Date(risetime * 1000L);
				formattedDate = format.format(date);
				System.out.println(formattedDate);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
