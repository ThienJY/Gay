/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Ethan
 */
import adt.Arraylist;
import adt.ListInterface;
import entity.Doctor;
import utility.DateUtils;

public class DoctorMaintenance {
    private ListInterface<Doctor> doctors;

    public DoctorMaintenance() {
        doctors = new Arraylist<>();
        // Initialize with sample data
        ListInterface<String> schedule1 = new Arraylist<>();
        schedule1.add("Monday 9-12");
        schedule1.add("Wednesday 14-17");
        doctors.add(new Doctor("D001", "Dr. Alice Smith", "Cardiology", schedule1));

        ListInterface<String> schedule2 = new Arraylist<>();
        schedule2.add("Tuesday 10-13");
        schedule2.add("Thursday 15-18");
        doctors.add(new Doctor("D002", "Dr. Bob Johnson", "Pediatrics", schedule2));
    }

    public String addDoctor(String id, String name, String specialty, String[] scheduleInputs) {
        // Validate ID format (Dxxx)
        if (!id.matches("D\\d{3}")) {
            return "Invalid ID format. ID must be Dxxx (e.g., D001).";
        }
        // Check for duplicate ID
        if (getDoctor(id) != null) {
            return "Doctor ID " + id + " already exists.";
        }
        // Validate name and specialty
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        if (specialty == null || specialty.trim().isEmpty()) {
            return "Specialty cannot be empty.";
        }
        // Process schedule inputs
        ListInterface<String> schedule = new Arraylist<>();
        for (String input : scheduleInputs) {
            String[] parts = input.trim().split("\\s+");
            if (parts.length == 2 && DateUtils.isValidSchedule(parts[0], parts[1])) {
                schedule.add(parts[0] + " " + parts[1]);
            } else {
                return "Invalid schedule entry: " + input + ". Format must be 'Day HH-HH'.";
            }
        }
        // Add doctor if all validations pass
        Doctor doctor = new Doctor(id, name.trim(), specialty.trim(), schedule);
        doctors.add(doctor);
        return "Doctor added successfully.";
    }

    public Doctor getDoctor(String id) {
        // Validate ID format
        if (!id.matches("D\\d{3}")) {
            return null;
        }
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public String updateDoctor(String id, String name, String specialty, String[] scheduleInputs) {
        // Validate ID format
        if (!id.matches("D\\d{3}")) {
            return "Invalid ID format. ID must be Dxxx (e.g., D001).";
        }
        Doctor doctor = getDoctor(id);
        if (doctor == null) {
            return "Doctor ID " + id + " not found.";
        }
        // Validate name and specialty
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        if (specialty == null || specialty.trim().isEmpty()) {
            return "Specialty cannot be empty.";
        }
        // Process schedule inputs
        ListInterface<String> schedule = new Arraylist<>();
        for (String input : scheduleInputs) {
            String[] parts = input.trim().split("\\s+");
            if (parts.length == 2 && DateUtils.isValidSchedule(parts[0], parts[1])) {
                schedule.add(parts[0] + " " + parts[1]);
            } else {
                return "Invalid schedule entry: " + input + ". Format must be 'Day HH-HH'.";
            }
        }
        // Update doctor
        doctor.setName(name.trim());
        doctor.setSpecialty(specialty.trim());
        doctor.setSchedule(schedule);
        return "Doctor updated successfully.";
    }

    public String deleteDoctor(String id) {
        // Validate ID format
        if (!id.matches("D\\d{3}")) {
            return "Invalid ID format. ID must be Dxxx (e.g., D001).";
        }
        Doctor doctor = getDoctor(id);
        if (doctor != null) {
            doctors.remove(doctor);
            return "Doctor deleted successfully.";
        }
        return "Doctor ID " + id + " not found.";
    }

    public ListInterface<Doctor> getAllDoctors() {
        return doctors;
    }

    public String generateSpecialtySummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("Specialty Summary Report\n");
        report.append("=======================\n");
        ListInterface<String> specialties = new Arraylist<>();
        ListInterface<Integer> doctorCounts = new Arraylist<>();
        ListInterface<Integer> totalHours = new Arraylist<>();

        // Collect unique specialties
        for (int i = 0; i < doctors.size(); i++) {
            String specialty = doctors.get(i).getSpecialty();
            if (!specialtiesContains(specialties, specialty)) {
                specialties.add(specialty);
            }
        }

        // Calculate counts and hours per specialty
        for (int i = 0; i < specialties.size(); i++) {
            String specialty = specialties.get(i);
            int count = 0;
            int hours = 0;
            for (int j = 0; j < doctors.size(); j++) {
                Doctor doctor = doctors.get(j);
                if (doctor.getSpecialty().equals(specialty)) {
                    count++;
                    ListInterface<String> schedule = doctor.getSchedule();
                    for (int k = 0; k < schedule.size(); k++) {
                        String[] parts = schedule.get(k).split("\\s+");
                        String[] times = parts[1].split("-");
                        int startHour = Integer.parseInt(times[0]);
                        int endHour = Integer.parseInt(times[1]);
                        hours += endHour - startHour;
                    }
                }
            }
            doctorCounts.add(count);
            totalHours.add(hours);
        }

        // Generate report
        for (int i = 0; i < specialties.size(); i++) {
            report.append("Specialty: ").append(specialties.get(i)).append("\n");
            report.append("  - Number of Doctors: ").append(doctorCounts.get(i)).append("\n");
            report.append("  - Total Scheduled Hours: ").append(totalHours.get(i)).append("\n");
        }
        return report.toString();
    }

    public String generateScheduleAvailabilityReport(String day) {
        // Validate day
        boolean validDay = false;
        for (String validDayStr : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}) {
            if (validDayStr.equalsIgnoreCase(day)) {
                validDay = true;
                break;
            }
        }
        if (!validDay) {
            return "Invalid day: " + day + ". Use Monday, Tuesday, etc.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Doctor Schedule Availability Report (").append(day).append(")\n");
        report.append("===========================================\n");
        boolean found = false;
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            ListInterface<String> schedule = doctor.getSchedule();
            for (int j = 0; j < schedule.size(); j++) {
                String[] parts = schedule.get(j).split("\\s+");
                if (parts[0].equalsIgnoreCase(day)) {
                    if (!found) {
                        found = true;
                    }
                    report.append("Doctor: ").append(doctor.getName()).append(" (").append(doctor.getSpecialty()).append(")\n");
                    report.append("  - ").append(schedule.get(j)).append("\n");
                    break;
                }
            }
        }
        if (!found) {
            report.append("No doctors available on ").append(day).append("\n");
        }
        return report.toString();
    }

    private boolean specialtiesContains(ListInterface<String> specialties, String specialty) {
        for (int i = 0; i < specialties.size(); i++) {
            if (specialties.get(i).equals(specialty)) {
                return true;
            }
        }
        return false;
    }
}
