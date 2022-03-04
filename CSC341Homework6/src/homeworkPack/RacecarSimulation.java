package homeworkPack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RacecarSimulation {
	
}

abstract class AnimationFrame extends JFrame{
	protected Thread animator;
	protected JPanel pnl;
	
	public AnimationFrame(String title){
		super(title);
		this.setSize(600, 600);
		this.setLocation(300, 100);
		pnl = getAnimationPanel();
		this.add(pnl);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				animator.interrupt();
				e.getWindow().dispose();
				System.exit(0);
			}
		});
		init();
		
	}
	
	public void updateMovement() {
		animator = new Thread(getTask());
		animator.start();
		return;
	}
	
	public void repaint(){
		super.repaint();
		pnl.repaint();
	}	
	
	abstract void init();
	abstract Runnable getTask();
	abstract JPanel getAnimationPanel();
}

class RaceSimuFrame extends AnimationFrame{
	private RaceCar[] cars;
	
	
	public RaceSimuFrame(String title){
		super(title);
	}

	@Override
	public void init() {
		int yStart=100;
		cars = new RaceCar[RaceDriver.players.size()];
		for(int i = 0; i < cars.length; i++){
			cars[i] = new RaceCar();
			cars[i].setPosition(20,yStart);
			cars[i].setName(RaceDriver.players.get(i).getName());
			yStart+=100;
		}		
		
	}	

	@Override
	Runnable getTask() {
		return new RainTask();
	}
	
	@Override
	JPanel getAnimationPanel() {
		class RainPanel extends JPanel{		
			public RainPanel(){
				this.setBackground(Color.white);
			}			
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				for(int i = 0; i < cars.length; i++){
					g2.fill(cars[i].getCar());
					g2.drawString(cars[i].getName()+" "+cars[i].getPos(),cars[i].getX(),cars[i].getY());
				}
				
				g2.drawLine(450,0,450,600);
				g2.drawString("START",50,50);
				g2.drawString("FINISH",450,50);
				g2.drawLine(50,0,50,600);
			}
		}//end RainPanel
		return new RainPanel();
	}
	
	class RainTask implements Runnable{
		@Override
		public void run() {
			
			for(int i = 0; i < cars.length;i++) {
				System.out.println(RaceDriver.players.get(i).getPos());
			}
			
			while( !Thread.currentThread().isInterrupted() ){
				for(int i = 0; i < cars.length; i++){
					RaceCar drop = cars[i];
					//System.out.println(drop.getPos());
					if(isCarAtPos(i)) {
						//System.out.println(drop.getName());
						continue;
					}
					if(allCarsAtPos()) {
						return;
					}
					if(RaceDriver.players.get(i).getPos()<cars[i].getPos()) {
						drop.reverse();
					}
					else {
						drop.move();
					}
					
					
				}
				try{
					Thread.sleep(30);
				}catch(InterruptedException e){
					System.out.println(e.getStackTrace().toString());
				}
				pnl.repaint();
			}			
		}
		
		public boolean isCarAtPos(int i) {
			if(RaceDriver.players.get(i).getPos()==cars[i].getPos())return true;
			else return false;
			
		}
		public boolean allCarsAtPos() {
			int allCars=0;
			for(int i = 0; i<cars.length;i++) {
				if(isCarAtPos(i)) {
					allCars++;
				}
			}
			if(allCars==cars.length)return true;
			else return false;
		}
	}
}
