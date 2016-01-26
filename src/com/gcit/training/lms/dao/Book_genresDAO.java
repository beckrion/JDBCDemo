package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Book_author;
import com.gcit.training.lms.entity.Book_genres;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;

public class Book_genresDAO extends AbstractDAO {
	public void create(Book_genres a) throws SQLException{
		save("insert into tbl_book_genres (genre_id,bookId) values (?,?)",new Object[] {a.getGenre().getGenreId(),a.getBook().getBookId()});	
	}
	public void updateGen(Book_genres a) throws SQLException{
		save("update tbl_book_genres set genre_id = ? where bookId = ?",new Object[] {a.getGenre().getGenreId(),a.getBook().getBookId()});	
	}
	public void delete(Book_genres p) throws SQLException {
		save("delete from tbl_book_genres where bookId = ?",
				new Object[] { p.getBook().getBookId()});
	}
	@Override
	protected List<Book_genres> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book_genres> aList = new ArrayList<Book_genres>();
		while(rs.next())
		{
			Book_genres a = new Book_genres();
			GenreDAO geRun = new GenreDAO();
			Genre ge = geRun.readOne(rs.getInt("genre_id"));
			BookDAO bkRun = new BookDAO();
			Book bk = bkRun.readOne(rs.getInt("bookId"));
			a.setBook(bk);
			a.setGenre(ge);
			aList.add(a);
		}
		return aList;
	}
	public List<Book_genres> readAll() throws SQLException {
		return (List<Book_genres>) read("select * from tbl_book_genres", null);
	}
	

}
