import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertEquals;

class AjouterControllerTest {

    @Test
    void ajouter() throws IOException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")) {
            //Testing key not existing
            String sql = "SELECT * FROM `clef` WHERE clef.nom = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "clef Test not exit");
            ResultSet results = stmt.executeQuery();
            assertEquals(false, results.next());

            //Testing key existing
            sql = "SELECT * FROM `clef` WHERE clef.nom = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "atatum0");
            results = stmt.executeQuery();
            assertEquals(true, results.next());


            sql = "INSERT INTO `clef`( `nom`, `ouvrir`, `nomCouleur`) VALUES (? , ? , ?)";
            stmt = con.prepareStatement(sql);
            String nomClef = "test";
            String descClef = "description Test";
            String couleurClef = "rouge";
            stmt.setString(1, nomClef);
            stmt.setString(2, descClef);
            stmt.setString(3, couleurClef);
            assertEquals(true, stmt.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}