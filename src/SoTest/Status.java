package SoTest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import dto.Submit_dto;

public class Status extends JFrame{
	// 0~100을 세어주는 프로그래스바 생성
		private JProgressBar jpb = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		private JLabel level_set;
		
		Submit_dto sub;

		
		public Status() {
			super("Statuz_Test");
			this.setSize(768,768);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setLayout(null);
			
			level_set = new JLabel(Integer.toString(sub.score/100));
			System.out.println(sub.score);
			level_set.setBounds(30, 30, 30, 30);;
			add(level_set);
			
			
			
			jpb.setStringPainted(true);
			jpb.setBounds(10, 10, 200, 20);
			jpb.setValue(sub.score%100);
			System.out.println(sub.score%100);
			add(jpb);
			
		}

		
		
		public static void main(String[] args) {
			new Status();
			
		}

		
}
