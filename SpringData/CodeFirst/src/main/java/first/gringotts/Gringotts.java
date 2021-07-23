package first.gringotts;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "gringotts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Gringotts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;
    @Column(name = "notes", length = 1000)
    private String notes;
    @Column(name = "age", columnDefinition = "INT UNSIGNED", nullable = false)
    private int age;
    @Column(name = "magic_wand_creator ", length = 100)
    private String magicWandCreator;
    @Column(name = "magic_wand_size")
    private int magicWandSize;
    @Column(name = "deposit_group", length = 20)
    private String depositGroup;
    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;
    @Column(name = "deposit_amount")
    private double depositAmount;
    @Column(name = "deposit_interest")
    private double depositInterest;
    @Column(name = "deposit_charge")
    private double depositCharge;
    @Column(name = "deposit_expiration_date")
    private LocalDateTime depositExpirationDate;
    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;


    public Gringotts() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public int getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(int magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public LocalDateTime getDepositStartDate() {
        return depositStartDate;
    }

    public void setDepositStartDate(LocalDateTime depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(double depositInterest) {
        this.depositInterest = depositInterest;
    }

    public double getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(double depositCharge) {
        this.depositCharge = depositCharge;
    }

    public LocalDateTime getDepositExpirationDate() {
        return depositExpirationDate;
    }

    public void setDepositExpirationDate(LocalDateTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public boolean isDepositExpired() {
        return isDepositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }

    @Override
    public String toString() {
        return "Gringotts{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", notes=" + notes +
                ", age=" + age +
                ", magicWandCreator='" + magicWandCreator + '\'' +
                ", magicWandSize=" + magicWandSize +
                ", depositGroup='" + depositGroup + '\'' +
                ", depositStartDate=" + depositStartDate +
                ", depositAmount=" + depositAmount +
                ", depositInterest=" + depositInterest +
                ", depositCharge=" + depositCharge +
                ", depositExpirationDate=" + depositExpirationDate +
                ", isDepositExpired=" + isDepositExpired +
                '}';
    }
}
