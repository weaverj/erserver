package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;

import java.util.ArrayList;
import java.util.List;

public class InboundPatientTestDouble implements InboundPatientSource {

   private ArrayList<Patient> inbounds;

   public InboundPatientTestDouble() {
      this.inbounds = new ArrayList<>();
   }

   public void simulateNewInboundPatient(Patient inbound) {
      inbounds.add(inbound);
   }

   @Override
   public List<Patient> currentInboundPatients() {
      return inbounds;
   }

   @Override
   public void informOfPatientArrival(int transportId) {
      // implement later when testing needed for this function.
   }
}
