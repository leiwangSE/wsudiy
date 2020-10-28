

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
    
    public Boolean validateUser(User user) throws SQLException {
    	ResultSet rs=null;
    	Boolean hasTuple=false;
        String sql = "SELECT * FROM users WHERE username=? and password=?";
        
        System.out.println("validateUser sql :" +sql);
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        
        if(user.getUsername()!=null&&user.getPassword()!=null) {
           rs = statement.executeQuery();
           if(rs.next()) {
        	   hasTuple=true;
           }
        }
        
        System.out.println("valid user and password "+hasTuple);
        
        statement.close();
         
        return hasTuple;
    }
    
    public boolean InitializeDB() throws SQLException {
        boolean hasTuples=false;
        String sql1 = "DROP TABLE if exists users";
        String sql2="CREATE TABLE users (\r\n" + 
        		"Username varchar(30) NOT NULL,\r\n" + 
        		"Password varchar(30) NOT NULL,\r\n" + 
        		"FirstName varchar(30) NOT NULL,\r\n" + 
        		"LastName varchar(30) NOT NULL,\r\n" + 
        		"Age int(3) NOT NULL,\r\n" + 
        		"primary key (Username)\r\n" + 
        		")";
        String sql3="INSERT INTO users(Username, Password, FirstName, LastName, Age)\r\n" + 
        		"VALUES (\"root\", \"pass1234\", \"Leo\", \"Wang\", 30),(\"leo.wang@gmail.com\", \"12345\", \"Leo\", \"Wang\", 30),\r\n" + 
        		"(\"Wentao.Bi@gmail.com\", \"123456\", \"Wentao\", \"Bi\", 27),(\"Peter.Zhang@gmail.com\", \"123456\", \"Peter\", \"Zhang\", 56),\r\n" + 
        		"(\"Jason.Zhang@gmail.com\", \"123456\", \"Jason\", \"Zhang\", 32),(\"Hill.Zhang@gmail.com\", \"123456\", \"Hill\", \"Zhang\", 28),\r\n" + 
        		"(\"Adam.Cheng@gmail.com\", \"123456\", \"Adam\", \"Cheng\", 33),(\"Wei.Qi@gmail.com\", \"123456\", \"Wei\", \"Qian\", 24),\r\n" + 
        		"(\"Zeyue.Lin@gmail.com\", \"123456\", \"Zeyue\", \"Lin\", 23),(\"Kaiyue.Zhou@gmail.com\", \"123456\", \"Kaiyue\", \"Zhou\", 33)"; 
        String sql4="SELECT * FROM users";
       
     
        System.out.println("Sql 1 is "+sql1);
        System.out.println("Sql 2 is "+sql2);
        System.out.println("Sql 3 is "+sql3);
        System.out.println("Sql 4 is "+sql4);
        
        connect();
        
        Statement statement = jdbcConnection.createStatement();
        
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
        hasTuples=statement.executeUpdate(sql3)>0;
       
        System.out.println("There are tuples in users table: "+hasTuples);
        
        statement.close();
        disconnect();
        return hasTuples;
        }
    
	public User getUser(String username, String password) throws SQLException {
		String sql="select * from users where username=? and password=?";
		User user=null;
		connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String username1 = resultSet.getString("Username");
            String password1 = resultSet.getString("Password");
            String firstname = resultSet.getString("FirstName");
            String lastname = resultSet.getString("LastName");
            int age = resultSet.getInt("Age");
            user = new User(username1,password1,firstname,lastname,age);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
	}
    
}
