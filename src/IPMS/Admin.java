package IPMS;

import java.sql.*;
import java.util.*;

public class Admin extends Company
{
    static boolean b1 = false;
    static boolean b2 = true;

    static void loginAdmin() throws Exception
    {
        String sql = "select * from admin";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        System.out.println("\n-----Admin Login-----\n");
        System.out.print("Enter User Email_Id : ");
        String a_userid = sc.next();
        System.out.print("Enter User Password : ");
        String a_pass = sc.next();

        while(rs.next())
        {
            if((rs.getString(3).equals(a_userid))&&(rs.getString(4).equals(a_pass)))
            {
                System.out.println("\u001B[32mAdmin login successful \u001B[0m");
                b1 = true;
                int choice1 = 0;

                do
                {
                    try
                    {
                        while (b2)
                        {
                            System.out.println("\n-----Admin Portal-----\n");
                            System.out.println("1 - View All Students");
                            System.out.println("2 - View All Companies");
                            System.out.println("3 - Delete Student");
                            System.out.println("4 - Delete Company");
                            System.out.println("5 - Search Student");
                            System.out.println("6 - Search Company");
                            System.out.println("7 - Exit\n");

                            System.out.print("Enter your choice : ");
                            choice1 = sc.nextInt();
                            switch(choice1)
                            {
                                case 1 :
                                    Admin.displayAllStudent();
                                    break;

                                case 2 :
                                    Admin.displayAllCompany();
                                    break;

                                case 3 :
                                    Admin.deleteStudent();
                                    break;

                                case 4 :
                                    Admin.deleteCompany();
                                    break;

                                case 5 :
                                    Admin.searchStudent();
                                    break;

                                case 6 :
                                    Admin.searchCompany();
                                    break;

                                case 7 :
                                    System.out.println("Exiting Admin Portal");
                                    b2=false;
                                    break;

                                default :
                                    System.out.println("\033[0;31mInvalid Choice\033[0m");
                                    break;
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("\033[0;31mInvalid Input. Please enter a number.\033[0m");
                        sc.nextLine();
                    }
                }while(choice1!=7);
            }
            else if (!((rs.getString(3).equals(a_userid)))&&(rs.getString(4).equals(a_pass)))
            {
                System.out.println("\033[0;31mInvalid Email ID\033[0m");
            }
            else if ((rs.getString(3).equals(a_userid))&&!((rs.getString(4).equals(a_pass))))
            {
                System.out.println("\033[0;31mInvalid Password\033[0m");
            }
        }
        if(b1==false)
        {
            System.out.println("\033[0;31mLogin Failed\033[0m");
        }
    }

    static void deleteStudent() throws Exception
    {
        String sql="Delete from student where s_name=?";
        PreparedStatement pst=con.prepareStatement(sql);

        System.out.print("Enter name of the student you want to delete : ");
        sc.nextLine();
        pst.setString(1,sc.nextLine());

        int r=pst.executeUpdate();

        if(r>0)
        {
            System.out.println("\u001B[32mStudent deleted successfully\u001B[0m");
        }
        else {
            System.out.println("\033[0;31mDeletion Failed \033[0m");
        }
    }

    static void deleteCompany() throws Exception
    {
        String sql="Delete from company where c_name=?";

        PreparedStatement pst=con.prepareStatement(sql);
        System.out.print("Enter name of the company you want to delete : ");
        sc.nextLine();
        pst.setString(1,sc.nextLine());

        int r=pst.executeUpdate();

        if(r>0)
        {
            System.out.println("\u001B[32mCompany deleted successful\u001B[0m");
        }
        else {
            System.out.println("\033[0;31mDeletion Failed\033[0m");
        }
    }

    static void displayAllStudent() throws Exception
    {
        String sql = "{call displayALlStudent}";

        CallableStatement cst = con.prepareCall(sql);
        ResultSet rs = cst.executeQuery();

        System.out.println("\n-----Students Details-----\n");
        System.out.println("+--------+---------------------------+------------+--------+------------+------+--------+------------------------+");
        System.out.println("| RollNo | Name                      | Mobile No  | Gender | DOB        | CGPA | Branch | Email ID               |");
        System.out.println("+--------+---------------------------+------------+--------+------------+------+--------+------------------------+");

        while(rs.next())
        {
            System.out.printf("| %-6s | %-25s | %-10s | %-6s | %-10s | %-4s | %-6s | %-22s |%n",+rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getDate(5),rs.getDouble(6),rs.getString(7),rs.getString(8));
            System.out.println("+--------+---------------------------+------------+--------+------------+------+--------+------------------------+");
        }
    }

    static void displayAllCompany() throws Exception
    {
        String sql = "{call displayAllCompany}";

        CallableStatement cst = con.prepareCall(sql);
        ResultSet rs = cst.executeQuery();

        System.out.println("\n-----Company Details-----\n");
        System.out.println("+----+-----------------+------------+---------------+----------------------+");
        System.out.println("| ID | Name            | Location   | Industry Type | Email ID             |");
        System.out.println("+----+-----------------+------------+---------------+----------------------+");

        while(rs.next())
        {
            System.out.printf("| %-2s | %-15s | %-10s | %-13s | %-20s |%n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            System.out.println("+----+-----------------+------------+---------------+----------------------+");
        }
    }

    static void searchStudent() throws Exception
    {
        BST bst = new BST();
        StudentBSTSearch.addStudentToBST(bst,con);

        while(true)
        {
            try
            {
                System.out.print("Enter Student ID to search : ");
                int search_id = sc.nextInt();
                StudentBSTSearch.searchById(bst,search_id,con);
                break;
            }
            catch (Exception e) {
                System.out.println("\033[0;31mInvalid Roll Number! Must be an integer\033[0m");
                sc.nextLine();
            }
        }
    }

    static void searchCompany() throws Exception
    {
        Queue q = new Queue();
        QueueSearch.addCompanyToQueue(q,con);

        while(true)
        {
            System.out.print("Enter Company ID to Search : ");
            try
            {
                int search_id = sc.nextInt();
                QueueSearch.searchById(q,search_id,con);
                break;
            }
            catch (Exception e) {
                System.out.println("\033[0;31mInvalid Roll Number! Must be an integer\033[0m");
                sc.nextLine();
            }
        }
    }
}