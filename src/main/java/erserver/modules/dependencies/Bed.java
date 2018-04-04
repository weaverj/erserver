package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;

public class Bed {

   private int bedId;
   private boolean criticalCare;
   private Patient patientAssigned;

   public Bed(int bedId, boolean criticalCare) {
      this.bedId = bedId;
      this.criticalCare = criticalCare;
   }

   public void assignPatient(Patient patient) {
      this.patientAssigned = patient;
   }

   public void patientDischarged() {
      this.patientAssigned = null;
   }

   public Patient getPatientAssigned() {
      return patientAssigned;
   }

   public int getBedId() {
      return bedId;
   }

   public boolean isCriticalCare() {
      return criticalCare;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Bed bed = (Bed) o;

      return bedId == bed.bedId;
   }

   @Override
   public int hashCode() {
      return bedId;
   }
}
