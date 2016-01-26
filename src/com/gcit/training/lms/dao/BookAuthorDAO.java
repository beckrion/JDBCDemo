package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookAuthor;

public class BookAuthorDAO extends AbstractDAO{
	public void create(BookAuthor bookAuthor) throws SQLException{
		save("insert into tbl_book_authors (bookId,authorId) values (?,?)",new Object[] {bookAuthor.getBook().getBookId(),bookAuthor.getAuthor().getAuthorId()});
		
	}
	public void updateAuthor(BookAuthor newBookAuthor,BookAuthor oldBookAuthor) throws SQLException{
		save("update tbl_book_authors set authorId = ? where bookId = ? and authorId = ?",new Object[] {newBookAuthor.getAuthor().getAuthorId(),oldBookAuthor.getBook().getBookId(),oldBookAuthor.getAuthor().getAuthorId()});
		
	}
	public void updateBook(BookAuthor newBookAuthor,BookAuthor oldBookAuthor) throws SQLException{
		save("update tbl_book_authors set bookId = ? where bookId = ?",new Object[] {newBookAuthor.getBook().getBookId(),oldBookAuthor.getBook().getBookId()});
		
	}
	public void delete(BookAuthor p) throws SQLException {
		save("delete from tbl_book_authors where bookId = ? and authorId = ?",
				new Object[] { p.getBook().getBookId(),p.getAuthor().getAuthorId() });
	}
	public List<BookAuthor> readAll() throws SQLException {
		return (List<BookAuthor>) read("select * from tbl_book_authors", null);
	}
	@Override
	protected List<BookAuthor> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<BookAuthor> aList = new ArrayList<BookAuthor>();
		while(rs.next())
		{
			BookAuthor a = new BookAuthor();
			AuthorDAO auRun = new AuthorDAO();
			Author au = auRun.readOne(rs.getInt("authorId"));
			BookDAO bkRun = new BookDAO();
			Book bk = bkRun.readOne(rs.getInt("bookId"));
			a.setAuthor(au);
			a.setBook(bk);
			aList.add(a);
		}
		return aList;
	}
	

}
