package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Book_loans;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Library_branch;

public class Book_loansDAO extends AbstractDAO {
	public void create(Book_loans a) throws SQLException {
		save("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate,dateIn) values (?,?,?,?,?,?)",
				new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId(),a.getDateOut(),a.getDueDate(),a.getDateIn() });
	}
	public void updateDateOut(Book_loans a) throws SQLException {
		save("update tbl_book_loans set dateOut = ? where bookId =? and branchId = ? and cardNo = ?",
				new Object[] { a.getDateOut(),a.getBook().getBookId(),a.getBranch().getBranchId(),a.getBorrower().getCardNo() });
	}
	public void updateDueDate(Book_loans a) throws SQLException {
		save("update tbl_book_loans set dueDate = ? where bookId =? and branchId = ? and cardNo = ?",
				new Object[] { a.getDueDate(),a.getBook().getBookId(),a.getBranch().getBranchId(),a.getBorrower().getCardNo() });
	}
	public void updateDateIn(Book_loans a) throws SQLException {
		save("update tbl_book_loans set dateIn = ? where bookId =? and branchId = ? and cardNo = ?",
				new Object[] { a.getDateIn(),a.getBook().getBookId(),a.getBranch().getBranchId(),a.getBorrower().getCardNo() });
	}
	@Override
	protected List<Book_loans> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book_loans> aList = new ArrayList<Book_loans>();
		while(rs.next())
		{
			Book_loans a = new Book_loans();
			BookDAO bkRun = new BookDAO();
			Book bk = bkRun.readOne(rs.getInt("bookId"));
			Library_branchDAO lbRun = new Library_branchDAO();
			Library_branch lb = lbRun.readOne(rs.getInt("branchId"));
			BorrowerDAO borrRun = new BorrowerDAO();
			Borrower borr = borrRun.readOne(rs.getInt("cardNo"));
			a.setBook(bk);
			a.setBranch(lb);
			a.setBorrower(borr);
			a.setDateOut(rs.getString("dateOut"));
			a.setDueDate(rs.getString("dueDate"));
			a.setDateIn(rs.getString("dateIn"));
			aList.add(a);
		}
		return aList;
	}
	public List<Book_loans> readAll() throws SQLException {
		return (List<Book_loans>) read("select * from tbl_book_loans", null);
	}

}
