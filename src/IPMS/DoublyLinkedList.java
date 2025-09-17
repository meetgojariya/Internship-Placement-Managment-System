package IPMS;

import java.sql.*;

class DoublyLinkedList
{
    class Node
    {
        int id;
        String name;
        Node prev;
        Node next;

        Node(int id, String name)
        {
            this.id = id;
            this.name = name;
        }
    }

    private Node head;
    private final Connection con;

    public DoublyLinkedList(Connection con)
    {
        this.con = con;
    }

    public void insert(int id, String name)
    {
        Node n = new Node(id, name);
        if (head == null)
        {
            head = n;
            return;
        }
        Node temp = head;

        while (temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = n;
        n.prev = temp;
    }

    public void deleteById(int id)
    {
        Node temp = head;

        while (temp != null && temp.id != id)
        {
            temp = temp.next;
        }

        if (temp == null)
        {
            System.out.println("Value not found in list.");
            return;
        }

        if (temp.prev != null)
        {
            temp.prev.next = temp.next;
        }
        else
        {
            head = temp.next;
        }

        if (temp.next != null)
        {
            temp.next.prev = temp.prev;
        }

        try
        {
            String sql = "delete from student where s_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int r = pst.executeUpdate();
            if (r > 0)
            {
                System.out.println("\u001B[32mProfile Deleted Successfully\u001B[0m");
            }
            else
            {
                System.out.println("\033[0;31mProfile Deletion Failed\033[0m");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error deleting from database: " + e.getMessage());
        }
    }
}