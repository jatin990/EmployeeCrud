package crudapp;

import java.sql.*;
import java.util.Scanner;


public class Display {

	public Display(Scanner sc) {
		DB db=new DB();
		db.getConnection();
		Connection con=db.connect;
		try {
			Statement st=con.createStatement();
			String displayq="Select * from employees";  
			ResultSet rs=st.executeQuery(displayq);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
					"Employee ID","First Name","Last Name","Email","Phone Number","Hire Date","Job Id","Salary","Commission PCT","Managerid","Department Id"));

			while(rs.next()) {
			    for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			    	System.out.printf("%-20s",rs.getString(columnIndex));
			    }
			    System.out.printf("%n");
			}

			con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
