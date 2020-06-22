package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class ContactDAO {
	Statement st;
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;
	public int insertToContact(String name, String email, String website, String message) {
		 Connection conn = DBConnectionUtil.getConnection();
		 int result=0;
		 Contact cont = null;
		 String sql = "INSERT INTO contacts(name, email, website, message) VALUE (?,?,?,?)";
	        try {
	           
	     
	            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);

	            pst.setString(1, name);
	            pst.setString(2, email);
	            pst.setString(3, website);
	            pst.setString(4, message);
	            result=pst.executeUpdate();
	            pst.close();
	            
	           
	           System.out.println("Successfully");
	        } catch (Exception E) {
	            System.out.println("The error is an error");
	        }
	        return result;
	}
	public List<Contact> getItems() {
		List<Contact> contactList = new ArrayList<Contact>();
		final String sql = "SELECT * FROM contacts";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Contact contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contactList;
	}
	public int delItem(int id) {
		int result=0;
		 final String sql = "DELETE FROM contacts WHERE id=?";
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
	public List<Contact> getItemsByPageAdmin(int offset) {
		List<Contact> contactList = new ArrayList<Contact>();
		final String sql = "SELECT * FROM contacts LIMIT ?,?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE_USER);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contact contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}
		return contactList;
		
	}

}
