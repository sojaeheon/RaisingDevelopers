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
	private float score=0;
	private int count=0;
	
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
		SqlConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//프레임
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//문제가 출력되는 창
		issues_area = new JTextArea();
		issues_area.setEditable(false);
		issues_area.setFont(new Font("Monospaced", Font.PLAIN, 13));
		issues_area.setLineWrap(true);
		issues_area.setBounds(84, 124, 680, 250);
		issues_area.setFont(font);
		frame.getContentPane().add(issues_area);
		
		//답 입력창
		input_area = new JTextField();
		input_area.setBounds(84, 420, 680, 90);
		frame.getContentPane().add(input_area);
		input_area.setColumns(10);
		
		//제출버튼
		JButton btn_submit = new JButton("제출");
		btn_submit.setBounds(369, 561, 97, 23);
		frame.getContentPane().add(btn_submit);
		
		JLabel lbl_timer = new JLabel("New label");
		lbl_timer.setBounds(707, 99, 57, 15);
		frame.getContentPane().add(lbl_timer);
		
		lbl_chapnum = new JLabel("Chapter : "+ Integer.toString(chap_num));
		lbl_chapnum.setBounds(392, 63, 84, 15);
		frame.getContentPane().add(lbl_chapnum);
		
		lbl_quesnum = new JLabel(Integer.toString(currentQuestionIndex+1));
		lbl_quesnum.setBounds(84, 99, 57, 15);
		frame.getContentPane().add(lbl_quesnum);
		
		JButton btn_left = new JButton("<");
		btn_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPreviousQuestion();
				
			}
		});
		btn_left.setBounds(305, 520, 97, 23);
		frame.getContentPane().add(btn_left);
		
		JButton btn_right = new JButton(">");
		btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToNextQuestion();

			}
		});
		btn_right.setBounds(432, 520, 97, 23);
		frame.getContentPane().add(btn_right);
		
		btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }

        });
		
		

		frame.setVisible(true);
		
		
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
            String sql = "SELECT problem, answer FROM question where chap_num = "+chap_num;
            
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
	
	private void displayQuestion() {
    	QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        issues_area.setText(currentQuestion.getQuestion());
        input_area.setText(user_ansArray[currentQuestionIndex]);
        
    }
	
	private void goToNextQuestion() {
		if (currentQuestionIndex < questions.size() - 1) {
			currentUserAnswer = input_area.getText();
    		user_ansArray[currentQuestionIndex] = currentUserAnswer;
            currentQuestionIndex++;
            lbl_quesnum.setText(Integer.toString(currentQuestionIndex+1));
            displayQuestion();
            
        } else {
            JOptionPane.showMessageDialog(frame, "모든 문제를 푸셨습니다!");
        }
	}
	private void goToPreviousQuestion() {
    	if (currentQuestionIndex > 0) {
    		currentUserAnswer = input_area.getText();
    		user_ansArray[currentQuestionIndex] = currentUserAnswer;
            currentQuestionIndex--;
			lbl_quesnum.setText(Integer.toString(currentQuestionIndex+1));
            displayQuestion();
        } else {
            JOptionPane.showMessageDialog(frame, "첫 번째 문제입니다.");
        }
    }
	private void checkAnswer() {
		for(int i =0;i<questions.size();i++) {
			try {
				if(user_ansArray[i].equals(questions.get(i).getAnswer())) {
					score+=0.09;
					count++;
				}
			}catch(NullPointerException e) {
				
			}
			
		}

        JOptionPane.showMessageDialog(frame, "총 10문제 중 "+count+"정답 \n 획득 점수는 "+score+"입니다!!");
        
	}
}
