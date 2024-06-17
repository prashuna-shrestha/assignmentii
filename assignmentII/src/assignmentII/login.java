package assignmentII;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class login implements ActionListener {
    JTextField userNameField;
    JPasswordField passwordField;

    public login() {
        JFrame jf = new JFrame("Login");

        // Main panel with GridLayout for labels and fields
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Panel for buttons with FlowLayout to align buttons in a row
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Creating components
        JLabel jl = new JLabel("Username:");
        JLabel jlp = new JLabel("Password:");
        userNameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        JButton loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");
        JButton registerBtn = new JButton("Register");

        // Adding components to the main panel
        mainPanel.add(jl);
        mainPanel.add(userNameField);
        mainPanel.add(jlp);
        mainPanel.add(passwordField);

        // Adding buttons to the button panel
        buttonPanel.add(loginBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(registerBtn);

        // Adding main panel and button panel to the frame
        jf.setLayout(new BorderLayout());
        jf.add(mainPanel, BorderLayout.CENTER);
        jf.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        loginBtn.addActionListener(this);
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userNameField.setText("");
                passwordField.setText("");
            }
        });
        registerBtn.addActionListener(this);

        jf.setSize(400, 160);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName;
        String password;

        if (e.getActionCommand().equalsIgnoreCase("Register")) {
            try {
                Writer writer = new FileWriter("C:\\Users\\Prashuna\\eclipse-workspace\\assignmentII\\src\\assignmentII\\login.txt", true);
                userName = userNameField.getText();
                password = new String(passwordField.getPassword()); // get password as string
                writer.write(userName + "\t" + password + "\n");
                writer.flush();
                writer.close();
                
                JOptionPane.showMessageDialog(null, "Registration successful", "Status", JOptionPane.INFORMATION_MESSAGE);
                
                // Optional: Clear fields after successful registration
                userNameField.setText("");
                passwordField.setText("");
                
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred during registration", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Login")) {
            userName = userNameField.getText();
            password = new String(passwordField.getPassword());

            String uName;
            String pass;

            String line = null;
            boolean b = false;
            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Prashuna\\eclipse-workspace\\assignmentII\\src\\assignmentII\\login.txt"));
                line = reader.readLine();
                while (line != null) {
                    String[] credentials = line.split("\t");
                    uName = credentials[0];
                    pass = credentials[1];
                    if (userName.equals(uName) && password.equals(pass)) {
                        b = true;
                        break;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Login file not found", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading login file", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (b) {
                // Successful login
                JOptionPane.showMessageDialog(null, "Login successful", "Status", JOptionPane.INFORMATION_MESSAGE);
                //dashbord
                Dashboard db= new Dashboard();
                userNameField.setText("");
                passwordField.setText("");
                
            } else {
                JOptionPane.showMessageDialog(null, "Username and password didn't match", "Status", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
