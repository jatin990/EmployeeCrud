package crudapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Modify {

	public Modify(Scanner sc)  {
		DB db = new DB();
		db.getConnection();
		Connection con = db.connect;
		List<String> fillable = Arrays.asList("first_name", "last_name", "department_id", "manager_id", "salary",
				"commission_pct", "phone_number", "email");
		String id = getEmployeeToUpdate(con, sc);
		if (id != null) {
			Map<String, String> toUpdate = getUpdateParameters(fillable,sc);
			try {
				Statement st = con.createStatement();
				String updateq = "update employees set ";
				int i=1;
				int s=toUpdate.size();
				boolean update=false;
				for(Entry<String,String> e :toUpdate.entrySet()) {
					if(!e.getValue().equals("")) {
						updateq+=e.getKey()+"=\""+e.getValue()+"\"";
						updateq+=",";
						update=true;
					}
				}
				updateq=updateq.substring(0, updateq.length()-1);
				updateq+=" where employee_id="+id;
				System.out.println(updateq);
				if(update)
					st.executeUpdate(updateq);
				else {
					System.out.println("Nothing to update");
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Something went wrong. Try again");
			}
		}

	}

	private Map<String, String> getUpdateParameters(List<String> fillable, Scanner sc) {
		String value;
		Map<String,String> toUpdate=new HashMap<String,String>();
		System.out.println("Leave the field empty if you dont want to update it");
		for (String s : fillable) {
			System.out.println("Enter "+s.replace("_", " "));
			value=sc.nextLine();
			toUpdate.put(s, value);
		}
		return toUpdate;
	}

	private String getEmployeeToUpdate(Connection con, Scanner sc) {
		System.out.println("Enter Employee id");
		String id = sc.nextLine();
		try {
			Statement st = con.createStatement();
			String q = "Select * from employees where employee_id=\"" + id+"\"";
			ResultSet rs = st.executeQuery(q);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int count = 0;
			System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "Employee ID",
					"First Name", "Last Name", "Email", "Phone Number", "Hire Date", "Job Id", "Salary",
					"Commission PCT", "Managerid", "Department Id"));
			while (rs.next()) {
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						System.out.printf("%-20s", rs.getString(columnIndex));
				}
				System.out.printf("%n");
				count++;
				id = rs.getString(1);
			}

			if (count != 0)
				System.out.println("Employee found");
			else {
				id = null;
				System.out.println("No employee found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Please try again");
		}
		return id;
	}

}
