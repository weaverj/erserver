package erserver.module3;

import erserver.module2.Patient;

import java.util.ArrayList;
import java.util.List;

public class Bed {

    private int bedId;
    private Patient patientAssigned;
    private List<Staff> staffAssigned;

    public Bed(int bedId) {
        this.bedId = bedId;
        staffAssigned = new ArrayList<>();
    }

    public void assignPatient(Patient patient) {
        this.patientAssigned = patient;
    }

    public void patientDischarged() {
        this.patientAssigned = null;
        this.staffAssigned = new ArrayList<>();
    }

    public Patient getPatientAssigned() {
        return patientAssigned;
    }

    public int getBedId() {
        return bedId;
    }

    public void assignStaffMember(Staff staff) {
        this.staffAssigned.add(staff);
    }

    public List getStaffAssigned() {
        return staffAssigned;
    }
}
