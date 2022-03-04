package homeworkPack;

public class RacePlayer {
	
	private int position;
	private String playerName;
	private int playChoice;
	private int playTimes;
	
	/*
	 * all the methods in RacePlayer are functional methods
	 */
	
	
	public RacePlayer(String name) {
		position=50;
		playerName=name;
		playChoice=1;
		playTimes=1;
	}
	
	public int getPlayChoice() {
		return playChoice;
	}

	public void setPlayChoice(int playChoice) {
		this.playChoice = playChoice;
	}

	public int getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}

	public void changePos(int increment) {
		this.position+=increment;
		//System.out.println(position);
	}
	
	public int getPos() {
		return position;
	}
	
	public String getName() {
		return playerName;
	}
	
	

}

