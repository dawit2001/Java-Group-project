/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package studentpaymentsystempackage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author abel
 */
public class DashboardFxmlController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button teacher_btn;
    @FXML
    private Button student_btn;
    @FXML
    private Button payment_btn;
    @FXML
    private Button logout;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Label dashboard_NS;
    @FXML
    private Label dashboard_NT;
    @FXML
    private Label dashboard_TI;
    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;
    @FXML
    private AnchorPane teachers_form;
    @FXML
    private TextField teachers_teacherID;
    @FXML
    private TextField teachers_name;
    @FXML
    private ComboBox<String> teachers_gender;      //   using the wildcard<?>  it accpets only rawtype(unsafe) observablelist but by menthioning the argument(<String>) it become type safe
    @FXML
    private TextField teachers_department;
    @FXML
    private TextField teachers_phoneNum;
    @FXML
    private TextArea teachers_address;
    @FXML
    private ComboBox<?> teachers_status;
    @FXML
    private Button teachers_addBtn;
    @FXML
    private Button teachers_updateBtn;
    @FXML
    private Button teachers_resetBtn;
    @FXML
    private Button teachers_deleteBtn;
    @FXML
    private TableView<TeacherData> teachers_tableView;
    @FXML
    private TableColumn<TeacherData, String> teachers_col_teacherID;
    @FXML
    private TableColumn<TeacherData, String> teachers_col_name;
    @FXML
    private TableColumn<TeacherData, String> teachers_col_gender;
    @FXML
    private TableColumn<TeacherData, String> teachers_col_department;
    @FXML
    private TableColumn<TeacherData, Integer> teachers_col_phoneNum;
    @FXML
    private TableColumn<TeacherData, String> teachers_col_address;
    @FXML
    private TableColumn<TeacherData, String> teachers_col_status;
    @FXML
    private AnchorPane students_form;
    @FXML
    private TextField students_studentID;
    @FXML
    private TextField students_name;
    @FXML
    private ComboBox<String> students_gender;
    @FXML
    private TextField students_department;
    @FXML
    private TextField students_phoneNum;
    @FXML
    private TextArea students_address;
    @FXML
    private ComboBox<Integer> students_year;
    @FXML
    private DatePicker students_startSemister;
    @FXML
    private DatePicker students_endSemister;
    @FXML
    private ComboBox<String> students_status;
    @FXML
    private Button students_addBtn;
    @FXML
    private Button students_clearBtn;
    @FXML
    private Button students_updateBtn;
    @FXML
    private Button students_deleteBtn;
    @FXML
    private TableView<StudentData> students_tableView;
    @FXML
    private TableColumn<StudentData, String> students_col_studentID;
    @FXML
    private TableColumn<StudentData, String> students_col_name;
    @FXML
    private TableColumn<StudentData, String> students_col_gender;
    @FXML
    private TableColumn<StudentData, String> students_col_department;
    @FXML
    private TableColumn<StudentData, Integer> students_col_phoneNum;
    @FXML
    private TableColumn<StudentData, String> students_col_address;
    @FXML
    private TableColumn<StudentData, Integer> students_col_year;
    @FXML
    private TableColumn<StudentData, String> students_col_startSemister;
    @FXML
    private TableColumn<StudentData, Date> students_col_endSemister;
    @FXML
    private TableColumn<StudentData, String> students_col_status;
    @FXML
    private AnchorPane payment_Form;
    @FXML
    private TableView<StudentData> payment_tableView;
    @FXML
    private TableColumn<StudentData, String> payment_col_studentID;
    @FXML
    private TableColumn<StudentData, String> payment_col_name;
    @FXML
    private TableColumn<StudentData, String> payment_col_department;
    @FXML
    private TableColumn<StudentData, Integer> payment_col_year;
    @FXML
    private TableColumn<StudentData, String> payment_col_startSemister;
    @FXML
    private TableColumn<StudentData, Date> payment_col_endSemister;
    @FXML
    private TableColumn<StudentData, String> payment_col_status;
    @FXML
    private TextField payment_studentID;
    @FXML
    private Label payment_studentName;
    @FXML
    private Label payment_studentDepartment;
    @FXML
    private Label payment_total;
    @FXML
    private TextField payment_amount;
    @FXML
    private Label payment_change;
    @FXML
    private Button payment_payBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayUsername();

        dashboardNS();
        dashboardNT();
        dashboardTI();
        dashboardChart();

        teacherGenderList();
        teacherStatusList();
        showTeacherData();

        studentsGenderList();
        studentsYearList();
        studentsStatusList();
        showStudentData();

        showPaymentData();

    }

    public void emptyFields() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }

    @FXML
    public void teachersAddBtn() {    // make the modifyer public inorder to get by controller

        String sql = "INSERT INTO teacher(teacherID, name, gender, department, phoneNum, address, status)"
                + "VALUES(?,?,?,?,?,?,?)";

        connect = DatabaseConnection.connectionDB();

        try {

            Alert alert;

            if (teachers_teacherID.getText().isEmpty() || teachers_name.getText().isEmpty()
                    || teachers_gender.getSelectionModel().getSelectedItem() == null
                    || teachers_department.getText().isEmpty()
                    || teachers_phoneNum.getText().isEmpty()
                    || teachers_address.getText().isEmpty()
                    || teachers_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();                                                                // if one of them is null or empty it calles the emptyFields method

            } else {

                String checkData = "SELECT teacherID FROM teacher WHERE teacherID='" + teachers_teacherID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Teacher ID: " + teachers_teacherID.getText() + " was already taken!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, teachers_teacherID.getText());
                    prepare.setString(2, teachers_name.getText());
                    prepare.setString(3, (String) teachers_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, teachers_department.getText());
                    //prepare.setString(5, teachers_phoneNum.getText());                         //the JDBC driver will automatically convert the String value to an int before storing it in the database.
                    prepare.setInt(5, Integer.parseInt(teachers_phoneNum.getText()));
                    prepare.setString(6, teachers_address.getText());
                    prepare.setString(7, (String) teachers_status.getSelectionModel().getSelectedItem());

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO INSERT all DATA
                    prepare.executeUpdate();
                    // TO UPDATE TABLEVIEW
                    showTeacherData();
                    // TO CLEAR ALL FIELDS
                    teachersClearBtn();
                }

            }
            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

    }

    @FXML
    public void teachersUpdateBtn() {
        String sql = "UPDATE teacher set name = '" + teachers_name.getText() // Note: you can send any data as a string the driver will convert it before sets to the database
                + "', gender = '" + teachers_gender.getSelectionModel().getSelectedItem()
                + "', department = '" + teachers_department.getText()
                + "', phoneNum = '" + teachers_phoneNum.getText()
                + "', address = '" + teachers_address.getText()
                + "', status = '" + teachers_status.getSelectionModel().getSelectedItem() + "'"
                + " WHERE teacherID = '" + teachers_teacherID.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        try {

            Alert alert;

            if (teachers_name.getText().isEmpty()
                    || teachers_gender.getSelectionModel().getSelectedItem() == null
                    || teachers_department.getText().isEmpty()
                    || teachers_phoneNum.getText().isEmpty()
                    || teachers_address.getText().isEmpty()
                    || teachers_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();                                                                // if one of them is null or empty it calles the emptyFields method

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Teacher ID: " + teachers_teacherID.getText() + "?");

                if (alert.showAndWait().get() == ButtonType.OK) {

                    prepare = connect.prepareStatement(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE all DATA
                    prepare.executeUpdate();
                    // TO UPDATE TABLEVIEW
                    showTeacherData();
                    // TO CLEAR ALL FIELDS
                    teachersClearBtn();
                }
            }
            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

    }

    @FXML
    public void teachersDeleteBtn() {

        String sql = "DELETE FROM teacher WHERE teacherID = '" + teachers_teacherID.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        try {
            Alert alert;

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Teacher ID: " + teachers_teacherID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {

                statement = connect.createStatement();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                // TO UPDATE all DATA
                statement.executeUpdate(sql);
                // TO UPDATE TABLEVIEW
                showTeacherData();
                // TO CLEAR ALL FIELDS
                teachersClearBtn();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled DELETE!");
                alert.showAndWait();
            }

            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

    }

    @FXML
    public void teachersClearBtn() {
        teachers_teacherID.setText("");
        teachers_name.setText("");
        teachers_gender.getSelectionModel().clearSelection();
        teachers_department.setText("");
        teachers_phoneNum.setText("");
        teachers_address.setText("");
        teachers_status.getSelectionModel().clearSelection();
    }

    public ObservableList<TeacherData> teacherDataList() {         // this used to link the teacher object to the database

        ObservableList<TeacherData> listData = FXCollections.observableArrayList();     // this create list of TeacherData object

        String sql = "SELECT * FROM teacher";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TeacherData tD;
            while (result.next()) {
                tD = new TeacherData(result.getInt("id"), result.getString("teacherID"),
                        result.getString("name"), result.getString("gender"),
                        result.getString("department"), result.getInt("phoneNum"),
                        result.getString("address"), result.getString("status")
                );
                listData.add(tD);    // when one object finshed the if there is another in the database loop through it and added to the list
            }

            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

        return listData;
    }

    private ObservableList<TeacherData> teacherDataList;

    public void showTeacherData() {                        //  this used to map the column and the object property and to dispaly it to the tableview

        teacherDataList = teacherDataList();

        teachers_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));    // this PropertyValueFactory is a class used for map the data in the TableView column to the corresponding property(i.e instance variable). of a Java object
        teachers_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));              // in simple word map the a single column to a specified filed or property of java object i.e Teacher ID column <->(connect) teacherID property
        teachers_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));          // setCellValueFactory this represents a single column in the tableview. used for converting the underling data to the 
        teachers_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        teachers_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        teachers_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        teachers_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        teachers_tableView.setItems(teacherDataList);    // this is used to display the value to the tableview

    }

    @FXML
    public void teachersSelect() {                                                  // this mehtod is called when a row in the tableview is selected 
        TeacherData tD = teachers_tableView.getSelectionModel().getSelectedItem();   // It first gets the TeacherData object(specific instance) corresponding to the selected row usign getSelectedItem() method
        int num = teachers_tableView.getSelectionModel().getSelectedIndex();         // this also gets the index of the selected row   

        if ((num - 1) < -1) {       // if there no is selected row i.e index < 0  it simply return wihtout doing anything
            return;
        }

        teachers_teacherID.setText(tD.getTeacherID());      // else it set's the corresponding object property to the TextFiled
        teachers_name.setText(tD.getName());
        teachers_department.setText(tD.getDepartment());
        teachers_phoneNum.setText(String.valueOf(tD.getPhoneNum()));  // or Integer.toString(tD.getPhoneNum());
        teachers_address.setText(tD.getAddress());

    }

    private String gender[] = {"Male", "Female", "Other"};
    private String t_status[] = {"Active", "Inactive"};

    public void teacherGenderList() {
        List<String> genderlist = new ArrayList<>();

        genderlist.addAll(Arrays.asList(gender));      // in this case we are conveting the arry to arraylist this may be give more flexible  but you can also use the array directly
        // or       for(String data: gender){
        //            genderlist.add(data);
        //        }
        ObservableList<String> listData = FXCollections.observableArrayList(genderlist);

        teachers_gender.setItems(listData);           // combobox  accpets only observableList that is why we declera ths one
    }

    public void teacherStatusList() {
        //List<String> statusList = new ArrayList<>();
        //statusList.addAll(Arrays.asList(status));

        ObservableList listData = FXCollections.observableArrayList(t_status);     // using wildcard and rawtype declaration don't need <String> but this is not typesafte and you can also use simply array

        teachers_status.setItems(listData);
    }

    public void studentsGenderList() {
        //List<String> genderlist = new ArrayList<>();
        // genderlist.addAll(Arrays.asList(gender));      // this can be shortned as  Arrays.aslist() method converts array to arraylist
        ObservableList<String> listData = FXCollections.observableArrayList(Arrays.asList(gender));

        students_gender.setItems(listData);           // combobox  accpets only observableList that is why we declera ths one
    }

    private Integer year[] = {1, 2, 3, 4, 5};
    private String s_status[] = {"Paid", "Unpaid"};

    public void studentsYearList() {

        ObservableList<Integer> listData = FXCollections.observableArrayList(Arrays.asList(year));

        students_year.setItems(listData);
    }

    public void studentsStatusList() {

        ObservableList<String> listData = FXCollections.observableArrayList(Arrays.asList(s_status));

        students_status.setItems(listData);
    }

    public ObservableList<StudentData> studentDataList() {   // this used to fill the student object from the database

        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // set the date format to match the format used in SQLite
            StudentData sD;
            while (result.next()) {

                sD = new StudentData(result.getInt("id"),
                        result.getString("studentID"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getString("department"),
                        result.getInt("phoneNum"),
                        result.getString("address"),
                        result.getInt("year"),
                        result.getString("startSemester"),
                        result.getDate("endSemester"),
                        result.getInt("price"),
                        result.getString("status"));

                listData.add(sD);    // when one object is finshed ,then if there is another in the database loop through it and added to the list
            }

            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

        return listData;
    }

    private ObservableList<StudentData> studentDataList;

    public void showStudentData() {                        //  this used to map the column and the object property and to dispaly it to the tableview

        studentDataList = studentDataList();

        students_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));    // this PropertyValueFactory is a class used for map the data in the TableView column to the corresponding property(i.e instance variable). of a Java object
        students_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));              // in simple word map the a single column to a specified filed or property of java object i.e Teacher ID column <->(connect) teacherID property
        students_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));          // setCellValueFactory this represents a single column in the tableview. used for converting the underling data to the 
        students_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        students_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        students_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        students_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        students_col_startSemister.setCellValueFactory(new PropertyValueFactory<>("startSemester"));
        students_col_endSemister.setCellValueFactory(new PropertyValueFactory<>("endSemester"));
        students_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        students_tableView.setItems(studentDataList);    // this is used to display the value to the tableview
        students_tableView.refresh();
    }

    @FXML
    public void studentSelect() {                                                  // this mehtod is called when a row in the tableview is selected 
        StudentData sD = students_tableView.getSelectionModel().getSelectedItem();   // It first gets the TeacherData object(specific instance) corresponding to the selected row usign getSelectedItem() method
        int num = students_tableView.getSelectionModel().getSelectedIndex();         // this also gets the index of the selected row   

        if ((num - 1) < -1) {       // if there no is selected row i.e index < 0  it simply return wihtout doing anything
            return;
        }

        students_studentID.setText(sD.getStudentID());      // else it set's the corresponding object property to the TextFiled
        students_name.setText(sD.getName());
        students_department.setText(sD.getDepartment());
        students_phoneNum.setText(String.valueOf(sD.getPhoneNum()));  // or Integer.toString(sD.getPhoneNum());
        students_address.setText(sD.getAddress());
        students_startSemister.setValue(LocalDate.parse(sD.getStartSemester()));  // local data can parse directly string to localDate 
        students_endSemister.setValue(sD.getEndSemester().toLocalDate());    // parse from sql.data to LoaclDate using toLocalDate() or use like this LocalDate.parse(String.valueOf(sD.startEndDate()))

    }

    private int totalMonth;
    private int price;

    @FXML
    public void studentsAddBtn() throws SQLException {      // this add button used for inserting data to the database from the from
        String sql = "INSERT INTO student(studentID, name, gender, department, phoneNum, address, year, startSemester, endSemester, price, status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        Alert alert;
        connect = DatabaseConnection.connectionDB();

        if (students_studentID.getText().isEmpty() || students_name.getText().isEmpty()
                || students_gender.getSelectionModel().getSelectedItem() == null
                || students_department.getText().isEmpty()
                || students_phoneNum.getText().isEmpty()
                || students_address.getText().isEmpty()
                || students_year.getSelectionModel().getSelectedItem() == null
                || students_startSemister.getValue() == null
                || students_endSemister.getValue() == null
                || students_status.getSelectionModel().getSelectedItem() == null) {

            emptyFields();                                                                // if one of them is null or empty it calles the emptyFields method

        } else {

            String checkData = "SELECT studentID FROM student WHERE studentID='" + students_studentID.getText() + "'";
            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Student ID: " + students_studentID.getText() + " was already taken!");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, students_studentID.getText());
                prepare.setString(2, students_name.getText());
                prepare.setString(3, (String) students_gender.getSelectionModel().getSelectedItem());   //1. here we are casting since the return is string like object but it is limited for some types 
                prepare.setString(4, students_department.getText());                               // can also use String.valueOf in place of casting but not the revers in most case
                // prepare.setString(5, students_phoneNum.getText());                              //2. the JDBC driver will automatically convert the String value to an int before storing it in the database.so we can set it like this for any datatype
                prepare.setInt(5, Integer.parseInt(students_phoneNum.getText()));          //but usign the appropriate method is good for handing error in this case better to use parepare.setiInt insteady of setString
                prepare.setString(6, students_address.getText());
                prepare.setInt(7, (int) students_year.getSelectionModel().getSelectedItem());   //3. this getSelectedItem() returns object of type Integer so we can cast insteady of use Integer.parseInt() since it excpect string but the return value is not string
                prepare.setString(8, String.valueOf(students_startSemister.getValue()));       //4. String.valueOf() can convert any datatype to string // in this case only use this insteady of casting since casting works for few datatype like numerics

                java.sql.Date endSemester = java.sql.Date.valueOf(students_endSemister.getValue());   //5. java.sql.Date and java.time.LocalDate are not exactly the same type, only the first one is the database accepts so we need to convert to that  // this methos is better than the above 4
                prepare.setDate(9, endSemester);                                                     // note DatePicker control in JavaFX returns a LocalDate value, not a java.sql.Date  before passing it to the setDate() we need to convert to that

                totalMonth = (int) ChronoUnit.MONTHS.between(students_startSemister.getValue(), students_endSemister.getValue());

                if ((int) students_year.getSelectionModel().getSelectedItem() >= 5) {
                    price = (totalMonth * 1200);
                } else {
                    price = (totalMonth * 1000);
                }

                prepare.setInt(10, price);
                prepare.setString(11, String.valueOf(students_status.getSelectionModel().getSelectedItem()));

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO INSERT all DATA
                prepare.executeUpdate();
                // TO UPDATE TABLEVIEW
                showStudentData();
                // TO CLEAR ALL FIELDS
                //teachersClearBtn();

                result.close();
                connect.close();
            }
        }

    }

    @FXML
    public void studentsUpdateBtn() throws SQLException {
        String sql = "UPDATE student SET name = ?, gender = ?, department = ?, phoneNum = ?, address = ?, year = ?, "
                + "startSemester = ?, endSemester = ?, price = ?, status = ? "
                + "WHERE studentID = '" + students_studentID.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        if (students_name.getText().isEmpty()
                || students_gender.getSelectionModel().getSelectedItem() == null
                || students_department.getText().isEmpty()
                || students_phoneNum.getText().isEmpty()
                || students_address.getText().isEmpty()
                || students_year.getSelectionModel().getSelectedItem() == null
                || students_startSemister.getValue() == null
                || students_endSemister.getValue() == null
                || students_status.getSelectionModel().getSelectedItem() == null) {

            emptyFields();                                                                // if one of them is null or empty it calles the emptyFields method

        } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Teacher ID: " + students_studentID.getText() + "?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, students_name.getText());
                prepare.setString(2, (String) students_gender.getSelectionModel().getSelectedItem());   //1. here we are casting since the return is string like object but it is limited for some types 
                prepare.setString(3, students_department.getText());                               // can also use String.valueOf in place of casting but not the revers in most case
                // prepare.setString(4, students_phoneNum.getText());                              //2. the JDBC driver will automatically convert the String value to an int before storing it in the database.so we can set it like this for any datatype
                prepare.setInt(4, Integer.parseInt(students_phoneNum.getText()));          //but usign the appropriate method is good for handing error in this case better to use parepare.setiInt insteady of setString
                prepare.setString(5, students_address.getText());
                prepare.setInt(6, (int) students_year.getSelectionModel().getSelectedItem());   //3. this getSelectedItem() returns object of type Integer so we can cast insteady of use Integer.parseInt() since it excpect string but the return value is not string
                prepare.setString(7, String.valueOf(students_startSemister.getValue()));       //4. String.valueOf() can convert any datatype to string // in this case only use this insteady of casting since casting works for few datatype like numerics

                java.sql.Date endSemester = java.sql.Date.valueOf(students_endSemister.getValue());   //5. java.sql.Date and java.time.LocalDate are not exactly the same type, only the first one is the database accepts so we need to convert to that  // this methos is better than the above 4
                prepare.setDate(8, endSemester);                                                     // note DatePicker control in JavaFX returns a LocalDate value, not a java.sql.Date  before passing it to the setDate() we need to convert to that

                totalMonth = (int) ChronoUnit.MONTHS.between(students_startSemister.getValue(), students_endSemister.getValue());

                if ((int) students_year.getSelectionModel().getSelectedItem() >= 5) {
                    price = (totalMonth * 1200);
                } else {
                    price = (totalMonth * 1000);
                }

                prepare.setInt(9, price);
                prepare.setString(10, String.valueOf(students_status.getSelectionModel().getSelectedItem()));

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully UPDATED!");
                alert.showAndWait();

                // TO INSERT all DATA
                prepare.executeUpdate();
                // TO UPDATE TABLEVIEW
                showStudentData();
                // TO CLEAR ALL FIELDS
                //teachersClearBtn();

                result.close();
                connect.close();
            }
        }

    }

    @FXML
    public void studentsDeleteBtn() throws SQLException {

        String sql = "DELETE FROM student WHERE studentID = '" + students_studentID.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        Alert alert;

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Student ID: " + students_studentID.getText() + "?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get().equals(ButtonType.OK)) {

            statement = connect.createStatement();

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully DELETED!");
            alert.showAndWait();

            // TO UPDATE all DATA
            statement.executeUpdate(sql);
            // TO UPDATE TABLEVIEW
            showStudentData();
            // TO CLEAR ALL FIELDS
            teachersClearBtn();
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Cancelled DELETE!");
            alert.showAndWait();
        }

        result.close();
        connect.close();

    }

    @FXML
    public void studentsClearBtn() {
        students_studentID.setText("");
        students_name.setText("");
        students_gender.getSelectionModel().clearSelection();
        students_department.setText("");
        students_phoneNum.setText("");
        students_address.setText("");
        students_year.getSelectionModel().clearSelection();
        students_startSemister.setValue(null);
        students_endSemister.setValue(null);
        students_status.getSelectionModel().clearSelection();

    }

    public ObservableList<StudentData> paymentDataList() {   // this used to fill the student object from the database that is unpaid

        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student WHERE status = 'Unpaid'";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentData sD;
            while (result.next()) {

                sD = new StudentData(result.getInt("id"),
                        result.getString("studentID"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getString("department"),
                        result.getInt("phoneNum"),
                        result.getString("address"),
                        result.getInt("year"),
                        result.getString("startSemester"),
                        result.getDate("endSemester"),
                        result.getInt("price"),
                        result.getString("status"));

                listData.add(sD);    // when one object is finshed ,then if there is another in the database loop through it and added to the list
            }

            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

        return listData;
    }

    private ObservableList<StudentData> paymentDataList;

    public void showPaymentData() {                        //  this used to map the column and the object property and to dispaly it to the tableview

        paymentDataList = paymentDataList();

        payment_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));    // this PropertyValueFactory is a class used for map the data in the TableView column to the corresponding property(i.e instance variable). of a Java object
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));              // in simple word map the a single column to a specified filed or property of java object i.e Teacher ID column <->(connect) teacherID property
        payment_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        payment_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        payment_col_startSemister.setCellValueFactory(new PropertyValueFactory<>("startSemester"));
        payment_col_endSemister.setCellValueFactory(new PropertyValueFactory<>("endSemester"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        payment_tableView.setItems(paymentDataList);    // this is used to display the value to the tableview
        payment_tableView.refresh();
    }

    public void paymentSelect() {                                                  // this mehtod is called when a row in the tableview is selected 
        StudentData sD = payment_tableView.getSelectionModel().getSelectedItem();   // It first gets the TeacherData object(specific instance) corresponding to the selected row usign getSelectedItem() method
        int num = payment_tableView.getSelectionModel().getSelectedIndex();         // this also gets the index of the selected row   

        if ((num - 1) < -1) {       // if there no is selected row i.e index < 0  it simply return wihtout doing anything
            return;
        }

        payment_studentID.setText(sD.getStudentID());      // else it set's the corresponding object property to the TextFiled

    }

    public ObservableList<String> paymentStudentIDList() {

        ObservableList<String> listData = FXCollections.observableArrayList();

        String sql = "SELECT studentID FROM student WHERE status = 'Unpaid'";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("studentID"));
            }
            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

        return listData;
    }

    private String payStudentName, payStudentDept;
    private int totalP;

    public void getNameDepartmentPrice() {

        String sql = "SELECT name, department, price FROM student WHERE studentID='" + payment_studentID.getText() + "' and status ='Unpaid'";

        connect = DatabaseConnection.connectionDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                payStudentName = result.getString("name");
                payStudentDept = result.getString("department");
                totalP = result.getInt("price");
            }
            result.close();
            connect.close();

        } catch (SQLException ex) {
        }

    }

    public void paymentShowSpecificData() {

        ObservableList<String> listData = paymentStudentIDList();

        getNameDepartmentPrice();

        boolean found = false;

        for (String studentID : listData) {
            if (studentID.equals(payment_studentID.getText())) {

                found = true;
            }
        }

        if (found) {
            payment_studentName.setText(payStudentName);
            payment_studentDepartment.setText(payStudentDept);
            payment_total.setText("$" + String.valueOf(totalP));
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("There is no unpaid Student with this ID:" + payment_studentID.getText());
            alert.showAndWait();
        }

    }

    private int amount, change;

    public void paymentAmount() {

        getNameDepartmentPrice();

        if (payment_studentID.getText().isEmpty() || payment_amount.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("There is somthing worng");
            alert.showAndWait();
            payment_amount.setText("");

        } else {

            amount = Integer.parseInt(payment_amount.getText());
            if (amount >= totalP) {
                change = (amount - totalP);
                payment_change.setText("$" + String.valueOf(change));
            } else {
                payment_amount.setText("");
                change = 0;
                amount = 0;
            }
        }

    }

    public void paymentPayBtn(ActionEvent event) throws SQLException {

        String sql = "UPDATE student SET status = 'Paid' WHERE studentID = '" + payment_studentID.getText() + "'";

        connect = DatabaseConnection.connectionDB();

        Alert alert;
        if (totalP == 0 || amount == 0 || payment_amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something Wrong :3");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successful!");
                alert.showAndWait();

                showPaymentData();
                paymentClear();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled.");
                alert.showAndWait();
            }
        }
    }

    public void paymentClear() {
        payment_studentID.setText("");
        payment_studentName.setText("");
        payment_studentDepartment.setText("");
        payment_total.setText("$0.0");
        payment_amount.setText("");
        payment_change.setText("$0.0");

        amount = 0;
        change = 0;
        totalP = 0;
    }

    public void dashboardNS() {

        String sql = "SELECT COUNT(studentID) FROM student WHERE status = 'Paid'";

        connect = DatabaseConnection.connectionDB();

        int ns = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ns = result.getInt("COUNT(studentID)");
            }

            dashboard_NS.setText(String.valueOf(ns));

        } catch (Exception e) {
        }

    }

    public void dashboardNT() {

        String sql = "SELECT COUNT(teacherID) FROM teacher WHERE status = 'Active'";

        connect = DatabaseConnection.connectionDB();

        int nt = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nt = result.getInt("COUNT(teacherID)");
            }
            dashboard_NT.setText(String.valueOf(nt));

        } catch (Exception e) {
        }

    }

    public void dashboardTI() {

        String sql = "SELECT SUM(price) FROM student WHERE status = 'Paid'";

        connect = DatabaseConnection.connectionDB();

        double ti = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getDouble("SUM(price)");
            }

            dashboard_TI.setText("$" + String.valueOf(ti));

        } catch (Exception e) {
        }

    }

    public void dashboardChart() {

        dashboard_incomeChart.getData().clear();

        String sql = "SELECT startSemester, SUM(price) FROM student WHERE status = 'Paid' GROUP BY startSemester ORDER BY TIMESTAMP(startSemester) ASC LIMIT 10";

        connect = DatabaseConnection.connectionDB();

        XYChart.Series chart = new XYChart.Series();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }

            dashboard_incomeChart.getData().add(chart);

        } catch (Exception e) {
        }

    }

    public void displayUsername() {

        String user = Data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }

    @FXML
    private void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            teachers_form.setVisible(false);
            students_form.setVisible(false);
            payment_Form.setVisible(false);

            dashboardNS();
            dashboardNT();
            dashboardTI();
            dashboardChart();

        } else if (event.getSource() == teacher_btn) {

            dashboard_form.setVisible(false);
            teachers_form.setVisible(true);
            students_form.setVisible(false);
            payment_Form.setVisible(false);

            teacherGenderList();
            teacherStatusList();
            showTeacherData();

        } else if (event.getSource() == student_btn) {
            dashboard_form.setVisible(false);
            teachers_form.setVisible(false);
            students_form.setVisible(true);
            payment_Form.setVisible(false);

            studentsGenderList();
            studentsYearList();
            studentsStatusList();
            showStudentData();

        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            teachers_form.setVisible(false);
            students_form.setVisible(false);
            payment_Form.setVisible(true);

            showPaymentData();
        }
    }

    @FXML
    private void logout() {
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want logout");
            Optional<ButtonType> option = alert.showAndWait();       // this Optional<ButtonType> java class uesed as a container object may or mayn't have null value 

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {    // it used to check weather user clike the button(Ok OR Cancel) or not(X) close the dialog box 
                StudentPaymentSystem.sceneFactory("LoginFxml");
            }
        } catch (IOException ex) {
        }
    }

}
