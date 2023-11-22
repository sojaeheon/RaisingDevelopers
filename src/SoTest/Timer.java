package SoTest;
import java.awt.Color;

import javax.swing.JLabel;


public class Timer extends Thread{
	
	private JLabel timerLabel;

	private int minute;
	private int second;
	
	public Timer(JLabel timerLabel,int time) {
		this.timerLabel = timerLabel;
		
		minute = time/60;
		second = time%60;
		
		
	}
	

	@Override
	public void run() {
		

		// TODO Auto-generated method stub
		while (true) {
			timerLabel.setText(minute + " : " + second);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
			if(minute ==0) {
				if(second == 0) {
					System.out.println("스레드 종료");
					break;
					
				} else {
					second--;
				}
			}else {
				if(second==0) {
					minute--;
					second=59;
				}else {
					second--;
				}
			}

			
		}
		
		

	}
	
	
}


	
