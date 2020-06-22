package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Slide;
import model.Song;
import utils.DBConnectionUtil;

public class SlideDao {
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	public int addItemToSlide(Song song) {
		int result = 0;
		final String sql = "INSERT INTO slide(id,name,picture,counter,cat_id) VALUES(?,?,?,?,?)";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, song.getId());
			pst.setString(2, song.getName());
			pst.setString(3, song.getPicture());
			pst.setInt(4, song.getCounter());
			pst.setInt(5, song.getCat_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}

		return result;
	}
	public List<Slide> getItems() {
		List<Slide> slideList = new ArrayList<Slide>();
		final String sql = "SELECT slide.id as id,slide.name as name,picture,counter,categories.name as category FROM slide INNER JOIN categories ON slide.cat_id=categories.id ";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Slide slide = new Slide(rs.getInt("id"), rs.getString("name"), rs.getString("picture"),
						rs.getInt("counter"), rs.getString("category"));
				slideList.add(slide);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return slideList;
	}
	public int editItem(Slide song) {
		int result = 0;
		final String sql = "UPDATE slide SET name=?,picture=?,cat_id=? WHERE id=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPicture());
			pst.setInt(3, song.getCat_id());
			pst.setInt(4, song.getId());
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
		int result = 0;
		final String sql = "DELETE FROM slide WHERE id = ?";
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
	public Slide checkTrung(int id) {
		 Slide slide=null;
		final String sql = "SELECT * FROM slide WHERE id= ?";
		conn=DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				slide = new Slide(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getInt("counter"), rs.getInt("cat_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return slide;
		
	}
	
	
}
