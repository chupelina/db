//package third;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//@Entity
//@Table(name = "courses")
//public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(nullable = false)
//    private String name;
//    @Column(name = "start_date", nullable = false)
//    private LocalDateTime startDate;
//    @Column(name = "end_date", nullable = false)
//    private LocalDateTime endDate;
//    @Column(nullable = false)
//    private int credits;
//    @ManyToOne
//    @MapsId("teacherId")
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;
//    @ManyToMany(mappedBy = "courses", targetEntity = Student.class)
//    private Set<Student> students;
//
//    public Course() {
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDateTime getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDateTime startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDateTime getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDateTime endDate) {
//        this.endDate = endDate;
//    }
//
//    public int getCredits() {
//        return credits;
//    }
//
//    public void setCredits(int credits) {
//        this.credits = credits;
//    }
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//
//    public Set<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<Student> students) {
//        this.students = students;
//    }
//}
