/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Ethan
 */
import adt.ListInterface;

public class Doctor {
    private String id;
    private String name;
    private String specialty;
    private ListInterface<String> schedule; // Store schedules as strings (e.g., "Monday 9-12")

    public Doctor(String id, String name, String specialty, ListInterface<String> schedule) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
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
        return "Doctor{id=" + id + ", name=" + name + ", specialty=" + specialty + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Doctor)) return false;
        Doctor other = (Doctor) obj;
        return id.equals(other.id);
    }
}