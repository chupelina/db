//package third;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "students")
//public class Student {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
//    @Column(name = "phone_number")
//    private String phoneNumber;
//    @Column(name = "averige_grade", nullable = false)
//    private double averageGrade;
//    @Column(nullable = false)
//    private int attendance;
//    @ManyToMany
//    @JoinTable( name= "course_students",
//    joinColumns = @JoinColumn(name = "students_id"),
//    inverseJoinColumns = @JoinColumn(name="courses_id"))
//    private Set<Course> courses;
//
//    public Student() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public double getAverageGrade() {
//        return averageGrade;
//    }
//
//    public void setAverageGrade(double averageGrade) {
//        this.averageGrade = averageGrade;
//    }
//
//    public int getAttendance() {
//        return attendance;
//    }
//
//    public void setAttendance(int attendance) {
//        this.attendance = attendance;
//    }
//
//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//}
