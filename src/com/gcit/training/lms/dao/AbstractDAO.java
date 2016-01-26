package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO {
	protected static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void save(String query, Object[] values) throws SQLException {
		Connection conn = getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(query);

		int count = 1;
		for(Object obj : values) {
			stmt.setObject(count, obj);
			count++;
		}
		
		stmt.executeUpdate();
	}

	protected List<?> read(String sql, Object[] values) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(sql);

		if(values != null) {
			int count = 1;
			for(Object obj : values) {
				stmt.setObject(count, obj);
				count++;
			}
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		return processResult(rs);
	}
	
	protected abstract List<?> processResult(ResultSet rs) throws SQLException;
	
	protected int readInt(String sql,Object[] values,String retValue) throws SQLException{
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);

		for(Object obj : values) {
			int count = 1;
			stmt.setObject(count, obj);
			count++;
		}
		ResultSet rs = stmt.executeQuery();
		return  rs.getInt(retValue);
	}

}
