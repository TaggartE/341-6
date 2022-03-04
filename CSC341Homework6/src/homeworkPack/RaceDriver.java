package homeworkPack;

import java.util.ArrayList;
import java.util.List;

public class RaceDriver {
	
	public static List<RacePlayer>players;
	private RaceSimuFrame rf;
	//private int raceLength;
	private IOHandler IO;
	private Dice dice;
	private RacePlayer leader;
	boolean aWinner;
	private final int GAME_LENGTH=400;

	public RaceDriver() {
		leader=null;
		aWinner=false;
		//raceLength=0;
	}
	/*
	 * procedural
	 * follows the procedures 
	 */
	public void play() {
		init();
		startGame();
		endGame();
	}
	/*
	 * temporal cohesion
	 * series of actions related in time
	 * initializes things
	 */
	public void init() {
		players = new ArrayList<RacePlayer>();
		IO = new IOHandler();
		dice = new Dice();		
		int numPlayers=IO.getSelection("How many players will be racing?",1,99);
		players.add(new HumanRacePlayer("YOU"));
		for(int i = 1; i<=numPlayers;i++) {
			players.add(new RacePlayer("COMPUTER "+i));	
		}
		for(RacePlayer p:players) {
			//getPlayInputs(p);
		}
		//raceLength = IO.getSelection("How long will this race be?",1,999);
		leader=players.get(0);
		rf = new RaceSimuFrame("Race Simulation");
		rf.setVisible(true);
	}
	/*
	 * procedural
	 * follows set of procedures
	 */
	public void startGame() {
		while(!aWinner) {
			playRound();
			roundStats();
		}
	}
	/*
	 * logical cohesion
	 * works one at a time
	 * cannot be reused
	 */
	public void playRound() {
		
		for(RacePlayer p: players) {
			getPlayInputs(p);
			for(int i = 0; i < p.getPlayTimes(); i++) {
				p.changePos(move(p.getPlayChoice(),p));
			}
			
			if(p.getPos()>=GAME_LENGTH) {
				aWinner=true;	
			}			
		}
	}
	/*
	 * functional
	 * does one well defined action
	 */
	public void roundStats() {
		String message = "";
		for(RacePlayer p:players) {
			message+=p.getName()+" "+p.getPos()+"\n";
		}
		IO.displayMessage(message);
		rf.updateMovement();
	}
	/*
	 * functional
	 * does one well defined action
	 * ends the game
	 */
	public void endGame() {
		calcLeader();
		IO.displayMessage("WINNER: "+leader.getName());
	}
	/*
	 * sequential
	 * performs several actions, each with their own entry point	
	 */
	public int move(int moveType,RacePlayer p) {
		if(moveType==1) {
			return (dice.roll()+(leader.getPos()-p.getPos())/2);
		}
		if(moveType==2) {
			int num = dice.roll();
			if(num%2==0) {
				return num*3;
			}
			else {
				return num;
			}
		}
		if(moveType==3) {
			int roll = dice.roll();
			if(roll==1||roll==2) {
				return (roll+(p.getPos()-getLast().getPos())/2);
			}
			else return -(roll+(p.getPos()-getLast().getPos())/2);
		}
		//if somehow the random choice does not work
		return dice.roll();
	}
	/*
	 * functional
	 * does one well defined action
	 * calculating the leader of the game
	 */
	public void calcLeader() {
		for(RacePlayer p: players) {
			if(p.getPos()>=leader.getPos()) {
				leader=p;
			}
		}
	}
	/*
	 * functional
	 * calculates one thing in a well defined method
	 */
	public RacePlayer getLast() {
		RacePlayer isLast = players.get(0);
		for(RacePlayer p:players) {
			if(p.getPos()>=isLast.getPos()) {
				isLast=p;
			}
		}
		return isLast;
	}
	/*
	 * functional cohesion
	 * gets inputs, whether it be for humans or computers
	 * the name of the method does what we want it to do
	 */
	public void getPlayInputs(RacePlayer p) {
		if(p instanceof HumanRacePlayer) {
			p.setPlayChoice(IO.getSelection("Select your play choice (1-3)",1,3));
			p.setPlayTimes(IO.getSelection("Select your play times", 2, 5));
		}
		else {
			//move type
			p.setPlayChoice(getRandomNumber(1,3));
			//move duration
			p.setPlayTimes(getRandomNumber(2,5));
		}
	}
	/*
	 * functional
	 * does one thing and can be reused many many times
	 */
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}
