package erserver.module3;

import erserver.module2.Patient;

import java.util.ArrayList;
import java.util.List;

public class Staff {

    private String name;
    private StaffRole role;

    private List<Patient> assignedPatients;
    private List<Bed> assignedBeds;

    public Staff(String name, StaffRole role) {
        this.name = name;
        this.role = role;
        this.assignedBeds = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public StaffRole getRole() {
        return role;
    }

    public void assignToBed(Bed bed) {
        assignedBeds.add(bed);
    }

    public void assignToPatient(Patient patient) {
        assignedPatients.add(patient);
    }

    public void clearAssignment(Patient patient) {
        assignedPatients.remove(patient);
    }

    public void clearAssignment(Bed bed) {
        assignedBeds.remove(bed);
    }
}
