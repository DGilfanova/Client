package controllers;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUIPreloadController extends Preloader {

    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/initPreloader.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Block Break");
        primaryStage.getIcons().add(new Image(getClass().getResource("/images/appIcon.png").toString()));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
