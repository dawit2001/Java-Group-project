
package assignemntloginpage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;


public class LoginFXMLController implements Initializable {

    @FXML
    private AnchorPane signin_form_page;
    @FXML
    private AnchorPane signup_page;
    @FXML
    private Button signup_page_btn;
    @FXML
    private TextField signin_username;
    @FXML
    private PasswordField signin_password;
    @FXML
    private Button login_btn;
    @FXML
    private AnchorPane signup_form_page;
    @FXML
    private TextField signup_username;
    @FXML
    private TextField signup_email;
    @FXML
    private PasswordField signup_password;
    @FXML
    private Button signin_page_btn;
    
    public static String loginUsername;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void switchPage(ActionEvent event) {

        if (event.getSource() == signup_page_btn) {
            signin_form_page.setVisible(false);
            signup_form_page.setVisible(true);
        }

        if (event.getSource() == signin_page_btn) {
            signin_form_page.setVisible(true);
            signup_form_page.setVisible(false);
        }
    }

    public void empty() {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }

    public void loginBtn() {

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = DatabaseConnection.connectionDB();

        Alert alert;
        if (signin_username.getText().isEmpty() || signin_password.getText().isEmpty()) {
            empty();

        } else {
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, signin_username.getText());
                prepare.setInt(2, Integer.parseInt(signin_password.getText()));
                result = prepare.executeQuery();

                if (result.next()) {
                     
                     loginUsername = signin_username.getText();          
                     LoginPage.sceneFactory("DashboardFXML");
                } else {
                    
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();

                    signin_username.setText("");
                    signin_password.setText("");
                }

                result.close();
                connect.close();

            } catch (SQLException | IOException e) {
            }
        }
    }

    public void signupBtn() throws SQLException {

        String sql = "INSERT INTO admin (username, email, password) VALUES(?,?,?)";

        connect = DatabaseConnection.connectionDB();

        Alert alert;
        if (signup_username.getText().isEmpty() || signup_email.getText().isEmpty() || signup_password.getText().isEmpty()) {
            empty();

        } else {

            String checkData = "SELECT username from admin WHERE username = '" + signup_username +"'";
            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username: " + signup_username.getText() + " was already taken!");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, signup_username.getText());
                prepare.setString(2, signup_email.getText());
                prepare.setInt(3, Integer.parseInt(signup_password.getText()));

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully create new account!");
                alert.showAndWait();

                prepare.executeUpdate();

                signup_username.setText("");
                signup_email.setText("");
                signup_password.setText("");
            }
        }
        result.close();
        connect.close();

    }

}
