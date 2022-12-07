package com.example.apcles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Class.passwordHash;
import java.io.IOException;
import java.net.PasswordAuthentication;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("connexion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Connexion");
        stage.setScene(scene);
        stage.show();
        /*
        --------------- Uncomment this if you don't have the hashed password in your database --------------------
        passwordHash hash = new passwordHash();
        hash.hash("motdepasse", "admin");
         */
    }

    public static void main(String[] args) {
        launch();
    }
}