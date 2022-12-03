package com.example.apcles;

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
    protected void connexion() throws IOException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ap-clefs", "root", "")){
            ResultSet results;
            String sql = "SELECT COUNT(*) FROM comptes WHERE nomUtilisateur = ? and motDePasse = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username.getText());
            stmt.setString(2, password.getText());
            results = stmt.executeQuery();
            while (results.next()){
                if (results.getInt(0) > 0){
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
        try (Connection con = DriverManager.getConnection("jdcb:mysql:ap-clefs//localhost:3306/", "root", "")){
            ListView<clef> listView = new ListView<>();
            ResultSet results;

            String sql = "SELECT * FROM clef";
            PreparedStatement stmt = con.prepareStatement(sql);
            results = stmt.executeQuery();
            while (results.next()){
                int id = results.getInt(0);
                String nom = results.getString(1);
                String ouvrir = results.getString(2);
                String nomCouleur = results.getString(3);
                clef clef = new clef(id, nom, ouvrir, nomCouleur);
                listView.getItems().add(clef);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}