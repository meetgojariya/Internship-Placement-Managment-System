package IPMS;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;

public class Student extends IPMS
{
    static boolean b1 = false;
    static boolean b2 = true;

    static void registerStudent() throws Exception
    {
        String sql = "insert ignore into student values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);

        while(true)
        {
            try
            {
                System.out.print("Enter Student Roll no : ");
                pst.setInt(1, sc.nextInt());
                break;
            }
            catch (Exception e) {
                System.out.println("\033[0;31mInvalid Roll Number! Must be an integer\033[0m");
                sc.nextLine();
            }
        }

        System.out.print("Enter Student Name : ");
        sc.nextLine();
        pst.setString(2, sc.nextLine());

        while (true) {
            try {
                System.out.print("Enter Student Mobile Number : ");
                long r_mobileno = sc.nextLong();
                if (r_mobileno >= 1000000000L && r_mobileno <= 9999999999L) {
                    pst.setLong(3, r_mobileno);
                    break;
                } else {
                    System.out.println("\033[0;31mMobile Number should be of 10 digits\033[0m");
                }
            } catch (Exception e) {
                System.out.println("\033[0;31mInvalid Mobile Number! Enter digits only\033[0m");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.print("Enter Student Gender : ");
            String r_gender = sc.next();
            if (r_gender.equalsIgnoreCase("male") || r_gender.equalsIgnoreCase("female")) {
                pst.setString(4, r_gender);
                break;
            }
            else {
                System.out.println("\033[0;31mGender must be male or female\033[0m");
            }
        }

        while (true) {
            try {
                Date current_date = Date.valueOf(LocalDate.now());
                System.out.print("Enter Student Birth Date(YYYY-MM-DD) : ");
                Date b_date = Date.valueOf(sc.next());
                if(b_date.before(current_date))
                {
                    pst.setDate(5, b_date);
                    break;
                }
                else
                {
                    System.out.println("\033[0;31mDOB should be not more than Current Date\033[0m");
                }
            }
            catch (Exception e) {
                System.out.println("\033[0;31mInvalid Date format! Use YYYY-MM-DD\033[0m");
                sc.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Enter Student CGPA : ");
                double r_cgpa = sc.nextDouble();
                if (r_cgpa >= 0 && r_cgpa <= 10)
                {
                    pst.setDouble(6, r_cgpa);
                    break;
                }
                else {
                    System.out.println("\033[0;31mCGPA must be between 0 to 10\033[0m");
                }
            }
            catch (Exception e) {
                System.out.println("\033[0;31mInvalid CGPA! Enter numbers only\033[0m");
                sc.nextLine();
            }
        }

        System.out.print("Enter Student Branch : ");
        pst.setString(7, sc.next());

        while (true) {
            System.out.print("Enter Student EmailID : ");
            String r_emailid = sc.next();
            if (r_emailid.contains("@gmail.com")) {
                pst.setString(8, r_emailid);
                break;
            } else {
                System.out.println("\033[0;31mWrong formatting of email id (must contain @gmail.com)\033[0m");
            }
        }

        while (true) {
            System.out.print("Enter Student Password : ");
            String r_password = sc.next();
            if (r_password.length() == 8) {
                pst.setString(9, r_password);
                break;
            } else {
                System.out.println("\033[0;31mPassword must be exactly 8 characters long\033[0m");
            }
        }

        int r = pst.executeUpdate();
        if (r > 0) {
            System.out.println("\u001B[32mStudent Registered Successfully\u001B[0m");
        }
        else {
            System.out.println("\033[0;31mStudent Registration Failed\033[0m");
        }
    }

    static String s_emailid;
    static boolean b3 = false;

    static void loginStudent() throws Exception
    {
        boolean b = false;
        String sql = "select * from student";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        System.out.print("Enter Student Email_Id : ");
        s_emailid = sc.next();
        System.out.print("Enter Student Password : ");
        String s_pass = sc.next();

        while(rs.next())
        {
            if((rs.getString(8).equals(s_emailid))&&(rs.getString(9).equals(s_pass)))
            {
                System.out.println("\u001B[32mStudent Login Successfully\u001B[0m");
                b = true;
                int choice1 =  0;

                do
                {
                    try
                    {
                        while (b2)
                        {
                            System.out.println("\n-----Student Portal-----\n");
                            System.out.println("1 - View Your profile");
                            System.out.println("2 - Update Your Profile");
                            System.out.println("3 - Apply to Company");
                            System.out.println("4 - View Application");
                            System.out.println("5 - Export Data");
                            System.out.println("6 - Delete Your Profile");
                            System.out.println("7 - Back to Student Menu\n");

                            System.out.print("Enter your choice : ");
                            choice1 = sc.nextInt();

                            switch (choice1)
                            {
                                case 1:
                                    Student.viewStudentProfile();
                                    break;

                                case 2:
                                    Student.updateStudent();
                                    break;

                                case 3 :
                                    Student.applyToCompany();
                                    break;

                                case 4 :
                                    Student.viewYourApplication();
                                    break;

                                case 5 :
                                    Student.exportData();
                                    break;

                                case 6 :
                                    Student.exportData();
                                    Student.deleteStudentByID();
                                    break;

                                case 7 :
                                    System.out.println("Exiting Student Portal");
                                    b2 = false;
                                    break;

                                default:
                                    System.out.println("\033[0;31mInvalid Choice\033[0m");
                                    break;
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("\033[0;31mInvalid Input. Please enter a number\033[0m");
                        sc.nextLine();
                    }
                }while(choice1!=7);
            }
            else if((rs.getString(8).equals(s_emailid))&&!((rs.getString(9).equals(s_pass))))
            {
                System.out.println("\033[0;31mInvalid Password\033[0m");
            }
            else if(!((rs.getString(8).equals(s_emailid)))&&(rs.getString(9).equals(s_pass)))
            {
                System.out.println("\033[0;31mInvalid EmailID\033[0m");
            }
        }
        if(b==false)
        {
            System.out.println("\033[0;31mLogin Failed\033[0m");
        }
    }

    static void viewStudentProfile() throws Exception
    {
        String sql = "select * from student";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next())
        {
            if(rs.getString(8).equals(s_emailid))
            {
                System.out.println("\nRoll Number : "+rs.getInt(1));
                System.out.println("Name : "+rs.getString(2));
                System.out.println("Mobile Number : "+rs.getLong(3));
                System.out.println("Gender : "+rs.getString(4));
                System.out.println("Birth Date : "+rs.getDate(5));
                System.out.println("CGPA : "+rs.getDouble(6));
                System.out.println("Branch : "+rs.getString(7));
                System.out.println("Email ID : "+rs.getString(8));
                System.out.println();
                b3 = true;
            }
        }
        if(b3==false)
        {
            System.out.println("\033[0;31mFailed to view profile\033[0m");
        }
    }

    static void updateStudent() throws Exception
    {
        String sql="update student set s_mobileno = ?,s_mailid = ?,s_password = ? where s_mailid = '"+s_emailid+"'";

        PreparedStatement pst= con.prepareCall(sql);

        while (true) {
            try {
                System.out.print("Enter Updated Mobile Number : ");
                long u_mobileno = sc.nextLong();
                if (u_mobileno >= 1000000000L && u_mobileno <= 9999999999L) {
                    pst.setLong(1, u_mobileno);
                    break;
                } else {
                    System.out.println("\033[0;31mMobile Number should be of 10 digits\033[0m");
                }
            } catch (Exception e) {
                System.out.println("\033[0;31mInvalid Mobile Number! Enter digits only\033[0m");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.print("Enter Updated EmailID : ");
            String u_emailid = sc.next();
            if (u_emailid.contains("@gmail.com")) {
                pst.setString(2, u_emailid);
                break;
            } else {
                System.out.println("\033[0;31mWrong formatting of email id (must contain @gmail.com)\033[0m");
            }
        }

        while (true) {
            System.out.print("Enter Student Password : ");
            String u_password = sc.next();
            if (u_password.length() == 8) {
                pst.setString(3, u_password);
                break;
            } else {
                System.out.println("\033[0;31mPassword must be exactly 8 characters long\033[0m");
            }
        }

        int r= pst.executeUpdate();
        if(r>0)
        {
            System.out.println("\u001B[32mProfile Updated Successfully\u001B[0m");
        }
        else
        {
            System.out.println("\033[0;31mUpdate Failed\033[0m");
        }
    }

    static void applyToCompany() throws Exception
    {
        String sql = "select * from placement";
        PreparedStatement pst3 = con.prepareStatement(sql);
        ResultSet rs1 = pst3.executeQuery();

        System.out.println("\n-----Available Jobs-----\n");

        System.out.println("+----+---------------+-------------------+----------------------+-----------------------------------------------------+------------+---------------+------------+--------------+-----------+----------+-------------+");
        System.out.println("| ID | Company Name  | Email ID          | Title                | Description                                         | Type       | Location      | Salary     | Required CGPA| Skills    | Vacancies| Date Posted |");
        System.out.println("+----+---------------+-------------------+----------------------+-----------------------------------------------------+------------+---------------+------------+--------------+-----------+----------+-------------+");

        while (rs1.next())
        {
            System.out.printf("| %-2s | %-13s | %-17s | %-20s | %-51s | %-10s | %-13s | %-10s | %-12s | %-9s | %-8s | %-11s |%n",rs1.getInt(1),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getDouble(9),rs1.getDouble(10),rs1.getString(11),rs1.getInt(12),rs1.getDate(13));
            System.out.println("+----+---------------+-------------------+----------------------+-----------------------------------------------------+------------+---------------+------------+--------------+-----------+----------+-------------+");
        }

        String sql1 = "select * from student";
        PreparedStatement pst1 = con.prepareStatement(sql1);
        ResultSet rs = pst1.executeQuery();

        while(rs.next())
        {
            if(rs.getString(8).equals(s_emailid))
            {
                double cgpa = rs.getDouble(6);
                String app_name = rs.getString(2);
                int app_id = rs.getInt(1);
                long app_mobileno = rs.getLong(3);
                String app_emailid = rs.getString(8);

                String sql2 = "insert ignore into application(a_job_id,a_s_id,a_s_name,a_s_mobileno,a_s_emailid,a_skill,a_jobtype,a_cgpa,a_resume) values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql2);

                System.out.print("Enter Job Id where you want to apply : ");
                pst.setInt(1,sc.nextInt());
                pst.setInt(2,app_id);
                pst.setString(3,app_name);
                pst.setLong(4,app_mobileno);
                pst.setString(5,app_emailid);
                System.out.print("Enter Your Skill : ");
                pst.setString(6,sc.next());

                boolean b = true;

                while (b)
                {
                    System.out.print("Enter Job Type(Internship/Full-Time) : ");
                    String a_jobtype = sc.next();
                    if((a_jobtype.equalsIgnoreCase("internship"))||a_jobtype.equalsIgnoreCase("full-time"))
                    {
                        pst.setString(7, a_jobtype);
                        b = false;
                    }
                    else
                    {
                        System.out.println("\033[0;31mJob Type must be Internship or Full-Time\033[0m");
                    }
                }

                pst.setDouble(8,cgpa);
                System.out.print("Upload Resume(enter file path) : ");
                sc.nextLine();
                String filepath = sc.nextLine();

                File f = new File(filepath);
                FileInputStream fis = new FileInputStream(f);
                pst.setBlob(9,fis);

                int r = pst.executeUpdate();
                if(r>0)
                {
                    System.out.println("\u001B[32mApplication Filed Successfully\u001B[0m");
                }
                else
                {
                    System.out.println("\033[0;31mApplication Failed\033[0m");
                }
            }
        }
    }

    static void viewYourApplication() throws Exception
    {
        boolean b = true;

        while (b==true)
        {
            String sql1 = "select * from selected_students";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();

            while (rs1.next())
            {
                if(rs1.getString(5).equals(s_emailid))
                {
                    String c_name = rs1.getString(2);
                    System.out.println("Congratulations! "+rs1.getString(3)+" you are selected for Interview at "+c_name);

                    String sql = "select * from interview where i_c_name = '"+c_name+"'";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    System.out.println("Interview Schedule : ");
                    while (rs.next())
                    {
                        System.out.println("Date : "+rs.getDate(4));
                        System.out.println("Time : "+rs.getTime(5));
                        System.out.println("Mode : "+rs.getString(6));
                        if(!(rs.getString(6).equalsIgnoreCase("No Location")))
                        {
                            System.out.println("Location : "+rs.getString(6));
                        }
                    }

                    b = false;
                    break;
                }
            }
            if(b==true)
            {
                System.out.println("Thank you for applying. You were not selected this time. Best wishes ahead");
                b = false;
            }
        }
    }

    static void exportData() throws Exception
    {
        boolean b = true;
        String sql = "select * from student";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if(b==true)
        {
            while (rs.next())
            {
                if(rs.getString(8).equals(s_emailid))
                {
                    FileWriter fw = new FileWriter("D:\\Student_"+rs.getInt(1)+".txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write("ID : "+rs.getInt(1)+"\n");
                    bw.write("Name : "+rs.getString(2)+"\n");
                    bw.write("Mobile Number : "+rs.getLong(3)+"\n");
                    bw.write("Gender : "+rs.getString(4)+"\n");
                    bw.write("Birthdate : "+rs.getDate(5)+"\n");
                    bw.write("CGPA : "+rs.getDouble(6)+"\n");
                    bw.write("Branch : "+rs.getString(7)+"\n");
                    bw.write("Email ID : "+rs.getString(8)+"\n");
                    bw.write("Password : "+rs.getString(9)+"\n");

                    System.out.println("\u001B[32mData Exported Successfully\u001B[0m");
                    bw.close();
                    fw.close();
                    b = false;
                }
            }
        }
        if(b==true)
        {
            System.out.println("\033[0;31mExporting Data Failed\033[0m");
        }
    }

    static void deleteStudentByID() throws Exception
    {
        DoublyLinkedList dll = new DoublyLinkedList(con);

        String sql = "select * from student";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        int delete_id = 0;

        while (rs.next())
        {
            if(rs.getString(8).equals(s_emailid))
            {
                delete_id = rs.getInt(1);
                dll.insert(rs.getInt(1),rs.getString(2));
            }
        }
        dll.deleteById(delete_id);
    }
}