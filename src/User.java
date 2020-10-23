

/**
 * Book.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */
public class User {
    
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected int age;
 
    public User() {
    }

    public User(String username) {
        this.username=username;
    }
 
    public User(String username, String password, String firstname, String lastname, int age) {
        this(password, firstname, lastname, age);
        this.username=username;
    }
     
    public User(String password, String firstname, String lastname, int age) {
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age=age;
    }
    
	public User(String username, String password) {
		this.username=username;
		this.password=password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
 
    
    
}


