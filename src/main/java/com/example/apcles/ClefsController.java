package com.example.apcles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Class.clef;
import java.io.IOException;
import java.sql.*;

public class ClefsController {
    @FXML
    public ListView listKey;
    @FXML
    private Button deconnexion;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<clef> tableKey;
    @FXML
    private ObservableList<clef> items = FXCollections.observableArrayList();
    @FXML
    private Button modifier;
    @FXML
    private Button suprimer;
    @FXML
    protected void initialize() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){

            ResultSet results;

            String sql = "SELECT * FROM clef";
            PreparedStatement stmt = con.prepareStatement(sql);
            results = stmt.executeQuery();
            while (results.next()){
                int id = results.getInt(1);
                String nom = results.getString(2);
                String ouvrir = results.getString(3);
                String nomCouleur = results.getString(4);
                clef clef = new clef(id, nom, ouvrir, nomCouleur);
                items.add(clef);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        tableKey.setItems(items);
    }
    @FXML
    protected void deconnexion() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("connexion.fxml"));
        Stage scene = (Stage) deconnexion.getScene().getWindow();
        scene.setTitle("Connexion");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
    @FXML
    protected void goToAjouter() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("ajouter.fxml"));
        Stage scene = (Stage) ajouter.getScene().getWindow();
        scene.setTitle("Ajouter une clé");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
    @FXML
    protected void supprimer() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer la clef");
        alert.setContentText("Êtes-vous sur ?");
        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == ButtonType.OK) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")) {
                    String sql = "DELETE FROM `clef` WHERE clef.id = ?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    //stmt.setString(1, listKey.select);
                    ResultSet results = stmt.executeQuery();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    @FXML
    protected void goToModifier() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("modifier.fxml"));
        Stage scene = (Stage) modifier.getScene().getWindow();
        scene.setTitle("Modifier une clé");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }

}
