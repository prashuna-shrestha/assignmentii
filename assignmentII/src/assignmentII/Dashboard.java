package assignmentII;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Dashboard {
    JTextField txtfn;
    JTextField txtln;
    JRadioButton radioMale;
    JRadioButton radioFemale;
    JButton addStudent;
    JComboBox<String> comboProgram;
    JComboBox<String> comboSection;
    JComboBox<String> comboStudent;
    JComboBox<String> comboIssued;
    JTextField issuedDateField;
    JTextField dueDateField;
    JTextField authorNameField;
    JTextField titleField;
    JTextField publicationNameField;
    JTextField subjectField;
    JTable recordTable;
    DefaultTableModel tableModel;
    DatabaseOperation dbOperation;

    public Dashboard() {
        dbOperation = new DatabaseOperation();
        JFrame frame = new JFrame("Library Management System");
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adding student tab
        JPanel studentPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        // Creating components
        JLabel fn = new JLabel("First Name:");
        JLabel ln = new JLabel("Last Name:");
        JLabel g = new JLabel("Gender:");
        JLabel p = new JLabel("Program:");
        JLabel s = new JLabel("Section:");

        txtfn = new JTextField(10);
        txtln = new JTextField(10);

        radioMale = new JRadioButton("Male");
        radioFemale = new JRadioButton("Female");

        // Adding radio buttons to a ButtonGroup
        ButtonGroup rg = new ButtonGroup();
        rg.add(radioMale);
        rg.add(radioFemale);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel to hold gender radio buttons
        genderPanel.add(radioMale);
        genderPanel.add(radioFemale);

        comboProgram = new JComboBox<>();
        comboProgram.addItem("BBA");
        comboProgram.addItem("BBA-TT");
        comboProgram.addItem("BCIS");
        comboProgram.addItem("BBA-BI");

        comboSection = new JComboBox<>();
        comboSection.addItem("Garnet/Grit");
        comboSection.addItem("intel");
        comboSection.addItem("fusion");
        comboSection.addItem("hypervisior");

        addStudent = new JButton("Add Student");
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = txtfn.getText();
                String lastName = txtln.getText();
                String gender = radioMale.isSelected() ? "Male" : "Female";
                String program = comboProgram.getSelectedItem().toString();
                String section = comboSection.getSelectedItem().toString();

                Student student = new Student(firstName, lastName, gender, program, section);
                dbOperation.writeStudentData(student);
            }
        });

        // Adding components to studentPanel
        studentPanel.add(fn);
        studentPanel.add(txtfn);
        studentPanel.add(ln);
        studentPanel.add(txtln);
        studentPanel.add(g);
        studentPanel.add(genderPanel); // Add the panel holding radio buttons
        studentPanel.add(p);
        studentPanel.add(comboProgram);
        studentPanel.add(s);
        studentPanel.add(comboSection);
        studentPanel.add(new JLabel("")); // Empty cell
        studentPanel.add(addStudent); // Add the button at the bottom left

        // Adding studentPanel to tabbedPane
        tabbedPane.addTab("Add Student", studentPanel);

        // Adding book tab
        JPanel addBookPanel = new JPanel(new GridLayout(5, 2));
        authorNameField = new JTextField();
        titleField = new JTextField();
        publicationNameField = new JTextField();
        subjectField = new JTextField();

        addBookPanel.add(new JLabel("Author Name:"));
        addBookPanel.add(authorNameField);
        addBookPanel.add(new JLabel("Title:"));
        addBookPanel.add(titleField);
        addBookPanel.add(new JLabel("Publication name:"));
        addBookPanel.add(publicationNameField);
        addBookPanel.add(new JLabel("Subject:"));
        addBookPanel.add(subjectField);
        JButton addBookButton = new JButton("Add Book");
        addBookPanel.add(new JLabel("")); // Empty cell
        addBookPanel.add(addBookButton);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authorName = authorNameField.getText();
                String title = titleField.getText();
                String publicationName = publicationNameField.getText();
                String subject = subjectField.getText();

                Book book = new Book(authorName, title, publicationName, subject);
                dbOperation.writeBookData(book);
            }
        });

        tabbedPane.addTab("Add Book", addBookPanel);

        // Adding issue book tab
        JPanel issueBookPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        issueBookPanel.add(new JLabel("Student Name:"));
        comboStudent = new JComboBox<>();
        comboStudent.addItem("preana");
        comboStudent.addItem("barsha");
        comboStudent.addItem("srijana");
        comboStudent.addItem("swastika");
        issueBookPanel.add(comboStudent);

        issueBookPanel.add(new JLabel("Book Issued:"));
        comboIssued = new JComboBox<>();
        comboIssued.addItem("java");
        comboIssued.addItem("IT");
        comboIssued.addItem("math");
        comboIssued.addItem("marketing");
        issueBookPanel.add(comboIssued);

        issueBookPanel.add(new JLabel("Issued Date:"));
        issuedDateField = new JTextField();
        issueBookPanel.add(issuedDateField);

        issueBookPanel.add(new JLabel("Due Date:"));
        dueDateField = new JTextField();
        issueBookPanel.add(dueDateField);

        JButton issueBookButton = new JButton("Issue Book");
        issueBookPanel.add(new JLabel("")); // Empty cell
        issueBookPanel.add(issueBookButton);

        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = comboStudent.getSelectedItem().toString();
                String bookIssued = comboIssued.getSelectedItem().toString();
                String issuedDate = issuedDateField.getText();
                String dueDate = dueDateField.getText();

                BookIssue bookIssue = new BookIssue(studentName, bookIssued, issuedDate, dueDate);
                dbOperation.writeBookIssued(bookIssue);
            }
        });


        tabbedPane.addTab("Issue Book", issueBookPanel);

        // Adding display record
        JPanel displayRecordPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"First Name", "Last Name", "Gender", "Program", "Section", "Book Taken"};
        tableModel = new DefaultTableModel(columnNames, 0);
        recordTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(recordTable);
        displayRecordPanel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 3) { // Assuming Display Record tab index is 3
                ArrayList<String[]> studentBookData = dbOperation.getStudentBookData();
                tableModel.setRowCount(0); // Clear existing rows
                for (String[] rowData : studentBookData) {
                    tableModel.addRow(rowData);
                }
            }
        });

        tabbedPane.addTab("Display Record", displayRecordPanel);

        frame.add(tabbedPane);
        frame.setSize(600, 400); // Adjust size as necessary
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}




