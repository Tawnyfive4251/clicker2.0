public class Producers extends Upgrades {

	// declare private variables
	private int dogeProduction;
	private int cost;
	private double costIncrease;

	// basic 1 parameter constructor
	public Producers(int i) {
		super(i, "producer");
		// case switch to initialize buttons
		switch (i) {
		case 0:
			dogeProduction = 1;
			cost = 8;
			costIncrease = 1.15799;
			break;

		case (1):

			dogeProduction = 5;
			cost = 42;
			costIncrease = 1.15799;
			;
			break;

		case (2):

			dogeProduction = 15;
			cost = 130;
			costIncrease = 1.15799;
			;
			break;

		case (3):

			dogeProduction = 49;
			cost = 256;
			costIncrease = 1.15799;
			;
			break;
		case (4):

			dogeProduction = 89;
			cost = 305;
			costIncrease = 1.15799;
			;
			break;
		case (5):

			dogeProduction = 123;
			cost = 809;
			costIncrease = 1.15799;
			;
			break;
		case (6):

			dogeProduction = 804;
			cost = 3563;
			costIncrease = 1.15799;
			;
			break;
		case (7):

			dogeProduction = 1337;
			cost = 9001;
			costIncrease = 1.15799;
			;
			break;
		case (8):

			dogeProduction = 5423;
			cost = 17843;
			costIncrease = 1.15799;
			;
			break;
		case (9):

			dogeProduction = 15324;
			cost = 90434;
			costIncrease = 1.15799;
			;
			break;
		case (10):

			dogeProduction = 40421;
			cost = 500032;
			costIncrease = 1.15799;
			;
			break;

		default:

			dogeProduction = 0;
			cost = 0;
			costIncrease = 0;
			;

		}
	}

	// get doge produced per one
	public int getDogeProduction() {
		return dogeProduction;
	}

	// get cost of producer
	public int getCost() {
		return cost;
	}

	// get cost increase
	public double getCostIncrease() {
		return costIncrease;
	}

	// set cost to specific amount
	public void setCost(int c) {

		cost = c;
	}

	// set the cost increase to specific amount
	public void setCostIncrease(int c) {

		costIncrease = c;

	}

	// increase the cost
	public void increaseCost() {
		cost = (int) (cost * costIncrease);

	}

}
