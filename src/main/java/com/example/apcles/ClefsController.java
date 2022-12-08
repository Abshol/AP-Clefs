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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Class.clef;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class ClefsController {
    @FXML
    public ListView listKey;
    @FXML
    public Button refresh;
    @FXML
    private Button deconnexion;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    private ObservableList<clef> items;
    @FXML
    private TableView<clef> tableKey = new TableView<clef>();
    @FXML
    private Button modifier;
    @FXML
    private Button suprimer;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;

    protected void insertData(boolean columnCreate) {
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
            if (columnCreate) {
                TableColumn nomCol = new TableColumn("Nom");
                nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
                nomCol.setPrefWidth(150.33336639404297);

                TableColumn couleurCol = new TableColumn("Couleur");
                couleurCol.setCellValueFactory(new PropertyValueFactory("nomCouleur"));
                couleurCol.setPrefWidth(150.33336639404297);

                TableColumn ouvrirCol = new TableColumn("Qu'est-ce qu'elle ouvre");
                ouvrirCol.setCellValueFactory(new PropertyValueFactory("ouvrir"));
                ouvrirCol.setPrefWidth(375);
                tableKey.setItems(items);
                tableKey.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                tableKey.getColumns().addAll(nomCol, couleurCol, ouvrirCol);
            }
            else {
                tableKey.setItems(items);
                tableKey.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void initialize() {
        insertData(true);
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
        scene.setTitle("Ajouter une clef");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
    @FXML
    protected void supprimer() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Oui");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Non");


        alert.setTitle("Supprimer la clef");
        alert.setContentText("Êtes-vous sur ?");
        System.out.println(alert.getButtonTypes());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            System.out.println("test");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")) {
                String sql = "DELETE FROM `clef` WHERE clef.id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, tableKey.getSelectionModel().getSelectedItem().getId());
                stmt.execute();
                refresh();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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
    protected void refresh() {
        insertData(false);
    }
    @FXML
    public void search() {
        String searchText = searchBar.getText().toLowerCase();

        FilteredList<clef> filteredList = new FilteredList<>(items, p -> true);

        filteredList.setPredicate(clef -> {
            return clef.getNom().toLowerCase().contains(searchText)
                    || clef.getOuvrir().toLowerCase().contains(searchText)
                    || clef.getNomCouleur().toLowerCase().contains(searchText);
        });

        SortedList<clef> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(tableKey.comparatorProperty());

        tableKey.setItems(sortedList);
    }

}
