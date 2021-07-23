//package fourth;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//@Entity
//@Table(name= "visitations")
//public class Visitation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(nullable = false)
//    private LocalDateTime date;
//    @Column(nullable = false)
//    private String comments;
//    @ManyToOne
//    @JoinColumn(name="patient_id", referencedColumnName = "id")
//    private Patient patient;
//
//    public Visitation() {
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
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//}
