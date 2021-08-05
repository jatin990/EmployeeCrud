package crudapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	Connection connect = null;

	public void getConnection() {
		String url="jdbc:mysql://localhost:3306/wileytask";
		try {
			Connection con=DriverManager.getConnection(url,"root","");
			this.connect= con;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
