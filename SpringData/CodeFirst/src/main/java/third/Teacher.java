//package third;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name= "teachers")
//public class Teacher {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name="first_name", nullable = false)
//    private String firstName;
//    @Column(name="last_name", nullable = false)
//    private String lastName;
//    @Column(name="phone_number")
//    private String phoneNumber;
//    private String email;
//    @Column(name="salary_per_hour", nullable = false)
//    private double salaryPerHour;
//    @OneToMany(mappedBy = "teacher",targetEntity = Course.class)
//    private Set<Course> courses;
//
//    public Teacher() {
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public double getSalaryPerHour() {
//        return salaryPerHour;
//    }
//
//    public void setSalaryPerHour(double salaryPerHour) {
//        this.salaryPerHour = salaryPerHour;
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
