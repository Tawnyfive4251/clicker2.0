import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Options extends JFrame implements ActionListener {

	//declare private variables
	private JButton song, close, increaseSong, decreaseSong;
	private JLabel volumeLevel;
	private int volume = 3;

	private boolean songOn = false;

	public Options() {

		// sets frame text and features
		super("Options");
		this.setIconImage(new ImageIcon("Images//doge.jpg").getImage());

		// gui dimensions and features
		setSize(200, 200);
		setResizable(false);
		setLayout(null);
		Container c = getContentPane();
		c.setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// song button
		if (Sounds.dogeSong.isRunning() != true) {
			song = new JButton(new ImageIcon("Images/mute.png"));
		} else {
			song = new JButton(new ImageIcon("Images/song.png"));
		}
		song.addActionListener(this);
		song.setBounds(10, 10, 70, 70);
		song.setOpaque(false);
		song.setBorder(BorderFactory.createLineBorder(Color.black));
		song.setToolTipText("Play Doge Adventure By Weebl");
		add(song);

		// increase button volumes of song
		increaseSong = new JButton(new ImageIcon("Images/plus.png"));
		increaseSong.addActionListener(this);
		increaseSong.setBounds(90, 30, 20, 20);
		increaseSong.setOpaque(false);
		increaseSong.setBorder(BorderFactory.createLineBorder(Color.black));
		increaseSong.setToolTipText("Increase song volume");
		add(increaseSong);

		//decrease volume of song button
		decreaseSong = new JButton(new ImageIcon("Images/minus.png"));
		decreaseSong.addActionListener(this);
		decreaseSong.setBounds(120, 30, 20, 20);
		decreaseSong.setOpaque(false);
		decreaseSong.setBorder(BorderFactory.createLineBorder(Color.black));
		decreaseSong.setToolTipText("Decrease song volume");
		add(decreaseSong);

		//shows volume level
		volumeLevel = new JLabel("Volume level: " + volume + "/5");
		volumeLevel.setBounds(90, 50, 100, 40);
		volumeLevel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		volumeLevel.setForeground(Color.black);
		add(volumeLevel);

		// close button
		close = new JButton("close");
		close.addActionListener(this);
		close.setBounds(60, 150, 70, 20);
		close.setOpaque(false);
		close.setBorder(BorderFactory.createLineBorder(Color.black));
		close.setToolTipText("close the options");
		add(close);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == song) {
			// Plays Doge Adventure by Weebl

			if (Sounds.dogeSong.isRunning() != true) {

				song.setIcon(new ImageIcon("Images/song.png"));
			}
			if (Sounds.dogeSong.isRunning() == true) {

				song.setIcon(new ImageIcon("Images/mute.png"));
			}
			Sounds.run("dogeSong");
		}
		if (e.getSource() == close) {
			// close program
			this.dispose();
		}
		if (e.getSource() == increaseSong) {
			//increase volume level
			volume = volume + Sounds.increase();
			volumeLevel.setText("Volume level: " + volume + "/5");
		}
		if (e.getSource() == decreaseSong) {

			//decrease volume level
			volume = volume + Sounds.decrease();
			volumeLevel.setText("Volume level: " + volume + "/5");
		}

	}

}
