import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Reports extends JFrame {

    JLabel membersLabel;
    JLabel trainersLabel;
    JLabel membershipsLabel;
    JLabel paymentsLabel;
    JLabel amountLabel;

    public Reports() {

        setTitle("Gym Management System - Reports");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel title =
                new JLabel("GYM REPORTS");

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setBounds(230, 30, 250, 40);
        panel.add(title);

        // Total Members
        membersLabel =
                new JLabel("Total Members: 0");

        membersLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        membersLabel.setBounds(100, 100, 300, 35);
        panel.add(membersLabel);

        // Total Trainers
        trainersLabel =
                new JLabel("Total Trainers: 0");

        trainersLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        trainersLabel.setBounds(100, 150, 300, 35);
        panel.add(trainersLabel);

        // Total Memberships
        membershipsLabel =
                new JLabel("Total Memberships: 0");

        membershipsLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        membershipsLabel.setBounds(100, 200, 350, 35);
        panel.add(membershipsLabel);

        // Total Payments
        paymentsLabel =
                new JLabel("Total Payments: 0");

        paymentsLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        paymentsLabel.setBounds(100, 250, 300, 35);
        panel.add(paymentsLabel);

        // Total Amount
        amountLabel =
                new JLabel("Total Amount: ₹0");

        amountLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        amountLabel.setBounds(100, 300, 350, 35);
        panel.add(amountLabel);

        // Refresh button
        JButton refreshButton =
                new JButton("Refresh Report");

        refreshButton.setBounds(
                220, 370, 180, 45
        );

        panel.add(refreshButton);

        refreshButton.addActionListener(
                e -> loadReports()
        );

        add(panel);

        loadReports();

        setVisible(true);
    }

    // Load Reports
    void loadReports() {

        int members =
                countRecords("data/members.txt");

        int trainers =
                countRecords("data/trainers.txt");

        int memberships =
                countRecords("data/memberships.txt");

        int payments =
                countRecords("data/payments.txt");

        double totalAmount =
                calculateAmount();

        membersLabel.setText(
                "Total Members: " + members
        );

        trainersLabel.setText(
                "Total Trainers: " + trainers
        );

        membershipsLabel.setText(
                "Total Memberships: " + memberships
        );

        paymentsLabel.setText(
                "Total Payments: " + payments
        );

        amountLabel.setText(
                "Total Amount: ₹" + totalAmount
        );
    }

    // Count records in file
    int countRecords(String fileName) {

        int count = 0;

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(fileName)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    count++;
                }
            }

            reader.close();

        } catch (IOException e) {

            return 0;
        }

        return count;
    }

    // Calculate total payment amount
    double calculateAmount() {

        double total = 0;

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

                    try {

                        double amount =
                                Double.parseDouble(
                                        payment[3]
                                );

                        total = total + amount;

                    } catch (NumberFormatException e) {

                        // Ignore invalid amount
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            return 0;
        }

        return total;
    }

    public static void main(String[] args) {

        new Reports();
    }
}