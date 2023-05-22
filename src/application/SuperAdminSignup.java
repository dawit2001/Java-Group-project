package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import database.databaseconnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;


public class SuperAdminSignup implements Initializable ,EventHandler<ActionEvent>{

    @FXML
    private Button btn_signup;

    @FXML
    private PasswordField psf_confirmpass, psf_password;

    @FXML
    private TextField txf_firstName, txf_lastname, txf_username;
        
    
    String query1 = "INSERT INTO Admin VALUE (?,?,?,?)";
    String query2 = "INSERT INTO Users VALUE (?,?,?,?)";
    
    
    
    @Override 
    public void initialize (URL url,ResourceBundle rb) {
    	btn_signup.setOnAction(this);
    	
    	
    }
    @Override
   public void handle (ActionEvent event) {
    	
    	if(txf_firstName.getText().equals("")||txf_lastname.getText().equals("")
    			||psf_password.getText().equals("")||psf_confirmpass.getText().equals("")) {
    		Alert alert = new Alert (AlertType.ERROR);
    		alert.setContentText("please fill all the input fields");
    		alert.setHeaderText(null);
    		alert.setWidth(200);
    		alert.setHeight(100);
    		alert.setTitle("Input Error");
    		alert.showAndWait();    		
    		System.out.println(alert.getContentText());
    		
    	}
    	else if(!psf_password.getText().equals(psf_confirmpass.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Password doesn't match");
    		alert.setHeaderText(null);
    		alert.setWidth(200);
    		alert.setHeight(100);
    		alert.setTitle("password error");
    		alert.showAndWait();   
    		
    	}
    	else {
    		try {
    			String name = txf_firstName.getText()+" "+txf_lastname.getText();
    			PreparedStatement stat1 = databaseconnector.con.prepareStatement(query1);
    			PreparedStatement stat2 = databaseconnector.con.prepareStatement(query2);
    			stat1.setString(1, "SAD001");
    			stat1.setString(2, name);
    			stat1.setString(3, txf_username.getText());
    			stat1.setString(4, psf_confirmpass.getText());
    			stat1.executeUpdate();
    			stat2.setString(1, "SAD001");
    			stat2.setString(2, txf_username.getText());
    			stat2.setString(3, "superadmin");
    			stat2.setString(4,psf_confirmpass.getText());
    			stat2.executeUpdate();
    			System.out.println("successful");
    			Main.SceneFactory("fxml/Login");
    			
    		} catch (SQLException | IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    	}
    	
    }

}

