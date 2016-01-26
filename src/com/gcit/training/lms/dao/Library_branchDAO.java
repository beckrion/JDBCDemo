package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Library_branch;

public class Library_branchDAO extends AbstractDAO {
	public void create (Library_branch a) throws SQLException
	{
		save("insert into tbl_library_branch (branchId,branchName,branchAddress) values (?,?,?)",new Object[] { a.getBranchId(),a.getBranchName(),a.getBranchAddress() });
	}
	public void updateName(Library_branch a) throws SQLException {
		save("update tbl_library_branch set branchName = ? where branchId = ?",
				new Object[] { a.getBranchName(),a.getBranchId() });
	}
	public void updateAddress(Library_branch a) throws SQLException {
		save("update tbl_library_branch set branchAddress = ? where branchId = ?",
				new Object[] { a.getBranchName(),a.getBranchId() });
	}
	public void delete(Library_branch a) throws SQLException {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { a.getBranchId() });
	}
	@Override
	protected List<Library_branch> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Library_branch> aList = new ArrayList<Library_branch>();
		while(rs.next())
		{
			Library_branch a = new Library_branch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			aList.add(a);
		}
		return aList;
	}
	public Library_branch readOne(int branchId) throws SQLException {
		List<Library_branch> list = (List<Library_branch>) read(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	public List<Library_branch> readAll() throws SQLException {
		return (List<Library_branch>) read("select * from tbl_library_branch", null);
	}

}
