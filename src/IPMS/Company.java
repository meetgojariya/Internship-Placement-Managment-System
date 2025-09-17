package IPMS;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

class Company extends IPMS
{
    static boolean b1 = false;
    static boolean b2 = true;

    static void registerCompany() throws Exception
    {
        String sql = "insert ignore into company values(?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);

        while(true)
        {
            System.out.print("Enter Company Id : ");
            try
            {
                pst.setInt(1, sc.nextInt());
                break;
            }
            catch (Exception e) {
                System.out.println("\033[0;31mInvalid Roll Number! Must be an integer\033[0m");
                sc.nextLine();
            }
        }

        System.out.print("Enter Company Name : ");
        sc.nextLine();
        pst.setString(2, sc.nextLine());

        System.out.print("Enter Company Location : ");
        pst.setString(3, sc.nextLine());

        System.out.print("Enter Company Type : ");
        pst.setString(4, sc.next());

        while (true) {
            System.out.print("Enter Company EmailId : ");
            String r_emailid = sc.next();

            if (r_emailid.toLowerCase().contains("@gmail.com"))
            {
                pst.setString(5, r_emailid);
                break;
            }
            else
            {
                System.out.println("\033[0;31mWrong formatting of Email ID\033[0m");
            }
        }

        while (true)
        {
            System.out.print("Enter Company Password : ");
            String r_password = sc.next();

            if (r_password.length() == 8) {
                pst.setString(6, r_password);
                break;
            } else {
                System.out.println("\033[0;31mPassword must be of 8 characters\033[0m");
            }
        }

        int r = pst.executeUpdate();
        if (r > 0) {
            System.out.println("\u001B[32mCompany Registered Successfully\u001B[0m");
        } else {
            System.out.println("\033[0;31mCompany Registration Failed\033[0m");
        }
    }

    static String com_emailid;

    static void loginCompany() throws Exception
    {
        boolean b = true;

        String sql = "select * from company";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        System.out.print("Enter Company Email Id : ");
        com_emailid = sc.next();
        System.out.print("Enter Company Password : ");
        String com_pass = sc.next();

        while (rs.next())
        {
            if ((rs.getString(5).equals(com_emailid)) && (rs.getString(6).equals(com_pass)))
            {
                System.out.println("\u001B[32mCompany login successful\u001B[0m");
                b = false;

                int choice1 = 0;

                do {
                    try
                    {
                        while (b2) {
                            System.out.println("\n-----Company Portal-----\n");
                            System.out.println("1 - Post Internship/Job Vacancy");
                            System.out.println("2 - View Applications");
                            System.out.println("3 - Edit Company Profile");
                            System.out.println("4 - Shortlist Students");
                            System.out.println("5 - Schedule Interview");
                            System.out.println("6 - Delete Company Profile");
                            System.out.println("7 - Back to Company Menu\n");

                            System.out.print("Enter your choice : ");
                            choice1 = sc.nextInt();

                            switch (choice1) {
                                case 1:
                                    Company.postJobVacancy();
                                    break;

                                case 2:
                                    Company.viewApplications();
                                    break;

                                case 3:
                                    Company.updateCompany();
                                    break;

                                case 4 :
                                    Company.shortlistStudent();
                                    break;

                                case 5 :
                                    Company.scheduleInterview();
                                    break;

                                case 6 :
                                    Company.deleteCompanyByID();
                                    break;

                                case 7 :
                                    System.out.println("Exiting Company Portal");
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
                } while (choice1 != 7);
            } else if ((rs.getString(5).equals(com_emailid)) && !((rs.getString(6).equals(com_pass))))
            {
                System.out.println("\033[0;31mInvalid Password\033[0m");
            }
            else if(!((rs.getString(5).equals(com_emailid))) && (rs.getString(6).equals(com_pass)))
            {
                System.out.println("\033[0;31mInvalid EmailID\033[0m");
            }
        }
        if(b==true)
        {
            System.out.println("\033[0;31mLogin Failed\033[0m");
        }
    }

    static void updateCompany() throws Exception
    {
        String sql = "update company set c_location = ?,c_emailid = ? where c_emailid = '" + com_emailid + "'";
        PreparedStatement pst = con.prepareStatement(sql);

        System.out.print("Enter your updated location : ");
        pst.setString(1, sc.next());

        while (true)
        {
            System.out.print("Enter your updated EmailId : ");
            String u_emailid = sc.next();

            if (u_emailid.toLowerCase().contains("@gmail.com"))
            {
                pst.setString(2, u_emailid);
                break;
            }
            else
            {
                System.out.println("\033[0;31mWrong formatting of Email ID\033[0m");
            }
        }

        int r = pst.executeUpdate();
        if (r > 0) {
            System.out.println("\u001B[32mUpdate successful\u001B[0m");
        }
        else {
            System.out.println("\033[0;31mUpdate Failed\033[0m");
        }
    }

    static void postJobVacancy() throws Exception
    {
        String sql1 = "select * from company";
        PreparedStatement pst1 = con.prepareStatement(sql1);
        ResultSet rs = pst1.executeQuery();

        while (rs.next())
        {
            if (rs.getString(5).equals(com_emailid))
            {
                int com_id = rs.getInt(1);
                String com_name = rs.getString(2);
                String com_location = rs.getString(3);
                String com_email = rs.getString(5);

                String sql = "insert into placement(job_c_id,job_c_name,job_c_email,job_title,job_description,job_type,job_location,salary,cgpa_required,skills,vacancies) values(?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setInt(1,com_id);
                pst.setString(2,com_name);
                pst.setString(3,com_email);
                System.out.print("Enter Job Title : ");
                sc.nextLine();
                pst.setString(4, sc.nextLine());
                System.out.print("Enter Job Description : ");
                pst.setString(5, sc.nextLine());

                while (true)
                {
                    System.out.print("Enter Job Type(Internship/Full-Time) : ");
                    String p_jobtype = sc.nextLine();
                    if((p_jobtype.equalsIgnoreCase("internship"))||p_jobtype.equalsIgnoreCase("full-time"))
                    {
                        pst.setString(6, p_jobtype);
                        break;
                    }
                    else
                    {
                        System.out.println("\033[0;31mJob Type must be Internship or Full-Time\033[0m");
                    }
                }

                pst.setString(7, com_location);

                while(true)
                {
                    try
                    {
                        System.out.print("Enter Stipend or Salary : ");
                        pst.setDouble(8, sc.nextDouble());
                        break;
                    }
                    catch (Exception e) {
                        System.out.println("\033[0;31mSalary or Stipend must be in numbers\033[0m");
                        sc.nextLine();
                    }
                }

                while (true) {
                    try {
                        System.out.print("Enter Required CGPA : ");
                        double p_cgpa = sc.nextDouble();
                        if (p_cgpa >= 0 && p_cgpa <= 10)
                        {
                            pst.setDouble(9, p_cgpa);
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

                System.out.print("Enter Required Skills : ");
                pst.setString(10, sc.next());

                while(true)
                {
                    try
                    {
                        System.out.print("Enter Job Vacancies : ");
                        pst.setInt(11, sc.nextInt());
                        break;
                    }
                    catch (Exception e) {
                        System.out.println("\033[0;31mInvalid Vacancy! Enter numbers only\033[0m");
                        sc.nextLine();
                    }
                }

                int r = pst.executeUpdate();
                if (r > 0)
                {
                    System.out.println("\u001B[32mJob vacancy posted successfully\u001B[0m");
                }
                else
                {
                    System.out.println("\033[0;31mPost Failed\033[0m");
                }
            }
        }
    }

    static void viewApplications() throws Exception
    {
        String sql1 = "select * from placement";
        PreparedStatement pst = con.prepareStatement(sql1);
        ResultSet rs1 = pst.executeQuery();
        while (rs1.next())
        {
            if(rs1.getString(4).equals(com_emailid))
            {
                int job_id = rs1.getInt(1);

                String sql2 = "select * from application";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery();

                System.out.println("\n-----Job Applications-----\n");

                System.out.println("+----+---------------------------+------------+------------------+--------+");
                System.out.println("| ID | Name                      | Skill      | Job Type         | CGPA   |");
                System.out.println("+----+---------------------------+------------+------------------+--------+");

                while (rs2.next())
                {
                    if(rs2.getInt(2)==job_id)
                    {
                        System.out.printf("| %-2s | %-25s | %-10s | %-16s | %-6s |%n",rs2.getInt(1),rs2.getString(4),rs2.getString(7),rs2.getString(8),rs2.getDouble(9));
                        System.out.println("+----+---------------------------+------------+------------------+--------+");
                    }
                }
            }
        }
    }

    static void shortlistStudent() throws Exception
    {
        String sql = "select * from company";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next())
        {
            if (rs.getString(5).equals(com_emailid))
            {
                String sql1 = "select * from placement where job_c_email='"+com_emailid+"'";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();

                while (rs1.next())
                {
                    String job_type = rs1.getString(7);
                    String job_skill = rs1.getString(11);
                    double job_cgpa = rs1.getDouble(10);
                    int job_vacancy = rs1.getInt(12);

                    String sql2 = "select * from application where a_skill = '"+job_skill+"' and a_jobtype = '"+job_type+"' and a_cgpa >= "+job_cgpa+" order by a_cgpa desc limit "+job_vacancy+"";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();

                    System.out.println("\n-----Shortlisted Students List-----\n");

                    System.out.println("+--------------------------+---------------+--------------------------------+--------------+--------------+-------+");
                    System.out.println("| Name                     | Mobile Number | Email ID                       | Skill        | Job Type     | CGPA  |");
                    System.out.println("+--------------------------+---------------+--------------------------------+--------------+--------------+-------+");

                    while (rs2.next())
                    {
                        System.out.printf("| %-25s | %-13s | %-30s | %-12s | %-12s | %-5.2f |%n",rs2.getString(4),rs2.getLong(5),rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getDouble(9));
                        System.out.println("+--------------------------+---------------+--------------------------------+--------------+--------------+-------+");

                        String sql3 = "insert ignore into selected_students(ss_c_name,ss_name,ss_mobileno,ss_emailid,ss_skill,ss_jobtype,ss_cgpa) values ('"+rs1.getString(3)+"','"+rs2.getString(4)+"',"+rs2.getLong(5)+",'"+rs2.getString(6)+"','"+rs2.getString(7)+"','"+rs2.getString(8)+"',"+rs2.getDouble(9)+")";
                        PreparedStatement pst3 = con.prepareStatement(sql3);
                        pst3.executeUpdate();
                    }
                }
            }
        }
    }

    static void scheduleInterview() throws Exception
    {
        String sql = "select * from placement";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next())
        {
            if(rs.getString(4).equals(com_emailid))
            {
                int c_id = rs.getInt(2);
                String c_name = rs.getString(3);

                String sql1 = "insert into interview values(?,?,?,?,?,?,?)";
                PreparedStatement pst1 = con.prepareStatement(sql1);

                while(true)
                {
                    try
                    {
                        System.out.print("Enter Interview ID : ");
                        pst1.setInt(1,sc.nextInt());
                        break;
                    }
                    catch (Exception e) {
                        System.out.println("\033[0;31mInvalid ID! Must be an integer\033[0m");
                        sc.nextLine();
                    }
                }
                pst1.setInt(2,c_id);
                pst1.setString(3,c_name);

                while (true) {
                    try {
                        Date current_date = Date.valueOf(LocalDate.now());
                        System.out.print("Enter Interview Date(YYYY-MM-DD) : ");
                        Date i_date = Date.valueOf(sc.next());
                        if (i_date.after(current_date)) {
                            pst1.setDate(4, i_date);
                            break;
                        }
                        else
                        {
                            System.out.println("\033[0;31mDate should be after current date\033[0m");
                        }
                    }
                    catch (Exception e) {
                        System.out.println("\033[0;31mInvalid Date format! Use YYYY-MM-DD\033[0m");
                        sc.nextLine();
                    }
                }

                while(true)
                {
                    try
                    {
                        System.out.print("Enter Interview Time(HH:MM:SS) : ");
                        pst1.setTime(5, Time.valueOf(sc.next()));
                        break;

                    } catch (Exception e) {
                        System.out.println("\033[0;31mInvalid Time format! Use HH:MM:SS\033[0m");
                        sc.nextLine();
                    }
                }

                String i_mode;
                while (true)
                {
                    try
                    {
                        System.out.print("Enter Interview Mode(Online/Offline) : ");
                        i_mode = sc.next();
                        if((i_mode.equalsIgnoreCase("online"))||(i_mode.equalsIgnoreCase("offline")))
                        {
                            pst1.setString(6,i_mode);
                            break;
                        }
                        else
                        {
                            System.out.println("\033[0;31mMode of interview must be Online or Offline\033[0m");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                        sc.nextLine();
                    }

                }

                if(i_mode.equals("offline"))
                {
                    System.out.print("Enter Interview Location : ");
                    sc.nextLine();
                    pst1.setString(7, sc.nextLine());
                }
                else
                {
                    pst1.setString(7,"No Location");
                }

                int r = pst1.executeUpdate();
                if(r>0)
                {
                    System.out.println("\u001B[32mInterview Scheduled Successfully\u001B[0m");
                }
                else
                {
                    System.out.println("\033[0;31mInterview Scheduling Failed\033[0m");
                }
            }
        }
    }

    static void deleteCompanyByID() throws Exception
    {
        CircularLinkedList cll = new CircularLinkedList(con);

        String sql = "select * from company";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        int delete_id = 0;

        while (rs.next())
        {
            if(rs.getString(5).equals(com_emailid))
            {
                delete_id = rs.getInt(1);
                cll.insert(rs.getInt(1),rs.getString(2));
            }
        }
        cll.deleteById(delete_id);
    }
}