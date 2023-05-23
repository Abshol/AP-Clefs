import javafx.collections.FXCollections;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

class ClefsControllerTest {

    @Test
    void insertData() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){

            ResultSet results;
            String sql = "SELECT * FROM clef";
            PreparedStatement stmt = con.prepareStatement(sql);
            results = stmt.executeQuery();
            assertEquals(true, results.next());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void supprimer() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")) {
            String sql = "INSERT INTO clef (id, nom, ouvrir, couleur) VALUES (0 , \"testDelete\", \"testDelete\", \"rouge\")";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();

            sql = "DELETE FROM `clef` WHERE id = 0";
            assertEquals(true, stmt.execute());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}