package com.example.apcles;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private void ajouter() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){
            String sql = "INSERT INTO `clef`( `nom`, `ouvrir`, `nomCouleur`) VALUES (? , ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomClef.getText());
            stmt.setString(2, descClef.getText());
            stmt.setString(3, couleurClef.getValue().toString());
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
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
            ObservableList<String> options = FXCollections.emptyObservableList();
            while (results.next()) {
                options.add(results.getString(0));
            }
            couleurClef.getItems().add(options);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}