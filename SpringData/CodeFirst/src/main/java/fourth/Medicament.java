//package fourth;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "medicaments")
//public class Medicament {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(nullable = false)
//    private String name;
//    @ManyToOne
//    @JoinColumn(name="diagnose_id", referencedColumnName = "id")
//    private Diagnose diagnose;
//
//    public Medicament() {
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
//    public Diagnose getDiagnose() {
//        return diagnose;
//    }
//
//    public void setDiagnose(Diagnose diagnose) {
//        this.diagnose = diagnose;
//    }
//}
