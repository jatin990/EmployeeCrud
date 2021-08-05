package crudapp;

import java.sql.ResultSet;
import java.util.Scanner;

public class UserInferface {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String choice="-1";
		while(!choice.equals("5")) {
			displayOptions();
			choice=sc.nextLine();
			switch (choice) {
			case "1":
				new Display(sc);
				break;
			case "2":
				new Search(sc);
				break;
			case "3":
				new AddEmployee(sc);
				break;
			case "4":
				new Modify(sc);
				break;
			case "5":
				break;
			default:
				break;
			}
		}
	}
	public static void displayOptions() {
		System.out.println("\n\n1) Display employees\n2)Search for an employee\n3)Add a new employee\n4)Modify employee detail\n5) Exit");
	}
	public static void printEmployee(ResultSet rs) {
		
	}

}
