package SoTest;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.quizpanel == null) {
			return;
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			Main.quizpanel.goToNextQuestion();
			
		}
		
	}

}
