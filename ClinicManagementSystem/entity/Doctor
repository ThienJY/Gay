//Ethan
package entity;

import adt.ListInterface;
import java.io.Serializable;

public class Doctor implements Serializable {
    private String id;
    private String name;
    private String specialty;
    private ListInterface<String> schedule;

    public Doctor(String id, String name, String specialty, ListInterface<String> schedule) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
    }

    public Doctor(String id, String name) {
        this.id = id;
        this.name = name;
        this.specialty = null;
        this.schedule = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public ListInterface<String> getSchedule() {
        return schedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setSchedule(ListInterface<String> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        if (specialty != null) {
            return String.format("Doctor{id=%s, name=%s, specialty=%s}", id, name, specialty);
        } else {
            return String.format("Doctor{id=%s, name=%s}", id, name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Doctor)) return false;
        Doctor other = (Doctor) obj;
        return id.equals(other.id);
    }
}
