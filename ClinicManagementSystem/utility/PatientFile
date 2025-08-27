package utility;

import adt.QueueInterface;
import adt.LinkedQueue;
import entity.Patient;
import java.io.*;

public class PatientFile {
    private static final String FILENAME = "Patients.dat";

    public void saveToFile(QueueInterface<Patient> patientQueue) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            LinkedQueue<Patient> tempQueue = new LinkedQueue<>();
            while (!patientQueue.isEmpty()) {
                Patient patient = patientQueue.dequeue();
                oos.writeObject(patient);
                tempQueue.enqueue(patient);
            }
            while (!tempQueue.isEmpty()) {
                patientQueue.enqueue(tempQueue.dequeue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueueInterface<Patient> readFromFile() {
        QueueInterface<Patient> patientQueue = new LinkedQueue<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                try {
                    Patient patient = (Patient) ois.readObject();
                    patientQueue.enqueue(patient);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patientQueue;
    }
}
