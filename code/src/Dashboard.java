import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Gym Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 225, 230));

        // Header
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(120, 70, 85));
        header.setBounds(0, 0, 800, 100);
        mainPanel.add(header);

        // Title
        JLabel title =
                new JLabel("GYM MANAGEMENT SYSTEM");

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setForeground(Color.WHITE);
        title.setBounds(230, 20, 400, 40);
        header.add(title);

        // Welcome
        JLabel welcome =
                new JLabel("Welcome, Admin!");

        welcome.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        welcome.setForeground(Color.WHITE);
        welcome.setBounds(330, 60, 200, 25);
        header.add(welcome);

        // Section heading
        JLabel section =
                new JLabel("Management Dashboard");

        section.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        section.setBounds(270, 120, 300, 35);
        mainPanel.add(section);

        // Members button
        JButton membersButton =
                createButton("Members");

        membersButton.setBounds(100, 180, 250, 70);
        mainPanel.add(membersButton);

        membersButton.addActionListener(e -> {
            new Members();
        });

        // Trainers button
        JButton trainersButton =
                createButton("Trainers");

        trainersButton.setBounds(450, 180, 250, 70);
        mainPanel.add(trainersButton);

        trainersButton.addActionListener(e -> {
            new Trainers();
        });

        // Membership button
        JButton membershipButton =
                createButton("Membership");

        membershipButton.setBounds(100, 280, 250, 70);
        mainPanel.add(membershipButton);

        membershipButton.addActionListener(e -> {
            new Membership();
        });

        // Payments button
        JButton paymentsButton =
                createButton("Payments");

        paymentsButton.setBounds(450, 280, 250, 70);
        mainPanel.add(paymentsButton);

        paymentsButton.addActionListener(e -> {
            new Payments();
        });

        // Reports button
        JButton reportsButton =
                createButton("Reports");

        reportsButton.setBounds(100, 380, 250, 70);
        mainPanel.add(reportsButton);

        reportsButton.addActionListener(e -> {
            new Reports();
        });

        // Logout button
        JButton logoutButton =
                createButton("Logout");

        logoutButton.setBounds(450, 380, 250, 70);
        mainPanel.add(logoutButton);

        logoutButton.addActionListener(e -> {

            dispose();
            new Login();

        });

        add(mainPanel);

        setVisible(true);
    }

    // Create styled button
    JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        button.setBackground(
                new Color(255, 245, 247)
        );

        button.setForeground(
                new Color(90, 55, 65)
        );

        button.setFocusPainted(false);

        return button;
    }
}