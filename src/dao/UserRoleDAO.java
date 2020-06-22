package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBConnectionUtil;

public class UserRoleDAO {
	Statement st=null;
	ResultSet rs = null;
	PreparedStatement pst;
	Connection conn;
	public  int addItem(int user_id, int role_id) {
		int result=0;
		final String sql="INSERT INTO user_role(user_id,role_id) VALUES(?,?)";
		Connection conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, role_id);
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
		final String sql="DELETE FROM user_role WHERE user_id=?";
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
	public int[] getRoles(int user_id) {
		RolesDao rolesDao=new RolesDao();
		int[] role = new int[rolesDao.getItems().size()];
		final String sql="SELECT role_id from user_role WHERE user_id=?";
		Connection conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs =pst.executeQuery();
			for(int i=0;i<3;i++) {
			while(rs.next()) {
				
					int roleID = rs.getInt("role_id");
					System.out.println("roleID:"+roleID);
					role[i] = roleID;		
				
			}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return role;
		
	}
	public static void main(String[] args) {
		System.out.println(new UserRoleDAO().getRoles(1).length);
		
	}
}
