//Yung Ka Leong
package control;

import adt.QueueInterface;
import adt.LinkedQueue;
import entity.Patient;
import utility.PatientFile;
import boundary.PatientMaintenanceUI;

public class PatientMaintenance {
    private QueueInterface<Patient> patientQueue = new LinkedQueue<>();
    private PatientFile patientFile = new PatientFile();
    private PatientMaintenanceUI ui;

    public PatientMaintenance() {
        patientQueue = patientFile.readFromFile();
        ui = new PatientMaintenanceUI(this);
    }

    public void testPatientMaintenance() {
        int select;
        while (true) {
            select = ui.getMenuSelect();
            switch (select) {
                case 1:
                    patientRegister();
                    break;
                case 2:
                    ui.showReports(generateGenderReport());
                    break;
                case 3:
                    ui.showReports(generatePatientListReport());
                    break;
                case 4:
                    patientFile.saveToFile(patientQueue);
                    System.out.println("Exit...");
                    return;
                default:
                    System.out.println("-- Unknown Option! Please select again. --");
                    break;
            }
        }
    }

    public Patient getPatientById(String id) {
        LinkedQueue<Patient> tempQueue = new LinkedQueue<>();
        Patient foundPatient = null;
        while (!patientQueue.isEmpty()) {
            Patient patient = patientQueue.dequeue();
            if (patient.getId().equals(id)) {
                foundPatient = patient;
            }
            tempQueue.enqueue(patient);
        }
        while (!tempQueue.isEmpty()) {
            patientQueue.enqueue(tempQueue.dequeue());
        }
        return foundPatient;
    }

    public void addNewPatient(Patient newPatient) {
        if (getPatientById(newPatient.getId()) != null) {
            System.out.println("Patient ID " + newPatient.getId() + " already exists.");
            return;
        }
        patientQueue.enqueue(newPatient);
        patientFile.saveToFile(patientQueue);
        System.out.println("New Patient was added in the Queuing");
    }

    public void patientRegister() {
        Patient newPatient = ui.newPatientDetail();
        System.out.println();
        System.out.println("*** Patient Details ***");
        System.out.println(newPatient.toString());
        boolean isConfirm = ui.getConfirm();
        if (isConfirm) {
            addNewPatient(newPatient);
        } else {
            System.out.println("The Registration is Cancel.");
        }
    }

    public String generateGenderReport() {
        StringBuilder report = new StringBuilder();
        LinkedQueue<Patient> tempQueue = new LinkedQueue<>();
        int maleCount = 0, femaleCount = 0, totalPatients = 0;

        while (!patientQueue.isEmpty()) {
            Patient patient = patientQueue.dequeue();
            totalPatients++;
            if ("Male".equalsIgnoreCase(patient.getGender())) {
                maleCount++;
            } else if ("Female".equalsIgnoreCase(patient.getGender())) {
                femaleCount++;
            }
            tempQueue.enqueue(patient);
        }
        while (!tempQueue.isEmpty()) {
            patientQueue.enqueue(tempQueue.dequeue());
        }

        report.append("Gender Distribution Report\n");
        report.append("=========================\n");
        if (totalPatients == 0) {
            report.append("No patients registered.\n");
        } else {
            report.append(String.format("Total Patients: %d\n", totalPatients));
            report.append(String.format("Male Patients: %d (%.2f%%)\n", maleCount, totalPatients > 0 ? (maleCount * 100.0 / totalPatients) : 0));
            report.append(String.format("Female Patients: %d (%.2f%%)\n", femaleCount, totalPatients > 0 ? (femaleCount * 100.0 / totalPatients) : 0));
        }

        return report.toString();
    }

    public String generatePatientListReport() {
        StringBuilder report = new StringBuilder();
        LinkedQueue<Patient> tempQueue = new LinkedQueue<>();
        int totalPatients = 0;

        report.append("Patient List Report\n");
        report.append("==================\n");
        if (patientQueue.isEmpty()) {
            report.append("No patients registered.\n");
        } else {
            while (!patientQueue.isEmpty()) {
                Patient patient = patientQueue.dequeue();
                totalPatients++;
                report.append(String.format("Patient %d:\n", totalPatients));
                report.append(patient.toString()).append("\n\n");
                tempQueue.enqueue(patient);
            }
            while (!tempQueue.isEmpty()) {
                patientQueue.enqueue(tempQueue.dequeue());
            }
            report.insert(0, String.format("Total Patients Registered: %d\n\n", totalPatients));
        }

        return report.toString();
    }
}
