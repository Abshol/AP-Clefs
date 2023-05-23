import javafx.scene.control.Alert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


class ModiferControllerTest {

    @Test
    void modifier() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){
            String sql = "INSERT INTO `clef`(`id`, `nom`, `ouvrir`, `nomCouleur`) VALUES (?, ? , ? , ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            String nomClef = "testUpdate";
            String descClef = "description Test";
            String couleurClef = "rouge";
            stmt.setInt(1, 100000);
            stmt.setString(2, nomClef);
            stmt.setString(3, descClef);
            stmt.setString(4, couleurClef);
            stmt.execute();

            sql = "UPDATE `clef` SET `nom` = ?, `ouvrir` = ?, `nomCouleur` = ? WHERE `clef`.`id` = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "testKey");
            stmt.setString(2, "Ouvre le test");
            stmt.setString(3, "bleu");
            stmt.setInt(4, 100000);
            assertEquals(false, !stmt.execute());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}