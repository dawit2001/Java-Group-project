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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class TeacherDashboardController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Button logout_btn;
    @FXML
    private TextField studentID_tf;
    @FXML
    private TextField studentName_tf;
    @FXML
    private Label courseID;
    @FXML
    private TextField courseID_tf;
    @FXML
    private TextField quiz_tf;
    @FXML
    private TextField test_tf;
    @FXML
    private TextField project_tf;
    @FXML
    private TextField mid__tf;
    @FXML
    private TextField final_tf;
    @FXML
    private Button total_btn;
    @FXML
    private Label total_grade_label;
    @FXML
    private Button add_marklist_btn;
    @FXML
    private Button clear_marklist_btn;
    @FXML
    private Button update_marklist_btn;
    @FXML
    private Button delete_marklist_btn;
    @FXML
    private TableView<GradeData> markList_tableView;
    @FXML
    private TableColumn<GradeData, String> markList_col_studentID;
    @FXML
    private TableColumn<GradeData, String> markList_col_name;
    @FXML
    private TableColumn<GradeData, String> markList_col_courseID;
    @FXML
    private TableColumn<GradeData, Double> markList_col_quiz;
    @FXML
    private TableColumn<GradeData, Double> markList_col_test;
    @FXML
    private TableColumn<GradeData, Double> markList_col_project;
    @FXML
    private TableColumn<GradeData, Double> markList_col_mid;
    @FXML
    private TableColumn<GradeData, Double> markList_col_final;
    @FXML
    private TableColumn<GradeData, Double> markList_col_total;
    @FXML
    private TableColumn<GradeData, Double> markList_col_grade;
    @FXML
    private TableView<StudentData> students_tableView;
    @FXML
    private TableColumn<StudentData, String> students_col_studentID;
    @FXML
    private TableColumn<StudentData, String> students_col_name;
    @FXML
    private TableColumn<StudentData, String> students_col_gender;
    @FXML
    private TableColumn<StudentData, String> students_col_year;
    @FXML
    private TableColumn<StudentData, String> students_col_department;

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
        // TODO
    	displayUsername();
        showMarkListGradeData();
        showStudentData();

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

    public String getDepartment() {
        String sql = "SELECT department FROM teacher WHERE teacherID ='" + Data.userID + "'";

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
        }
        return dept;
    }

    public String dept;

    Double totalGrade;
    String grade;

    @FXML
    private void totalGrade() {
        totalGrade = Double.parseDouble(quiz_tf.getText())
                + Double.parseDouble(test_tf.getText())
                + Double.parseDouble(project_tf.getText())
                + Double.parseDouble(mid__tf.getText())
                + Double.parseDouble(final_tf.getText());
        total_grade_label.setText(String.valueOf(totalGrade));

        if (totalGrade >= 90 && totalGrade <= 100) {
            grade = "A+";
        } else if (totalGrade >= 85 && totalGrade < 90) {
            grade = "A";
        } else if (totalGrade >= 80 && totalGrade < 85) {
            grade = "A-";
        } else if (totalGrade >= 75 && totalGrade < 80) {
            grade = "B+";
        } else if (totalGrade >= 70 && totalGrade < 75) {
            grade = "B";
        } else if (totalGrade >= 65 && totalGrade < 70) {
            grade = "B-";
        } else if (totalGrade >= 60 && totalGrade < 65) {
            grade = "B";
        } else if (totalGrade >= 55 && totalGrade < 60) {
            grade = "C+";
        } else if (totalGrade >= 50 && totalGrade < 55) {
            grade = "C";
        } else if (totalGrade > 40 && totalGrade < 50) {
            grade = "D";
        } else if (totalGrade <= 40) {
            grade = "F";
        }
    }

    public void addMarkList() {

        String sql = "INSERT INTO grade(studentID, courseID, quizGrade, testGrade, assignmentGrade, midGrade, finalGrade, totalGrade, grade, teacherID) VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = DatabaseConnection.connectionDB();

        if (studentID_tf.getText().isEmpty() || studentName_tf.getText().isEmpty()
                || courseID_tf.getText().isEmpty()
                || quiz_tf.getText().isEmpty()
                || test_tf.getText().isEmpty()
                || project_tf.getText().isEmpty()
                || mid__tf.getText().isEmpty()
                || final_tf.getText().isEmpty()) {

            errorMessage("Please fill all blank fields");                                                                 // if one of them is null or empty it calles the emptyFields method

        } else {

            try {

                String checkCourseID = "SELECT courseID FROM course WHERE department = '" + dept + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkCourseID);

                if (result.next()) {

                    String checkRepition = "SELECT courseID FROM grade WHERE studentID = '" + studentID_tf.getText() + "' AND courseID = '" + courseID_tf.getText() + "'";
                    statement = connect.createStatement();
                    result = statement.executeQuery(checkRepition);

                    if (result.next()) {
                        errorMessage("This CourseID: " + courseID_tf.getText() + " Is Aleardy Filed For StudnetID: "+studentID_tf.getText());

                    } else {

                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, studentID_tf.getText());
                        prepare.setString(2, courseID_tf.getText());
                        prepare.setString(3, quiz_tf.getText());
                        prepare.setString(4, test_tf.getText());
                        prepare.setString(5, project_tf.getText());
                        prepare.setString(6, mid__tf.getText());
                        prepare.setString(7, final_tf.getText());
                        prepare.setDouble(8, totalGrade);
                        prepare.setString(9, grade);
                        prepare.setString(10, Data.userID);

                        successMessage("Successfully Added!");

                        // TO INSERT all DATA
                        prepare.executeUpdate();
                        // TO UPDATE TABLEVIEW
                        showMarkListGradeData();
                        // TO CLEAR ALL FIELDS
                        //teachersClearBtn();
                    }
                } else {
                    errorMessage("There IS No Course In This CourseID " + courseID_tf.getText());
                }
            } catch (SQLException ex) {
            }

        }
    }

    @FXML
    private void clearMarkList(ActionEvent event) {
    }

    @FXML
    private void updateMarkList(ActionEvent event) {
    }

    @FXML
    private void deleteMarkList(ActionEvent event) {
    }

    @FXML
    private void markListSelect(MouseEvent event) {
    }

    public ObservableList<GradeData> MarkListGradeData() {

        dept = getDepartment();
        ObservableList<GradeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM grade g JOIN course c ON g.courseID = c.courseID "
                + "JOIN student s ON g.studentID = s.studentID "
                + "JOIN teacher t ON g.teacherID = t.teacherID "
                + "WHERE t.teacherID = '" + Data.userID + "'";
        connect = DatabaseConnection.connectionDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            GradeData mkgd;
            while (result.next()) {
                mkgd = new GradeData(result.getString("studentID"),
                        result.getString("name"),
                        result.getString("courseID"),
                        result.getDouble("quizGrade"),
                        result.getDouble("testGrade"),
                        result.getDouble("assignmentGrade"),
                        result.getDouble("midGrade"),
                        result.getDouble("finalGrade"),
                        result.getDouble("totalGrade"),
                        result.getString("grade")
                );
                listData.add(mkgd);
            }

            result.close();
            connect.close();
        } catch (SQLException ex) {

        }
        return listData;
    }

    private ObservableList<GradeData> MarkListGradeData;

    public void showMarkListGradeData() {

        MarkListGradeData = MarkListGradeData();

        markList_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        markList_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        markList_col_courseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        markList_col_quiz.setCellValueFactory(new PropertyValueFactory<>("quizGrade"));
        markList_col_test.setCellValueFactory(new PropertyValueFactory<>("testGrade"));
        markList_col_project.setCellValueFactory(new PropertyValueFactory<>("assignmentGrade"));
        markList_col_mid.setCellValueFactory(new PropertyValueFactory<>("midGrade"));
        markList_col_final.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        markList_col_total.setCellValueFactory(new PropertyValueFactory<>("totalGrade"));
        markList_col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        markList_tableView.setItems(MarkListGradeData);
        markList_tableView.refresh();
    }

    public ObservableList<StudentData> studentDataList() {   // this used to fill the student object from the database

        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student WHERE department = '" + dept + "'";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentData sD;
            while (result.next()) {

                sD = new StudentData(result.getString("studentID"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getInt("year"),
                        result.getString("department")
                );
                listData.add(sD);
            }

            result.close();
            connect.close();
        } catch (SQLException ex) {

        }

        return listData;
    }

    private ObservableList<StudentData> studentDataList;

    public void showStudentData() {

        studentDataList = studentDataList();

        students_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        students_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        students_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        students_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        students_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));

        students_tableView.setItems(studentDataList);
        students_tableView.refresh();
    }

    @FXML
    public void studentSelect() {
        StudentData sD = students_tableView.getSelectionModel().getSelectedItem();
        int num = students_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {       // if there no is selected row i.e index < 0  it simply return wihtout doing anything
            return;
        }

        studentID_tf.setText(sD.getStudentID());
        studentName_tf.setText(sD.getName());
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
                FileWriter fwr = new FileWriter("src/loghistory/teacherlog.txt", true);
                fwr.write("Teacher ID: " + Data.username + " logged Outinto: " + currentDateTime + "\n");
                fwr.close();
            }
        } catch (IOException ex) {
        }
    }
    public void displayUsername() {

        String user = Data.username;
        user = user.toUpperCase() ;

        username.setText(user);

    }

}
