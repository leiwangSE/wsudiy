
 
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

import java.util.Arrays;

 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author 	Lei and Mahzad
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
    private UserDao userDao;
    private QuestionDao questionDao;
    private VideoDao videoDao;
    private TagDao tagDao;
    private FavoriteDao favoriteDao;
    private HttpSession session=null;
    
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        userDao = new UserDao(jdbcURL, jdbcUsername, jdbcPassword);
        questionDao= new QuestionDao(jdbcURL, jdbcUsername, jdbcPassword);
        videoDao=new VideoDao(jdbcURL, jdbcUsername, jdbcPassword);
        tagDao=new TagDao(jdbcURL, jdbcUsername, jdbcPassword);
        favoriteDao=new FavoriteDao(jdbcURL, jdbcUsername, jdbcPassword);
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
            case "/postQuestion":
            	postQuestion(request, response);
            	break;
            case "/listQuestions":
            	listQuestions(request, response);
            	break;
            case "/showPostForm":
            	showPostForm(request, response);
            	break;
            case "/postVideo":
            	postVideo(request, response);
            	break;
            case "/listVideos":
            	listVideos(request, response);
            	break;
            case "/listAllQsandVs":
            	listAllQsandVs(request, response);
            	break;
            case "/player":
            	playVideo(request,response);
            	break;
            case "/favorite":
            	addFavorite(request,response);
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
        response.sendRedirect("Login.jsp");
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
        	    RequestDispatcher dispatcher = request.getRequestDispatcher("PostQuestion.jsp");
        	    System.out.println("logined username is +++++++"+session.getAttribute("loginUsername"));
        	    System.out.println("session id:"+session.getId());
        	    request.setAttribute("username", session.getAttribute("loginUsername"));
        	    dispatcher.forward(request, response);
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
    

	private void postQuestion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("Post Question");
		int qid=questionDao.getQid();
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("loginUsername");
		String question=request.getParameter("question");
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		String tag=request.getParameter("tags");
		String [] tagsArray=null;
		
		tagsArray=tag.split(",");
		
		System.out.println(username);
		System.out.println(question);
		System.out.println(date);
		System.out.println(Arrays.toString(tagsArray));
		
		Question que=new Question(question, username, date);
		questionDao.postQuestion(que);
		
		
		
		Tag tagObj=new Tag(qid,tagsArray);
		tagDao.insertTag(tagObj);
		
		
		response.sendRedirect("listQuestions");
		
	}
	
	private void listQuestions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Question> listQuestions = questionDao.listAllQuestions();
        								
        request.setAttribute("listQuestions", listQuestions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListQuestions.jsp");
        dispatcher.forward(request, response);
    }
	

	private void showPostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int qid=Integer.parseInt(request.getParameter("qid"));
		System.out.println("qid: "+qid);
		Question newquestion=questionDao.getQuestion(qid);
		request.setAttribute("question", newquestion);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("PostVideo.jsp");
        dispatcher.forward(request, response);
	}
	
	private void postVideo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("Post Video");
		
		int qid=Integer.parseInt(request.getParameter("qid"));
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("loginUsername");
		String url=request.getParameter("url");
		String title=request.getParameter("title");
		String des=request.getParameter("des");
		long millis=System.currentTimeMillis();  
		java.sql.Date postDate=new java.sql.Date(millis);
		
		
		System.out.println(username);
		System.out.println("url: "+url);
		System.out.println("title: "+ title);
		System.out.println("des: "+des);
		System.out.println(postDate);
		
		
		Video video=new Video(url, title,des, qid, username, postDate);
		videoDao.postVideo(video);
		
		
		response.sendRedirect("listVideos");
		
		
	}
	

	private void listVideos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Video> listVideos = videoDao.listAllVideos();
		List<Question> listQuestions = questionDao.listAllQuestions();
		
		int lastQid=listQuestions.get(listQuestions.size()-1).getQid();
		
        request.setAttribute("listQuestions", listQuestions);
        request.setAttribute("listVideos", listVideos);
        request.setAttribute("lastQid", lastQid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListVideos.jsp");
        dispatcher.forward(request, response);
	}
	
	private void listAllQsandVs(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Video> listVideos = videoDao.listAllVideos();
		List<Question> listQuestions = questionDao.listAllQuestions();
		
		
        request.setAttribute("listQuestions", listQuestions);
        request.setAttribute("listVideos", listVideos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListAllQsandVs.jsp");
        dispatcher.forward(request, response);
		
	}
	private void playVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("loginUsername");
		System.out.println("id:"+session.getId());
		
		String url=request.getParameter("url");
		String suburl=url.substring(17);
		System.out.println("suburl:"+suburl);
		RequestDispatcher dispatcher=request.getRequestDispatcher("Player.jsp");
		request.setAttribute("username", username);
		System.out.println("username:"+username);
		request.setAttribute("suburl", suburl);
		request.setAttribute("url", url);
		dispatcher.forward(request, response);
		
	}
 
	private void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String username=request.getParameter("username");
		String url=request.getParameter("url");
	    
		Favorite favorite=new Favorite(url, username);
		favoriteDao.insertFavorite(favorite);
		
        response.sendRedirect("MyFavorites.jsp");;
		
	}
	
}