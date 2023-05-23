import com.example.apcles.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import Class.passwordHash;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;


class ConnexionControllerTest {

    @Test
    void connexion() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ap-clefs", "root", "")) {
            passwordHash hash = new passwordHash();
            ResultSet results;
            String sql = "SELECT motDePasse FROM comptes WHERE nomUtilisateur = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "testUser");
            results = stmt.executeQuery();
            while (results.next()) {
                assertEquals(true, hash.verify("motdepasse", results.getString(1)));
                assertEquals(false, hash.verify("notdepasse", results.getString(1)));
            }

            stmt = con.prepareStatement(sql);
            stmt.setString(1, "testUserssssssssssssss");
            results = stmt.executeQuery();
            assertEquals(false, results.next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}