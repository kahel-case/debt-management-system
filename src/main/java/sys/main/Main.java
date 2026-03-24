package sys.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sys.database.DatabaseManager;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/sys/main/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Debt-tabase");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseManager.createTable();
        launch();
    }
}