package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Roles;
import utils.DBConnectionUtil;

public class RolesDao {
	Connection conn;
	Statement st=null;
	ResultSet rs = null;
	PreparedStatement pst;
		public List<Roles> getItems() {
		
		List<Roles> rolesList = new ArrayList<Roles>();
		final String sql = "SELECT id,name FROM roles";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Roles role = new Roles(rs.getInt("id"), rs.getString("name"));
				rolesList.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn);
			DBConnectionUtil.close(rs);
			DBConnectionUtil.close(st);
		}
		
		return rolesList;
	}
		public int editRole(int id, int role_id) {
			int result=0;
			final String sql = "UPDATE user_role SET role_id=? WHERE user_id=?";
			conn = DBConnectionUtil.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, role_id);
				pst.setInt(2, id);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		public int delItem(int id) {
			int result=0;
			final String sql = "DELETE FROM user_role WHERE user_id=?";
			conn = DBConnectionUtil.getConnection();
			try {
				pst = conn.prepareStatement(sql);
			
				pst.setInt(1, id);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		public int addItems(int id, int role_id) {
			int result=0;
			final String sql="INSERT INTO user_role(user_id,role_id) VALUES(?,?)";
			Connection conn = DBConnectionUtil.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
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
}
