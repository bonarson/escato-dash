package om.logiciel_gestion.ecolage.gestion_escato.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private String idStudent;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String gender;
    private String grade;
    private String number;
    //    private java.sql.Date enrollmentDate;
//    private String status;
    private String enrollmentDate;
    private double amount;
    private String status;

    public List<Student> readStudents(Connection connection) {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String idStudent = resultSet.getString("id_student");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String gender = resultSet.getString("gender");
                String grade = resultSet.getString("grade");
                String number = resultSet.getString("num");
//                String enrollmentDateString = resultSet.getString("enrollment_date");
//                java.sql.Date enrollmentDate = null;
                String enrollmentDat = resultSet.getString("enrollment_date");
                double amount = resultSet.getDouble("amount");
                String status = resultSet.getString("status");

                try {
                    if (enrollmentDat != null) {
                        java.util.Date parsedDate = dateFormat.parse(enrollmentDat);
                        enrollmentDat = String.valueOf(new Date(parsedDate.getTime()));
                    }
                } catch (ParseException e) {
                    throw new RuntimeException("Error parsing enrollment date: " + enrollmentDat, e);
                }

                Student student = new Student(idStudent, lastName, firstName, dateOfBirth, gender, grade, number, enrollmentDat, amount, status);
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading students from the database", e);
        }

        return students;
    }

    public List<Student> readStudentByIdStudent(Connection connection, String idStudent) {
        String sql = "SELECT * FROM student WHERE id_student = ?";
        List<Student> students = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student(null, null, null, null, null, null, null, null, 0, null);

                student.setIdStudent(resultSet.getString("id_student"));
                student.setLastName(resultSet.getString("last_name"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setDateOfBirth(resultSet.getString("date_of_birth"));
                student.setGender(resultSet.getString("gender"));
                student.setGrade(resultSet.getString("grade"));
                student.setNumber(resultSet.getString("num"));
                //                String enrollmentDateString = resultSet.getString("enrollment_date");
//                java.sql.Date enrollmentDate = null;
                String enrollmentDat = resultSet.getString("enrollment_date");
                student.setStatus(resultSet.getString("status"));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public List<Student> readStudentByClassName(Connection connection, String grade) {
        String sql = "SELECT * FROM student WHERE LOWER(grade) LIKE LOWER(?)";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + grade + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("id_student"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("gender"),
                        resultSet.getString("grade"),
                        resultSet.getString("num"),
//                        Date.valueOf(resultSet.getString("enrollment_date")),
                        resultSet.getString("enrollment_date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("status")
                );
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }


    public boolean deleteStudentByIdStudent(Connection connection, String idStudent) {
        String sql = "DELETE FROM student WHERE id_student=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idStudent);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student with ID " + idStudent + " was deleted successfully.");
                return true;
            } else {
                System.out.println("No student found with ID " + idStudent);
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting student with ID " + idStudent, e);
        }
    }

    public boolean updateStudent(Connection connection, String columnName, String value, String idStudent) {
        // Construire la requête SQL avec le nom de la colonne
        String sql = "UPDATE student SET " + columnName + " = ? WHERE id_student = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Définir les valeurs des paramètres
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, idStudent);

            // Exécuter la requête de mise à jour
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student with ID " + idStudent + " was updated successfully.");
                return true;
            } else {
                System.out.println("No student found with ID " + idStudent);
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating student with ID " + idStudent, e);
        }
    }

    public boolean addStudent(Connection connection, Student student) {
        String sql = "INSERT INTO student (id_student, last_name, first_name, date_of_birth, gender, grade, num,amount,status) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getIdStudent());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getDateOfBirth());
            preparedStatement.setString(5, student.getGender());
            preparedStatement.setString(6, student.getGrade());
            preparedStatement.setString(7, student.getNumber());
            preparedStatement.setDouble(8, student.getAmount() );
            preparedStatement.setString(9, student.getStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        try (Connection connection = db.connDb()) {
            Student student = new Student(null, null, null, null, null, null, null, null, 0, null);
            List<Student> students = student.readStudentByClassName(connection, "3em");
            for (Student s : students) {
                System.out.println(s);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
