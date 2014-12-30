package teich.nytimes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class NYTFrame extends JFrame {

	private JLabel[] headline;
	private JLabel[] paragraph;

	public NYTFrame() {
		setSize(800, 600);
		setTitle("NY Times");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		DownloadArticlesThread thread = new DownloadArticlesThread(this);
		thread.start();

		headline = new JLabel[10];
		paragraph = new JLabel[10];
		for (int k = 0; k < 10; k++) {
			headline[k] = new JLabel();
			paragraph[k] = new JLabel();
		}
		setLayout(new GridLayout(20, 2));

		for (int l = 0; l < 10; l++) {
			add(headline[l]);
			add(paragraph[l]);
		}
		final JTextArea date = new JTextArea("yyyymmdd");
		add(date);
		JButton change = new JButton("Change Date");

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newDate = date.getText();
				try {
					URL url = new URL(
							"http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&begin_date="
									+ newDate + "&end_date=" + newDate
									+ "&api-key=ba6ceae674fe79db68a0b95e1c9c0c40:3:70543319");
					DownloadDifferentDayThread thread = new DownloadDifferentDayThread(url, headline, paragraph);
					thread.start();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		change.addActionListener(listener);
		add(change);
	}

	public void display(NYTimes times) {
		for (int j = 0; j < 10; j++) {
			headline[j].setText(times.getResponse().getDocs()[j].getHeadline().getMain());
			paragraph[j].setText(times.getResponse().getDocs()[j].getLead_paragraph());
		}
	}

	public static void main(String[] args) {
		NYTFrame frame = new NYTFrame();
		frame.setVisible(true);
	}
}
