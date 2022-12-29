package com.guerra.util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class ShowAlertsUtil {

    public static void showInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        TextArea textArea = new TextArea();
        textArea.setText(content);
        textArea.setEditable(false);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
}
