/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package studentpaymentsystempackage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author abel
 */
public class StudentPaymentSystem extends Application {
        public static Scene scene;
        private double x = 0;
        private double y = 0;
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginFxml.fxml"));
             
//        scene = new Scene(loadFXML("LoginFxml"));
          scene = new Scene(root);
          stage.setResizable(false);

        //scene.getStylesheets().add(getClass().getResource("loginfxml.css").toExternalForm());
        
        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            
            stage.setOpacity(.8);
        });
        
        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });
        
         //stage.initStyle(StageStyle.TRANSPARENT);
         Image image = new Image("image/tuition.png");
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }

      public static void sceneFactory(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().sizeToScene();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        return loader.load(StudentPaymentSystem.class.getResource(fxml+".fxml"));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
        
}    
