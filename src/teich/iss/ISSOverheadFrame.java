package teich.iss;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ISSOverheadFrame extends JFrame {

	private JTextField text;
	private JButton getTimes;

	JList list;

	public ISSOverheadFrame() {
		setSize(400, 300);
		setTitle("ISS");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		text = new JTextField("1602 Avenue J Brooklyn, NY 11230");

		getTimes = new JButton("Get Times");

		p.add(text);
		list = new JList<String>();
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DownloadGetLocationThread thread = new DownloadGetLocationThread(
						list, text.getText());
				thread.start();

			}
		};

		getTimes.addActionListener(listener);
		p.add(getTimes);

		add(list, BorderLayout.CENTER);

		add(p, BorderLayout.NORTH);

	}

	public void getLatandLon(ISS iss) {

	}

	public static void main(String[] args) {
		ISSOverheadFrame frame = new ISSOverheadFrame();
		frame.setVisible(true);
	}
}
