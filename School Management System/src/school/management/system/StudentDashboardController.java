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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Button logout_btn;
    @FXML
    private AnchorPane mycourses_page;
    @FXML
    private Button mycourses_btn;
    @FXML
    private Button mystatus_btn;
    @FXML
    private TableView<CourseData> course_tableView;
    @FXML
    private TableColumn<CourseData, String> course_col_courseID;
    @FXML
    private TableColumn<CourseData, String> course_col_courseName;
    @FXML
    private TableColumn<CourseData, Integer> course_col_creditHour;
    @FXML
    private TableColumn<CourseData, Integer> course_col_year;
    @FXML
    private TableColumn<CourseData, String> course_col_coursCatagory;
    @FXML
    private AnchorPane mystatus_page;
    @FXML
    private TableView<GradeData> status_tableView;
    @FXML
    private TableColumn<GradeData, String> status_col_courseID;
    @FXML
    private TableColumn<GradeData, String> status_col_courseName;
    @FXML
    private TableColumn<GradeData, Double> status_col_quiz;
    @FXML
    private TableColumn<GradeData, Double> status_col_test;
    @FXML
    private TableColumn<GradeData, Double> status_col_project;
    @FXML
    private TableColumn<GradeData, Double> status_col_mid;
    @FXML
    private TableColumn<GradeData, Double> status_col_final;
    @FXML
    private TableColumn<GradeData, Double> status_col_total;
    @FXML
    private TableColumn<GradeData, Double> status_col_grade;

    @FXML
    private TableColumn<GradeData,Double> status_col_sgpa;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private String department;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	displayUsername();
        showCourseData();
        showGradeData();
    }
//      public String dept;

    public String getDepartment() {
        String sql = "SELECT department FROM student WHERE studentID ='" + Data.userID + "'";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                dept = result.getString("department");
            }
            result.close();
            connect.close();

        } catch (SQLException ex) {
        	System.out.println(ex);
        }
        return dept;
    }

    public String dept;

    public ObservableList<CourseData> CourseData() {

        dept = getDepartment();

        ObservableList<CourseData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course WHERE department = '" + dept + "' OR department = 'all'";

        connect = DatabaseConnection.connectionDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            CourseData cd;
            while (result.next()) {
                cd = new CourseData(result.getString("courseID"),
                        result.getString("courseName"),
                        result.getInt("creditHour"),
                        result.getInt("year"),
                        result.getString("courseCatagory"),
                        result.getString("department")
                );
                listData.add(cd);
            }

            result.close();
            connect.close();
        } catch (SQLException ex) {

        }
        return listData;
    }

    private ObservableList<CourseData> courseData;

    public void showCourseData() {

        courseData = CourseData();

        course_col_courseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));           // In this case, the property name passed to PropertyValueFactory is "courseID". This              
        course_col_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));       //indicates that the value for each cell in the course_col_courseID column should be           
        course_col_creditHour.setCellValueFactory(new PropertyValueFactory<>("creditHour"));       // obtained by calling the getTeacherID() . this becaude PropertyValueFactory use 
        course_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));                    // get method in the underlying naming convention i.e getCourseID
        course_col_coursCatagory.setCellValueFactory(new PropertyValueFactory<>("courseCatagory"));

        course_tableView.setItems(courseData);
        course_tableView.refresh();
    }

    public ObservableList<GradeData> GradeData() {

        //dept = getDepartment();
        ObservableList<GradeData> listData = FXCollections.observableArrayList();

//        String sql = "SELECT courseID, courseName, quizGrade, testGrade, assignmentGrade, midGrade,"
//                + " finalGrade, totalGrade, grade FROM grade g JOIN course c ON g.courseID = c.courseID WHERE studentID = '" + Data.userID + "'";
        String sql = "SELECT * FROM grade g JOIN course c ON g.courseID = c.courseID WHERE studentID = '" + Data.userID + "'";

        connect = DatabaseConnection.connectionDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            GradeData gd;
            while (result.next()) {
                gd = new GradeData(result.getString("courseID"),
                        result.getString("courseName"),
                        result.getDouble("quizGrade"),
                        result.getDouble("testGrade"),
                        result.getDouble("assignmentGrade"),
                        result.getDouble("midGrade"),
                        result.getDouble("finalGrade"),
                        result.getDouble("totalGrade"),
                        result.getString("grade")
//                        result.getDouble("")
                );
                listData.add(gd);
            }

            result.close();
            connect.close();
        } catch (SQLException ex) {
        	System.out.println(ex);

        }
        return listData;
    }

    private ObservableList<GradeData> GradeData;

    public void showGradeData() {

        GradeData = GradeData();

        status_col_courseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));           // In this case, the property name passed to PropertyValueFactory is "courseID". This              
        status_col_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));       //indicates that the value for each cell in the course_col_courseID column should be           
        status_col_quiz.setCellValueFactory(new PropertyValueFactory<>("quizGrade"));             // obtained by calling the getCourseID() . this becaude PropertyValueFactory use 
        status_col_test.setCellValueFactory(new PropertyValueFactory<>("testGrade"));             // get method in the underlying naming convention i.e getCourseID 
        status_col_project.setCellValueFactory(new PropertyValueFactory<>("assignmentGrade"));     // this PropertyValueFactory is highly  dependant on the each get methods so it must be named correctly the method name
        status_col_mid.setCellValueFactory(new PropertyValueFactory<>("midGrade"));
        status_col_final.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        status_col_total.setCellValueFactory(new PropertyValueFactory<>("totalGrade"));
        status_col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        status_tableView.setItems(GradeData);
        course_tableView.refresh();
    }

    @FXML
    private void switchForm(ActionEvent event) {

        if (event.getSource() == mycourses_btn) {
            mycourses_page.setVisible(true);
            mystatus_page.setVisible(false);
        } else if (event.getSource() == mystatus_btn) {
            mycourses_page.setVisible(false);
            mystatus_page.setVisible(true);
        }
    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want logout");
            Optional<ButtonType> option = alert.showAndWait();       // this Optional<ButtonType> java class uesed as a container object may or mayn't have null value 

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {    // it used to check weather user clike the button(Ok OR Cancel) or not(X) close the dialog box 
                Main.sceneFactory("LoginFxml");

                LocalDateTime currentDateTime = LocalDateTime.now();
                FileWriter fwr = new FileWriter("src/loghistory/studentlog.txt", true);
                fwr.write("Student ID: " + Data.username + " logged Outinto: " + currentDateTime + "\n");
                fwr.close();
            }
        } catch (IOException ex) {
        }
    }
    public void displayUsername() {

        String user = Data.username;
        user = user.toUpperCase();

        username.setText(user);

    }

}


// this is the end of line 