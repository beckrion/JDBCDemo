package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookAuthor;
import com.gcit.training.lms.entity.BookGenres;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;

public class BookGenresDAO extends AbstractDAO {
	public void create(BookGenres a) throws SQLException{
		save("insert into tbl_book_genres (genre_id,bookId) values (?,?)",new Object[] {a.getGenre().getGenreId(),a.getBook().getBookId()});	
	}
	public void updateGen(BookGenres a) throws SQLException{
		save("update tbl_book_genres set genre_id = ? where bookId = ?",new Object[] {a.getGenre().getGenreId(),a.getBook().getBookId()});	
	}
	public void delete(BookGenres p) throws SQLException {
		save("delete from tbl_book_genres where bookId = ?",
				new Object[] { p.getBook().getBookId()});
	}
	@Override
	protected List<BookGenres> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<BookGenres> aList = new ArrayList<BookGenres>();
		while(rs.next())
		{
			BookGenres a = new BookGenres();
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
	public List<BookGenres> readAll() throws SQLException {
		return (List<BookGenres>) read("select * from tbl_book_genres", null);
	}
	

}
