//Yung Ka Leong
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Patient implements Serializable {
    private String id;
    private String name;
    private String gender;
    private int age;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String bloodType;
    private static final DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
        this.gender = null;
        this.age = 0;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.bloodType = null;
    }

    public Patient(String id, String name, String gender, int age, LocalDate dateOfBirth, String phoneNumber, String bloodType) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append(String.format("ID               : %s\n", id));
        details.append(String.format("Name             : %s\n", name));
        if (gender != null) {
            details.append(String.format("Gender           : %s\n", gender));
            details.append(String.format("Age              : %d\n", age));
            details.append(String.format("Date of Birth    : %s\n", dateOfBirth != null ? dateOfBirth.format(myFormat) : "N/A"));
            details.append(String.format("Phone Number     : %s\n", phoneNumber != null ? phoneNumber : "N/A"));
            details.append(String.format("Blood Type       : %s\n", bloodType != null ? bloodType : "N/A"));
        }
        return details.toString();
    }
}
