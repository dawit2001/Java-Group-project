/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package school.management.system;

/**
 *
 * @author abel
 */
public class GradeData {

    private Integer id;
    private String gradeID;
    private String studentID;
    private String  name;
    
    private String courseID;
    private String courseName;
    private Double quizGrade;
    private Double testGrade;
    private Double assignmentGrade;
    private Double midGrade;
    private Double finalGrade;
    private Double totalGrade;
    private String grade;
    private Double sgpa;
    
   // private String teacherID;
    
    
       // This is used for the MarkList table
    public GradeData(String studentID, String name, String courseID, Double quizGrade, Double testGrade,
            Double assignmentGrade, Double midGrade, Double finalGrade, Double totalGrade, String grade) {

        this.studentID = studentID;
        this.name = name;
        this.courseID = courseID;
        this.quizGrade = quizGrade;
        this.testGrade = testGrade;
        this.assignmentGrade = assignmentGrade;
        this.midGrade = midGrade;
        this.finalGrade = finalGrade;
        this.totalGrade = totalGrade;
        this.grade = grade;
       
    }
          // This is used for the status table
    public GradeData(String courseID, String courseName, Double quizGrade, Double testGrade,
            Double assignmentGrade, Double midGrade, Double finalGrade, Double totalGrade, String grade) {

        this.courseID = courseID;
        this.courseName = courseName;
        this.quizGrade = quizGrade;
        this.testGrade = testGrade;
        this.assignmentGrade = assignmentGrade;
        this.midGrade = midGrade;
        this.finalGrade = finalGrade;
        this.totalGrade = totalGrade;
        this.grade = grade;
    }
    
    public String getStudentID() {
        return studentID;
    }

    public String getCourseID() {
        return courseID;
    }
    
     public String getName() {
        return name;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public Double getQuizGrade() {
        return quizGrade;
    }

    public Double getTestGrade() {
        return testGrade;
    }

    public Double getAssignmentGrade() {
        return assignmentGrade;
    }

    public Double getMidGrade() {
        return midGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }
    
    public Double getTotalGrade() {
        return totalGrade;
    }

    public String getGrade() {
        return grade;
    }

}
