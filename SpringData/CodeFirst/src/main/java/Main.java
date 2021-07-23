
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        String PAYMENT_SYSTEM_DB = "payment";
        String GRINGOTTS_DB = "gringotts";
        String SALES_DB = "sales";
        String UNIVERSITY_DB = "university";
        String HOSPITAL_DB = "hospital";

// I have no idea how to make that working more clearly, sorry about that!

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory(PAYMENT_SYSTEM_DB);
        EntityManager em = emf.createEntityManager();


    }
}
