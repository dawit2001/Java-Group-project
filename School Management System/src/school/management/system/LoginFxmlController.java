/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package school.management.system;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class LoginFxmlController implements Initializable {

	    @FXML
	    private Button admin_login_btn;

	    @FXML
	    private PasswordField admin_password;

	    @FXML
	    private AnchorPane admin_portal_page;

	    @FXML
	    private TextField admin_username;

	    @FXML
	    private AnchorPane form_page;

	    @FXML
	    private AnchorPane rollSelection_page;

	    @FXML
	    private ComboBox<String> select_role_btn;

	    @FXML
	    private Button student_lgoin_page_btn;

	    @FXML
	    private Button student_login_btn;

	    @FXML
	    private AnchorPane student_login_page;

	    @FXML
	    private PasswordField student_login_password;

	    @FXML
	    private TextField student_login_studentID;

	    @FXML
	    private AnchorPane student_portal_page;

	    @FXML
	    private Button student_signup_btn;

	    @FXML
	    private PasswordField student_signup_confirm_password;

	    @FXML
	    private AnchorPane student_signup_page;

	    @FXML
	    private Button student_signup_page_btn;

	    @FXML
	    private PasswordField student_signup_password;

	    @FXML
	    private TextField student_signup_studentID;

	    @FXML
	    private TextField student_signup_username;

	    @FXML
	    private Button teacher_login_btn;

	    @FXML
	    private AnchorPane teacher_login_page;

	    @FXML
	    private Button teacher_login_page_btn;

	    @FXML
	    private PasswordField teacher_login_password;

	    @FXML
	    private TextField teacher_login_teacherID;

	    @FXML
	    private AnchorPane teacher_portal_page;

	    @FXML
	    private Button teacher_signup_btn;

	    @FXML
	    private PasswordField teacher_signup_confirm_password;

	    @FXML
	    private AnchorPane teacher_signup_page;

	    @FXML
	    private Button teacher_signup_page_btn;

	    @FXML
	    private PasswordField teacher_signup_password;

	    @FXML
	    private TextField teacher_signup_teacherID;

	    @FXML
	    private TextField teacher_signup_username;


    public static String loginUsername;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private LocalDateTime currentDateTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userRoleList();
    }

    public void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void successMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void adminLogin() {

        String sql = "SELECT * FROM admin WHERE name = ? and password = ?";

        connect = DatabaseConnection.connectionDB();

        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
            errorMessage(" plase fill all blank space");

        } else {
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, admin_username.getText());
                prepare.setInt(2, Integer.parseInt(admin_password.getText()));
                result = prepare.executeQuery();

                if (result.next()) {
                   
                    //loginUsername = admin_username.getText();
                    Data.username = admin_username.getText();
                    System.out.println(Data.username);
                   try {
                	   
                	   Main.sceneFactory("DashboardFxml");}catch (Exception e) {
                	   System.out.println(e);
                	   
                   };

                    currentDateTime = LocalDateTime.now();
                    FileWriter fwr = new FileWriter("src/loghistory/adminlog.txt", true);
                    fwr.write("\nAdmin Name: " + admin_username.getText() + " logged Into: " + currentDateTime + "\nAnd\n");
                    fwr.close();

                } else {
                	System.out.println(result);

                    errorMessage("Wrong Username/Password\n");

                    admin_username.setText("");
                    admin_password.setText("");
                }

                result.close();
                connect.close();

            } catch (SQLException | IOException e) {
            	System.out.println(e);
            }
        }
    }

    @FXML
    public void teacherLogin() {
        String sql = "SELECT * FROM teacher WHERE teacherID = ? and password = ?";

        connect = DatabaseConnection.connectionDB();
        if(teacher_login_teacherID.equals(null)) {
        	   System.out.println("teacher_login_teacherId is null");
        }
        if (teacher_login_teacherID.getText().isEmpty() || teacher_login_password.getText().isEmpty()) {
            errorMessage(" plase fill all blank space");

        } else {
            try {
            	System.out.println(teacher_login_teacherID.getText());
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, teacher_login_teacherID.getText());
                prepare.setInt(2, Integer.parseInt(teacher_login_password.getText()));
                result = prepare.executeQuery();

                if (result.next()) {

//                    loginUsername = admin_username.getText();
//                    SchoolManagementSystem.sceneFactory("DashboardFXML");
                	 Data.username = result.getString("name");
                    Data.userID = teacher_login_teacherID.getText();
                    
                    Main.sceneFactory("TeacherDashboard");

                    currentDateTime = LocalDateTime.now();
                    FileWriter fwr = new FileWriter("src/loghistory/teacherlog.txt", true);
                    fwr.write("\nTeacher ID: " + teacher_login_teacherID.getText() + " logged into: " + currentDateTime + "\nAnd\n");
                    fwr.close();
                } else {

                    errorMessage("Wrong Username/Password\n");

                    teacher_login_teacherID.setText("");
                    teacher_login_password.setText("");
                }

                result.close();
                connect.close();

            } catch (SQLException | IOException e) {
            	System.out.println(e);
            }
        }
    }

    @FXML
    public void teacherSignup() throws SQLException {

        String sql = "UPDATE teacher SET password = ? WHERE teacherID = '" + teacher_signup_teacherID.getText()
                + "' and name = '" + teacher_signup_username.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        if (teacher_signup_teacherID.getText().isEmpty() || teacher_signup_username.getText().isEmpty()
                || teacher_signup_password.getText().isEmpty() || teacher_signup_confirm_password.getText().isEmpty()) {
            errorMessage(" Plase Fill All Blank Space");

        } else if (Integer.parseInt(teacher_signup_password.getText())
                != Integer.parseInt(teacher_signup_confirm_password.getText())) {
            errorMessage(" Password Confirmation Mismatch");
            teacher_signup_password.clear();
            teacher_signup_confirm_password.clear();

        } else {
            String checkTeacher = "SELECT teacherID from teacher WHERE teacherID = '" + teacher_signup_teacherID.getText()
                    + "' and name = '" + teacher_signup_username.getText() + "'";
            statement = connect.createStatement();
            result = statement.executeQuery(checkTeacher);

            if (result.next()) {
                String checkAccount = "SELECT * from teacher WHERE password IS NULL AND teacherID = '" + teacher_signup_teacherID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkAccount);

                if (result.next()) {

                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, Integer.parseInt(teacher_signup_password.getText()));

                    successMessage("Account created Successfully");
                    prepare.executeUpdate();

                    teacher_signup_teacherID.setText("");
                    teacher_signup_username.setText("");
                    teacher_signup_password.setText("");
                    teacher_signup_confirm_password.setText("");
                } else {
                    errorMessage("There Is An Account In Ihis Id: " + teacher_signup_teacherID.getText());
                }
            } else {
                errorMessage("There Is No Teacher In Ihis Id: " + teacher_signup_teacherID.getText());
            }
            result.close();
            connect.close();
        }

    }

    @FXML
    public void studentLogin() {
        String sql = "SELECT * FROM student WHERE studentID = ? and 	password = ?";

        connect = DatabaseConnection.connectionDB();

        if (student_login_studentID.getText().isEmpty() || student_login_password.getText().isEmpty()) {
            errorMessage(" plase fill all blank space");

        } else {
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, student_login_studentID.getText());
                prepare.setInt(2, Integer.parseInt(student_login_password.getText()));
                result = prepare.executeQuery();

                if (result.next()) {
                	 Data.username = result.getString("name");
                    Data.userID = student_login_studentID.getText();
                   Main.sceneFactory("StudentDashboard");

                    currentDateTime = LocalDateTime.now();
                    FileWriter fwr = new FileWriter("src/loghistory/studentlog.txt", true);
                    fwr.write("\nStudent ID: " + student_login_studentID.getText() + " logged into: " + currentDateTime + "\nAnd\n");
                    fwr.close();
                } else {

                    errorMessage("Wrong Username/Password\n");

                    student_login_studentID.setText("");
                    student_login_password.setText("");
                }

                result.close();
                connect.close();

            } catch (SQLException | IOException e) {
            	System.out.println(e);
            }
        }
    }

    @FXML
    public void studentSignup() throws SQLException {

        String sql = "UPDATE student SET password = ? WHERE studentID = '" + student_signup_studentID.getText()
                + "' and name = '" + student_signup_username.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        if (student_signup_studentID.getText().isEmpty() || student_signup_username.getText().isEmpty()
                || student_signup_password.getText().isEmpty() || student_signup_confirm_password.getText().isEmpty()) {
            errorMessage(" Plase Fill All Blank Space");

        } else if (Integer.parseInt(student_signup_password.getText())
                != Integer.parseInt(student_signup_confirm_password.getText())) {
            errorMessage(" Password Confirmation Mismatch");
            student_signup_password.clear();
            student_signup_confirm_password.clear();

        } else {
            String checkStudent = "SELECT studentID from student WHERE studentID = '" + student_signup_studentID.getText()
                    + "' and name = '" + student_signup_username.getText() + "'";
            statement = connect.createStatement();
            result = statement.executeQuery(checkStudent);

            if (result.next()) {
                String checkAccount = "SELECT * from student WHERE password IS NULL AND studentID = '" + student_signup_studentID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkAccount);

                if (result.next()) {

                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, Integer.parseInt(student_signup_password.getText()));

                    successMessage("Account Created Successfully!");
                    prepare.executeUpdate();

                    student_signup_studentID.setText("");
                    student_signup_username.setText("");
                    student_signup_password.setText("");
                    student_signup_confirm_password.setText("");
                } else {
                    errorMessage("There Is An Account In Ihis Id: " + student_signup_studentID.getText());
                }
            } else {
                errorMessage("There Is No Student In Ihis Id: " + student_signup_studentID.getText());
            }
            result.close();
            connect.close();
        }

    }

    @FXML
    private void switchForm(ActionEvent event) {

        if (event.getSource() == teacher_signup_page_btn) {
            teacher_login_page.setVisible(false);
            teacher_signup_page.setVisible(true);
        } else if (event.getSource() == teacher_login_page_btn) {
            teacher_login_page.setVisible(true);
            teacher_signup_page.setVisible(false);
        } else if (event.getSource() == student_signup_page_btn) {
            student_login_page.setVisible(false);
            student_signup_page.setVisible(true);
        } else if (event.getSource() == student_lgoin_page_btn) {
            student_login_page.setVisible(true);
            student_signup_page.setVisible(false);
        }
    }

    private String[] multiplyUser = {"Admin", "Teacher", "Student"};

    public void userRoleList() {

        ObservableList<String> listData = FXCollections.observableArrayList(Arrays.asList(multiplyUser));
        select_role_btn.setItems(listData);
    }

    @FXML
    public void switchRole() {

        if (select_role_btn.getSelectionModel().getSelectedItem().equals("Admin")) {
            admin_portal_page.setVisible(true);
            teacher_portal_page.setVisible(false);
            student_portal_page.setVisible(false);
        } else if (select_role_btn.getSelectionModel().getSelectedItem().equals("Teacher")) {
            admin_portal_page.setVisible(false);
            teacher_portal_page.setVisible(true);
            student_portal_page.setVisible(false);
        } else if (select_role_btn.getSelectionModel().getSelectedItem().equals("Student")) {
            admin_portal_page.setVisible(false);
            teacher_portal_page.setVisible(false);
            student_portal_page.setVisible(true);
        }

    }
}
