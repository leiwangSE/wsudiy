
 
import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author 	Lei
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
 
    
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        userDao = new UserDao(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("Action +" + action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
                
            case "/insert":
                insertUser(request, response);
                break;
            case "/login":
            	loginUser(request, response);
     
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
        dispatcher.forward(request, response);
    }
 
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName =request.getParameter("firstname");
        String lastName =request.getParameter("lastname");
        int age =Integer.parseInt(request.getParameter("age"));
 
        User newUser = new User(username, password, firstName, lastName, age);
        userDao.insertUser(newUser);
        response.sendRedirect("list");
    }
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User newUser = new User(username, password);
        userDao.loginUser(newUser);
        response.sendRedirect("list");
        	
	}
 
}