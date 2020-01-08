import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class Gui extends JFrame implements ActionListener {

	// declare and initialize variables
	long doge = 0;

	// maximum upgrade buttons in array
	public static final int MAX_UPGRADES = 11;
	public static final int MAX_CLICK = 5;
	public static final int MAX_ACHIEVEMENTS = 5;

	private String line[] = new String[100];// news lines
	private String flavourText[] = new String[50]; // flavor text lines

	private int i = 0; // timer fps update
	private int dps; // doge per second
	private int animation = 0; // animations
	private int clickUpgrade = 1; // doge clicker upgrades
	private int clickMultiply = 1; // click multiplier from multiple clicks

	private int cps = 0; // clicks per second by player
	private int multiplier = 1;
	private boolean hotStreak = false;

	// declaring jlabels and jbuttons
	private JLabel title, dogeProducers, dogeClickers, dogeClicktext, dogeCount,
			dogePerSecond, flavourClick, achievementText, clickMultiplier,
			cpsIndicator, eventIndicator;

	// declaring all buttons and object variables
	private JButton[] producers = new JButton[MAX_UPGRADES];
	private Producers[] producerStats = new Producers[MAX_UPGRADES];
	private JLabel[] buyProducers = new JLabel[MAX_UPGRADES];
	private JLabel[] buyDetails = new JLabel[MAX_UPGRADES];
	private JLabel[] newsHeadline = new JLabel[3];
	private JButton[] clickers = new JButton[MAX_CLICK];
	private Clickers[] clickerStats = new Clickers[MAX_CLICK];
	private JLabel[] buyClickers = new JLabel[MAX_CLICK];
	private Achievements[] achievementStats = new Achievements[MAX_ACHIEVEMENTS];
	private JLabel[] achievements = new JLabel[MAX_ACHIEVEMENTS];
	private JButton dogeClick, devButton, options, save, open;
	Timer timer;

	// gui constructor
	public Gui() throws IOException {

		// sets frame text and features
		super("Doge Clicker 1.0");
		this.setIconImage(new ImageIcon("Images//doge.jpg").getImage());

		// initializes sound files
		Sounds.initialize();

		// gui dimensions and features
		setSize(1000, 700);
		setResizable(false);
		setLayout(null);
		Container c = getContentPane();
		c.setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// timer for doge per second run method runs every millisecond
		timer = new Timer();
		timer.schedule(new RemindTask(), 1000, 10);

		// bolded title
		title = new JLabel("Doge Clicker v1.0");
		title.setBounds(0, 0, getWidth(), 40);
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		title.setForeground(Color.white);
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title);

		// reads news.txt to have import text to array
		String filePath = "news.txt";
		BufferedReader fileIn = new BufferedReader(new FileReader(filePath));
		for (int i = 0; i < line.length; i++) {

			// reads lines and saves until done reading
			if ((line[i] = fileIn.readLine()) != null) {
			}
		}
		fileIn.close();// close file

		// read flavor text.txt to import text to array
		filePath = "flavourtext.txt";
		fileIn = new BufferedReader(new FileReader(filePath));
		for (int i = 0; i < flavourText.length; i++) {

			// reads lines until complety reading
			if ((flavourText[i] = fileIn.readLine()) != null) {
			}
		}
		fileIn.close();

		// flavour label that pops up randomly when doge is clicked
		flavourClick = new JLabel("Wow! Such click!");
		flavourClick.setBounds(400, 100, getWidth(), getHeight());
		flavourClick.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		flavourClick.setForeground(Color.white);
		flavourClick.setOpaque(false);
		add(flavourClick);

		// label for achievements
		achievementText = new JLabel("These are your achievements");
		achievementText.setBounds(75, 2, getWidth(), 15);
		achievementText.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		achievementText.setForeground(Color.white);
		add(achievementText);

		// label for doge buying and click upgrades
		dogeProducers = new JLabel("Buy to make more doge");
		dogeProducers.setBounds(50, 160, getWidth(), 40);
		dogeProducers.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		dogeProducers.setForeground(Color.white);
		add(dogeProducers);
		dogeClickers = new JLabel("Miscellaneous upgrades wow");
		dogeClickers.setBounds(700, 160, getWidth(), 40);
		dogeClickers.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		dogeClickers.setForeground(Color.white);
		add(dogeClickers);

		// doge click button
		dogeClick = new JButton(new ImageIcon("Images/doge.jpg"));
		dogeClick.addActionListener(this);
		dogeClick.setBounds(450, 100, 100, 100);
		dogeClick.setOpaque(false);
		dogeClick.setBorder(BorderFactory.createLineBorder(Color.black));
		dogeClick.setToolTipText("Each click gives you " + clickUpgrade
				+ " doge. wow");
		add(dogeClick);

		// click multiplier
		clickMultiplier = new JLabel(multiplier + "x");
		clickMultiplier.setBounds(570, 120, getWidth(), 50);
		clickMultiplier.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		clickMultiplier.setForeground(Color.white);
		add(clickMultiplier);
		// clicks per second indicator
		cpsIndicator = new JLabel(cps + " clicks per second");
		cpsIndicator.setBounds(570, 150, getWidth(), 50);
		cpsIndicator.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		cpsIndicator.setForeground(Color.white);
		add(cpsIndicator);

		// event indicator
		eventIndicator = new JLabel("Welcome to doge clicker!");
		eventIndicator.setBounds(700, 530, getWidth(), 50);
		eventIndicator.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		eventIndicator.setForeground(Color.white);
		add(eventIndicator);

		// states the num of doge and doge per second
		dogeCount = new JLabel("Doge: " + doge);
		dogeCount.setBounds(0, 0, getWidth(), 120);
		dogeCount.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		dogeCount.setForeground(Color.white);
		dogeCount.setHorizontalAlignment(JLabel.CENTER);
		add(dogeCount);
		dogePerSecond = new JLabel("You get " + dps + " doge per second");
		dogePerSecond.setBounds(0, 25, getWidth(), 120);
		dogePerSecond.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		dogePerSecond.setForeground(Color.white);
		dogePerSecond.setHorizontalAlignment(JLabel.CENTER);
		add(dogePerSecond);
		dogeClicktext = new JLabel("Click for more doge");
		dogeClicktext.setBounds(400, 185, 200, 50);
		dogeClicktext.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		dogeClicktext.setForeground(Color.white);
		dogeClicktext.setHorizontalAlignment(JLabel.CENTER);
		add(dogeClicktext);
		// doge button testing button
		devButton = new JButton(new ImageIcon());
		devButton.addActionListener(this);
		devButton.setBounds(0, 0, 50, 50);
		devButton.setToolTipText("Such Secret");
		devButton.setOpaque(false);
		devButton.setContentAreaFilled(false);
		devButton.setBorderPainted(false);
		add(devButton);

		// options and save buttons
		options = new JButton(new ImageIcon("Images/option.png"));
		options.addActionListener(this);
		options.setBounds(900, 10, 70, 70);
		options.setOpaque(false);
		options.setBorder(BorderFactory.createLineBorder(Color.black));
		options.setToolTipText("Go to options");
		add(options);
		save = new JButton(new ImageIcon("Images/save.png"));
		save.addActionListener(this);
		save.setBounds(820, 10, 70, 70);
		save.setOpaque(false);
		save.setBorder(BorderFactory.createLineBorder(Color.black));
		save.setToolTipText("Save a file");
		add(save);
		open = new JButton(new ImageIcon("Images/open.png"));
		open.addActionListener(this);
		open.setBounds(740, 10, 70, 70);
		open.setOpaque(false);
		open.setBorder(BorderFactory.createLineBorder(Color.black));
		open.setToolTipText("Open a file");
		add(open);

		// news headline label that will move
		for (int i = 0; i < 3; i++) {

			newsHeadline[i] = new JLabel(
					"Welcome to Doge clicker this is a news headline!");
			newsHeadline[i].setBounds(-200 - (475 * i), 615, getWidth(), 40);
			newsHeadline[i].setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			newsHeadline[i].setForeground(Color.white);
			add(newsHeadline[i]);

		}

		// create all buttons and button stats and labels for producers
		for (int i = 0; i < MAX_UPGRADES; i++) {

			producerStats[i] = new Producers(i);
			producers[i] = new JButton(new ImageIcon(
					producerStats[i].getImage()));
			producers[i].addActionListener(this);
			producers[i].setOpaque(false);
			producers[i].setBorder(BorderFactory.createLineBorder(Color.black));
			producers[i].setToolTipText("Your "
					+ producerStats[i].getButtonName() + " gives "
					+ producerStats[i].getDogeProduction()
					* producerStats[i].getCount() + " doge per second");
			producers[i].setBounds(0, 0, 70, 70);

			buyProducers[i] = new JLabel("Buy "
					+ producerStats[i].getButtonName() + " for "
					+ producerStats[i].getCost() + " doge");
			buyProducers[i].setBounds(0, 0, getWidth(), 100);
			buyProducers[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			buyProducers[i].setForeground(Color.white);

			buyDetails[i] = new JLabel("You have: "
					+ producerStats[i].getCount() + " "
					+ producerStats[i].getButtonName());
			buyDetails[i].setBounds(0, 0, getWidth(), 100);
			buyDetails[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			buyDetails[i].setForeground(Color.white);

		}
		// buttons and labels for click upgrades clickers
		for (int i = 0; i < MAX_CLICK; i++) {

			clickerStats[i] = new Clickers(i);
			clickers[i] = new JButton(new ImageIcon(clickerStats[i].getImage()));
			clickers[i].addActionListener(this);
			if (clickerStats[i].getClickMultiplier() == 1) {
				clickers[i].setToolTipText("Buy this "
						+ clickerStats[i].getButtonName() + " to get +"
						+ clickerStats[i].getClickBonus() + " doge per click");
			} else {
				clickers[i].setToolTipText("Buy this "
						+ clickerStats[i].getButtonName() + " to get x"
						+ clickerStats[i].getClickMultiplier()
						+ " doge per click");
			}
			clickers[i].setOpaque(false);
			clickers[i].setBorder(BorderFactory.createLineBorder(Color.black));
			clickers[i].setBounds(0, 0, 70, 70);

			buyClickers[i] = new JLabel("Buy "
					+ clickerStats[i].getButtonName() + " for "
					+ clickerStats[i].getCost() + " doge");
			buyClickers[i].setBounds(0, 0, getWidth(), 100);
			buyClickers[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			buyClickers[i].setForeground(Color.white);

		}

		// labels for achievements
		for (int i = 0; i < MAX_ACHIEVEMENTS; i++) {
			achievementStats[i] = new Achievements(i);
			achievements[i] = new JLabel(new ImageIcon(
					achievementStats[i].getImage()));
			achievements[i].setBorder(BorderFactory
					.createLineBorder(Color.black));
			achievements[i].setToolTipText("Achievement: "
					+ achievementStats[i].getButtonName());
			achievements[i].setBounds(0, 0, 70, 70);

		}

		// JPanel containing achievements
		JPanel achievementPanel = new JPanel();
		achievementPanel.setPreferredSize(new Dimension(350, 70));
		achievementPanel.setLayout(null);
		achievementPanel.setOpaque(false);

		// JScrollpane containing JPanel for achievements
		JScrollPane achievementDisplay = new JScrollPane();
		achievementDisplay.setViewportBorder(new LineBorder(Color.black));
		achievementDisplay.setSize(280, 90);
		achievementDisplay
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		achievementDisplay
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		achievementDisplay.getVerticalScrollBar().setUnitIncrement(10);
		achievementDisplay.setLocation(50, 20);
		achievementDisplay.setOpaque(false);
		add(achievementDisplay);

		// adds the panel
		achievementDisplay.getViewport().add(achievementPanel);
		achievementDisplay.getViewport().setOpaque(false);

		// adds all achievements
		for (int i = 0; i < MAX_ACHIEVEMENTS; i++) {

			achievementPanel.add(achievements[i]);
			achievements[i].setLocation(0 + i * 70, 0);
			achievements[i].setVisible(false);
		}

		// jpanel containing upgrades for producers
		JPanel upgradePanel = new JPanel();
		upgradePanel.setPreferredSize(new Dimension(350, 770));
		upgradePanel.setLayout(null);
		upgradePanel.setOpaque(false);

		// Jscrollpane containing jpanel for producers
		JScrollPane producerUpgrades = new JScrollPane();
		producerUpgrades.setViewportBorder(new LineBorder(Color.black));
		producerUpgrades.setSize(350, 300);
		producerUpgrades
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		producerUpgrades
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		producerUpgrades.getVerticalScrollBar().setUnitIncrement(10);
		producerUpgrades.setLocation(0, 200);
		producerUpgrades.setOpaque(false);
		add(producerUpgrades);

		producerUpgrades.getViewport().setOpaque(false);
		producerUpgrades.getViewport().add(upgradePanel);

		// adds all upgrades
		for (int i = 0; i < MAX_UPGRADES; i++) {

			upgradePanel.add(producers[i]);
			producers[i].setLocation(0, (i) * 70);
			upgradePanel.add(buyProducers[i]);
			buyProducers[i].setLocation(90, (i * 70) - 35);
			upgradePanel.add(buyDetails[i]);
			buyDetails[i].setLocation(90, (i * 70) - 20);

		}

		// jpanel containing upgrades for clickers
		JPanel clickPanel = new JPanel();
		clickPanel.setPreferredSize(new Dimension(350, 350));
		clickPanel.setLayout(null);
		clickPanel.setOpaque(false);

		// Jscrollpane containing jpanel for clickers
		JScrollPane clickUpgrades = new JScrollPane();
		clickUpgrades.setViewportBorder(new LineBorder(Color.black));
		clickUpgrades.setSize(350, 300);
		clickUpgrades
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		clickUpgrades
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		clickUpgrades.getVerticalScrollBar().setUnitIncrement(10);
		clickUpgrades.setLocation(650, 200);
		clickUpgrades.setOpaque(false);
		add(clickUpgrades);
		clickUpgrades.getViewport().add(clickPanel);
		clickUpgrades.getViewport().setOpaque(false);

		// adds all click upgrades
		for (int i = 0; i < MAX_CLICK; i++) {

			clickPanel.add(clickers[i]);
			clickers[i].setLocation(0, (i) * 70);
			clickPanel.add(buyClickers[i]);
			buyClickers[i].setLocation(80, (i * 70) - 30);

		}

		// dancing snoop dog image
		JLabel snoop = new JLabel(new ImageIcon("Images//snoop.gif"));
		snoop.setBounds(450, 370, 150, 308);
		snoop.setOpaque(false);
		add(snoop);

		// background image
		JLabel background = new JLabel(new ImageIcon(
				"Images//dogebackground.png"));
		background.setBounds(0, 0, 1000, 700);
		add(background);

		// makes everything above visible
		setVisible(true);
		// flavour click will remain invisible
		flavourClick.setVisible(false);

		// timer for news headline, runs every 20 milliseconds
		MyTimerTask task = new MyTimerTask();
		Timer newsTimer = new Timer();
		newsTimer.scheduleAtFixedRate(task, 0, 20);
	}

	// news headline timer
	public void header() {

		newsHeadline[0].setLocation(newsHeadline[0].getX() + 1,
				newsHeadline[0].getY());
		newsHeadline[1].setLocation(newsHeadline[1].getX() + 1,
				newsHeadline[1].getY());
		newsHeadline[2].setLocation(newsHeadline[2].getX() + 1,
				newsHeadline[2].getY());

		// if it touches edge of the screen, it goes back to beginning of path
		if (newsHeadline[0].getX() >= this.getWidth()) {

			newsHeadline[0].setText(line[(int) (Math.random() * 99)]);
			newsHeadline[0].setLocation(-(475), newsHeadline[0].getY());

		}
		if (newsHeadline[1].getX() >= this.getWidth()) {

			newsHeadline[1].setText(line[(int) (Math.random() * 99)]);
			newsHeadline[1].setLocation(-(475), newsHeadline[1].getY());

		}

		if (newsHeadline[2].getX() >= this.getWidth()) {

			newsHeadline[2].setText(line[(int) (Math.random() * 99)]);
			newsHeadline[2].setLocation(-(475), newsHeadline[2].getY());

		}

	}

	// action listeners
	public void actionPerformed(ActionEvent e) {

		// doge click button gives doge
		if (e.getSource() == dogeClick) {

			cps = cps + 1;

			Sounds.run("wow");

			// increases size of button temporarily
			if (animation == 0) {
				dogeClick.setBounds(460, 110, 80, 80);
				dogeClick.setIcon(new ImageIcon("Images/dogeopen.jpg"));
				animation = 1;

			} else if (animation == 1) {
				dogeClick.setBounds(450, 100, 100, 100);
				dogeClick.setIcon(new ImageIcon("Images/doge.jpg"));
				animation = 0;
			}

			// adds doge accordingly and updates JLabel
			doge = doge + ((clickUpgrade) * clickMultiply) * multiplier;
			dogeCount.setText("Doge: " + doge);

			// randomize text
			flavourClick.setText(flavourText[(int) (Math.random() * 49)]);
			flavourClick.setBounds((int) (Math.random() * (800)),
					(int) ((Math.random() * (401)) + 50), getWidth(), 50);
			flavourClick.setFont(new Font("Comic Sans MS", Font.BOLD,
					(int) ((Math.random() * (15)) + 15)));
			Color colour = Random.getRandomColour();
			flavourClick.setForeground(colour);
			flavourClick.setVisible(true);

		}
		// for loop for all buttons
		for (int i = 0; i < MAX_UPGRADES; i++) {

			// updates button stats and count
			if (e.getSource() == producers[i]
					&& doge >= producerStats[i].getCost()) {

				doge = doge - producerStats[i].getCost();
				producers[i].setIcon(new ImageIcon("Images//bought.PNG"));
				producerStats[i].increaseCount();
				producerStats[i].increaseCost();
				producers[i].setToolTipText("Your "
						+ producerStats[i].getButtonName() + " gives "
						+ producerStats[i].getDogeProduction()
						* producerStats[i].getCount() + " doge per second");
				dps = dps + producerStats[i].getDogeProduction();
				buyProducers[i].setText("Buy "
						+ producerStats[i].getButtonName() + " for "
						+ producerStats[i].getCost() + " doge");
				buyDetails[i].setText("You have: "
						+ producerStats[i].getCount() + " "
						+ producerStats[i].getButtonName());
			}

		}
		// updates click stats and count
		for (int i = 0; i < MAX_CLICK; i++) {
			if (e.getSource() == clickers[i]
					&& doge >= clickerStats[i].getCost()) {

				doge = doge - clickerStats[i].getCost();
				clickerStats[i].increaseCount();
				clickUpgrade = clickUpgrade + clickerStats[i].getClickBonus();
				clickMultiply = clickMultiply
						* clickerStats[i].getClickMultiplier();
				dogeClick.setToolTipText("Each click gives you "
						+ (clickUpgrade) * clickMultiply + " doge. wow");

				clickers[i].setVisible(false);
				buyClickers[i].setVisible(false);

			}
		}

		// secret developer button in corner
		if (e.getSource() == devButton) {

			doge = doge * 2;

			// plays Sandstorm by Darude
			Sounds.run("sandstorm");

		}
		if (e.getSource() == options) {

			// opens options gui
			Options options = new Options();

		}
		// saves current progress into save file
		if (e.getSource() == save) {

			// opens JOtionPane
			Sounds.run("save");
			Save temp = new Save();
			String name = JOptionPane
					.showInputDialog("What is the name of your save file?");
			temp.createOutputFile("Save//" + name + ".txt");

			String producerCount = "";
			String clickCount = "";
			String achievementCount = "";

			// adds line of code for the amount of producers
			for (int i = 0; i < MAX_UPGRADES; i++) {

				producerCount = producerCount + producerStats[i].getCount()
						+ "|";

			}
			// adds line of code for the amount of clickers
			for (int i = 0; i < MAX_CLICK; i++) {

				// if bought write true
				if (clickerStats[i].getCount() != 0) {
					clickCount = clickCount + "t|";
				} else {
					// if not bought write false
					clickCount = clickCount + "f|";
				}

			}

			// adds a line of code for achievements
			for (int i = 0; i < MAX_ACHIEVEMENTS; i++) {

				// if possess write true
				if (achievementStats[i].getCount() != 0) {
					achievementCount = achievementCount + "t|";
				} else {
					// if do not have write f
					achievementCount = achievementCount + "f|";
				}

			}

			// add all lines to file
			temp.addInfo("" + doge);
			temp.addInfo("" + producerCount);
			temp.addInfo("" + clickCount);
			temp.addInfo("" + achievementCount);
			temp.closeOutputFile();
		}
		// opens existing save file
		if (e.getSource() == open) {

			Save temp = new Save();
			String name = JOptionPane
					.showInputDialog("What is the name of your save file?");
			temp.openInputFile("Save//" + name + ".txt");
			try {

				// counters to open save file
				int add = 0;
				String data = "";
				int producerCount = 0;
				int clickCount = 0;
				int achievementCount = 0;

				// turn each line into char array
				doge = Long.parseLong(temp.getInfo());
				char producerSave[] = temp.getInfo().toCharArray();
				char clickSave[] = temp.getInfo().toCharArray();
				char achievementSave[] = temp.getInfo().toCharArray();

				// looks at producer line and adjusts values and resets Jlabel
				// text
				for (int i = 0; i < producerSave.length; i++) {

					if (producerSave[i] != '|') {

						data = data
								+ Character.getNumericValue(producerSave[i]);

					} else {

						// updates data in producers
						add = Integer.parseInt(data);
						producerStats[producerCount].setCount(add);
						producerStats[producerCount]
								.setCost((int) (producerStats[producerCount]
										.getCost() * (add * producerStats[producerCount]
										.getCostIncrease())));
						buyProducers[producerCount].setText("Buy "
								+ producerStats[producerCount].getButtonName()
								+ " for "
								+ producerStats[producerCount].getCost()
								+ " doge");
						buyDetails[producerCount].setText("You have: "
								+ producerStats[producerCount].getCount() + " "
								+ producerStats[producerCount].getButtonName());
						producers[producerCount].setToolTipText("Your "
								+ producerStats[producerCount].getButtonName()
								+ " gives "
								+ producerStats[producerCount]
										.getDogeProduction()
								* producerStats[producerCount].getCount()
								+ " doge per second");
						dps = dps
								+ (producerStats[producerCount]
										.getDogeProduction() * producerStats[producerCount]
										.getCount());

						data = "";
						producerCount++;
						add = 0;

					}

				}
				// reads clicker upgrades saves
				for (int i = 0; i < MAX_CLICK * 2; i++) {

					if (clickSave[i] == 't') {

						// updates data in clickers
						clickerStats[clickCount].setCount(1);
						clickUpgrade = (clickUpgrade + clickerStats[clickCount]
								.getClickBonus())
								* clickerStats[clickCount].getClickMultiplier();
						dogeClick.setToolTipText("Each click gives you "
								+ clickUpgrade + " doge. wow");

						clickers[clickCount].setVisible(false);
						buyClickers[clickCount].setVisible(false);

						clickCount++;

					} else if (clickSave[i] == 'f') {
						clickCount++;
					}

				}
				// reads achievement lines
				for (int i = 0; i < MAX_ACHIEVEMENTS * 2; i++) {

					if (achievementSave[i] == 't') {

						// updates achievements
						achievementStats[achievementCount].setCount(1);
						achievements[achievementCount].setVisible(true);

						achievementCount++;

					} else if (achievementSave[i] == 'f') {
						achievementCount++;
					}

				}

				dogeCount.setText("Doge: " + doge);
			} catch (IOException e1) {
				// access invalid file
				e1.printStackTrace();
				System.out.println("Invalid file!");
			}

			// closes input file
			try {
				temp.closeInputFile();
			} catch (IOException e1) {
				// access invalid file
				e1.printStackTrace();
				System.out.println("Invalid file!");
			}

		}
	}

	// timer task for adding doge
	class RemindTask extends TimerTask {

		@Override
		public void run() {

			// updates game
			dogeCount.setText("Doge: " + doge);
			dogePerSecond.setText("You get " + dps + " doge per second");
			cpsIndicator.setText(cps + " clicks per second");
			i++;

			// every second checks for click multiplier and gives bonus click
			// points
			if (i == 100) {

				if (cps >= 8) {
					hotStreak = true;
					multiplier = 2;
					if (cps >= 12) {
						multiplier = 3;
					}
					if (cps >= 16) {
						multiplier = 4;
					}
					if (cps >= 20) {
						multiplier = 5;
						eventIndicator.setText("Achievement Gotten!: "
								+ achievementStats[4].getButtonName());
						achievements[4].setVisible(true);
						achievementStats[4].increaseCount();
					}
					clickMultiplier.setText(multiplier + "x");

				} else {
					hotStreak = false;
				}

				if (hotStreak == false) {
					multiplier = 1;
					clickMultiplier.setText(multiplier + "x");// resets
																// multiplier
				}

				cps = 0;

				doge = doge + dps;
				flavourClick.setVisible(false);

				for (int i = 0; i < MAX_UPGRADES; i++) {
					producers[i].setIcon(new ImageIcon(producerStats[i]
							.getImage()));
				}

				i = 0;
			}

			// checks if achievement was achieved
			if (doge > 100) {
				eventIndicator.setText("Achievement Gotten!: "
						+ achievementStats[0].getButtonName());
				achievements[0].setVisible(true);
				achievementStats[0].increaseCount();
			}
			if (dps > 100) {
				eventIndicator.setText("Achievement Gotten!: "
						+ achievementStats[1].getButtonName());
				achievements[1].setVisible(true);
				achievementStats[1].increaseCount();
			}
			if (doge > 1000) {
				eventIndicator.setText("Achievement Gotten!: "
						+ achievementStats[2].getButtonName());
				achievements[2].setVisible(true);
				achievementStats[2].increaseCount();
			}
			if (dps > 1000) {
				eventIndicator.setText("Achievement Gotten!: "
						+ achievementStats[3].getButtonName());
				achievements[3].setVisible(true);
				achievementStats[3].increaseCount();
			}

		}
	}

	// run timer for news headline
	class MyTimerTask extends TimerTask {
		public void run() {
			header();
		}
	}

}
