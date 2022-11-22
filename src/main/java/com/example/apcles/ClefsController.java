package com.example.apcles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClefsController {
    @FXML
    private Button deconnexion;
    @FXML
    protected void deconnexion() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("connexion.fxml"));
        Stage scene = (Stage) deconnexion.getScene().getWindow();
        scene.setTitle("Connexion");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
}
