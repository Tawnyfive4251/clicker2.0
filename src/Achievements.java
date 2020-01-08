public class Achievements extends Upgrades {

	//NOTE: THis class was not able to be fully used
	
	private int dogeMultiplier = 1;
	private int bonusDoge = 10;
	private int goldenDoge = 100;

	//basic 1 parameter constructor
	public Achievements(int i) {
		super(i, "achievements");
		// case switch to initialize buttons
		switch (i) {
		case 0:
			dogeMultiplier = 2;
			break;

		case (1):
			bonusDoge = 100;
			;
			break;

		case (2):

			goldenDoge = 1000;
			;
			break;

		case (3):

			dogeMultiplier = 3;
			;
			break;

		default:

			;

		}
	}

}
