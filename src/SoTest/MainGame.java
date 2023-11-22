package SoTest;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import dto.QuizQuestion;



public class MainGame {
	
	private int chap_num;
	
	
	//sql관련
	private Connection con=null;
	private ResultSet resultSet;
	private ArrayList<QuizQuestion> questions;
	private String[] user_ansArray= new String[10];
	
	private int currentQuestionIndex = 0;
	private String currentUserAnswer;
	
	//frame관련
	private JFrame frame;
	private JTextArea issues_area;
	private JTextField input_area;
	private JLabel lbl_quesnum;
	private JLabel lbl_chapnum;
	private JLabel timerLabel;
	
	private Font font = new Font("Monospaced",Font.BOLD,20);

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainGame window = new MainGame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainGame(int chap_num) {
		this.chap_num=chap_num;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		//프레임
		frame = new JFrame();
		frame.setBounds(100, 100, 768, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//문제가 출력되는 창
		issues_area = new JTextArea();
		issues_area.setEditable(false);
		issues_area.setFont(new Font("Monospaced", Font.PLAIN, 13));
		issues_area.setLineWrap(true);
		issues_area.setBounds(65, 192, 595, 233);
		issues_area.setFont(font);
		frame.getContentPane().add(issues_area);
		
		//답 입력창
		input_area = new JTextField();
		input_area.setBounds(65, 435, 595, 60);
		frame.getContentPane().add(input_area);
		input_area.setColumns(10);
		
		//타이머 스레드
		timerLabel = new JLabel();
		timerLabel.setOpaque(true);
		timerLabel.setBounds(836, 136, 57, 30);
		timerLabel.setForeground(Color.black);
		Timer timer = new Timer(timerLabel,20);
		frame.getContentPane().add(timerLabel);
		
		timer.start();
		
		
		
		

		
		
		lbl_chapnum = new JLabel("Chapter : "+ Integer.toString(chap_num));
		lbl_chapnum.setBounds(314, 114, 84, 15);
		frame.getContentPane().add(lbl_chapnum);
		
		lbl_quesnum = new JLabel("문제번호");
		lbl_quesnum.setBounds(65, 167, 57, 15);
		frame.getContentPane().add(lbl_quesnum);
		
		//이전 문제 이동
		JButton btn_left = new JButton("<");
		btn_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPreviousQuestion();
				
			}
		});
		btn_left.setBounds(167, 505, 97, 23);
		frame.getContentPane().add(btn_left);
		
		//다음 문제 이동
		JButton btn_right = new JButton(">");
		btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToNextQuestion();

			}
		});
		btn_right.setBounds(453, 505, 97, 23);
		frame.getContentPane().add(btn_right);
		
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
		btn_1.setBounds(302, 159, 25, 23);
		frame.getContentPane().add(btn_1);
		
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
		btn_2.setBounds(339, 159, 25, 23);
		frame.getContentPane().add(btn_2);
		
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
		btn_3.setBounds(376, 159, 25, 23);
		frame.getContentPane().add(btn_3);
		
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
		btn_4.setBounds(413, 159, 25, 23);
		frame.getContentPane().add(btn_4);
		
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
		btn_5.setBounds(450, 159, 25, 23);
		frame.getContentPane().add(btn_5);
		
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
		btn_6.setBounds(487, 159, 25, 23);
		frame.getContentPane().add(btn_6);
		
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
		btn_7.setBounds(524, 159, 25, 23);
		frame.getContentPane().add(btn_7);
		
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
		btn_8.setBounds(561, 159, 25, 23);
		frame.getContentPane().add(btn_8);
		
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
		btn_9.setBounds(598, 159, 25, 23);
		frame.getContentPane().add(btn_9);
		
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
		btn_10.setBounds(635, 159, 25, 23);
		frame.getContentPane().add(btn_10);
		
		//제출버튼
		JButton btn_submit = new JButton("제출");
		btn_submit.setBounds(314, 505, 97, 23);
		frame.getContentPane().add(btn_submit);
		btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		timer.interrupt();
        		
                checkAnswer();
                
            }

        });
		
	
		frame.setVisible(true);
//		frame.getContentPane().addKeyListener(new EnterKeyListener());
//		frame.getContentPane().setFocusable(true);
//		frame.getContentPane().requestFocus();
		
				
		SqlConnection();
		
//		while(timer.isAlive()) {
//			if(timer.getState() == timer.getState().TERMINATED) {
//				checkAnswer();
//			}
//			
//		}
		//엔터키 이벤트 처리
		
		
		
	}
	
	
	//sql연결
	private void SqlConnection() {
		
		String server = "jdbc:mysql://localhost:3306/game"; // 서버 주소
	    
        String user_name = "root"; //  접속자 id
        String password = "2049"; // 접속자 pw
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
        }
        try {
            
            con = DriverManager.getConnection(server, user_name, password);
            System.out.println("연결 완료!");
            
            // 데이터베이스에서 문제 불러오는 쿼리를 실행
            String sql = "SELECT problem, answer FROM question where chap_num = " + chap_num;
            
            System.out.println(chap_num);
            //연결한 db(con)에 sql을 넣고, 나온 값은 statement에 저장
            PreparedStatement statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            questions = new ArrayList<>();
            
            
            while (resultSet.next()) {
                String question = resultSet.getString("problem");
                String answer = resultSet.getString("answer");
                questions.add(new QuizQuestion(question, answer));
            }

            
            if (questions.isEmpty()) {
                JOptionPane.showMessageDialog(null, "문제가 없습니다.");
                System.exit(0);
            } else {
                displayQuestion();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터베이스 연결 오류");
            System.exit(1);
        }
        
        
	}
	
	//문제 출력
	private void displayQuestion() {
    	QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        issues_area.setText(currentQuestion.getQuestion());
        input_area.setText(user_ansArray[currentQuestionIndex]);
        
    }
	
	//다음 문제로 이동하는 함수
	private void goToNextQuestion() {
		if (currentQuestionIndex < questions.size()-1) {
			currentUserAnswer = input_area.getText();
    		user_ansArray[currentQuestionIndex] = currentUserAnswer;
            currentQuestionIndex++;
            lbl_quesnum.setText(Integer.toString(currentQuestionIndex+1));
            displayQuestion();
            input_area.requestFocus();
            
        } else {
            JOptionPane.showMessageDialog(frame, "모든 문제를 푸셨습니다!");
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
            JOptionPane.showMessageDialog(frame, "첫 번째 문제입니다.");
        }
    }
	
	//답 제출 함수
	private void checkAnswer() {
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


        JOptionPane.showMessageDialog(frame, "총 10문제 중 "+count+"정답 \n 획득 점수는 "+count*10+"입니다!!");
        
	}
	
	class EnterKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_F5) {
				goToNextQuestion();
			}
		}
	}
}


