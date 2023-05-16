/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package assignemntloginpage;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author abel
 */
public class LoginPage extends Application {
    
   private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginFXML"));
        //scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Login Page");
//        Image image = new Image("/images/android.png");
//        stage.getIcons().add(image);  //Set Icon for the App
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public static void sceneFactory(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().sizeToScene();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        return loader.load(LoginPage.class.getResource(fxml+".fxml"));
    }
}