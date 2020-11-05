
 
import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author 	Lei and Mahzad
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
    private UserDao userDao;
    private HttpSession session=null;
    
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
        System.out.println("Action + " + action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
                
            case "/insert":
                insertUser(request, response);
                break;
            case "/login":
            	validateUser(request, response);
            	break;
            case "/initializeDB":
            	initializeDB(request, response);
            	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//In case of forward, redirect happens at server end and not visible to client.
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
        response.sendRedirect("Registered.jsp");
    }
    
    private void validateUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	//response.setCharacterEncoding("utf-8");
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username is "+username);
        System.out.println("password is "+password);
        User newUser = new User(username, password);
        if(userDao.validateUser(newUser))
        	{
        	System.out.println("the user exists in database");
        	User user=userDao.getUser(username,password);
        	System.out.println("input username is "+user.getUsername());
        	session=request.getSession();
        	session.setAttribute("loginUsername", user.getUsername());
        	session.setAttribute("loginPassword", user.getPassword());
        	System.out.println("logined username is "+session.getAttribute("loginUsername"));
        	System.out.println("logined password is "+session.getAttribute("loginPassword")+"\n");
        	session.setAttribute("loginFisrtName", user.getFirstname());
        	session.setAttribute("loginLastName", user.getFirstname());
        	session.setAttribute("loginage", user.getAge());
        	        	
        	if(session.getAttribute("loginUsername").equals("root")){
//            	RequestDispatcher dispatcher = request.getRequestDispatcher("InitializeDB.jsp");
//              dispatcher.forward(request, response);
        		System.out.println("Redirect to InitializeDB.jsp");
            	response.sendRedirect("InitializeDB.jsp");            	         
        	}
        	else {        	
        		
        		System.out.println("Logined as a normal user successfully");
            	response.sendRedirect("Logined.jsp");
        	}   
        	}
        else {
        	System.out.println("Invalid username and password, redirect to Login.jsp again \n");
        	response.sendRedirect("Login.jsp");
        	}
        	
	
    }  
    private void initializeDB(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
 		boolean initialize=userDao.InitializeDB();
 		if(initialize) {
 			 RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");
 	        dispatcher.forward(request, response);
 		}
 		
 	}
 
}