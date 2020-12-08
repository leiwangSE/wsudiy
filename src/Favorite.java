
public class Favorite {

	private String url;
	private String username;
	
	public Favorite()
	{
		
	}
	
	public Favorite(String url, String username) {
		this.url=url;
		this.username=username;
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
	
}
