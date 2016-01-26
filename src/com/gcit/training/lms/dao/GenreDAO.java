package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Genre;

public class GenreDAO extends AbstractDAO {
	public void create (Genre a) throws SQLException
	{
		save("insert into tbl_genre (genre_id,genre_name) values (?,?)",new Object[] { a.getGenreId(),a.getGenreName() });
	}
	public void update (Genre a) throws SQLException
	{
		save("update tbl_genre set genre_name = ? where genre_id = ?",new Object[]{a.getGenreId()});
	}
	public void delete(Genre a) throws SQLException {
		save("delete from tbl_genre where genre_id = ?",new Object[]{a.getGenreId()});
	}
	@Override
	protected List<Genre> processResult(ResultSet rs) throws SQLException {
		List<Genre> aList = new ArrayList<Genre>();
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			aList.add(a);
		}
		return aList;
	}
	public List<Genre> readAll() throws SQLException {
		return (List<Genre>) read("select * from tbl_genre", null);
	}
	public Genre readOne(int genreId) throws SQLException {
		List<Genre> list = (List<Genre>) read(
				"select * from tbl_author where genre_id = ?",
				new Object[] { genreId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
