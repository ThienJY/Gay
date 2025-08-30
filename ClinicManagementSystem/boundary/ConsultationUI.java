//Thien Jie Yao 2401621
package boundary;

import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import control.PatientMaintenance;
import control.DoctorManagementControl;
import java.util.Scanner;

public class ConsultationUI {
    private final Scanner sc = new Scanner(System.in);
    private final PatientMaintenance patientMaintenance;
    private final DoctorManagementControl doctorManagement;

    public ConsultationUI(PatientMaintenance patientMaintenance, DoctorManagementControl doctorManagement) {
        this.patientMaintenance = patientMaintenance;
        this.doctorManagement = doctorManagement;
    }

    public int getMenuChoice() {
        System.out.println("\nCONSULTATION MANAGEMENT MENU");
        System.out.println("1. Assign Consultation");
        System.out.println("2. Schedule appointment");
        System.out.println("3. View waiting queue");
        System.out.println("4. Serve next patient");
        System.out.println("5. Peek next patient");
        System.out.println("6. View consultation history (completed)");
        System.out.println("7. Generate Report");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter choice: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {}
        System.out.println();
        return choice;
    }

    public Patient inputPatient() {
        while (true) {
            System.out.print("Enter Patient ID (format Pxxx, e.g., P001): ");
            String pid = sc.nextLine().trim();
            if (!pid.matches("P\\d{3}")) {
                System.out.println("Invalid ID format. ID must be Pxxx (e.g., P001).");
                continue;
            }
            Patient patient = patientMaintenance.getPatientById(pid);
            if (patient == null) {
                System.out.println("Patient ID " + pid + " not found.");
                continue;
            }
            return patient;
        }
    }

    public Doctor inputDoctor() {
        while (true) {
            System.out.print("Enter Doctor ID (format Dxxx, e.g., D001): ");
            String did = sc.nextLine().trim();
            if (!did.matches("D\\d{3}")) {
                System.out.println("Invalid ID format. ID must be Dxxx (e.g., D001).");
                continue;
            }
            Doctor doctor = doctorManagement.getDoctor(did);
            if (doctor == null) {
                System.out.println("Doctor ID " + did + " not found.");
                continue;
            }
            return new Doctor(doctor.getId(), doctor.getName());
        }
    }

    public String inputConsultationId() {
        System.out.print("Enter Consultation ID: ");
        return sc.nextLine().trim();
    }

    public String inputDateTimeISO() {
        System.out.print("Enter DateTime (YYYY-MM-DDTHH:MM): ");
        return sc.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showQueue(String str) {
        System.out.println("Current Waiting Queue:");
        System.out.println(str);
    }

    public void showHistory(String str) {
        System.out.println("Completed Consultations:");
        System.out.println(str);
    }

    public void showReports(String str) {
        System.out.println("\n=== Reports ===\n" + str);
    }
}
