/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package school.management.system;

/**
 *
 * @author abel
 */
public class CourseData {

    private Integer id;
    private String courseID;
    private String courseName;
    private Integer creditHour;
    private Integer year;
    private String courseCatagory;
    private String department;

    public CourseData(String courseID, String courseName, Integer creditHour, Integer year, String courseCatagory, String department) {

        this.courseID = courseID;
        this.courseName = courseName;
        this.creditHour = creditHour;
        this.year = year;
        this.courseCatagory = courseCatagory;
        this.department = department;

    }

    public Integer getID() {
        return id;
    }

    public String getCourseID() {
        return courseID;
    }
    
     public String getCourseName() {
        return courseName;
    }

    public Integer getCreditHour() {
        return creditHour;
    }
    
    public Integer getYear() {
        return year;
    }

    public String getCourseCatagory() {
        return courseCatagory;
    }

    public String getDepartment() {
        return department;
    }

}
