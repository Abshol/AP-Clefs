package com.example.apcles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModiferController {
    @FXML
    private Button annuler;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox couleur;
    @FXML
    private TextArea ouvrir;
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
            stmt.setString(3, (String) couleur.getValue());
            //stmt.setInt(4, cible id);
            stmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
