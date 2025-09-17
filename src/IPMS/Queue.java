package IPMS;

import java.sql.*;
import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Queue implementation using Linked List
class Queue extends IPMS
{
    Node front;
    Node rear;

    public Queue()
    {
        this.front = this.rear = null;
    }

    public void enqueue(int data)
    {
        Node n = new Node(data);

        if (rear == null) {
            front = rear = n;
            return;
        }
        rear.next = n;
        rear = n;
    }

    public boolean search(int value) {
        Node temp = front;
        while (temp != null) {
            if (temp.data == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}

class QueueSearch
{
    public static void addCompanyToQueue(Queue queue, Connection con) {
        try {
            String sql = "select c_id from company";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("c_id");
                queue.enqueue(id);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchById(Queue q, int c_id, Connection con)
    {
        try {
            if (q.search(c_id))
            {
                String sql = "select * from company where c_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, c_id);

                ResultSet rs = pst.executeQuery();

                if (rs.next())
                {
                    System.out.println("Company ID: " + rs.getInt(1));
                    System.out.println("Company Name: " + rs.getString(2));
                    System.out.println("Location: " + rs.getString(3));
                    System.out.println("Industry Type: " + rs.getString(4));
                    System.out.println("Email: " + rs.getString(5));
                    System.out.println("Password: " + rs.getString(6));
                }
                else
                {
                    System.out.println("No company found in database with c_id = " + c_id);
                }

            }
            else
            {
                System.out.println("Company ID " + c_id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}