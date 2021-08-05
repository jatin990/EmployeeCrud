package crudapp;

import java.sql.*;
import java.util.Scanner;


public class AddEmployee {
	public AddEmployee(Scanner sc) {
		DB db=new DB();
		db.getConnection();
		Connection con=db.connect;
		Employee add=getEmployeeDetails(sc);
		try {
			PreparedStatement addEmp=con.prepareStatement("Insert into employees values (null,?,?,?,?,?,?,?,0,?,?)");  
			addEmp.setString(1,add.first_name);
			addEmp.setString(2,add.last_name);
			addEmp.setString(3,add.email);
			addEmp.setString(4,add.ph_no);
			addEmp.setString(5,add.hiredate);
			addEmp.setString(6,add.job_id);
			addEmp.setString(7,add.salary);
			addEmp.setString(8,add.manager_id);
			addEmp.setString(9,add.department_id);
			int i=addEmp.executeUpdate();
			System.out.println(i+" records inserted");  
			con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Please try again");
		}
	}

	private Employee getEmployeeDetails(Scanner sc) {
		System.out.println("Enter first name");
		String fname=sc.nextLine();
		System.out.println("Enter last name");
		String lname=sc.nextLine();
		System.out.println("Enter email");
		String email=sc.nextLine();
		System.out.println("Enter Phone no");
		String pno=sc.nextLine();
		//yyyy-mm-dd
		System.out.println("Enter hiring date");
		String hdate=sc.nextLine();
		System.out.println("Enter Job id");
		String job_id=sc.nextLine();
		System.out.println("Enter salary");
		String salary=sc.nextLine();
		System.out.println("Enter manager id");
		String manager_id=sc.nextLine();
		System.out.println("Enter department id");
		String dep_id=sc.nextLine();
		return new Employee(fname,lname,email,pno,hdate,job_id,salary,manager_id, dep_id);
	}

}
