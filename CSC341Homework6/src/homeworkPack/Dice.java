package homeworkPack;

public class Dice {
	//a die needs to roll a random number
	//in this case we are rolling a die numbered 1-6
	public Dice() {
	}
	/*
	 * functional
	 * does one thing and it can be done multiple times
	 */
	public int roll() {
		return (int)(Math.random()*6 + 1);
	}

}
