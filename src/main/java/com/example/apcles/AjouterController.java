package com.example.apcles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class AjouterController {
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;

    @FXML
    private void ajouter() {
        try (Connection con = DriverManager.getConnection("jdcb:mysql://localhost:3306/")){
            ResultSet results;
            String sql =
        } catch (SQLException e){
            e.printStackTrace();
        }

        try (PreparedStatement updateSales = con.prepareStatement(updateString);
             PreparedStatement updateTotal = con.prepareStatement(updateStatement)) {
            con.setAutoCommit(false);
            for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) {
                updateSales.setInt(1, e.getValue().intValue());
                updateSales.setString(2, e.getKey());
                updateSales.executeUpdate();

                updateTotal.setInt(1, e.getValue().intValue());
                updateTotal.setString(2, e.getKey());
                updateTotal.executeUpdate();
                con.commit();
            }
        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch (SQLException excep) {
                    JDBCTutorialUtilities.printSQLException(excep);
                }
            }
        }
    }
    @FXML
    private void annuler() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("clefs.fxml"));
        Stage scene = (Stage) annuler.getScene().getWindow();
        scene.setTitle("Gestionnaire de Clefs");
        scene.setScene(new Scene(root));
        scene.centerOnScreen();
    }
}