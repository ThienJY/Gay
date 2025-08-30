package main;

import boundary.ConsultationUI;
import boundary.PatientMaintenanceUI;
import boundary.DoctorManagementUI;
import control.MaintainConsultation;
import control.PatientMaintenance;
import control.DoctorManagementControl;
import utility.MessageUI;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PatientMaintenance patientMaintenance = new PatientMaintenance();
        DoctorManagementControl doctorManagement = new DoctorManagementControl();
        while (true) {
            System.out.println("\n=== Clinic Management System ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Consultation Management");
            System.out.println("3. Doctor Management");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidChoiceMessage();
                continue;
            }

            switch (choice) {
                case 1:
                    patientMaintenance.testPatientMaintenance();
                    break;
                case 2:
                    MaintainConsultation consultation = new MaintainConsultation(patientMaintenance, doctorManagement);
                    consultation.run();
                    break;
                case 3:
                    DoctorManagementUI doctorUI = new DoctorManagementUI();
                    doctorUI.start();
                    break;
                case 4:
                    MessageUI.displayExitMessage();
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        }
    }
}
