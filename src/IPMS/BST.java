package IPMS;

import java.sql.*;
import java.util.*;

class BSTNode {
    int s_id;
    BSTNode left;
    BSTNode right;

    public BSTNode(int s_id) {
        this.s_id = s_id;
        this.left = this.right = null;
    }
}

class BST
{
    BSTNode root;

    public void insert(int s_id)
    {
        root = insertRec(root, s_id);
    }

    private BSTNode insertRec(BSTNode root, int s_id)
    {
        if (root == null)
        {
            root = new BSTNode(s_id);
            return root;
        }
        if (s_id < root.s_id)
        {
            root.left = insertRec(root.left, s_id);
        }
        else if (s_id > root.s_id)
        {
            root.right = insertRec(root.right, s_id);
        }
        return root;
    }

    public boolean search(int s_id)
    {
        return searchRec(root, s_id);
    }

    private boolean searchRec(BSTNode root, int s_id)
    {
        if (root == null)
        {
            return false;
        }
        if (root.s_id == s_id)
        {
            return true;
        }
        if (s_id < root.s_id)
        {
            return searchRec(root.left, s_id);
        }
        else
        {
            return searchRec(root.right, s_id);
        }
    }
}

class StudentBSTSearch
{

    public static void addStudentToBST(BST bst, Connection con)
    {
        try {
            String sql = "select s_id from student";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt(1);
                bst.insert(id);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void searchById(BST bst, int s_id, Connection con)
    {
        try {
            if (bst.search(s_id))
            {
                String sql = "select * from student where s_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, s_id);

                ResultSet rs = pst.executeQuery();

                if (rs.next())
                {
                    System.out.println("Student ID : " + rs.getInt(1));
                    System.out.println("Name : " + rs.getString(2));
                    System.out.println("Mobile No : " + rs.getLong(3));
                    System.out.println("Gender : " + rs.getString(4));
                    System.out.println("Birthdate : " + rs.getDate(5));
                    System.out.println("CGPA : " + rs.getDouble(6));
                    System.out.println("Branch : " + rs.getString(7));
                    System.out.println("Email ID : " + rs.getString(8));
                    System.out.println("Password : " + rs.getString(9));
                }
                else {
                    System.out.println("No student found in database with s_id = " + s_id);
                }
            }
            else {
                System.out.println("\033[0;31mStudent ID " + s_id + " not found in BST \033[0m");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}