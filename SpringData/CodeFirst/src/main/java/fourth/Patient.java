//package fourth;
//
//import javax.persistence.*;
//import javax.sql.rowset.serial.SerialBlob;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//@Entity
//@Table(name= "patients")
//public class Patient {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
//    private String address;
//    private String email;
//    @Column(name = "date_of_birth", nullable = false)
//    private LocalDate dateOfBirth;
//    @Column(columnDefinition = "BLOB")
//    private SerialBlob picture;
//    @Column(name = "has_medical_insurance", nullable = false)
//    private boolean hasMedicalInsurance;
//    @OneToMany(mappedBy = "patient", targetEntity = Diagnose.class)
//    private Set<Diagnose> diagnoses;
//    @OneToMany(mappedBy = "patient", targetEntity =Visitation.class)
//    private Set<Visitation> visitations;
//
//    public Patient() {
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
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
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
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public SerialBlob getPicture() {
//        return picture;
//    }
//
//    public void setPicture(SerialBlob picture) {
//        this.picture = picture;
//    }
//
//    public boolean isHasMedicalInsurance() {
//        return hasMedicalInsurance;
//    }
//
//    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
//        this.hasMedicalInsurance = hasMedicalInsurance;
//    }
//
//    public Set<Diagnose> getDiagnoses() {
//        return diagnoses;
//    }
//
//    public void setDiagnoses(Set<Diagnose> diagnoses) {
//        this.diagnoses = diagnoses;
//    }
//
//    public Set<Visitation> getVisitations() {
//        return visitations;
//    }
//
//    public void setVisitations(Set<Visitation> visitations) {
//        this.visitations = visitations;
//    }
//}
