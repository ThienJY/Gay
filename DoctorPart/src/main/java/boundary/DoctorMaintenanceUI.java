/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

/**
 *
 * @author Ethan
 */
import control.DoctorMaintenance;
import entity.Doctor;
import adt.ListInterface;
import java.util.Scanner;

public class DoctorMaintenanceUI {
    private DoctorMaintenance control;
    private Scanner scanner;

    public DoctorMaintenanceUI() {
        control = new DoctorMaintenance();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nDoctor Management Module");
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. View All Doctors");
            System.out.println("5. Generate Specialty Summary Report");
            System.out.println("6. Generate Doctor Schedule Availability Report");
            System.out.println("7. Exit");
            System.out.println("Enter choice: ");
            String choiceInput = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    updateDoctor();
                    break;
                case 3:
                    deleteDoctor();
                    break;
                case 4:
                    viewDoctor();
                    break;
                case 5:
                    System.out.println(control.generateSpecialtySummaryReport());
                    break;
                case 6:
                    System.out.println("Enter day (e.g., Monday): ");
                    String day = scanner.nextLine();
                    System.out.println(control.generateScheduleAvailabilityReport(day));
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private void addDoctor() {
        System.out.println("Enter Doctor ID (format Dxxx, e.g., D001): ");
        String id = scanner.nextLine();
        System.out.println("Enter Doctor Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Specialty: ");
        String specialty = scanner.nextLine();
        System.out.println("Enter Schedule (e.g., Monday 9-12,Tuesday 10-13): ");
        String[] schedule = scanner.nextLine().split(",");
        String result = control.addDoctor(id, name, specialty, schedule);
        System.out.println(result);
    }

    private void updateDoctor() {
        System.out.println("Enter Doctor ID to update (format Dxxx, e.g., D001): ");
        String id = scanner.nextLine();
        System.out.println("Enter New Doctor Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter New Specialty: ");
        String specialty = scanner.nextLine();
        System.out.println("Enter New Schedule (e.g., Monday 9-12,Tuesday 10-13): ");
        String[] schedule = scanner.nextLine().split(",");
        String result = control.updateDoctor(id, name, specialty, schedule);
        System.out.println(result);
    }

    private void deleteDoctor() {
        System.out.println("Enter Doctor ID to delete (format Dxxx, e.g., D001): ");
        String id = scanner.nextLine();
        String result = control.deleteDoctor(id);
        System.out.println(result);
    }

    private void viewDoctor() {
        ListInterface<Doctor> doctors = control.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            System.out.println("All Doctors:");
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                System.out.println(doctor.toString());
                System.out.println("Schedule:");
                ListInterface<String> schedule = doctor.getSchedule();
                if (schedule.isEmpty()) {
                    System.out.println("  - No schedule assigned.");
                } else {
                    for (int j = 0; j < schedule.size(); j++) {
                        System.out.println("  - " + schedule.get(j));
                    }
                }
                System.out.println(); // Blank line for readability
            }
        }
    }
}