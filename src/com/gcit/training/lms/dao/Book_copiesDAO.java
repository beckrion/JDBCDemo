package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Book_copies;
import com.gcit.training.lms.entity.Library_branch;

public class Book_copiesDAO extends AbstractDAO {
	public void create (Book_copies a) throws SQLException
	{
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)",new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId(),a.getNoOfCopies() });
	}
	public void updateBook(Book_copies newBk,Book_copies oldBk) throws SQLException {
		save("update tbl_book_copies set bookId = ? where bookId =? and branchId = ?",
				new Object[] { newBk.getBook().getBookId(),oldBk.getBook().getBookId(),newBk.getBranch().getBranchId() });
	}
	public void updateBranch(Book_copies newBk,Book_copies oldBk) throws SQLException {
		save("update tbl_book_copies set branchId = ? where bookId =? and branchId = ?",
				new Object[] { newBk.getBranch().getBranchId(),oldBk.getBook().getBookId(),newBk.getBranch().getBranchId() });
	}
	public void updateNo(Book_copies a) throws SQLException {
		save("update tbl_book_copies set noOfCopies = ? where branchId = ? and bookId = ?",
				new Object[] { a.getNoOfCopies(),a.getBranch().getBranchId(),a.getBook().getBookId() });
	}
	public void delete(Book_copies a) throws SQLException {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId() });
	}
	@Override
	protected List<Book_copies> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book_copies> aList = new ArrayList<Book_copies>();
		while(rs.next())
		{
			Book_copies a = new Book_copies();
			BookDAO bkRun = new BookDAO();
			Book bk = bkRun.readOne(rs.getInt("bookId"));
			Library_branchDAO brhRun = new Library_branchDAO();
			Library_branch brh = brhRun.readOne(rs.getInt("branchId"));
			a.setBook(bk);
			a.setBranch(brh);
			a.setNoOfCopies(rs.getInt("noOfCopies"));
			aList.add(a);
		}
		return aList;
	}
	public List<Book_copies> readAll() throws SQLException {
		return (List<Book_copies>) read("select * from tbl_book_copies", null);
	}
	

}
