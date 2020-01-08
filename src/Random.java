import java.awt.Color;

public class Random {

	// returns random colour
	public static Color getRandomColour() {

		int r = (int) ((Math.random() * (254)) + 1);
		int g = (int) ((Math.random() * (254)) + 1);
		int b = (int) ((Math.random() * (254)) + 1);

		Color temp = new Color(r, g, b);

		return temp;

	}

}
