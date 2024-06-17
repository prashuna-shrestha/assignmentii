package assignmentII;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DatabaseOperation {
    String url = "jdbc:mysql://localhost:3306/libraryDetails";
    String username = "root";
    String password = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeStudentData(Student student) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO students (firstName, lastName, gender, program, section) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getGender());
            statement.setString(4, student.getProgram());
            statement.setString(5, student.getSection());

            int n = statement.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Data saved", "Status", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data not saved", "Status", JOptionPane.INFORMATION_MESSAGE);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeBookData(Book book) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO books (authorName, title, publicationName, subject) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, book.getAuthorName());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getPublicationName());
            statement.setString(4, book.getSubject());

            int n = statement.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Data saved", "Status", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data not saved", "Status", JOptionPane.INFORMATION_MESSAGE);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeBookIssued(BookIssue bookIssue) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO bookIssue (studentName, bookIssued, issuedDate, dueDate) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, bookIssue.getStudentName());
            statement.setString(2, bookIssue.getBookIssued());
            statement.setDate(3, Date.valueOf(bookIssue.getIssuedDate()));
            statement.setDate(4, Date.valueOf(bookIssue.getDueDate()));

            int n = statement.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Data saved", "Status", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data not saved", "Status", JOptionPane.INFORMATION_MESSAGE);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getStudentBookData() {
        ArrayList<String[]> data = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String query = "SELECT s.firstName, s.lastName, s.gender, s.program, s.section, b.bookIssued " +
                           "FROM students s " +
                           "LEFT JOIN bookIssue b ON s.firstName = b.studentName"; // Join on the studentName and firstName for simplicity
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String[] row = {
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("gender"),
                    resultSet.getString("program"),
                    resultSet.getString("section"),
                    resultSet.getString("bookIssued")
                };
                data.add(row);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}

