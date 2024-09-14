package om.logiciel_gestion.ecolage.gestion_escato.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {
    public Connection connDb() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:dbescato.db");
            Statement stmt = conn.createStatement();
            stmt.execute("PRAGMA foreign_keys = ON;");
            if (conn != null) {
                System.out.println("connection donne ! ");
                new InitDb(conn);
                System.out.println("Database connected and table checked/created.");
            } else {
                System.out.println("error path");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
