package com.example.apcles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Class.clef;

import java.io.IOException;
import java.sql.*;

public class ModiferController {
    @FXML
    private Button annuler;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox couleur;
    @FXML
    private TextArea ouvrir;
    private ObservableList<clef> items;
    @FXML
    private TableView<clef> tableKey = new TableView<clef>();
    @FXML
    protected void initialize() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){

            ResultSet results;
            items = FXCollections.observableArrayList();
            String sql = "SELECT * FROM clef";
            PreparedStatement stmt = con.prepareStatement(sql);
            results = stmt.executeQuery();
            while (results.next()){
                //nom.setCellFactory(new PropertyValueFactory<>(results.getString(2)));
                int id = results.getInt(1);
                String nom = results.getString(2);
                String ouvrir = results.getString(3);
                String nomCouleur = results.getString(4);
                clef clef = new clef(id, nom, ouvrir, nomCouleur);
                items.addAll(clef);
            }
//            TableColumn idCol = new TableColumn("Id");
//            idCol.setCellFactory(new PropertyValueFactory("id"));
//            idCol.setPrefWidth(75.0);

            TableColumn nomCol = new TableColumn("Nom");
            nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            nomCol.setPrefWidth(76.33330535888672);

            TableColumn couleurCol = new TableColumn("Couleur");
            couleurCol.setCellValueFactory(new PropertyValueFactory("nomCouleur"));
            couleurCol.setPrefWidth(116.66659545898438);

            TableColumn ouvrirCol = new TableColumn("Qu'est-ce qu'elle ouvre");
            ouvrirCol.setCellValueFactory(new PropertyValueFactory("ouvrir"));
            ouvrirCol.setPrefWidth(259.00001525878906);

            tableKey.setItems(items);
            tableKey.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            tableKey.getColumns().addAll(nomCol, couleurCol, ouvrirCol);

            sql = "SELECT * FROM couleur";
            stmt = con.prepareStatement(sql);
            results = stmt.executeQuery();
            while (results.next()) {
                couleur.getItems().add(results.getString("nom"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void retour() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("clefs.fxml"));
        Stage scene = (Stage) annuler.getScene().getWindow();
        scene.setTitle("Gestionnaire de clefs");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
    @FXML
    protected void modifier() throws IOException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){

            String sql = "UPDATE `clef` SET `nom` = ?, `ouvrir` = ?, `nomCouleur` = ? WHERE `clef`.`id` = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nom.getText());
            stmt.setString(2, ouvrir.getText());
            stmt.setString(3, couleur.getValue().toString());
            stmt.setInt(4, tableKey.getSelectionModel().getSelectedItem().getId());
            if (!stmt.execute()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès !");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("La clef a bien été modifiée !");
                alert.showAndWait();
                retour();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur !");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Une erreur est survenue lors de la modification de la clef.");
                alert.showAndWait();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
