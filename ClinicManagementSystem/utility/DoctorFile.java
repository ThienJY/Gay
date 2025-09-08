//ETHAN
package utility;

import adt.ListInterface;
import adt.List;
import entity.Doctor;
import java.io.*;


public class DoctorFile {
    private static final String FILENAME = "Doctors.dat";

   
    public boolean saveToFile(ListInterface<Doctor> doctorList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            List<Doctor> tempList = new List<>();
          
            for (int i = 1; i <= doctorList.getNumberOfEntries(); i++) {
                Doctor doctor = doctorList.getEntry(i); 
                oos.writeObject(doctor); 
                tempList.add(doctor); 
            }
            
            doctorList.clear();
            for (int i = 1; i <= tempList.getNumberOfEntries(); i++) {
                doctorList.add(tempList.getEntry(i)); 
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
            return false;
        }
    }

    
    public ListInterface<Doctor> readFromFile() {
        ListInterface<Doctor> doctorList = new List<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                try {
                    Doctor doctor = (Doctor) ois.readObject();
                    doctorList.add(doctor); 
                } catch (EOFException e) {
                    break; 
                }
            }
        } catch (FileNotFoundException e) {
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return doctorList;
    }
}
