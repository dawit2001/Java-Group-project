package application;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.databaseconnector;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable ,EventHandler <ActionEvent>{ 
	 @FXML
	    private Button btn_login;

	    @FXML
	    private Label lb_role;

	    @FXML
	    private PasswordField txf_password;

	    @FXML
	    private TextField txf_username;
	    String role;
	   
	
	    String query = "Select * from Users where Password = ? AND UserName = ?";
	    
	 
	  
	    @Override
	    public void initialize (URL url,ResourceBundle rb) {
	    	btn_login.setOnAction(this);
	    	
	    }
	    @Override 
	    public void handle (ActionEvent event) {
	    	if(txf_username.getText().equals("")||txf_password.getText().equals("")) {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("input error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("please fill all the fields");
	    		alert.showAndWait();
	    	}
	    	else {
	    	try {
				PreparedStatement stat1 = databaseconnector.con.prepareStatement(query);
				stat1.setString(1, txf_password.getText());
				stat1.setString(2,txf_username.getText());			    
			    ResultSet result = stat1.executeQuery();
			    
			    if(!result.next()) {
			    	System.out.println(txf_username.getText()+txf_password.getText());
			    	Alert alert = new Alert(AlertType.ERROR);
		    		alert.setTitle(null);
		    		alert.setHeaderText(null);
		    		alert.setContentText("Something wrong...\n please provide your correct username and password");
		    		alert.showAndWait();
			    }
			    else {
			    	Main.LogWriter(result.getString("UserName"), result.getString("Role"));
			    	switch(result.getString("Role")) {
			    	case "superadmin":
			    		Main.SceneFactory("fxml/SuperAdminPage");
			    		break;
			    	case "student":
			    	   Main.SceneFactory("fxml/StudentPage");
			    	   break;
			    	case "admin":
			    		Main.SceneFactory("fxml/AdminPage");
			    		break;
			    	case "employee":
			    		Main.SceneFactory("fxml/EmployeePage");
			    		break;
			    		
			    		
			    	  
			    	}
			    	
			    	
			    }
				
				
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }}
	    


}
