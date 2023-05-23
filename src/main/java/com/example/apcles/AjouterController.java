package com.example.apcles;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterController implements Initializable {
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    @FXML
    private TextField nomClef;
    @FXML
    private ComboBox couleurClef;
    @FXML
    private TextArea descClef;
    @FXML
    private Label labelNom;
    @FXML
    private void ajouter() throws IOException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){
            String sql = "SELECT * FROM `clef` WHERE clef.nom = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomClef.getText());
            ResultSet results = stmt.executeQuery();
            if (results.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur !");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Erreur: Le nom de la clef est déjà enregistré");

                alert.showAndWait();
            }
            else {
                sql = "CALL `insert_key`(? , ?, ?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, nomClef.getText());
                stmt.setString(2, couleurClef.getValue().toString());
                stmt.setString(3, descClef.getText());
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
        } catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void annuler() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("clefs.fxml"));
        Stage scene = (Stage) annuler.getScene().getWindow();
        scene.setTitle("Gestionnaire de Clefs");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ap-clefs", "root", "")){
            ResultSet results;
            String sql = "SELECT * FROM couleur";
            PreparedStatement stmt = con.prepareStatement(sql);
            results = stmt.executeQuery();
            while (results.next()) {
                couleurClef.getItems().add(results.getString("nom"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}