package model;

public class User {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private int role_id;
	private String role_name;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String fullname,int role_id) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role_id=role_id;
	}
	
	
	
	public User(int id, String username, String password, String fullname, int role_id, String role_name) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role_id = role_id;
		this.role_name = role_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public User(String username, String password, String fullname) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
	
	
	public User(int id, String username, int role_id, String role_name) {
		super();
		this.id = id;
		this.username = username;
		this.role_id = role_id;
		this.role_name = role_name;
	}
	public User(int id, String username, String password, int role_id, String role_name) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role_id = role_id;
		this.role_name = role_name;
	}
	public User(String username, String fullname, int role_id, String role_name) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.role_id = role_id;
		this.role_name = role_name;
	}
	public User(int id, String username, String password, String fullname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
	
	public User(int id, String username, String fullname) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
	}
	public User(int id, String username, String fullname,int role_id) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.role_id=role_id;
	}
	
	public User(String username, String password, String fullname,int role_id) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role_id =  role_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	
	
}
