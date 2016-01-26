package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Publisher;

public class BookDAO extends AbstractDAO {

	
	public void create (Book a) throws SQLException
	{
		save("insert into tbl_book (title,pubId) values (?,?)",new Object[] { a.getTitle(),a.getPublisher().getPublisherId() });

		int bookId1 = readInt("select bookId from tbl_book where title = ?,pubId = ?",new Object[]{a.getTitle(),a.getPublisher().getPublisherId()},"bookId");
		List<Author> au = a.getAuthors();
		for(Iterator<Author> i =au.iterator();i.hasNext(); ){
			save("insert into tbl_book_authors (bookId,authorId) value(?,?)",new Object[]{bookId1,i.next().getAuthorId()});
		}
	}
	public void update (Book a) throws SQLException
	{
		save("update tbl_book set title = ?,pubId = ? where bookId = ?",new Object[]{a.getTitle(),a.getPublisher().getPublisherId(),a.getBookId()});
	}
	public void delete(Book a) throws SQLException {
		save("delete from tbl_book where bookId = ?",new Object[]{a.getBookId()});
		save("delete from tbl_book_authors where bookId = ?",new Object[]{a.getBookId()});

	}
	public void updateTitle (Book a) throws SQLException
	{
		save("update tbl_book set title = ? where bookId = ?",new Object[]{a.getTitle(),a.getBookId()});

	}
	public void updatePubId (Book a) throws SQLException
	{
		save( "update tbl_book set pubId = ? where bookId = ?",new Object[]{a.getPublisher().getPublisherId(),a.getBookId()});

	}
	public void delAuthor (Book a) throws SQLException
	{
		List<Author> au = a.getAuthors();
		Iterator<Author> i =au.iterator();
		save("delete from tbl_book_authors where bookId = ? and authorId = ?",new Object[]{a.getBookId(),i.next().getAuthorId()});
		
	}
	public void addAuthor(Book a) throws SQLException
	{
		 
		List<Author> au = a.getAuthors();
		Iterator<Author> i =au.iterator();
		save("insert into tbl_book_authors (bookId,authorId) value(?,?)",new Object[]{a.getBookId(),i.next().getAuthorId()});
		
	}
	public void updateAuthor(Book newBook,Book oldBook) throws SQLException
	{
		delAuthor(oldBook);
		addAuthor(newBook);
	}
	public List<Book> readAll() throws SQLException {
		return (List<Book>) read("select * from tbl_book", null);
	}
	public Book readOne(int bookId) throws SQLException {
		List<Book> bkList = (List<Book>) read(
				"select * from tbl_book where bookId = ?",
				new Object[] { bookId });
		
		if(bkList != null && bkList.size() > 0) {
			return bkList.get(0);
		} else {
			return null;
		}
	}
	@Override
	protected List<Book> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book> aList = new ArrayList<Book>();
		while (rs.next()){
				Book a = new Book();
				a.setBookId(rs.getInt("BookId"));
				a.setTitle(rs.getString("Title"));
				PublisherDAO getPub = new PublisherDAO();
				Publisher p = getPub.readOne(rs.getInt("pubId"));
				a.setPublisher(p);	
			aList.add(a);	
		}
		return aList;
	}
	


}
