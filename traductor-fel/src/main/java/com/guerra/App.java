package com.guerra;

import com.guerra.util.ShowAlertsUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setOnCloseRequest(event -> {
            Alert alert = ShowAlertsUtil.showConfirmation("Cerrar aplicación", "¿Está seguro que desea cerrar la aplicación?");

            if (alert.getResult() == ButtonType.OK) {
                Platform.exit();
                return;
            }

            event.consume();

        });

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/FELView.fxml"));

        var scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}