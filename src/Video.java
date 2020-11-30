import java.sql.Date;

public class Video {

	private String url;
	private String title;
	private String des;
	private int qid;
	private String username;
	private Date postDate;
	
	public Video() {};
	
	public Video(String url, String title, String des, int qid, String username, Date postDate) {
		this.url=url;
		this.title=title;
		this.des=des;
		this.qid=qid;
		this.username=username;
		this.postDate=postDate;		
	};
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
