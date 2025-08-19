package entity;

import java.time.LocalDateTime;

/**
 * Consultation entity
 * Author: Thien (student)
 */
public class Consultation {
    private String consultationId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String status; // SCHEDULED, COMPLETED, CANCELLED

    public Consultation(String consultationId, Patient patient, Doctor doctor, LocalDateTime dateTime, String status) {
        this.consultationId = consultationId;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getConsultationId() { return consultationId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
    public void setDateTime(LocalDateTime dt) { this.dateTime = dt; }
    public void setDoctor(Doctor d) { this.doctor = d; }

    @Override
    public String toString() {
        return String.format("%s | %s -> %s at %s | %s",
                consultationId,
                patient.getName(),
                doctor.getName(),
                dateTime,
                status);
    }
}
