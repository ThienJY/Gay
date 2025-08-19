package boundary;

import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.util.Scanner;

/**
 * Consultation UI (console)
 * Author: Thien (student)
 */
public class ConsultationUI {
    private final Scanner sc = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\nCONSULTATION MANAGEMENT MENU");
        System.out.println("1. Enqueue walk-in patient");
        System.out.println("2. Schedule appointment (future)");
        System.out.println("3. View waiting queue");
        System.out.println("4. Serve next patient");
        System.out.println("5. Peek next patient");
        System.out.println("6. View consultation history (completed)");
        System.out.println("7. Reports (summary)");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {}
        System.out.println();
        return choice;
    }

    public Patient inputPatient() {
        System.out.print("Enter Patient ID: ");
        String pid = sc.nextLine().trim();
        System.out.print("Enter Patient Name: ");
        String pname = sc.nextLine().trim();
        return new Patient(pid, pname);
    }

    public Doctor inputDoctor() {
        System.out.print("Enter Doctor ID: ");
        String did = sc.nextLine().trim();
        System.out.print("Enter Doctor Name: ");
        String dname = sc.nextLine().trim();
        return new Doctor(did, dname);
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

    public void showReports(String report) {
        System.out.println("\n=== Reports ===\n" + report);
    }
}
