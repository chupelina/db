//package fourth;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "diagnoses")
//public class Diagnose {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(nullable = false)
//    private String name;
//    private String comments;
//    @OneToMany(mappedBy = "diagnose", targetEntity = Medicament.class)
//    @Column(nullable = false)
//    private Set<Medicament> medicaments;
//    @ManyToOne
//    @JoinColumn(name="patient_id", referencedColumnName = "id")
//    private Patient patient;
//
//    public Diagnose() {
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
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public Set<Medicament> getMedicaments() {
//        return medicaments;
//    }
//
//    public void setMedicaments(Set<Medicament> medicaments) {
//        this.medicaments = medicaments;
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
