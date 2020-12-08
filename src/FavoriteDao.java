

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

public class FavoriteDao {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public FavoriteDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean insertFavorite(Favorite favorite) throws SQLException {
    	System.out.println("Call insetFavorite");
    	boolean rowInserted = false;
        String sql = "INSERT INTO wsudiy.favorites (Url, Username) VALUES (?, ?)";
        connect();
       
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, favorite.getUrl());
        statement.setString(2, favorite.getUsername());
        System.out.println("Sql script: "+sql);
        System.out.println("Url: "+favorite.getUrl());
        System.out.println("Username: "+ favorite.getUsername());
        
        rowInserted = statement.executeUpdate() > 0;
        System.out.println("Inserted:"+rowInserted);
        
        statement.close();

        disconnect();
		return rowInserted;
    
   
    } 
    
}
