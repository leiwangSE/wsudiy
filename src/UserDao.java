

import java.sql.Connection;
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

public class UserDao {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean insertUser(User user) throws SQLException {
    	System.out.println("Call insetUser");
        String sql = "INSERT INTO users (username, password, firstname, lastname, age) VALUES (?, ?, ?, ?, ?)";
        System.out.println("Sql script");
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstname());
        statement.setString(4, user.getLastname());
        statement.setInt(5, user.getAge());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public User loginUser(User user) throws SQLException {
        User userResult=null;
        String sql = "SELECT * FROM users WHERE username=? and password=?";
       
        System.out.println("Sql script");
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        
       
        ResultSet resultSet = statement.executeQuery();
        
        System.out.println("Execute successfully");
        
        if (resultSet.next()) {
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
           
            userResult = new User(username, password);
            System.out.println(userResult.username);
            System.out.println(userResult.password);
        }
         
        resultSet.close();
        statement.close();
         
        return userResult;
    }
    
    
}
