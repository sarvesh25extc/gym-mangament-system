import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Members extends JFrame {

    JTextField idField;
    JTextField nameField;
    JTextField ageField;
    JTextField phoneField;
    JTextField membershipField;

    JTextArea displayArea;

    public Members() {

        setTitle("Gym Management System - Members");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel title = new JLabel("MEMBER MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(230, 20, 300, 30);
        panel.add(title);

        // Member ID
        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setBounds(50, 80, 100, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(160, 80, 180, 25);
        panel.add(idField);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 120, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 120, 180, 25);
        panel.add(nameField);

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 160, 100, 25);
        panel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(160, 160, 180, 25);
        panel.add(ageField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 200, 100, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(160, 200, 180, 25);
        panel.add(phoneField);

        // Membership
        JLabel membershipLabel = new JLabel("Membership:");
        membershipLabel.setBounds(50, 240, 100, 25);
        panel.add(membershipLabel);

        membershipField = new JTextField();
        membershipField.setBounds(160, 240, 180, 25);
        panel.add(membershipField);

        // Add button
        JButton addButton = new JButton("Add Member");
        addButton.setBounds(380, 80, 130, 35);
        panel.add(addButton);

        // View button
        JButton viewButton = new JButton("View Members");
        viewButton.setBounds(380, 130, 130, 35);
        panel.add(viewButton);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(380, 180, 130, 35);
        panel.add(searchButton);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(380, 230, 130, 35);
        panel.add(deleteButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 300, 580, 200);
        panel.add(scrollPane);

        // Add member
        addButton.addActionListener(e -> addMember());

        // View members
        viewButton.addActionListener(e -> viewMembers());

        // Search member
        searchButton.addActionListener(e -> searchMember());

        // Delete member
        deleteButton.addActionListener(e -> deleteMember());

        add(panel);
        setVisible(true);
    }

    // Add member
    void addMember() {

        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String phone = phoneField.getText();
        String membership = membershipField.getText();

        if (id.isEmpty() || name.isEmpty() ||
            age.isEmpty() || phone.isEmpty() ||
            membership.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            FileWriter writer =
                    new FileWriter("data/members.txt", true);

            writer.write(
                    id + "," +
                    name + "," +
                    age + "," +
                    phone + "," +
                    membership + "\n"
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Member Added Successfully!"
            );

            clearFields();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving member!"
            );
        }
    }

    // View all members
    void viewMembers() {

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("data/members.txt")
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] member = line.split(",");

                if (member.length == 5) {

                    displayArea.append(
                            "ID: " + member[0] +
                            " | Name: " + member[1] +
                            " | Age: " + member[2] +
                            " | Phone: " + member[3] +
                            " | Membership: " + member[4] +
                            "\n"
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading members!"
            );
        }
    }

    // Search member
    void searchMember() {

        String searchId = idField.getText();

        if (searchId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Member ID to search!"
            );

            return;
        }

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("data/members.txt")
                    );

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {

                String[] member = line.split(",");

                if (member.length == 5 &&
                    member[0].equals(searchId)) {

                    displayArea.append(
                            "ID: " + member[0] + "\n" +
                            "Name: " + member[1] + "\n" +
                            "Age: " + member[2] + "\n" +
                            "Phone: " + member[3] + "\n" +
                            "Membership: " + member[4]
                    );

                    found = true;
                    break;
                }
            }

            reader.close();

            if (!found) {

                displayArea.setText(
                        "Member not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching member!"
            );
        }
    }

    // Delete member
    void deleteMember() {

        String deleteId = idField.getText();

        if (deleteId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Member ID to delete!"
            );

            return;
        }

        try {

            File inputFile =
                    new File("data/members.txt");

            File tempFile =
                    new File("data/temp.txt");

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(inputFile)
                    );

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(tempFile)
                    );

            String line;
            boolean deleted = false;

            while ((line = reader.readLine()) != null) {

                String[] member = line.split(",");

                if (member.length == 5 &&
                    member[0].equals(deleteId)) {

                    deleted = true;
                    continue;
                }

                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member Deleted Successfully!"
                );

                displayArea.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting member!"
            );
        }
    }

    // Clear fields
    void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        membershipField.setText("");
    }

    public static void main(String[] args) {

        new Members();
    }
}