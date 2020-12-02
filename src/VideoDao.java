

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author Lei
 *
 */

public class VideoDao {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public VideoDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     //connect with DB;
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
            	//load DB driver, and each DB has its own driver;
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            //setup connection with DB;
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     //disconnect with the DB;
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     //prepareStatement allows to issue SQL query to DB
    public boolean postVideo(Video video) throws SQLException {
    	System.out.println("Call postQuestion");
        String sql = "INSERT INTO wsudiy.videos (Url, Title, Des, Qid, Username, Postdate) VALUES (?, ?, ?, ?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, video.getUrl());
        statement.setString(2, video.getTitle());
        statement.setString(3, video.getDes());
        statement.setInt(4, video.getQid());
        statement.setString(5,video.getUsername());
        statement.setDate(6, video.getPostDate());
        
        System.out.println("Sql script: "+sql);
        
        
        boolean rowInserted = statement.executeUpdate() > 0;
        System.out.println("Inserted a video:"+rowInserted);
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Video>listVideos()throws SQLException {
        List<Video> listVideos=new ArrayList<>();
         
        String sql = "SELECT * FROM videos";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String url=resultSet.getString("Url");
            String title = resultSet.getString("Title");
            String des = resultSet.getString("Des");
            int qid = resultSet.getInt("Qid");
            String username=resultSet.getString("Username");
            Date postDate=resultSet.getDate("Postdate");
            
            
             
            Video video= new Video(url,title,des,qid,username,postDate);
            listVideos.add(video);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listVideos;
    }
    
	public List<Video> listAllVideos() throws SQLException {
		 List<Video> listVideos = new ArrayList<>();
         
	        String sql = "SELECT * FROM videos";
	         
	        connect();
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	           
	            String url = resultSet.getString("Url");
	            String title = resultSet.getString("Title");
	            String des = resultSet.getString("Des");
	            int qid=resultSet.getInt("Qid");
	            String username=resultSet.getString("Username");
	            Date postDate =resultSet.getDate("Postdate");
	            
	            Video video=new Video(url,title,des,qid,username,postDate);
	            listVideos.add(video);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return listVideos;
	}
    
}
