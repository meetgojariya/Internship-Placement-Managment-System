package IPMS;

import java.sql.*;
import java.util.*;

class CircularLinkedList
{
    class Node
    {
        int id;
        String name;
        Node next;

        Node(int id, String name)
        {
            this.id = id;
            this.name = name;
            this.next = null;
        }
    }

    private Node head = null;
    private Node temp = null;
    private final Connection con;

    public CircularLinkedList(Connection con)
    {
        this.con = con;
    }

    public void insert(int id, String name)
    {
        Node n = new Node(id, name);
        if (head == null)
        {
            head = n;
            temp = n;
            temp.next = head;
        }
        else
        {
            temp.next = n;
            temp = n;
            temp.next = head;
        }
    }

    public void deleteById(int id)
    {
        if (head == null)
        {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        Node prev = temp;
        boolean found = false;

        do {
            if (current.id == id)
            {
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found)
        {
            System.out.println("Value not found in list.");
            return;
        }

        try
        {
            String sql = "delete from company where c_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            int r = pst.executeUpdate();

            if (r>0)
            {
                if (current == head && current == temp)
                {
                    head = null;
                    temp = null;
                }
                else if (current == head)
                {
                    head = head.next;
                    temp.next = head;
                }
                else if (current == temp)
                {
                    temp = prev;
                    temp.next = head;
                }
                else
                {
                    prev.next = current.next;
                }

                System.out.println("Deleted ID " + id);
            }
            else
            {
                System.out.println("\033[0;31mID not found in database\033[0m");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error deleting from database: " + e.getMessage());
        }
    }
}