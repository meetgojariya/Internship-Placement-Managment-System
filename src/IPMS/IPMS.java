package IPMS;

import java.sql.*;
import java.util.*;

public class IPMS
{
    static Scanner sc = new Scanner(System.in);
    static Connection con;
    public static void main(String[] args) throws Exception
    {
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);

        String dburl = "jdbc:mysql://localhost:3306/project";
        String dbuser = "root";
        String dbpass = "";

        con = DriverManager.getConnection(dburl,dbuser,dbpass);

        int choice = 0;

        do {
            try {
                System.out.println("\n-----Internship/Placement Management System-----\n");
                System.out.println("1 - Admin");
                System.out.println("2 - Student");
                System.out.println("3 - Company");
                System.out.println("4 - Exit\n");

                System.out.print("Enter choice : ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        Admin.loginAdmin();
                        break;

                    case 2:
                        int choice1 = 0;
                        do {
                            try {
                                System.out.println("\n-----Student-----\n");
                                System.out.println("1 - Student Registration");
                                System.out.println("2 - Student Login");
                                System.out.println("3 - Back to Main Menu\n");

                                System.out.print("Enter your choice : ");
                                choice1 = sc.nextInt();

                                switch (choice1) {
                                    case 1:
                                        Student.registerStudent();
                                        break;

                                    case 2:
                                        Student.loginStudent();
                                        break;

                                    case 3:
                                        System.out.println("Exiting Student");
                                        break;

                                    default:
                                        System.out.println("\033[0;31mInvalid Choice\033[0m");
                                        break;
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("\033[0;31mInvalid Input! Please enter a number\033[0m");
                                sc.nextLine();
                            }
                        } while (choice1 != 3);
                        break;

                    case 3:
                        int choice2 = 0;
                        do {
                            try {
                                System.out.println("\n-----Company-----\n");
                                System.out.println("1 - Company Registration");
                                System.out.println("2 - Company Login");
                                System.out.println("3 - Back to Main Menu\n");

                                System.out.print("Enter your choice : ");
                                choice2 = sc.nextInt();

                                switch (choice2) {
                                    case 1:
                                        Company.registerCompany();
                                        break;

                                    case 2:
                                        Company.loginCompany();
                                        break;

                                    case 3:
                                        System.out.println("Exiting Company");
                                        break;

                                    default:
                                        System.out.println("\033[0;31mInvalid Choice\033[0m");
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("\033[0;31mInvalid Input! Please enter a number\033[0m");
                                sc.nextLine();
                            }
                        } while (choice2 != 3);
                        break;

                    case 4:
                        System.out.println("Exiting System");
                        break;

                    default:
                        System.out.println("\033[0;31mInvalid Choice\033[0m");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("\033[0;31mInvalid Input! Please enter a number\033[0m");
                sc.nextLine();
            }
        } while (choice != 4);
    }
}