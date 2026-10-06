import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Login extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public Login() {

        setTitle("Gym Management System - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 225, 230));

        // Title
        JLabel title =
                new JLabel("GYM MANAGEMENT SYSTEM");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setForeground(
                new Color(120, 70, 85)
        );

        title.setBounds(100, 40, 350, 40);
        panel.add(title);

        // Login heading
        JLabel loginTitle =
                new JLabel("Admin Login");

        loginTitle.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        loginTitle.setBounds(190, 95, 150, 30);
        panel.add(loginTitle);

        // Username
        JLabel usernameLabel =
                new JLabel("Username:");

        usernameLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        usernameLabel.setBounds(80, 150, 100, 30);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(190, 150, 220, 30);
        panel.add(usernameField);

        // Password
        JLabel passwordLabel =
                new JLabel("Password:");

        passwordLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        passwordLabel.setBounds(80, 200, 100, 30);
        panel.add(passwordLabel);

        passwordField =
                new JPasswordField();

        passwordField.setBounds(190, 200, 220, 30);
        panel.add(passwordField);

        // Login button
        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        loginButton.setBackground(
                new Color(120, 70, 85)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setFocusPainted(false);

        loginButton.setBounds(190, 260, 220, 40);
        panel.add(loginButton);

        // Login action
        loginButton.addActionListener(e -> login());

        add(panel);

        setVisible(true);
    }

    // Login method
    void login() {

        String username =
                usernameField.getText();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password!"
            );

            return;
        }

        boolean valid = false;

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/users.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] user =
                        line.split(",");

                if (user.length >= 2
                        && user[0].equals(username)
                        && user[1].equals(password)) {

                    valid = true;
                    break;
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading user data!"
            );

            return;
        }

        if (valid) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!"
            );

            dispose();

            new Dashboard();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password!"
            );
        }
    }

    public static void main(String[] args) {

        new Login();
    }
}