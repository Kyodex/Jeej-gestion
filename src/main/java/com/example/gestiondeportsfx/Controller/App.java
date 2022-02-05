package com.example.gestiondeportsfx.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private AnchorPane mainPanel;
    public static Scene mainScene;
    public Stage mainStage;
    private static final int HEIGHT =734;
    private static final int WIDTH =983;


    public void start(Stage stage) throws IOException {
        mainScene = new Scene(loadFXML("home"),WIDTH,HEIGHT);
        stage.setScene(mainScene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        mainScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/gestiondeportsfx/"+fxml+".fxml"));
        return loader.load();
    }

}
