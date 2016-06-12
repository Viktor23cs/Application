package com.application.package1;

import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Database {
	static Connection c;
	static Statement s;
	static ResultSet r;

	Database() throws SQLException, ClassNotFoundException {

		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";

		Class.forName(driver);
		String db = "jdbc:ucanaccess://D:/workspace/Application/src/com/" 
		+ "application/package1/Authorization.accdb";
		
		c = DriverManager.getConnection(db);

		s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql = "SELECT * FROM Table1";
		r = s.executeQuery(sql);

	}
}
