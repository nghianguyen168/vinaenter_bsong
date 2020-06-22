package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Song;
import model.User;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class SongDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	public List<Song> getItems() {

		List<Song> songList = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("date_create"), rs.getString("picture"), rs.getInt("counter"),
						rs.getInt("cat_id"));
				songList.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return songList;

	}

	public List<Song> getItemsNews() {

		List<Song> songListByIdNews = new ArrayList<Song>();
		final String sql = "SELECT id,name,cat_id FROM songs ORDER BY date_create DESC LIMIT 5";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getInt("cat_id"));
				songListByIdNews.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return songListByIdNews;

	}

	public List<Song> getsongListByIdById(int cid) {
		List<Song> songListById = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs WHERE cat_id=" + cid;
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("date_create"), rs.getString("picture"), rs.getInt("counter"),
						rs.getInt("cat_id"));
				songListById.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return songListById;
	}

	public Song getItemByCatId(int cid) {
		Song song = null;
		final String sql = "SELECT * FROM songs WHERE id=" + cid;
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getString("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return song;
	}

	public List<Song> getRelatedList(int cid, int did) {
		conn = DBConnectionUtil.getConnection();
		List<Song> relatedList = new ArrayList<Song>();
		Song song = null;
		final String sql = "select * FROM songs WHERE cat_id = ? AND id <> ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, did);
			rs = pst.executeQuery();
			while (rs.next()) {
				song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getString("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"));
				relatedList.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}
		return relatedList;
	}

	public List<Song> getItemsAdmin() {
		List<Song> songList = new ArrayList<Song>();
		final String sql = "SELECT songs.id,songs.name,picture,counter,categories.id,categories.name as category FROM songs INNER JOIN categories ON songs.cat_id=categories.id ORDER BY songs.id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("picture"),
						rs.getInt("counter"), rs.getString("category"));
				songList.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return songList;
	}

	public int addItem(Song song) {
		int result = 0;
		final String sql = "INSERT INTO songs(name,preview_text,detail_text,picture,cat_id) VALUES(?,?,?,?,?)";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
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

	public int delItem(int id) {
		int result = 0;
		final String sql = "DELETE FROM songs WHERE id = ?";
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

	public Song getItem(int id) {
		Song song = null;
		final String sql = "SELECT songs.id AS id,songs.name,picture,counter,preview_text,detail_text,date_create,categories.id as cat_id,categories.name as category "
				+ " FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.id = ? ";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getString("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"),rs.getString("category"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return song;
	}

	public int editItem(Song song) {
		int result = 0;
		final String sql = "UPDATE songs SET name=?,preview_text=?,detail_text=?,date_create=?,picture=?,cat_id=? WHERE id=?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getDate_create());
			pst.setString(5, song.getPicture());
			pst.setInt(6, song.getCat_id());
			pst.setInt(7, song.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}

		return result;
	}

	public List<Song> searchItems(String searchText) {
		List<Song> songListSearch = new ArrayList<Song>();
		final String sql = "SELECT songs.id AS id,songs.name AS name,picture,counter,preview_text,detail_text,date_create,categories.id as cat_id,categories.name as category "
				+ "FROM songs INNER JOIN categories ON songs.cat_id=categories.id "
				+ "WHERE songs.name LIKE ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst =conn.prepareStatement(sql);
			pst.setString(1, "%"+searchText+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getString("date_create"), rs.getString("picture"),
						rs.getInt("counter"), rs.getInt("cat_id"),rs.getString("category"));
				songListSearch.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return songListSearch;
	}
	
	public static void main(String[] args) {
		System.out.println(new SongDAO().searchItems("Hoa mai").get(1).toString());
	}

	public int delItemByCatId(int id) {
		int result = 0;
		final String sql = "DELETE FROM songs WHERE cat_id = ?";
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

	public Song checkTrungSong(String name) {
		Song song =null;
		final String sql = "SELECT * FROM songs WHERE name=?";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			pst.setString(1, name);
			rs =pst.executeQuery();
			if (rs.next()) {
				song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("date_create"), rs.getString("picture"), rs.getInt("counter"),
						rs.getInt("cat_id"));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return song;
	}
	public int updateViews(int did) {
		int result=0;
		final String sql="UPDATE songs SET counter=counter+1 WHERE id=?";
		conn=DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, did);
			result=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Song> getMaxViews() {

		List<Song> maxViewsList = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs ORDER BY counter DESC LIMIT 3";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("date_create"), rs.getString("picture"), rs.getInt("counter"),
						rs.getInt("cat_id"));
				maxViewsList.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return maxViewsList;
	}

	public List<Song> getItemsByPage(int offset) {
		List<Song> songListByPage = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs ORDER BY id DESC LIMIT ?,?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("date_create"), rs.getString("picture"), rs.getInt("counter"),
						rs.getInt("cat_id"));
				songListByPage.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);
		}

		return songListByPage;
	}

	public List<Song> getItemsByPageAdmin(int offset) {
		List<Song> songListByPageAdmin = new ArrayList<Song>();
		final String sql = "SELECT songs.id,songs.name,picture,counter,categories.id,categories.name as category FROM songs"
				+ " INNER JOIN categories ON songs.cat_id=categories.id ORDER BY songs.id DESC LIMIT ?,?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("picture"),
						rs.getInt("counter"), rs.getString("category"));
				songListByPageAdmin.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}

		return songListByPageAdmin;
	}

	public List<Song> getItemsByPageCat(int offset, int cid) {
		List<Song> songListById = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs WHERE cat_id=" + cid +" LIMIT ?,?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();

			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("date_create"), rs.getString("picture"), rs.getInt("counter"),
						rs.getInt("cat_id"));
				songListById.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}

		return songListById;
	
	}
}
