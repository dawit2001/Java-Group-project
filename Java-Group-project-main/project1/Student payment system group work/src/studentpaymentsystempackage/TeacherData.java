package studentpaymentsystempackage;

/**
 *
 * @author abel
 */
public class TeacherData {

    private Integer id;
    private String teacherID;
    private String name;
    private String gender;
    private String department;
    private Integer phoneNum;
    private String address;
    private String status;
    
    public TeacherData(Integer id, String teacherID, String name,String gender,String department,Integer phoneNum,String address,String status){
        
        this.id = id;
        this.teacherID = teacherID;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.phoneNum = phoneNum;
        this.address = address;
        this.status = status;

    }

    
    public Integer getId() {
        return id;
    }

    public String getTeacherID() {
        return teacherID;
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


    public String getStatus() {
        return status;
    }




}
