package homeworkPack;

import javax.swing.JOptionPane;

public class IOHandler {
	
	
	public IOHandler() {
		
	}
	/*
	 * functional
	 * very reusable
	 * clearly defined
	 */
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	/*
	 * functional
	 * reusable
	 * clearly defined
	 */
	public int getSelection(String message,int lowerBound,int upperBound) {
		boolean isValid=false;
		int selection=0;
		while(!isValid) {
			selection = Integer.parseInt(JOptionPane.showInputDialog(message));
			
			if(selection>=lowerBound&&selection<=upperBound) {
				isValid=true;
			}
			if(isValid)break;
			displayMessage("You made an invalid selection!");
		}
		
		return selection;
	}

}
