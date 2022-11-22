package com.example.apcles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class ConnexionController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    protected void connexion() {
        if (username.getText().equals("admin") && password.getText().equals("motdepasse")) {
            welcomeText.setTextFill(Paint.valueOf("green"));
            welcomeText.setText("Vous êtes connectés");
        }
        else {
            welcomeText.setTextFill(Paint.valueOf("red"));
            welcomeText.setText("Le nom d'utilisateur ou le mot de passe rentré n'est pas correcte.");
        }

    }
}