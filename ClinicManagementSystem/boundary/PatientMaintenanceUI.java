//Yung Ka Leong
package boundary;

import entity.Patient;
import utility.DateValidator;
import control.PatientMaintenance;
import java.util.Scanner;
import java.time.LocalDate;

public class PatientMaintenanceUI {
    Scanner scanner = new Scanner(System.in);
    private final PatientMaintenance patientMaintenance;

    public PatientMaintenanceUI(PatientMaintenance patientMaintenance) {
        this.patientMaintenance = patientMaintenance;
    }

    public int getMenuSelect() {
        while (true) {
     System.out.println("== Patient Management Page ==\n" + "1. Patient register\n" + "2. Gender Report\n" + "3. Patient List Report\n" + "4. Exit");
            System.out.print(" > ");
            String input = scanner.nextLine().trim();
            try {
                int select = Integer.parseInt(input);
                if (select >= 1 && select <= 4) {
                    return select;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public Patient newPatientDetail() {
        String id = getPatientId();
        String name = getPatientName();
        String gender = getPatientGender();
        int age = getPatientAge();
        LocalDate birthOfDate = getPatientBirthDate();
        String phoneNumber = getPatientPhoneNumber();
        String bloodType = getPatientBloodType();
        return new Patient(id, name, gender, age, birthOfDate, phoneNumber, bloodType);
    }

    private String getPatientId() {
        System.out.print("Enter Patient ID (format Pxxx, e.g., P001): ");
        String id = scanner.nextLine().trim();
        if (!id.matches("P\\d{3}")) {
            System.out.println("Invalid ID format. ID must be Pxxx (e.g., P001).");
            return getPatientId();
        }
        if (patientMaintenance.getPatientById(id) != null) {
            System.out.println("Patient ID " + id + " already exists.");
            return getPatientId();
        }
        return id;
    }

    private String getPatientName() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        if (name != null && !name.trim().isEmpty())
            return name;
        else {
            System.out.println("-- The name can't be empty! Please Enter again. --");
            return getPatientName();
        }
    }

    private String getPatientGender() {
        System.out.print("Enter your gender (e.g. M = male,F = female): ");
        String gender = scanner.nextLine();
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
            return "Male";
        else if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female"))
            return "Female";
        else {
            System.out.println("-- Incorrect Gender! Please Enter again. --");
            return getPatientGender();
        }
    }

    private int getPatientAge() {
        System.out.print("Enter your age: ");
        String input = scanner.nextLine();
        try {
            int age = Integer.parseInt(input);
            if (age < 1) {
                System.out.println("-- The age is Too young! Please Enter again. --");
                return getPatientAge();
            } else if (age > 150) {
                System.out.println("-- The age is Too old! Please Enter again. --");
                return getPatientAge();
            } else {
                return age;
            }
        } catch (NumberFormatException e) {
            System.out.println("-- Invalid input! Please enter a valid number. --");
            return getPatientAge();
        }
    }

    private LocalDate getPatientBirthDate() {
        System.out.print("Enter your Date of Birth (e.g. 15/06/2010): ");
        String birthDateStr = scanner.nextLine();
        LocalDate birthDate = DateValidator.getValidDate(birthDateStr);
        if (birthDate != null)
            return birthDate;
        else {
            System.out.println("-- Invalid date or format! Please Enter again. --");
            return getPatientBirthDate();
        }
    }

    private String getPatientPhoneNumber() {
        System.out.print("Enter your Phone number (e.g. 012-3456789): ");
        String phoneNumber = scanner.nextLine();
        if (phoneNumber.matches("\\d{3}-\\d{7}$"))
            return phoneNumber;
        else {
            System.out.println("-- Invalid format! Please Enter again. --");
            return getPatientPhoneNumber();
        }
    }

    private String getPatientBloodType() {
        System.out.print("Enter your blood type [ A, B, AB, O ]: ");
        String bloodType = scanner.nextLine();
        if (bloodType.equalsIgnoreCase("A") || bloodType.equalsIgnoreCase("B") ||
            bloodType.equalsIgnoreCase("AB") || bloodType.equalsIgnoreCase("O")) {
            return bloodType.toUpperCase();
        } else {
            System.out.println("-- Unknown Blood Type! Please Enter again. --");
            return getPatientBloodType();
        }
    }

    public boolean getConfirm() {
        System.out.print("Are you confirm for this register? [(Y)es / (N)o ]: ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("Yes"))
            return true;
        else if (confirm.equalsIgnoreCase("N") || confirm.equalsIgnoreCase("No"))
            return false;
        else {
            System.out.println("-- Unknown Option! Please Enter again. --");
            return getConfirm();
        }
    }

    public void showReports(String reports) {
        System.out.println("\n=== Patient Reports ===\n" + reports);
    }
}
