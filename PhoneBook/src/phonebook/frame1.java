package phonebook;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.Stack;
import javax.swing.JOptionPane;

public class frame1 extends JFrame {

    private JPanel contentPane;
    private JTextField nameField;
    private JTextField numberField;
    private JTextField input;
    private JTable table;
    private DefaultTableModel tableModel;
    public Link first;
    private ImageIcon backgroundImage;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame1 frame = new frame1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public frame1() {
    	
    	first = null;
    	/*
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 768);
        contentPane = new JPanel();
        setTitle("Phone Book Application");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));*/
    	
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 768);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("Phone Book Application");
        // Set the JFrame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        
        JLabel lblNewLabel = new JLabel("Contact Name");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setBounds(68, 116, 126, 41);
        contentPane.add(lblNewLabel);

        JLabel lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPhoneNumber.setBounds(68, 179, 126, 41);
        contentPane.add(lblPhoneNumber);

        nameField = new JTextField();
        nameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        nameField.setBounds(243, 122, 260, 27);
        contentPane.add(nameField);
        nameField.setColumns(10);

        numberField = new JTextField();
        numberField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        numberField.setColumns(10);
        numberField.setBounds(243, 185, 260, 27);
        contentPane.add(numberField);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        saveButton.setBounds(68, 248, 106, 35);
        contentPane.add(saveButton);

        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        updateButton.setBounds(229, 248, 106, 35);
        contentPane.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        deleteButton.setBounds(397, 248, 106, 35);
        contentPane.add(deleteButton);

        input = new JTextField();
        input.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        input.setColumns(10);
        input.setBounds(68, 322, 272, 41);
        contentPane.add(input);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        searchButton.setBounds(397, 322, 106, 41);
        contentPane.add(searchButton);

        // Initialize the table model
        String[] columnNames = {"Name", "Phone Number"};
        tableModel = new DefaultTableModel(null, columnNames);

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(68, 408, 540, 300);
        contentPane.add(scrollPane);

        // Display button
        JButton displayButton = new JButton("Display");
        displayButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        displayButton.setBounds(543, 322, 106, 41);
        contentPane.add(displayButton);
        
        JButton ruse = new JButton("Recently Added");
        ruse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        ruse.setBounds(543, 248, 178, 35);
        contentPane.add(ruse);
        
        JLabel lblNewLabel_1 = new JLabel("Phone Book Application");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel_1.setBounds(528, 26, 308, 41);
        contentPane.add(lblNewLabel_1);

        
        
        //Entry entry = new Entry(enteredName, enteredNumber);
        
        // Action listeners
        saveButton.addActionListener(e -> insertFirst());
       searchButton.addActionListener(e -> find());
        displayButton.addActionListener(e -> displayList());
        deleteButton.addActionListener(e -> deleteNode());  
        ruse.addActionListener(e -> lastAdd());
        
        
    }
    
    
    public void insertFirst() {
        String enteredName = nameField.getText();
        String enteredPhoneNumber = numberField.getText();

        // Validate entered data
        if (enteredName.isEmpty() || enteredPhoneNumber.isEmpty()) {
            // Display validation error message
            JOptionPane.showMessageDialog(null, "Please enter both name and phone number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(enteredPhoneNumber.length()!=10) {
        	JOptionPane.showMessageDialog(null, "Please enter Valid Phone Number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Assuming Link class has a constructor that accepts name and phone number
        Link newLink = new Link(enteredName, enteredPhoneNumber);
        newLink.next = first;
        first = newLink;

        // Display success message
        JOptionPane.showMessageDialog(null, "Record added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear text fields
        nameField.setText("");
        numberField.setText("");
    }

/*
    public void displayList() {
        Link current = first;
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Clear the existing rows in the table
        model.setRowCount(0);

        while (current != null) {

            // Add the link's data to the table
            model.addRow(new Object[]{current.enteredName, current.enteredPhoneNumber});

            current = current.next;
        }
    }
    */
    
    
    
    public void displayList() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Clear the existing rows in the table
        model.setRowCount(0);

        // Sort the linked list in alphabetical order before displaying
        Link sortedList = mergeSort(first);
        
        // Traverse the sorted list and add each node's data to the table
        while (sortedList != null) {
            model.addRow(new Object[]{sortedList.enteredName, sortedList.enteredPhoneNumber});
            sortedList = sortedList.next;
        }
    }

    // Merge sort for linked list
    public Link mergeSort(Link head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Link middle = getMiddle(head);
        Link nextOfMiddle = middle.next;
        middle.next = null;

        // Recursively sort the two halves
        Link left = mergeSort(head);
        Link right = mergeSort(nextOfMiddle);

        // Merge the sorted halves
        return merge(left, right);
    }

    // Merge two sorted linked lists
    public Link merge(Link left, Link right) {
        Link result = null;

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        // Compare enteredName for sorting
        if (left.enteredName.compareTo(right.enteredName) < 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }

        return result;
    }

    // Get the middle of the linked list for merging
    public Link getMiddle(Link head) {
        if (head == null) {
            return head;
        }

        Link slow = head;
        Link fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }





    
    
    
    
    
    
    
    
    
    public void lastAdd() {
        Link current = first;
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Clear the existing rows in the table
        model.setRowCount(0);

        while (current != null) {
            // Add the link's data to the end of the table
            model.addRow(new Object[]{current.enteredName, current.enteredPhoneNumber});

            current = current.next;
        }
    }



/*
    public int deleteFirst() {
        Link temp = first;
        first = first.next;
        int y = temp.data;
        return y;
    }*/

   /* public boolean deleteNode() {
    	
    	String name= input.getText();
    	
        Link current = first;
        Link pcurrent = first;

        if (first == null) {
            return false; // List is empty
        } else {
            while (current != null) {
                if (current.enteredName.equals(name)) {
                    pcurrent.next = current.next;
                    displayList();
                    return true; // Node deleted successfully
                } else {
                    pcurrent = current;
                    current = current.next;
                }
            }
            return false; // Node with the given name not found
        }
    }*/
    

    public boolean deleteNode() {
        String name = input.getText();
        
        // Check if the list is empty
        if (first == null) {
            return false; // List is empty
        } else {
            Link current = first;
            Link pcurrent = null;

            while (current != null) {
                if (current.enteredName.equals(name)) {
                    // Check if the node to be deleted is the first node
                    if (pcurrent == null) {
                        first = current.next;
                    } else {
                        pcurrent.next = current.next;
                    }
                    
                    displayList();
                    
                    // Show a message box indicating successful deletion
                    JOptionPane.showMessageDialog(null, "Contact name '" + name + "' deleted successfully.");

                    return true; // Node deleted successfully
                } else {
                    pcurrent = current;
                    current = current.next;
                }
            }

            // Show a message box indicating that the node with the given name was not found
            JOptionPane.showMessageDialog(null, "Node with name '" + name + "' not found.");

            return false; // Node with the given name not found
        }
    }


    
        // ... other members

        public Link find() {
        	

        	String name= input.getText();
            Link current = first;
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear the table

            while (current != null) {
                if (current.enteredName.equals(name)) {
                	model.addRow(new Object[]{current.enteredName, current.enteredPhoneNumber});

                    return current; // Node found
                }
                
                current = current.next;
                
            }

            return null; // Node with the given name not found
        }
}

class Link {
	 
	public String enteredName;
    public String enteredPhoneNumber;
    public Link next;
   

    public Link(String Name, String Number) {
        enteredName = Name;
        enteredPhoneNumber = Number;
        next = null;
    }
    /*public void displayLink() {
        System.out.println("Name: " + enteredname + ", Phone: " + enteredphoneNumber);
    }*/
}
/*
class BinarySearchTree {
	public Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    class Node {
        String enteredName;
        String enteredPhoneNumber;
        Node left, right;

        public Node(String enteredName, String enteredPhoneNumber) {
            this.enteredName = enteredName;
            this.enteredPhoneNumber = enteredPhoneNumber;
            this.left = this.right = null;
        }
    }

    public void insert(String enteredName, String enteredPhoneNumber) {
        root = insertRec(root, enteredName, enteredPhoneNumber);
    }

    public Node insertRec(Node root, String enteredName, String enteredPhoneNumber) {
        if (root == null) {
            return new Node(enteredName, enteredPhoneNumber);
        }

        if (enteredName.compareTo(root.enteredName) < 0) {
            root.left = insertRec(root.left, enteredName, enteredPhoneNumber);
        } else if (enteredName.compareTo(root.enteredName) > 0) {
            root.right = insertRec(root.right, enteredName, enteredPhoneNumber);
        }

        return root;
    }

    public Node search(String enteredName) {
        return searchRec(root, enteredName);
    }

    public Node searchRec(Node root, String enteredName) {
        if (root == null || root.enteredName.equals(enteredName)) {
            return root;
        }

        if (enteredName.compareTo(root.enteredName) < 0) {
            return searchRec(root.left, enteredName);
        } else {
            return searchRec(root.right, enteredName);
        }
    }

    public Node getRoot() {
        return root;
    }

   
}



class Node {
    String enteredName;
    String enteredPhoneNumber;
    Node left, right;

    // Constructor
    public Node(String enteredName, String enteredPhoneNumber) {
        this.enteredName = enteredName;
        this.enteredPhoneNumber = enteredPhoneNumber;
        this.left = this.right = null;
    }
}
*/



