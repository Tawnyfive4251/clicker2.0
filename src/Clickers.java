public class Clickers extends Upgrades {

	private int clickMultiplier;
	private int clickBonus;
	private int cost;

	// basic constructor with 1 parameter
	public Clickers(int i) {
		super(i, "clicker");

		// case switch to initialize buttons
		switch (i) {
		case 0:
			clickMultiplier = 1;
			clickBonus = 5;
			cost = 100;
			break;

		case (1):

			clickMultiplier = 2;
			clickBonus = 0;
			cost = 1000;
			;
			break;

		case (2):

			clickMultiplier = 1;
			clickBonus = 250;
			cost = 10000;
			;
			break;

		case (3):

			clickMultiplier = 3;
			clickBonus = 0;
			cost = 100000;
			;
			break;
		case (4):

			clickMultiplier = 1;
			clickBonus = 35000;
			cost = 1000000;
			;
			break;

		default:

			clickMultiplier = 0;
			clickBonus = 0;
			cost = 0;
			;
			break;

		}

	}

	// get multiplier
	public int getClickMultiplier() {
		return clickMultiplier;
	}

	// get click bonus
	public int getClickBonus() {
		return clickBonus;
	}

	// get cost
	public int getCost() {
		return cost;
	}

}
