package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;

public class AssignPatientToBedCommand {

   private StaffAssignmentManager staffAssignmentManager;
   private InboundPatientSource inboundPatientSource;

   public AssignPatientToBedCommand(StaffAssignmentManager staffAssignmentManager, InboundPatientSource inboundPatientSource) {
      this.staffAssignmentManager = staffAssignmentManager;
      this.inboundPatientSource = inboundPatientSource;
   }

   public void assignPatientToBed(int transportId, int bedId) {
      Bed bed = staffAssignmentManager.getBedById(bedId);
      Patient patient = getPatientByTransport(transportId);
      staffAssignmentManager.assignPatientToBed(patient, bed);
      inboundPatientSource.informOfPatientArrival(transportId);
   }

   private Patient getPatientByTransport(int transportId) {
      for (Patient patient : inboundPatientSource.currentInboundPatients()) {
         if (patient.getTransportId() == transportId) {
            return patient;
         }
      }
      throw new RuntimeException("Unable to find inbound patient " + transportId);
   }


}
