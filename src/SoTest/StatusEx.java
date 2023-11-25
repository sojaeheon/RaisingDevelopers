package SoTest;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StatusEx extends JFrame implements ActionListener, Runnable {

	// 0~100을 세어주는 프로그래스바 생성
	private JProgressBar jpb = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
	private JButton bt1 = new JButton("시작");
	private JButton bt2 = new JButton("멈춤");

	// 생성자 호출
	public StatusEx() {
		super("TEST");
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		con.add("North", new JLabel("프로그래스 바 연습"));
		con.add("Center", jpb);
		// JProgressBar 셋팅
		jpb.setStringPainted(true);
		jpb.setString("0%");
		// 화면에 올리기
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(bt1);
		jp.add(bt2);
		bt1.addActionListener(this);
		bt2.addActionListener(this);

		con.add("South", jp);
		setSize(300, 150);
		setVisible(true);
	}

	private boolean bb = true;
	private static int ii;

	// 스레드 오버라이드 메소드
	@Override
	public void run() {
		if (ii == 100) {
			ii = 0;
		}
		for (int i = ii; i <= 100; i++) {
			if (!bb)
				break;
			try {
				Thread.sleep(50);
			} catch (InterruptedException ee) {
			}
			jpb.setValue(i);
			ii = i;
			jpb.setString(i + "%");

		}
		bt1.setEnabled(true);
		bt2.setEnabled(false);
	}

	// ActionListener 오버라이드 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt1) { // "시작" 버튼 클릭
			bb = true;
			new Thread(this).start();
			bt1.setEnabled(false);
			bt2.setEnabled(true);

		} else if (e.getSource() == bt2) { // "멈춤"버튼 클릭
			bb = false;
			bt1.setEnabled(true);
			bt2.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		new StatusEx();
	}

}
