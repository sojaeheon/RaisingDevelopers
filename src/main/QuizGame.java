package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dto.QuizQuestion;
import dto.Submit_dto;
import entity.Player;



public class QuizGame extends JFrame  {
	// SCREEN SETTING
	static final int originalTilesize = 16; // 16X16 title
	static final int scale = 3;

	public final static int tileSize = originalTilesize * scale; // 48*48 title
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 16;
	public final static int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final static int screenHeight = tileSize * maxScreenRow; // 768 pixels

	//11/23
	private Image screenImage;
	private Graphics screenGraphic;
	//11/23
	private Image introBackground =  new ImageIcon(getClass().getResource("../res/background/QuizGame_images.png")).getImage();


//	private GamePanel gp;

	private JTextArea issues_area;
	private JTextField input_area;
	private JLabel lbl_quesnum;
	private JLabel lbl_chapnum;
	private JLabel timerLabel;
	GamePanel gp;


	private Font font = new Font("Monospaced",Font.BOLD,20);

	//데이터베이스 연결 및 문제관련 변수
	DBconnection db = new DBconnection();
	private ArrayList<QuizQuestion> questions;
	private String[] user_ansArray= new String[10];

	public int currentQuestionIndex = 0;
	public static String currentUserAnswer;

	public QuizGame(GamePanel gp) {
		this.gp = gp;
		questions = db.load_Problem(gp.player.currentCh);
		initialize();
		displayQuestion();


	}

	private void initialize() {

		setUndecorated(true);
		setTitle("DynamicBeat");
		setSize(screenWidth, screenHeight);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
        
        
      //문제가 출력되는 창
        issues_area = new JTextArea();
		issues_area.setEditable(false);
		issues_area.setFont(new Font("Monospaced", Font.PLAIN, 13));
		issues_area.setLineWrap(true);
		issues_area.setBounds(85,213,481,201);
		issues_area.setFont(font);
		this.add(issues_area);

		//답 입력창
		input_area = new JTextField();
		input_area.setBounds(82,416,485,43);
		this.add(input_area);
		input_area.setColumns(10);
		input_area.requestFocus();
		input_area.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					goToNextQuestion();
				}
			}
		});

		lbl_chapnum = new JLabel("Chapter : "+ Integer.toString(gp.player.currentCh));
		lbl_chapnum.setBounds(118, 160, 125, 20);
		this.add(lbl_chapnum);

		lbl_quesnum = new JLabel("문제 : "+Integer.toString(Submit_dto.chap));
		lbl_quesnum.setBounds(85, 197, 76, 13);
		this.add(lbl_quesnum);

		timerLabel = new JLabel();
		timerLabel.setOpaque(true);
		timerLabel.setBackground(Color.WHITE);
		Font timer_font = new Font("Monospaced",Font.BOLD,16);
		timerLabel.setFont(timer_font);
		Timer timer = new Timer(timerLabel, 30);
//		timer.start();
		timer.startQuizThread();
		timerLabel.setBounds(580, 213, 95, 28);
		add(timerLabel);
//		if(timer.getState()== Thread.State.TERMINATED) {
//			checkAnswer();
//		}



		//제출버튼
		JButton btn_submit = new JButton("제출");
		btn_submit.setBounds(605, 465, 70, 22);
		this.add(btn_submit);
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				timer.stopquizThread();
				int result = JOptionPane.showConfirmDialog(null, "제출하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					timer.startQuizThread();
				} else if (result == JOptionPane.YES_OPTION) {
					checkAnswer();

				} else {
					timer.startQuizThread();

				}

			}

		});

		//이전 문제 이동
		JButton btn_left = new JButton("<");
		btn_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPreviousQuestion();

			}
		});
		btn_left.setBounds(230, 465, 63, 22);
		this.add(btn_left);

		//다음 문제 이동
		JButton btn_right = new JButton(">");
		btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToNextQuestion();

			}
		});
		btn_right.setBounds(320, 465, 63, 22);
		this.add(btn_right);


		//1번 문제로 이동
		JButton btn_1 = new JButton("1번 문제");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 0;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);

			}
		});
		btn_1.setBounds(575, 263, 100, 15);
		this.add(btn_1);

		//2번 문제로 이동
		JButton btn_2 = new JButton("2번 문제");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 1;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_2.setBounds(575, 283, 100, 15);
		this.add(btn_2);

		//3번 문제로 이동
		JButton btn_3 = new JButton("3번 문제");
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 2;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_3.setBounds(575, 303, 100, 15);
		this.add(btn_3);

		//4번 문제로 이동
		JButton btn_4 = new JButton("4번 문제");
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 3;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_4.setBounds(575, 323, 100, 15);
		this.add(btn_4);

		//5번 문제로 이동
		JButton btn_5 = new JButton("5번 문제");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 4;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_5.setBounds(575, 343, 100, 15);
		this.add(btn_5);

		//6번 문제로 이동
		JButton btn_6 = new JButton("6번 문제");
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 5;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_6.setBounds(575, 363, 100, 15);
		this.add(btn_6);

		//7번 문제로 이동
		JButton btn_7 = new JButton("7번 문제");
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 6;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_7.setBounds(575, 383, 100, 15);
		this.add(btn_7);

		//8번 문제로 이동
		JButton btn_8 = new JButton("8번 문제");
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 7;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_8.setBounds(575, 403, 100, 15);
		this.add(btn_8);

		//9번 문제로 이동
		JButton btn_9 = new JButton("9번 문제");
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 8;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_9.setBounds(575, 423, 100, 15);
		this.add(btn_9);

		//10번 문제로 이동
		JButton btn_10 = new JButton("10번 문제");
		btn_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUserAnswer = input_area.getText();
				user_ansArray[currentQuestionIndex] = currentUserAnswer;

				currentQuestionIndex = 9;
				QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
				System.out.println(currentQuestion.getQuestion());
				issues_area.setText(currentQuestion.getQuestion());
				input_area.setText(user_ansArray[currentQuestionIndex]);
			}
		});
		btn_10.setBounds(575, 443, 100, 15);
		this.add(btn_10);

	}


	private void setDoubleBuffered(boolean b) {
		// TODO Auto-generated method stub

	}

	private void displayQuestion() {
		QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        issues_area.setText(currentQuestion.getQuestion());
        input_area.setText(user_ansArray[currentQuestionIndex]);
        
    }

	//다음 문제로 이동하는 함수
	public void goToNextQuestion() {
		if (currentQuestionIndex < questions.size()-1) {
			currentUserAnswer = input_area.getText();
			user_ansArray[currentQuestionIndex] = currentUserAnswer;
            currentQuestionIndex++;
            lbl_quesnum.setText("문제 : "+Integer.toString(currentQuestionIndex+1));
            displayQuestion();
            input_area.requestFocus();
            
            
        } else {
            JOptionPane.showMessageDialog(this, "모든 문제를 푸셨습니다!");
        }
	}

	//이전 문제로 이동하는 함수
	private void goToPreviousQuestion() {
		if (currentQuestionIndex > 0) {
			currentUserAnswer = input_area.getText();
			user_ansArray[currentQuestionIndex] = currentUserAnswer;
            currentQuestionIndex--;
			lbl_quesnum.setText("문제 : "+Integer.toString(currentQuestionIndex+1));
            displayQuestion();
            input_area.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "첫 번째 문제입니다.");
        }
    }

	//답 제출 함수
	public void checkAnswer() {
		currentUserAnswer = input_area.getText();
		user_ansArray[currentQuestionIndex] = currentUserAnswer;

		float score=0;
		int count=0;

		for(int i =0;i<questions.size();i++) {
			try {
				if(user_ansArray[i].equals(questions.get(i).getAnswer())) {
					score+=0.09;
					count++;
				}


			}catch(NullPointerException e) {

			}

		}

		JOptionPane.showMessageDialog(this, "총 10문제 중 "+count+"정답 \n 획득 점수는 "+count*10+"입니다!!");
		
		JOptionPane.showMessageDialog(this, "집으로 이동합니다.");
		
        Submit_dto sub = null;
        gp.player.score += count*1.0;
		gp.player.level = (int)gp.player.score;
		gp.player.currentCh++;
		gp.gameState = gp.transitionState;

		gp.eHandler.tempMap = 0;
        gp.eHandler.tempCol = 8;
        gp.eHandler.tempRow = 14;
//        gp.gameState = gp.playState;
        
//		if (gp.player.currentCh >= 2 && gp.player.score >= 2.0) {
//
////			gp.eHandler.tempMap = 0;
////	        gp.eHandler.tempCol = 8;
////	        gp.eHandler.tempRow = 14;
////	        gp.gameState = gp.transitionState;
//			
//			gp.gameState = gp.endingState;
//	        
//		}
        dispose();
        gp.requestFocus();

//        new Status();


	}


	//11/23
	public void paint(Graphics g) {
		screenImage = createImage(screenWidth, screenHeight);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}


	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, this.getWidth(), this.getHeight(), 0, 0, screenWidth, screenHeight, null);
		this.paintComponents(g);
		this.repaint();
	}

	public class Timer implements Runnable{

		private JLabel timerLabel;
		Thread times;


		private int minute;
		private int second;

		public Timer(JLabel timerLabel,int time) {
			this.timerLabel = timerLabel;


			minute = time/60;
			second = time%60;


		}


		public void startQuizThread() {
			times = new Thread(this);
			times.start();
		}

		public void stopquizThread() {
			times.interrupt();
		}


		@Override
		public void run() {

			// TODO Auto-generated method stub
			while (true) {
				timerLabel.setText(minute + " : " + second);
				timerLabel.setHorizontalTextPosition(timerLabel.CENTER);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					return;
				}
				if (minute == 0) {
					if (second == 0) {
						System.out.println("스레드 종료");
						break;

					} else {
						second--;
					}
				} else {
					if (second == 0) {
						minute--;
						second = 59;
					} else {
						second--;
					}
				}

				if (minute == 0 && second == 0) {
					break;

				}

			}
			if(minute==0 && second==0) {
				checkAnswer();
			}


		}


	}


}
