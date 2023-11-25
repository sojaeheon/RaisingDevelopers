package SoTest;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dto.QuizQuestion;
import dto.Submit_dto;
import SoTest.KeyListener;

public class QuizGame extends JPanel implements Runnable {
	//11/23
	private Image screenImage;
	private Graphics screenGraphic;
	//11/23
	private Image introBackground =  new ImageIcon(this.getClass().getResource("../res/background/QuizGame_images.png")).getImage();
	
	private Main testScreen;
	
	private JTextArea issues_area;
	private JTextField input_area;
	private JLabel lbl_quesnum;
	private JLabel lbl_chapnum;
	private JLabel timerLabel;
	private int chap;
	
	private Font font = new Font("Monospaced",Font.BOLD,20);
	
	//데이터베이스 연결 및 문제관련 변수
	DBconnection db = new DBconnection();
	private ArrayList<QuizQuestion> questions;
	private String[] user_ansArray= new String[10];
	
	public static int currentQuestionIndex = 0;
	public static String currentUserAnswer;
	
	public QuizGame(Main testScreen, int chap) {
		this.testScreen = testScreen;
		this.chap = chap;
		questions = db.load_Problem(chap);
		initialize();
		displayQuestion();
		addKeyListener(new KeyListener());
		Music introMusic = new Music("introMusic.mp3",true);
		introMusic.start();
		
		
	}

	private void initialize() {

		this.setLayout(null);
		this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
		this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        
        
       
		this.requestFocus();
		setFocusable(true);
        
        
      //문제가 출력되는 창
        issues_area = new JTextArea();
		issues_area.setEditable(false);
		issues_area.setFont(new Font("Monospaced", Font.PLAIN, 13));
		issues_area.setLineWrap(true);
		issues_area.setBounds(83,202,470,189);
		issues_area.setFont(font);
		this.add(issues_area);
		
		//답 입력창
		input_area = new JTextField();
		input_area.setBounds(81,395,475,40);
		this.add(input_area);
		input_area.setColumns(10);
		
		lbl_chapnum = new JLabel("Chapter : "+ Integer.toString(chap));
		lbl_chapnum.setBounds(115, 152, 125, 20);
		this.add(lbl_chapnum);
		
		lbl_quesnum = new JLabel("문제번호");
		lbl_quesnum.setBounds(82, 187, 76, 13);
		this.add(lbl_quesnum);
		
		//이전 문제 이동
		JButton btn_left = new JButton("<");
		btn_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPreviousQuestion();
				
			}
		});
		btn_left.setBounds(220, 445, 63, 22);
		this.add(btn_left);
		
		//다음 문제 이동
		JButton btn_right = new JButton(">");
		btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToNextQuestion();

			}
		});
		btn_right.setBounds(310, 445, 63, 22);
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
		btn_1.setBounds(562, 240, 100, 15);
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
		btn_2.setBounds(562, 260, 100, 15);
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
		btn_3.setBounds(562, 280, 100, 15);
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
		btn_4.setBounds(562, 300, 100, 15);
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
		btn_5.setBounds(562, 320, 100, 15);
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
		btn_6.setBounds(562, 340, 100, 15);
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
		btn_7.setBounds(562, 360, 100, 15);
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
		btn_8.setBounds(562, 380, 100, 15);
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
		btn_9.setBounds(562, 400, 100, 15);
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
		btn_10.setBounds(562, 420, 100, 15);
		this.add(btn_10);
		
		

		//제출버튼
		JButton btn_submit = new JButton("제출");
		btn_submit.setBounds(590, 445, 70, 22);
		this.add(btn_submit);
		btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//        		timer.interrupt();
        		
                checkAnswer();
                
            }

        });
		
		
		
		
	}
	
	@Override
	public void run() {
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
            lbl_quesnum.setText(Integer.toString(currentQuestionIndex+1));
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
			lbl_quesnum.setText(Integer.toString(currentQuestionIndex+1));
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
        
        Submit_dto sub = null;
        sub.score += count*10;
        sub.chap++;
        testScreen.change("gamepanel");
//        new Status();
        
        
        
        
        
	}
	
	//11/23
	public void paint(Graphics g) {
		screenImage = createImage(Main.screenWidth, Main.screenHeight);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, this.getWidth(), this.getHeight(), 0, 0, Main.screenWidth, Main.screenHeight, null);
		this.paintComponents(g);
		this.repaint();
	}
	
	
}
