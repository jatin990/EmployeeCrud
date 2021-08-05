package crudapp;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Search {

	public Search(Scanner sc) {
		Map<String, String> SearchP = new HashMap<String, String>();
		SearchP.put("Employee id", "employee_id");
		SearchP.put("First name", "first_name");
		SearchP.put("Department", "department_id");
		SearchP.put("Manager", "manager_id");
		List<String> equalComp = Arrays.asList("employee_id", "department_id", "manager_id");
		Map<String, String> AppliedP = getSearchParameters(SearchP);
		DB db = new DB();
		db.getConnection();
		Connection con = db.connect;
		try {
			Statement st = con.createStatement();
			String displayq = "Select * from employees where ";
			int s = AppliedP.size();
			if (s != 0) {
				int i = 1;
				for (Entry<String, String> entry : AppliedP.entrySet()) {
					if (equalComp.contains(entry.getKey()))
						//todo :allow search by department name manager name etc
						displayq += "`" + entry.getKey() + "`" + " = " + "\"" + entry.getValue() + "\"";
					else
						displayq += "`" + entry.getKey() + "`" + " like " + "\"" + entry.getValue() + "\"";
					if (i != s)
						displayq += "and";
					i++;
				}
				ResultSet rs = st.executeQuery(displayq);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				printheader();
				int count=0;
				while (rs.next()) {
					for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						System.out.printf("%-20s", rs.getString(columnIndex));
					}
					System.out.printf("%n");
					count++;
				}
				System.out.println(count+" Employee(s) found");
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Something went wrong. Try again");
		}
	}

	private void printheader() {
		System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
				"Employee ID", "First Name", "Last Name", "Email", "Phone Number", "Hire Date", "Job Id",
				"Salary", "Commission PCT", "Managerid", "Department Id"));		
	}

	private Map<String, String> getSearchParameters(Map<String, String> searchP) {
		Map<String, String> AppliedP = new HashMap<String, String>();
		String search = "Search by\n";
		int i = 1;
		for (String key : searchP.keySet()) {
			search += i + ")" + key + "\n";
			i++;
		}
		i = 1;
		System.out.println(search);
		Scanner sc = new Scanner(System.in);
		String key = sc.nextLine();
		for (Entry<String, String> key1 : searchP.entrySet()) {
			if (key.equals(String.valueOf(i))) {
				System.out.println("Enter the value");
				String value = sc.nextLine();
				AppliedP.put(key1.getValue(), value);
			}
			i++;
		}
		return AppliedP;
	}
}
