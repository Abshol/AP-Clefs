package com.example.apcles;

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
    private void ajouter() throws IOException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){
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


            sql = "INSERT INTO `clef`( `nom`, `ouvrir`, `nomCouleur`) VALUES (? , ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nomClef.getText());
            stmt.setString(2, descClef.getText());
            stmt.setString(3, couleurClef.getValue().toString());
            System.out.println(stmt);
            stmt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès !");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("La clef a bien été créée !");
            alert.showAndWait();

            Parent root = FXMLLoader.load(Start.class.getResource("clefs.fxml"));
            Stage scene = (Stage) annuler.getScene().getWindow();
            scene.setTitle("Gestionnaire de Clefs");
            scene.setScene(new Scene(root));
            scene.centerOnScreen();
    }

    @Test
    void initialize() {
    }
}