import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Membership extends JFrame {

    JTextField memberIdField;
    JTextField memberNameField;
    JTextField typeField;
    JTextField startDateField;
    JTextField endDateField;

    JTextArea displayArea;

    public Membership() {

        setTitle("Gym Management System - Membership");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel title = new JLabel("MEMBERSHIP MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(210, 20, 350, 30);
        panel.add(title);

        // Member ID
        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setBounds(50, 80, 100, 25);
        panel.add(idLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(160, 80, 180, 25);
        panel.add(memberIdField);

        // Member Name
        JLabel nameLabel = new JLabel("Member Name:");
        nameLabel.setBounds(50, 120, 100, 25);
        panel.add(nameLabel);

        memberNameField = new JTextField();
        memberNameField.setBounds(160, 120, 180, 25);
        panel.add(memberNameField);

        // Membership Type
        JLabel typeLabel = new JLabel("Membership:");
        typeLabel.setBounds(50, 160, 100, 25);
        panel.add(typeLabel);

        typeField = new JTextField();
        typeField.setBounds(160, 160, 180, 25);
        panel.add(typeField);

        // Start Date
        JLabel startLabel = new JLabel("Start Date:");
        startLabel.setBounds(50, 200, 100, 25);
        panel.add(startLabel);

        startDateField = new JTextField();
        startDateField.setBounds(160, 200, 180, 25);
        panel.add(startDateField);

        // End Date
        JLabel endLabel = new JLabel("End Date:");
        endLabel.setBounds(50, 240, 100, 25);
        panel.add(endLabel);

        endDateField = new JTextField();
        endDateField.setBounds(160, 240, 180, 25);
        panel.add(endDateField);

        // Add button
        JButton addButton = new JButton("Add Membership");
        addButton.setBounds(380, 80, 150, 35);
        panel.add(addButton);

        // View button
        JButton viewButton = new JButton("View Memberships");
        viewButton.setBounds(380, 130, 150, 35);
        panel.add(viewButton);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(380, 180, 150, 35);
        panel.add(searchButton);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(380, 230, 150, 35);
        panel.add(deleteButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(displayArea);

        scrollPane.setBounds(50, 300, 580, 200);
        panel.add(scrollPane);

        // Button actions
        addButton.addActionListener(e -> addMembership());

        viewButton.addActionListener(e -> viewMemberships());

        searchButton.addActionListener(e -> searchMembership());

        deleteButton.addActionListener(e -> deleteMembership());

        add(panel);
        setVisible(true);
    }

    // Add Membership
    void addMembership() {

        String memberId = memberIdField.getText();
        String memberName = memberNameField.getText();
        String type = typeField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        if (memberId.isEmpty()
                || memberName.isEmpty()
                || type.isEmpty()
                || startDate.isEmpty()
                || endDate.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            FileWriter writer =
                    new FileWriter(
                            "data/memberships.txt",
                            true
                    );

            writer.write(
                    memberId + ","
                    + memberName + ","
                    + type + ","
                    + startDate + ","
                    + endDate + "\n"
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Membership Added Successfully!"
            );

            clearFields();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving membership!"
            );
        }
    }

    // View Memberships
    void viewMemberships() {

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/memberships.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] membership =
                        line.split(",");

                if (membership.length == 5) {

                    displayArea.append(
                            "Member ID: "
                            + membership[0]
                            + " | Name: "
                            + membership[1]
                            + " | Type: "
                            + membership[2]
                            + " | Start: "
                            + membership[3]
                            + " | End: "
                            + membership[4]
                            + "\n"
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading memberships!"
            );
        }
    }

    // Search Membership
    void searchMembership() {

        String searchId =
                memberIdField.getText();

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
                            new FileReader(
                                    "data/memberships.txt"
                            )
                    );

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {

                String[] membership =
                        line.split(",");

                if (membership.length == 5
                        && membership[0].equals(searchId)) {

                    displayArea.append(
                            "Member ID: "
                            + membership[0]
                            + "\nName: "
                            + membership[1]
                            + "\nMembership: "
                            + membership[2]
                            + "\nStart Date: "
                            + membership[3]
                            + "\nEnd Date: "
                            + membership[4]
                    );

                    found = true;
                    break;
                }
            }

            reader.close();

            if (!found) {

                displayArea.setText(
                        "Membership not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching membership!"
            );
        }
    }

    // Delete Membership
    void deleteMembership() {

        String deleteId =
                memberIdField.getText();

        if (deleteId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Member ID to delete!"
            );

            return;
        }

        try {

            File inputFile =
                    new File(
                            "data/memberships.txt"
                    );

            File tempFile =
                    new File(
                            "data/temp.txt"
                    );

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

                String[] membership =
                        line.split(",");

                if (membership.length == 5
                        && membership[0].equals(deleteId)) {

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
                        "Membership Deleted Successfully!"
                );

                displayArea.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Membership not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting membership!"
            );
        }
    }

    // Clear fields
    void clearFields() {

        memberIdField.setText("");
        memberNameField.setText("");
        typeField.setText("");
        startDateField.setText("");
        endDateField.setText("");
    }

    public static void main(String[] args) {

        new Membership();
    }
}