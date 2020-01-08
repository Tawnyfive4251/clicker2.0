public class Upgrades {

	//private variables
	private String buttonName;
	private String image;
	private int count = 0;

	//two parameter constructor
	public Upgrades(int i, String type) {

		if (type.equals("producer")) {
			switch (i) {
			case 0:
				buttonName = "Doge Cursor";
				image = "Images/dogecursor.png";
				break;

			case (1):
				buttonName = "Doge worker";
				image = "Images/dogeworker.png";
				;
				break;

			case (2):
				buttonName = "Doge Miner";
				image = "Images/dogeminer.png";
				;
				break;

			case (3):
				buttonName = "Doge Farmer";
				image = "Images/dogefarmer.png";
				;
				break;
			case (4):
				buttonName = "Doge Farm";
				image = "Images/dogefarm.png";
				;
				break;
			case (5):
				buttonName = "Doge Mine";
				image = "Images/dogemine.png";
				;
				break;
			case (6):
				buttonName = "Doge Factory";
				image = "Images/dogefactory.png";
				;
				break;
			case (7):
				buttonName = "Doge Bank";
				image = "Images/dogebank.png";
				;
				break;
			case (8):
				buttonName = "Doge Trade Center";
				image = "Images/dogetrade.png";
				;
				break;
			case (9):
				buttonName = "Doge Space station";
				image = "Images/dogespacestation.png";
				;
				break;
			case (10):
				buttonName = "Doge Portal";
				image = "Images/dogeportal.png";
				;

				break;

			}
		} else if (type.equals("clicker")) {

			switch (i) {
			case 0:
				buttonName = "Extra click upgrade";
				image = "Images/extraclick.png";
				break;

			case (1):
				buttonName = "Double doge click upgrade";
				image = "Images/doubledoge.png";
				;
				break;

			case (2):
				buttonName = "Premium click upgrade";
				image = "Images/premiumdoge.png";
				;
				break;

			case (3):
				buttonName = "Triple doge click upgrade";
				image = "Images/tripledoge.png";
				;
				break;
			case (4):
				buttonName = "Mega doge click upgrade";
				image = "Images/megadoge.png";
				;
				break;

			}

		} else if (type.equals("achievements")) {

			switch (i) {
			case 0:
				buttonName = "Doge Recruit";
				image = "Images/dogerecruit.png";
				break;

			case (1):
				buttonName = "Doge Journeyman";
				image = "Images/dogejourneyman.png";

				;
				break;

			case (2):
				buttonName = "Doge expert";
				image = "Images/dogexpert.png";
			
				;
				break;

			case (3):
				buttonName = "Doge Veteran";
				image = "Images/dogeveteran.jpg";

				;
				break;
				
			case (4):
				buttonName = "Speedy Click";
				image = "Images/speedyclick.png";

				;
				break;	
			}

		}
	}

	//get button name
	public String getButtonName() {
		return buttonName;
	}

	//get image
	public String getImage() {
		return image;
	}

	//increase button count
	public void increaseCount() {
		count++;
	}

	//increase count
	public int getCount() {
		return count;
	}

	//set the count to an int
	public void setCount(int c) {
		count = c;
	}

}
