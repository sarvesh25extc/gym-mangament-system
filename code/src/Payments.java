import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Payments extends JFrame {

    JTextField paymentIdField;
    JTextField memberIdField;
    JTextField memberNameField;
    JTextField amountField;
    JTextField dateField;

    JTextArea displayArea;

    public Payments() {

        setTitle("Gym Management System - Payments");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel title = new JLabel("PAYMENT MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(220, 20, 300, 30);
        panel.add(title);

        // Payment ID
        JLabel paymentIdLabel =
                new JLabel("Payment ID:");

        paymentIdLabel.setBounds(50, 80, 100, 25);
        panel.add(paymentIdLabel);

        paymentIdField = new JTextField();
        paymentIdField.setBounds(160, 80, 180, 25);
        panel.add(paymentIdField);

        // Member ID
        JLabel memberIdLabel =
                new JLabel("Member ID:");

        memberIdLabel.setBounds(50, 120, 100, 25);
        panel.add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(160, 120, 180, 25);
        panel.add(memberIdField);

        // Member Name
        JLabel nameLabel =
                new JLabel("Member Name:");

        nameLabel.setBounds(50, 160, 100, 25);
        panel.add(nameLabel);

        memberNameField = new JTextField();
        memberNameField.setBounds(160, 160, 180, 25);
        panel.add(memberNameField);

        // Amount
        JLabel amountLabel =
                new JLabel("Amount:");

        amountLabel.setBounds(50, 200, 100, 25);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(160, 200, 180, 25);
        panel.add(amountField);

        // Payment Date
        JLabel dateLabel =
                new JLabel("Payment Date:");

        dateLabel.setBounds(50, 240, 100, 25);
        panel.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(160, 240, 180, 25);
        panel.add(dateField);

        // Add button
        JButton addButton =
                new JButton("Add Payment");

        addButton.setBounds(380, 80, 140, 35);
        panel.add(addButton);

        // View button
        JButton viewButton =
                new JButton("View Payments");

        viewButton.setBounds(380, 130, 140, 35);
        panel.add(viewButton);

        // Search button
        JButton searchButton =
                new JButton("Search");

        searchButton.setBounds(380, 180, 140, 35);
        panel.add(searchButton);

        // Delete button
        JButton deleteButton =
                new JButton("Delete");

        deleteButton.setBounds(380, 230, 140, 35);
        panel.add(deleteButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(displayArea);

        scrollPane.setBounds(50, 300, 580, 200);
        panel.add(scrollPane);

        // Button actions
        addButton.addActionListener(e -> addPayment());

        viewButton.addActionListener(e -> viewPayments());

        searchButton.addActionListener(e -> searchPayment());

        deleteButton.addActionListener(e -> deletePayment());

        add(panel);
        setVisible(true);
    }

    // Add Payment
    void addPayment() {

        String paymentId =
                paymentIdField.getText();

        String memberId =
                memberIdField.getText();

        String memberName =
                memberNameField.getText();

        String amount =
                amountField.getText();

        String date =
                dateField.getText();

        if (paymentId.isEmpty()
                || memberId.isEmpty()
                || memberName.isEmpty()
                || amount.isEmpty()
                || date.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            FileWriter writer =
                    new FileWriter(
                            "data/payments.txt",
                            true
                    );

            writer.write(
                    paymentId + ","
                    + memberId + ","
                    + memberName + ","
                    + amount + ","
                    + date + "\n"
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Payment Added Successfully!"
            );

            clearFields();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving payment!"
            );
        }
    }

    // View Payments
    void viewPayments() {

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/payments.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] payment =
                        line.split(",");

                if (payment.length == 5) {

                    displayArea.append(
                            "Payment ID: "
                            + payment[0]
                            + " | Member ID: "
                            + payment[1]
                            + " | Name: "
                            + payment[2]
                            + " | Amount: ₹"
                            + payment[3]
                            + " | Date: "
                            + payment[4]
                            + "\n"
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading payments!"
            );
        }
    }

    // Search Payment
    void searchPayment() {

        String searchId =
                paymentIdField.getText();

        if (searchId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Payment ID to search!"
            );

            return;
        }

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/payments.txt"
                            )
                    );

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {

                String[] payment =
                        line.split(",");

                if (payment.length == 5
                        && payment[0].equals(searchId)) {

                    displayArea.append(
                            "Payment ID: "
                            + payment[0]
                            + "\nMember ID: "
                            + payment[1]
                            + "\nMember Name: "
                            + payment[2]
                            + "\nAmount: ₹"
                            + payment[3]
                            + "\nPayment Date: "
                            + payment[4]
                    );

                    found = true;
                    break;
                }
            }

            reader.close();

            if (!found) {

                displayArea.setText(
                        "Payment not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching payment!"
            );
        }
    }

    // Delete Payment
    void deletePayment() {

        String deleteId =
                paymentIdField.getText();

        if (deleteId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Payment ID to delete!"
            );

            return;
        }

        try {

            File inputFile =
                    new File(
                            "data/payments.txt"
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

                String[] payment =
                        line.split(",");

                if (payment.length == 5
                        && payment[0].equals(deleteId)) {

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
                        "Payment Deleted Successfully!"
                );

                displayArea.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting payment!"
            );
        }
    }

    // Clear fields
    void clearFields() {

        paymentIdField.setText("");
        memberIdField.setText("");
        memberNameField.setText("");
        amountField.setText("");
        dateField.setText("");
    }

    public static void main(String[] args) {

        new Payments();
    }
}