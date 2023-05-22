package application;
import database.databaseconnector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.*;


public class Main extends Application{
	public static Scene scene;
	@Override
	public void start(Stage primaryStage) throws SQLException, IOException {
		 final String query = "select * from Users where Role ='superadmin'";
			databaseconnector db = new databaseconnector();
			PreparedStatement stat = db.con.prepareStatement(query);
			  ResultSet result = stat.executeQuery();
			 
		
			  try {
				  if(!result.next()) {
					  scene= new Scene(LoadFxml("fxml/superAdminRegistration"));
					  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
				  }
			
				  else {scene= new Scene(LoadFxml("fxml/Login"));
		    
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
				  }} catch(Exception e) {
			e.printStackTrace();
		}
		}

   
	public static void SceneFactory(String fxml) throws IOException {
		scene.setRoot(LoadFxml(fxml));
		scene.getWindow().sizeToScene();
		
	}
	public  static Parent LoadFxml(String fxml) throws IOException {
	   return FXMLLoader.load(Main.class.getResource(fxml+".fxml"));
	   
   }
	public static void LogWriter (String username,String role) {
		String path = "/home/dawit/eclipse-workspace/Jdbc/logfile.txt";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String date = now.format(format);
		String formatedString = String.format("%-50s %-30s %-30s", username,role,date);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
			writer.write(formatedString);
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		public static void main(String[] args) throws SQLException, IOException   {  
		
			launch(args);		
	}
}
