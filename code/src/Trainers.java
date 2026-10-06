import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Trainers extends JFrame {

    JTextField idField;
    JTextField nameField;
    JTextField ageField;
    JTextField phoneField;
    JTextField specializationField;

    JTextArea displayArea;

    public Trainers() {

        setTitle("Gym Management System - Trainers");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel title = new JLabel("TRAINER MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(220, 20, 300, 30);
        panel.add(title);

        // Trainer ID
        JLabel idLabel = new JLabel("Trainer ID:");
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

        // Specialization
        JLabel specializationLabel =
                new JLabel("Specialization:");

        specializationLabel.setBounds(50, 240, 100, 25);
        panel.add(specializationLabel);

        specializationField = new JTextField();
        specializationField.setBounds(160, 240, 180, 25);
        panel.add(specializationField);

        // Add button
        JButton addButton = new JButton("Add Trainer");
        addButton.setBounds(380, 80, 130, 35);
        panel.add(addButton);

        // View button
        JButton viewButton = new JButton("View Trainers");
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

        JScrollPane scrollPane =
                new JScrollPane(displayArea);

        scrollPane.setBounds(50, 300, 580, 200);
        panel.add(scrollPane);

        // Button actions
        addButton.addActionListener(e -> addTrainer());

        viewButton.addActionListener(e -> viewTrainers());

        searchButton.addActionListener(e -> searchTrainer());

        deleteButton.addActionListener(e -> deleteTrainer());

        add(panel);
        setVisible(true);
    }

    // Add Trainer
    void addTrainer() {

        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String phone = phoneField.getText();
        String specialization =
                specializationField.getText();

        if (id.isEmpty() || name.isEmpty()
                || age.isEmpty() || phone.isEmpty()
                || specialization.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            FileWriter writer =
                    new FileWriter(
                            "data/trainers.txt",
                            true
                    );

            writer.write(
                    id + "," +
                    name + "," +
                    age + "," +
                    phone + "," +
                    specialization + "\n"
            );

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Trainer Added Successfully!"
            );

            clearFields();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving trainer!"
            );
        }
    }

    // View Trainers
    void viewTrainers() {

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/trainers.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] trainer = line.split(",");

                if (trainer.length == 5) {

                    displayArea.append(
                            "ID: " + trainer[0]
                            + " | Name: " + trainer[1]
                            + " | Age: " + trainer[2]
                            + " | Phone: " + trainer[3]
                            + " | Specialization: "
                            + trainer[4]
                            + "\n"
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading trainers!"
            );
        }
    }

    // Search Trainer
    void searchTrainer() {

        String searchId = idField.getText();

        if (searchId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Trainer ID to search!"
            );

            return;
        }

        displayArea.setText("");

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/trainers.txt"
                            )
                    );

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {

                String[] trainer = line.split(",");

                if (trainer.length == 5
                        && trainer[0].equals(searchId)) {

                    displayArea.append(
                            "ID: " + trainer[0] + "\n"
                            + "Name: " + trainer[1] + "\n"
                            + "Age: " + trainer[2] + "\n"
                            + "Phone: " + trainer[3] + "\n"
                            + "Specialization: "
                            + trainer[4]
                    );

                    found = true;
                    break;
                }
            }

            reader.close();

            if (!found) {

                displayArea.setText(
                        "Trainer not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching trainer!"
            );
        }
    }

    // Delete Trainer
    void deleteTrainer() {

        String deleteId = idField.getText();

        if (deleteId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Trainer ID to delete!"
            );

            return;
        }

        try {

            File inputFile =
                    new File("data/trainers.txt");

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

                String[] trainer = line.split(",");

                if (trainer.length == 5
                        && trainer[0].equals(deleteId)) {

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
                        "Trainer Deleted Successfully!"
                );

                displayArea.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Trainer not found!"
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting trainer!"
            );
        }
    }

    // Clear fields
    void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        specializationField.setText("");
    }

    public static void main(String[] args) {

        new Trainers();
    }
}