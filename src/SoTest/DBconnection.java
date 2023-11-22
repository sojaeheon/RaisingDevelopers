package SoTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.QuizQuestion;

public class DBconnection {
	private Connection con=null;
	private ResultSet resultSet;
	private ArrayList<QuizQuestion> questions;
	
	
	String server = "jdbc:mysql://localhost:3306/game"; // 서버 주소
    
    String user_name = "root"; //  접속자 id
    String password = "2049"; // 접속자 pw
	
	public DBconnection() {
		
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
	        e.printStackTrace();
	    }
	    
		
	}
	
   
    public ArrayList<QuizQuestion> load_Problem(int chap){
    	// 데이터베이스에서 문제 불러오는 쿼리를 실행
    	try {
	        
	        con = DriverManager.getConnection(server, user_name, password);
	        System.out.println("연결 완료!");
	        String sql = "SELECT problem, answer FROM question where chap_num = " + chap;
	        
	        System.out.println(chap);
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
            }
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "데이터베이스 연결 오류");
	        System.exit(1);
	    }

         return questions;

    	
    }
    

}
