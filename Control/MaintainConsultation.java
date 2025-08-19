package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import adt.ArrayList;
import adt.ListInterface;
import boundary.ConsultationUI;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDateTime;

/**
 * MaintainConsultation (Control)
 * Author: Thien (student)
 * Demonstrates ECB with a custom Queue ADT for active waiting patients.
 */
public class MaintainConsultation {

    private final QueueInterface<Consultation> waitingQueue = new LinkedQueue<>();
    private final ListInterface<Consultation> history = new ArrayList<>();
    private final ConsultationUI ui = new ConsultationUI();

    public void run() {
        boolean cont = true;
        while (cont) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1: enqueueWalkIn(); break;
                case 2: scheduleAppointment(); break;
                case 3: ui.showQueue(queueToString()); break;
                case 4: serveNext(); break;
                case 5: peekNext(); break;
                case 6: ui.showHistory(historyToString()); break;
                case 7: ui.showReports(generateReports()); break;
                case 0: cont = false; break;
                default: ui.showMessage("Invalid choice.\n");
            }
        }
    }

    private void enqueueWalkIn() {
        Patient p = ui.inputPatient();
        Doctor d = ui.inputDoctor();
        String cid = ui.inputConsultationId();
        Consultation c = new Consultation(cid, p, d, LocalDateTime.now(), "SCHEDULED");
        waitingQueue.enqueue(c);
        ui.showMessage("Enqueued: " + c + "\n");
    }

    private void scheduleAppointment() {
        Patient p = ui.inputPatient();
        Doctor d = ui.inputDoctor();
        String cid = ui.inputConsultationId();
        String iso = ui.inputDateTimeISO();
        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(iso);
        } catch (Exception e) {
            ui.showMessage("Invalid datetime format. Example: 2025-08-14T09:30\n");
            return;
        }
        Consultation c = new Consultation(cid, p, d, dt, "SCHEDULED");
        waitingQueue.enqueue(c);
        ui.showMessage("Scheduled & queued: " + c + "\n");
    }

    private void serveNext() {
        Consultation c = waitingQueue.dequeue();
        if (c == null) {
            ui.showMessage("Queue empty.\n");
            return;
        }
        c.setStatus("COMPLETED");
        history.add(c);
        ui.showMessage("Served & completed: " + c + "\n");
    }

    private void peekNext() {
        Consultation c = waitingQueue.getFront();
        if (c == null) {
            ui.showMessage("Queue empty.\n");
        } else {
            ui.showMessage("Next to serve: " + c + "\n");
        }
    }

    private String queueToString() {
        StringBuilder sb = new StringBuilder();
        LinkedQueue<Consultation> temp = new LinkedQueue<>();
        int idx = 1;
        while (!waitingQueue.isEmpty()) {
            Consultation c = waitingQueue.dequeue();
            sb.append(idx++).append(") ").append(c).append("\n");
            temp.enqueue(c);
        }
        while (!temp.isEmpty()) waitingQueue.enqueue(temp.dequeue());
        return sb.toString();
    }

    private String historyToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= history.getNumberOfEntries(); i++) {
            sb.append(i).append(") ").append(history.getEntry(i)).append("\n");
        }
        return sb.toString();
    }

    private String generateReports() {
        int served = 0;
        java.time.LocalDate today = java.time.LocalDate.now();
        for (int i = 1; i <= history.getNumberOfEntries(); i++) {
            Consultation c = history.getEntry(i);
            if (c.getDateTime().toLocalDate().equals(today)) served++;
        }
        int queueLen = waitingQueue.size();
        return String.format("Total served today: %d\nCurrent waiting queue length: %d\n", served, queueLen);
    }

    public static void main(String[] args) {
        new MaintainConsultation().run();
    }
}
