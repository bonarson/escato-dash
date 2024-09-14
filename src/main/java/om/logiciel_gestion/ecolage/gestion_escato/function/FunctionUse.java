package om.logiciel_gestion.ecolage.gestion_escato.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionUse {

    public boolean authenticateAccount(String email, String password, Connection connection) {
        String query = "SELECT * FROM admin WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return password.equals(resultSet.getString("password"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean checkPin(Connection connection, String pinCode) {
        String query = " select * from pin_code where pin_pass=? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pinCode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return pinCode.equals(resultSet.getString("pin_pass"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    public boolean changePinCode(Connection connection, String oldPin, String newPin) {
        String query = " update  pin_code set pin_pass=? where pin_pass=? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPin);
            preparedStatement.setString(2, oldPin);

            int rowsUpdated = preparedStatement.executeUpdate();


            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
