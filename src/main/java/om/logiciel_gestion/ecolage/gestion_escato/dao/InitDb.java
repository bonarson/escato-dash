package om.logiciel_gestion.ecolage.gestion_escato.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDb {
    public InitDb(Connection conn) {
        //============= admin ===========
        String checkTableExistQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='admin'";
        String createTableQuery = "create table IF NOT EXISTS admin(\n" +
                "id_admin  integer primary key,\n" +
                "user_name varchar(200),\n" +
                "email varchar(200),\n" +
                "password varchar(200)\n" +
                ")";
        String initDataQuery = "insert into admin(user_name,email,password)values\n" +
                "('Bonarson','hei.bonarson@gmail.com','mot@pass6')";
        //============= admin ===========

        //============= pin===========
        String checkTablePinExistQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='pin_code'";

        String createTablePinQuery = "create table IF NOT EXISTS pin_code(\n" +
                "id_pin integer primary key,\n" +
                "pin_pass varchar(100)\n" +
                ")";

        String initDataPinQuery = "insert into pin_code(pin_pass)values('22104')";
        //============= pin===========

        //============= Student=======

        String checkTableStudentExistQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='student'";

        String createTableStudentQuery = "CREATE TABLE IF NOT EXISTS student (\n" +
                "id_student VARCHAR(200) primary key,\n" +
                "last_name VARCHAR(200),\n" +
                "first_name VARCHAR(200),\n" +
                "date_of_birth VARCHAR(200),\n" +
                "gender varchar(200),\n" +
                "grade VARCHAR(200),\n" +
                "num int,\n" +
                "enrollment_date DATE DEFAULT CURRENT_DATE,\n" +
                "amount FLOAT," +
                "status varchar(200)\n" +
                ");";

        String initDataStudentQuery = "INSERT INTO student (id_student, last_name, first_name, date_of_birth,gender, grade,num,amount,status) \n" +
                "VALUES ('123', 'Doe', 'John', '08-04-2002','M', 'A1',20,5000,'Inscription')";

        //============= Student=======
        String checkTableEcolageExistQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='ecolage'";
        String createTableEcolageQuery = "CREATE TABLE ecolage (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    amount FLOAT,\n" +
                "    payment_method VARCHAR(225),\n" +
                "    contact varchar(255),\n" +
                "    month VARCHAR(225),\n" +
                "    date_and_time TIMESTAMP DEFAULT (date('now')),\n" +
                "    id_student VARCHAR(225),\n" +
                "    FOREIGN KEY (id_student) REFERENCES student(id_student) ON DELETE CASCADE\n" +
                ");";
        String initDataEcolageQuery = "INSERT INTO ecolage (amount, payment_method,contact, month, id_student) \n" +
                "VALUES (500.00, 'Cash','0343787792', 'January', '123');";

        //=============Ecolage========


        //============================


        try (Statement stmt = conn.createStatement()) {
            // Check if table exists
            ResultSet rs = stmt.executeQuery(checkTableExistQuery);
            if (!rs.next()) {
                // Table does not exist, create it
                stmt.executeUpdate(createTableQuery);
                System.out.println("Table admin created ");
                // Insert initial data
                stmt.executeUpdate(initDataQuery);
                System.out.println("Table initial admin data inserted.");
            } else {
                System.out.println("Table admin already exists. No action taken.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (Statement stmt = conn.createStatement()) {
            // Check if table exists
            ResultSet rs = stmt.executeQuery(checkTablePinExistQuery);
            if (!rs.next()) {
                // Table does not exist, create it
                stmt.executeUpdate(createTablePinQuery);
                System.out.println("Table pin created ");
                // Insert initial data
                stmt.executeUpdate(initDataPinQuery);
                System.out.println("Table initial pin data inserted.");
            } else {
                System.out.println("Table pin already exists. No action taken.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (Statement stmt = conn.createStatement()) {
            // Check if table exists
            ResultSet rs = stmt.executeQuery(checkTableStudentExistQuery);
            if (!rs.next()) {
                // Table does not exist, create it
                stmt.executeUpdate(createTableStudentQuery);
                System.out.println("Table student created ");
                // Insert initial data
                stmt.executeUpdate(initDataStudentQuery);
                System.out.println("Table initial student data inserted.");
            } else {
                System.out.println("Table student already exists. No action taken.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (Statement stmt = conn.createStatement()) {
            // Check if table exists
            ResultSet rs = stmt.executeQuery(checkTableEcolageExistQuery);
            if (!rs.next()) {
                // Table does not exist, create it
                stmt.executeUpdate(createTableEcolageQuery);
                System.out.println("Table ecolage created ");
                // Insert initial data
                stmt.executeUpdate(initDataEcolageQuery);
                System.out.println("Table initial ecolage data inserted.");
            } else {
                System.out.println("Table ecolage already exists. No action taken.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
