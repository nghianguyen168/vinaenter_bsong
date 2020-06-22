package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Category;
import model.Logo;
import utils.DBConnectionUtil;

public class LogoDAO {
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	public int updateLogo(Logo logo) {
		
			int result=0;
			conn = DBConnectionUtil.getConnection();
			final String sql = "UPDATE logo SET name=?";
			
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, logo.getLogoName());
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
	public Logo getItem() {
		
		Logo logo = null;
			final String sql = "SELECT * FROM logo";
			conn = DBConnectionUtil.getConnection();
			try {
				st=conn.createStatement();
				rs = st.executeQuery(sql);
				if(rs.next()) {
					logo=new Logo(rs.getString("name"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBConnectionUtil.close(conn, pst, rs);
			}
			
			return logo;
	}
}
