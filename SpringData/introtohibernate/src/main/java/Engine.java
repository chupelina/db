import entities.*;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private final BufferedReader reader;


    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
////         Ex 2. Change casing
//        ChangeCasingEx2();
//
////         Ex 3. Contains Employee
//        try {
//            containsEmployeeEx3();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////         Ex 4.Employees with Salary Over 50 000
//        employeesWithSalaryOver50000Ex4();
//
////         Ex 5.Employees from Department
//        employeesFromDepartmentEx5();

////        Ex 6.Adding a New Address and Updating Employee
//        try {
//            AddingANewAddressAndUpdatingEmployeeEx6();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        Ex 7.Addresses with Employee Count
//        addressesWithEmployeeCountEx7();

////         Ex 8.Get Employee with Project
//        try {
//            getEmployeeWithProjectEx8();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////         Ex 9.Find Latest 10 Projects
//        findLatest10ProjectsEx9();
//
////         Ex 10.Increase Salaries
//        increaseSalariesEx10();
//
////         Ex 11.Find Employees by First Name
//        try {
//            findEmployeesFirstNameEx11();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

////         Ex 12.Employees Maximum Salaries
//          employeesMaximumSalariesEx12();

//        ex 13
        try {
            removeTownEx13();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void removeTownEx13() throws IOException {
        System.out.println("Enter town for delete:");
        String townName = reader.readLine();

        this.entityManager.getTransaction().begin();

        Town townDelete = entityManager.createQuery("select t FROM Town t "
                + "WHERE t.name = :town", Town.class)
                .setParameter("town", townName)
                .getSingleResult();

        List<Address> addressesToDelete = this.entityManager
                .createQuery("select a FROM Address a WHERE a.town.id= :id", Address.class)
                .setParameter("id", townDelete.getId())
                .getResultList();

        addressesToDelete
                .forEach(t -> t.getEmployees()
                        .forEach(em -> em.setAddress(null)));

        addressesToDelete.forEach(this.entityManager::remove);
        this.entityManager.remove(townDelete);

        int countDeletedAddresses = addressesToDelete.size();
        System.out.printf("%d address%s in %s deleted",
                countDeletedAddresses,
                countDeletedAddresses == 1 ? "" : "es",
                townName);

        this.entityManager.getTransaction().commit();
    }

    private void employeesMaximumSalariesEx12() {
        class  dep{
            String name;
            double max;
        }


        entityManager
                .createQuery("SELECT e.department.name, MAX(e.salary) " +
                                "FROM Employee  e " +
                                "GROUP BY e.department.name \n " +
                                "HAVING MAX(e.salary) NOT BETWEEN :low AND :up",
                        dep.class
                )
                .setParameter("low", 30000)
                .setParameter("up", 70000)
                .getResultStream().forEach( d-> System.out.println(d.name+" "+d.max));
        System.out.println( );


    }

    private void findEmployeesFirstNameEx11() throws IOException {
        System.out.println(" Enter  the beginning of the first name");
        String beginName = reader.readLine();

        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager
                .createQuery("select  e FROM Employee e "
                        + "WHERE e.firstName LIKE CONCAT(:letters, '%')", Employee.class)
                .setParameter("letters", beginName)
                .getResultList();

        employees.forEach(empl -> System.out.printf("%s %s - %s - ($%s)%n",
                empl.getFirstName(),
                empl.getLastName(),
                empl.getJobTitle(),
                empl.getSalary()));
    }

    private void increaseSalariesEx10() {
        entityManager.getTransaction().begin();
        int affectedRow = entityManager
                .createQuery("update Employee e " +
                        "set e.salary=e.salary*1.12 " +
                        "where  e.department.id in (1,2,4,11)")
                .executeUpdate();


        entityManager.getTransaction().commit();

        entityManager.createQuery("select e from Employee e " +
                "where  e.department.id in (1,2,4,11)", Employee.class)
                .getResultStream().forEach(e ->
                System.out.printf("%s %s ($%.2f)%n",
                        e.getFirstName(), e.getLastName(),
                        e.getSalary()));
    }

    private void findLatest10ProjectsEx9() {

        List<Project> projects = this.entityManager
                .createQuery("SELECT p FROM Project p ORDER BY p.startDate Desc,p.name", Project.class)
                .setMaxResults(10)
                .getResultList();
        projects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project ->
                        System.out.printf("Project name: %s%n" +
                                        "\tProject Description: %s%n" +
                                        "\tProject Start Date: %s %n" +
                                        "\tProject End Date: %s%n",
                                project.getName(),
                                project.getDescription(),
                                project.getStartDate(),

                                project.getEndDate()));
    }

    private void getEmployeeWithProjectEx8() throws IOException {
        System.out.println("Enter valid employeed Id");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = entityManager.find(Employee.class, id);

        System.out.printf("%s %s - %s%n", employee.getFirstName(),
                employee.getLastName(), employee.getJobTitle());
        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("\t%s%n", p.getName()));

    }

    private void addressesWithEmployeeCountEx7() {
        List<Address> addresses = entityManager
                .createQuery("select a from Address a " +
                        "order by a.employees.size desc ", Address.class)
                .setMaxResults(10).getResultList();


        addresses.forEach(address -> {
            System.out.printf("%s ,%s - %d employees %n",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
        });
    }

    private void AddingANewAddressAndUpdatingEmployeeEx6() throws IOException {

        Address address = createAddress("Vitoshka 15");
        System.out.println("Enter employee lastName :");
        String lastName = reader.readLine();
        Employee employee = entityManager
                .createQuery("select e from Employee e " +
                        "where e.lastName= :name ", Employee.class)
                .setParameter("name", lastName).getSingleResult();
        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.flush();
        entityManager.getTransaction().commit();

    }

    private Address createAddress(String adressText) {
        Address address = new Address();
        address.setText(adressText);
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        return address;

    }

    private void employeesFromDepartmentEx5() {
        entityManager
                .createQuery("select e from Employee e " +
                        "where e.department.name = 'Research and Development' " +
                        "order by e.salary,e.id", Employee.class)
                .getResultStream()
                .forEach(employee -> System.out.printf("%s %s from Research and Development - $%.2f%n"
                        , employee.getFirstName(), employee.getLastName(), employee.getSalary()
                ));


    }

    private void employeesWithSalaryOver50000Ex4() {
        List<Employee> employees = entityManager
                .createQuery("select e from Employee e where e.salary>50000")
                .getResultList();
        employees.forEach(employee -> System.out.println(employee.getFirstName()));

    }

    private void containsEmployeeEx3() throws IOException {
        System.out.println("Enter employee full name :");
        String fullName = reader.readLine();

        List<Employee> employees = entityManager.createQuery("select e from Employee e " +
                "where concat(e.firstName,' ',e.lastName)= :name ", Employee.class)
                .setParameter("name", fullName)
                .getResultList();
        System.out.println(employees.size() == 0 ? "NO" : "YES");

    }

    private void ChangeCasingEx2() {
        List<Town> towns = entityManager
                .createQuery("SELECT t FROM Town t " +
                        "where  length(t.name)<=5 ", Town.class)
                .getResultList();
        entityManager.getTransaction().begin();
        towns.forEach(entityManager::detach);

        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());

        }

        towns.forEach(entityManager::merge);
        entityManager.flush();

        entityManager.getTransaction().commit();
    }
}
