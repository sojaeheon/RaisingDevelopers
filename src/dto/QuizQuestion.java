package dto;

public class QuizQuestion {
	private String question;
    private String answer;
    private String user_answer;

    public QuizQuestion(String question, String answer,String user_answer) {
        this.question = question;
        this.answer = answer;
        this.user_answer = user_answer;
    }
    public QuizQuestion(String question, String answer) {
    	this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
    
    public String getUseAnswer() {
    	return user_answer;
    }
}
