package teich.weather;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageDownloadThread extends Thread {

	private URL url;
	private JLabel imageLabel;

	public ImageDownloadThread(URL url, JLabel imageLabel) {
		this.url = url;
		this.imageLabel = imageLabel;
	}

	public void run() {

		ImageIcon icon = new ImageIcon(url);
		imageLabel.setIcon(icon);

	}
}
