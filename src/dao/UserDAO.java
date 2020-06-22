package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Song;
import model.User;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class UserDAO {
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	public List<User> getItems() {
		List<User> userList = new ArrayList<User>();
		final String sql = "SELECT id,username,password,fullname,ur.role_id AS role_id FROM users AS u INNER JOIN user_role AS ur ON u.id=ur.user_id ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"),rs.getInt("role_id"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}
		
		return userList;
		
		
	}
	public int addItem(String username, String password, String fullname) {
		int result=0;
		final String sql="INSERT INTO users(username,password,fullname) VALUES(?,?,?)";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, fullname);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}
	public User getItem(int id) {
		User user = null;
		final String sql = "SELECT id,username,password,fullname,role_id FROM users AS u INNER JOIN user_role AS ur ON u.id=ur.user_id WHERE id=? ";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"),rs.getInt("role_id"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	public int editItem(User user) {
		int result=0;
		final String sql="UPDATE users SET username=?, fullname=? WHERE id="+user.getId();
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			
			pst.setString(2, user.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	
	}
	public int delItem(int id) {
		int result=0;
		final String sql="DELETE FROM users WHERE id=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}
	public User checkUser(String username, String password) {
		User user =null;
		final String sql="SELECT id,username,password,fullname,role_id FROM users AS u INNER JOIN user_role "
				+ "AS ur ON u.id=ur.user_id   WHERE username=? AND password =?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"),rs.getInt("role_id"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return user;
		
	}
	
	public List<User> UserByRole(String username, String password) {
		List<User> userRoleList=null;
		final String sql="SELECT u.id AS id,username,role_id,r.name AS role_name FROM users AS u INNER JOIN"
				+ " user_role AS ur ON u.id=ur.user_id INNER JOIN roles AS r ON r.id = ur.role_id  WHERE username=? AND password=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getInt("role_id"), rs.getString("role_name"));
				userRoleList.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return userRoleList;
		
	}
	
	
	
	public List<User> getRoleList(int user_id) {
		List<User> RoleList = new ArrayList<User>();
		final String sql = "SELECT username,fullname,r.name AS role_name,r.id AS role_id FROM users AS u INNER JOIN user_role AS ur ON u.id=ur.user_id INNER JOIN roles AS r ON ur.role_id=r.id WHERE u.id=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getString("username"), rs.getString("fullname"), rs.getInt("role_id"), rs.getString("role_name"));
				RoleList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return RoleList;
	}
	
	public List<String> getRoleID(int user_id) {
		List<String> RoleList = new ArrayList<>();
		final String sql = "SELECT username,fullname,r.name AS role_name,r.id AS role_id FROM users AS u INNER JOIN user_role AS ur ON u.id=ur.user_id INNER JOIN roles AS r ON ur.role_id=r.id WHERE u.id=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				String role_id = rs.getString("role_id");
				
				RoleList.add(role_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return RoleList;
	}
	
			
			
	public User checkTrungUser(String username) {

		User user = null;
		final String sql="SELECT * FROM users WHERE username = ?";
		conn=DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return user;
	}
	public User checkAdmin(int role_id) {
		User user = null;
		final String sql="SELECT * FROM users WHERE role_id = ?";
		conn=DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, role_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"),rs.getInt("role_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return user;
	}
	public List<User> getItemsByPageAdmin(int offset) {
		List<User> userList = new ArrayList<User>();
		final String sql = "SELECT id,username,fullname FROM users LIMIT ?,?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE_USER);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}
		
		return userList;
	}
	public int getUserID(String username) {
		int id = 0;
		User user =null;
		final String sql = "SELECT * FROM users WHERE username=? ";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				id=user.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	public static void main(String[] args) {
		System.out.println(new UserDAO().UserByRole("admin", "e10adc3949ba59abbe56e057f20f883e")	);
	}
	public User checkPassword(int id, String password) {
		int result = 0;
		User user =null;
		final String sql = "SELECT * FROM users WHERE id=? AND password=? ";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	public int editPassword(int id, String new_password) {
		int result = 0;
		User user =null;
		final String sql = "UPDATE users SET PASSWORD=? WHERE id=? ";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, new_password);
			pst.setInt(2,id);
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
