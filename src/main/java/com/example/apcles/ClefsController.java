package com.example.apcles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ClefsController {
    @FXML
    private Button deconnexion;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TableView tableKey;
    @FXML
    private Button modifier;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private ObservableList<clef> items = FXCollections.observableArrayList();
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

    @FXML
    public void search() {
        // Get the search text
        String searchText = searchBar.getText().toLowerCase();

        // Create a FilteredList that will be used to filter the TableView
        FilteredList<clef> filteredList = new FilteredList<>(items, p -> true);

        // Set the Predicate that will be used to filter the items in the TableView
        filteredList.setPredicate(clef -> {
            // Check if the search text matches the name, opening, or color of the key
            return clef.getNom().toLowerCase().contains(searchText)
                    || clef.getOuvrir().toLowerCase().contains(searchText)
                    || clef.getNomCouleur().toLowerCase().contains(searchText);
        });

        // Create a SortedList that will be used to sort the TableView
        SortedList<clef> sortedList = new SortedList<>(filteredList);

        // Set the comparator that will be used to sort the items in the TableView
        sortedList.comparatorProperty().bind(tableKey.comparatorProperty());

        // Update the items in the TableView
        tableKey.setItems(sortedList);
    }

}
