package crudapp;

public class Employee {
	public String emp_id,first_name,last_name,email,ph_no,hiredate,job_id,salary,manager_id,department_id;
	
	public Employee( String first_name, String last_name,String email, String ph_no, String hiredate, String job_id,
			String salary, String manager_id, String department_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.ph_no = ph_no;
		this.hiredate = hiredate;
		this.job_id = job_id;
		this.salary = salary;
		this.manager_id = manager_id;
		this.department_id = department_id;
	}

}
