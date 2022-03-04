package homeworkPack;

import java.awt.Shape;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class RaceCar {
	private int x,y;
	private String name;
	
	public RaceCar() {
		
		x=y=20;
	}
	
	public void setPosition(int xPos,int yPos) {
		x=xPos;
		y=yPos;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void move() {
		x++;
	}
	public void reverse() {
		x--;
	}
	public Shape getCar() {
		return new Ellipse2D.Double(x,y,50,50);
		
	}
	
	public int getPos() {
		return x;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}



/*
 * public class Raindrop {
	private int x, y, currentSize, visibleSize;
	private static Random r = new Random();
	private final int MAX_RIPPLE = 30;
	private final int RIPPLE_STEP = 2;
	
	public Raindrop(){
		currentSize = 0;
		visibleSize = 1;
		x = y = 1;
	}
	
	public void setPosition(int xPos, int yPos){
		x = xPos;
		y = yPos;
		visibleSize = Math.abs((r.nextInt() % MAX_RIPPLE)) + 1;
		currentSize = 1;
	}
	
	public void ripple(){
		x -= RIPPLE_STEP / 2;
		y -= RIPPLE_STEP / 2;
		currentSize += RIPPLE_STEP;
	}
	
	public boolean isVisible(){
		return currentSize < visibleSize;
	}
	
	public Shape getDrop(){
		return new Ellipse2D.Double(x, y, currentSize, currentSize);
	}	
}
 * 
 */
