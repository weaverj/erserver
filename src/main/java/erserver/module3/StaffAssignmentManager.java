package erserver.module3;

import erserver.module2.Patient;

import java.util.*;

public class StaffAssignmentManager {

   private ArrayList<Staff> shiftStaff;
   private ArrayList<Bed> beds;
   private HashMap<Bed, List<Staff>> bedStaffAssignments;

   public StaffAssignmentManager() {
      StaffRepository staffRepo = new StaffRepository();
      shiftStaff = new ArrayList<>();
      shiftStaff.addAll(staffRepo.getShiftStaff());
      beds = new ArrayList<>();
      BedRepository bedRepo = new BedRepository();
      beds.addAll(bedRepo.getAllBeds());
      bedStaffAssignments = new HashMap<>();
   }

   public List<Staff> getShiftStaff() {
      return Collections.unmodifiableList(shiftStaff);
   }

   public List<Staff> getAvailableStaff() {
      ArrayList<Staff> availableStaff = new ArrayList<>();
      for (Staff staff : shiftStaff) {
         boolean staffAssigned = false;
         for (Map.Entry<Bed, List<Staff>> bedListEntry : bedStaffAssignments.entrySet()) {
            if (bedListEntry.getValue().contains(staff)) {
               staffAssigned = true;
            }
         }
         if (!staffAssigned) {
            availableStaff.add(staff);
         }
      }
      return Collections.unmodifiableList(availableStaff);
   }

   public List<Staff> getPhysiciansOnDuty() {
      ArrayList<Staff> physicians = new ArrayList<>();
      for (Staff staff : shiftStaff) {
         if (staff.getRole().equals(StaffRole.DOCTOR)) {
            physicians.add(staff);
         }
      }
      return Collections.unmodifiableList(physicians);
   }

   public List getBeds() {
      return Collections.unmodifiableList(beds);
   }

   public List<Bed> getAvailableBeds() {
      ArrayList<Bed> availableBeds = new ArrayList<>();
      for (Bed bed : beds) {
         if (bed.getPatientAssigned() == null) {
            availableBeds.add(bed);
         }
      }
      return Collections.unmodifiableList(availableBeds);
   }

   public void assignPatientToBed(Patient patient, Bed bed) {
      bed.assignPatient(patient);
   }

   public void assignStaffMemberToBed(Staff staff, Bed bed) {
      List<Staff> currentlyAssignedToBed = bedStaffAssignments.get(bed);
      if (currentlyAssignedToBed == null) {
         currentlyAssignedToBed = new ArrayList<>();
      }
      currentlyAssignedToBed.add(staff);
   }


}
