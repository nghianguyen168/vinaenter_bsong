package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import utils.DBConnectionUtil;

public class CategoryDAO {
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	public List<Category> getItems() {
		List<Category> catList = new ArrayList<Category>();
		final String sql = "SELECT id,name FROM categories ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Category cat = new Category(rs.getInt("id"), rs.getString("name"));
				catList.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}
		
		return catList;
	}
	public int addItem(Category cat) {
		int result=0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO categories(name) VALUES(?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
			result = pst.executeUpdate();   //Số kết quả truy vấn thành công trả về
			System.out.println("result:"+result);
			
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
		conn = DBConnectionUtil.getConnection();
		final String sql = "DELETE FROM categories WHERE id=?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();   //Số kết quả truy vấn thành công trả về
			System.out.println("result:"+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
}
	public Category getItemById(int id) {
		Category cat = null;
		final String sql = "SELECT id,name FROM categories WHERE id=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				cat = new Category(rs.getInt("id"), rs.getString("name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		
		return cat;
	}
	public int editItem(Category cat) {
		int result=0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE categories SET  name=? WHERE id=?";
		
		try {
			pst = conn.prepareStatement(sql);
			
			
			pst.setString(1, cat.getName());
			pst.setInt(2, cat.getId());
			result = pst.executeUpdate();   //Số kết quả truy vấn thành công trả về
			System.out.println("result:"+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}
	public int getSumCat() {
		int sumCat=0;
		final String sql = "SELECT COUNT(id) AS sum FROM categories";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				sumCat=rs.getInt("sum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}
		
		return sumCat;
	}
	public Category checkTrungCat(String name) {
		Category cat = null;
		final String sql="SELECT * FROM categories WHERE name = ?";
		conn=DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()) {
				cat = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return cat;
	}
	
}
