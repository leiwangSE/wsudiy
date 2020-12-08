import java.sql.Date;

public class Review {
	private int revid;
	private String  rating;
	private String remark;
	private String url;
	private String username;
	private Date revDate;
	
	public Review() {
		
	}
	
	public Review(int revid, String  rating, String remark, String url, String username, Date revDate) {
		this.revid=revid;
		this.rating=rating;
		this.remark=remark;
		this.url=url;
		this.username=username;
		this.revDate=revDate;
	}
	
	public int getRevid() {
		return revid;
	}

	public void setRevid(int revid) {
		this.revid = revid;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getRevDate() {
		return revDate;
	}
	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}
}
