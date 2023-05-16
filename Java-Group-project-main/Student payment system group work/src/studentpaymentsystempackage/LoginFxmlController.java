/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package studentpaymentsystempackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class LoginFxmlController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private TextField su_email;
    @FXML
    private PasswordField su_password;
    @FXML
    private Button su_signupBtn;
    @FXML
    private TextField su_username;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;
    @FXML
    private Button si_loginBtn;
    @FXML
    private AnchorPane sub_form;
    @FXML
    private Button sub_signupBtn;
    @FXML
    private Label edit_label;
    @FXML
    private Button sub_loginBtn;
    @FXML
    // private Label si_username_error;
    // @FXML
    // private Label si_pass_error;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void login() {

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, si_username.getText());
            prepare.setString(2, si_password.getText());
            result = prepare.executeQuery(); // if the input are match from the admin table it will retrun the data else
            // not return anything

            Alert alert;

            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {

                    Data.username = si_username.getText();

                    // si_loginBtn.getScene().getWindow().hide();
                    // LINK YOUR DASHBOARD FORM
                    StudentPaymentSystem.sceneFactory("DashboardFxml");

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
            result.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signup() {

        String sql = "INSERT INTO admin (username ,email ,password) VALUES(?,?,?)";

        connect = DatabaseConnection.connectionDB();

        try {
            Alert alert;

            if (su_email.getText().isEmpty() || su_username.getText().isEmpty()
                    || su_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (su_password.getText().length() < 2) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid password :3");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, su_username.getText());
                    prepare.setString(2, su_email.getText());
                    prepare.setString(3, su_password.getText());

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully create new account!");
                    alert.showAndWait();

                    prepare.executeUpdate();

                    su_email.setText("");
                    su_username.setText("");
                    su_password.setText("");
                }
            }
            result.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signupSlider() {

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(300);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            edit_label.setText("Login Account");

            sub_signupBtn.setVisible(false);
            sub_loginBtn.setVisible(true);

        });

    }

    public void loginSlider() {

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            edit_label.setText("Create Account");

            sub_signupBtn.setVisible(true);
            sub_loginBtn.setVisible(false);
        });

    }

}
