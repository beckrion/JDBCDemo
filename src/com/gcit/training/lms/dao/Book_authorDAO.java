package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Book_author;

public class Book_authorDAO extends AbstractDAO{
	public void create(Book_author bookAuthor) throws SQLException{
		save("insert into tbl_book_authors (bookId,authorId) values (?,?)",new Object[] {bookAuthor.getBook().getBookId(),bookAuthor.getAuthor().getAuthorId()});
		
	}
	public void updateAuthor(Book_author newBookAuthor,Book_author oldBookAuthor) throws SQLException{
		save("update tbl_book_authors set authorId = ? where bookId = ? and authorId = ?",new Object[] {newBookAuthor.getAuthor().getAuthorId(),oldBookAuthor.getBook().getBookId(),oldBookAuthor.getAuthor().getAuthorId()});
		
	}
	public void updateBook(Book_author newBookAuthor,Book_author oldBookAuthor) throws SQLException{
		save("update tbl_book_authors set bookId = ? where bookId = ?",new Object[] {newBookAuthor.getBook().getBookId(),oldBookAuthor.getBook().getBookId()});
		
	}
	public void delete(Book_author p) throws SQLException {
		save("delete from tbl_book_authors where bookId = ? and authorId = ?",
				new Object[] { p.getBook().getBookId(),p.getAuthor().getAuthorId() });
	}
	public List<Book_author> readAll() throws SQLException {
		return (List<Book_author>) read("select * from tbl_book_authors", null);
	}
	@Override
	protected List<Book_author> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book_author> aList = new ArrayList<Book_author>();
		while(rs.next())
		{
			Book_author a = new Book_author();
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
