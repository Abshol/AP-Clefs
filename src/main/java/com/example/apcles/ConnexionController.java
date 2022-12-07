package com.example.apcles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import Class.clef;
import java.io.IOException;
import java.sql.*;
import Class.passwordHash;

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
    private TableView<clef> tableKey =  new TableView<>();
    @FXML
    protected void connexion() throws IOException {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ap-clefs", "root", "")){
            passwordHash hash = new passwordHash();
            ResultSet results;
            String sql = "SELECT motDePasse FROM comptes WHERE nomUtilisateur = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username.getText());
            results = stmt.executeQuery();
            while (results.next()){
                if (hash.verify(password.getText(), results.getString(1))){
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
    }
}