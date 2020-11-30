import java.sql.Date;

public class Question {
	
	private int qid;
	private String question;
	private String username;
	private Date askDate;
	
	public Question () {
	};
	
	public Question (int qid) {
		this.qid=qid;
	}
	
	public Question (String question, String username) {
		this.question=question;
		this.username=username;
	}
	
	public Question (String question, String username, Date askDate) {
		this.question=question;
		this.username=username;
		this.askDate=askDate;
	}
	
	public Question(int qid,String question,String username,Date askDate) {
		this(question, username, askDate);
		this.qid=qid;
	};
	
	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getAskDate() {
		return askDate;
	}

	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}
	
}
