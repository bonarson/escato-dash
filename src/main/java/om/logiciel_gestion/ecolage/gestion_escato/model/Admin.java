package om.logiciel_gestion.ecolage.gestion_escato.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Admin {
    private String userName;
    private String email;
    private String password;

    public void addAdmin(Admin admin, Connection connection) {
        String sql = "INSERT INTO admin(user_name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, admin.getUserName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.executeUpdate();
            System.out.println("Admin added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding admin: " + e);
        }
    }
}
