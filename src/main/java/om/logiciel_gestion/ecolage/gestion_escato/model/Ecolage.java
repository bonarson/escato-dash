package om.logiciel_gestion.ecolage.gestion_escato.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Ecolage {
    private int id;
    private int amountEcolage;
    private String paymentMethod;
    private String contact;
    private String month;
    private String dateAndTime;
    private String idStudent;
    private String lastName;
    private String firstName;

    public boolean addEcolage(Connection connection, Ecolage ecolage) {
        String sql = "INSERT INTO ecolage (amount, payment_method,contact, month, id_student) \n" +
                "VALUES (?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ecolage.getAmountEcolage());
            preparedStatement.setString(2, ecolage.getPaymentMethod());
            preparedStatement.setString(3, ecolage.getContact());
            preparedStatement.setString(4, ecolage.getMonth());
            preparedStatement.setString(5, ecolage.getIdStudent());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ecolage> readEcolages(Connection connection) {
        String sql = "SELECT e.*, s.first_name, s.last_name\n" +
                "FROM ecolage e\n" +
                "INNER JOIN student s ON e.id_student = s.id_student;";
        List<Ecolage> ecolages = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int amountEcolage = resultSet.getInt("amount");
                String paymentMethod = resultSet.getString("payment_method");
                String contact = resultSet.getString("contact");
                String month = resultSet.getString("month");
                String dateAndTime = resultSet.getString("date_and_time");
                String idStudent = resultSet.getString("id_student");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");


                Ecolage ecolage = new Ecolage(id, amountEcolage, paymentMethod, contact, month, dateAndTime, idStudent, lastName, firstName);
                ecolages.add(ecolage);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ecolages;
    }


    public String sumEcolageTotal(Connection conn) {
        String sql = "SELECT SUM(amount) AS total_amount\n" +
                "FROM ecolage;";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return String.valueOf(rs.getDouble("total_amount"));
            } else {
                return "No data found";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String SumAmountInscri(Connection connection) {
        String sql = "SELECT SUM(amount) AS total_amount\n" +
                "FROM student where status='Inscription' ;";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return String.valueOf(rs.getDouble("total_amount"));
            } else {
                return "No data found";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String SumAmountReinscri(Connection connection) {
        String sql = "SELECT SUM(amount) AS total_amount\n" +
                "FROM student where status='Réinscription' ;";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return String.valueOf(rs.getDouble("total_amount"));
            } else {
                return "No data found";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String SumAmountRegister(Connection connection) {
        String sql = "SELECT SUM(amount) AS total_amount\n" +
                "FROM student ;";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return String.valueOf(rs.getDouble("total_amount"));
            } else {
                return "No data found";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String sumEcolageTotalNow(Connection conn) {
        String sql = "SELECT SUM(amount) AS total_amount FROM ecolage where date_and_time = date('now');";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return String.valueOf(rs.getDouble("total_amount"));
            } else {
                return "No data found";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String dateNow(Connection connection) {
        String sql = "select date('now');";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return String.valueOf(rs.getString("date('now')"));
            } else {
                return "No data found";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Ecolage> readEcolageByIdStudent(Connection connection, String idStudent) {
        String sql = "SELECT e.*, s.first_name, s.last_name\n" +
                "FROM ecolage e\n" +
                "INNER JOIN student s ON e.id_student = s.id_student\n" +
                "WHERE e.id_student=?";
        List<Ecolage> ecolages = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int amountEcolage = resultSet.getInt("amount");
                String paymentMethod = resultSet.getString("payment_method");
                String contact = resultSet.getString("contact");
                String month = resultSet.getString("month");
                String dateAndTime = resultSet.getString("date_and_time");
                String idStudents = resultSet.getString("id_student");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                Ecolage ecolage = new Ecolage(id, amountEcolage, paymentMethod, contact, month, dateAndTime, idStudents, lastName, firstName);
                ecolages.add(ecolage);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ecolages;
    }
    public List<Ecolage> readEcolageByPaymentMethode(Connection connection, String paymentMethod){
        String sql = "SELECT e.*, s.first_name, s.last_name\n" +
                "FROM ecolage e\n" +
                "INNER JOIN student s ON e.id_student = s.id_student\n" +
                "WHERE e.payment_method=? ";

        List<Ecolage> ecolages = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, paymentMethod);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int amountEcolage = resultSet.getInt("amount");
                String paymentMethd = resultSet.getString("payment_method");
                String contact = resultSet.getString("contact");
                String month = resultSet.getString("month");
                String dateAndTime = resultSet.getString("date_and_time");
                String idStudents = resultSet.getString("id_student");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                Ecolage ecolage = new Ecolage(id, amountEcolage, paymentMethd, contact, month, dateAndTime, idStudents, lastName, firstName);
                ecolages.add(ecolage);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ecolages;

    }

    public boolean deleteEcolageByIdStudent(Connection connection, int id) {
        String sql = "DELETE FROM ecolage WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ecolage with ID " + id + " Student was deleted successfully.");
                return true;
            } else {
                System.out.println("No Ecolage found with ID Student : " + id);
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Ecolage with ID  " + id, e);
        }
    }


    public static void main(String[] args) {
        Ecolage ecolage = new Ecolage(0, 0, null, null, null, null, null, null, null);
        DbConnection db = new DbConnection();
//        System.out.println(ecolage.readEcolageByIdStudent(db.connDb(), "123"));
//        System.out.println(ecolage.sumEcolageTotal(db.connDb()));
//
//        System.out.println(ecolage.dateNow(db.connDb()));
//        System.out.println(ecolage.sumEcolageTotalNow(db.connDb()));

        System.out.println(ecolage.SumAmountInscri(db.connDb()));
        System.out.println(ecolage.SumAmountReinscri(db.connDb()));
        System.out.println(ecolage.SumAmountRegister(db.connDb()));

        System.out.println(ecolage.readEcolageByPaymentMethode(db.connDb(), "Cash"));
    }
}
