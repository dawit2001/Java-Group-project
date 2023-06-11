
package school.management.system;

import java.sql.Date;

/**
 *
 * @author abel
 */

// import java.util.Date;

public class StudentData {

    private Integer id;
    private String studentID;
    private String name;
    private String gender;
    private String department;
    private Integer phoneNum;
    private String address;
    private Integer year;
    private String startSemester;
    private Date endSemester;
    private Integer price;
    private String status;
    private Integer password;

    public StudentData(Integer id, String studentID, String name, String gender, String department, Integer phoneNum,
            String address, Integer year, String startSemester, Date endSemester, Integer price, String status) {

        this.id = id;
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.phoneNum = phoneNum;
        this.address = address;
        this.year = year;
        this.startSemester = startSemester;
        this.endSemester = endSemester;
        this.price = price;
        this.status = status;

    }
    
     public StudentData(String studentID, String name, String gender, Integer year, String department) {
     
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.year = year;
        this.department = department;
        
    }

    public Integer getId() {
        return id;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public Integer getYear() {
        return year;
    }

    public String getStartSemester() {
        return startSemester;
    }

    public Date getEndSemester() {
        return endSemester;
    }

    public Integer getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

}
//