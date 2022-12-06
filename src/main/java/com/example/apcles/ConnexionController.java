package com.example.apcles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ConnexionController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button connexion;
    @FXML
    private ListView<clef> listKey =  new ListView<>();
    @FXML
    protected void connexion() throws IOException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ap-clefs", "root", "")){
            ResultSet results;
            String sql = "SELECT COUNT(*) FROM comptes WHERE nomUtilisateur = ? and motDePasse = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username.getText());
            stmt.setString(2, password.getText());
            results = stmt.executeQuery();
            while (results.next()){
                if (results.getInt(1) > 0){
                    Parent root = FXMLLoader.load(Start.class.getResource("clefs.fxml"));
                    Stage scene = (Stage) connexion.getScene().getWindow();
                    scene.setTitle("gestionnaire de clefs");
                    scene.setScene(new Scene(root));
                    scene.centerOnScreen();
                }
                else {
                    welcomeText.setTextFill(Paint.valueOf("red"));
                    welcomeText.setText("Le nom d'utilisateur ou le mot de passe rentré n'est pas correcte.");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap-clefs", "root", "")){
            ObservableList<clef> items = FXCollections.observableArrayList();
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
            listKey.setItems(items);
            listKey.refresh();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}