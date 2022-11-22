package com.example.apcles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

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
        if (username.getText().equals("admin") && password.getText().equals("motdepasse")) {
            welcomeText.setTextFill(Paint.valueOf("green"));
            welcomeText.setText("Vous êtes connectés");
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
}