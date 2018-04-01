package erserver.module3;

import erserver.module2.Patient;
import erserver.module3.vendorpagersystem.PagerSystem;
import erserver.module3.vendorpagersystem.PagerTransport;

import java.util.ArrayList;
import java.util.List;

public class AlertScanner {

   private static final String ADMIN_ON_CALL_DEVICE = "111-111-1111";

   private StaffAssignmentManager staffAssignmentManager;
   private InboundPatientController inboundPatientController;
   private ArrayList<Integer> criticalPatientNotificationsSent;

   public AlertScanner(StaffAssignmentManager staffAssignmentManager, InboundPatientController inboundPatientController) {
      this.staffAssignmentManager = staffAssignmentManager;
      this.inboundPatientController = inboundPatientController;
      criticalPatientNotificationsSent = new ArrayList<>();
   }

   public void scan() {
      System.out.println("Scanning for situations requiring alerting...");
      List<Patient> inbound = inboundPatientController.currentInboundPatients();
      for (Patient patient : inbound) {
         if (Priority.RED.equals(patient.getPriority())) {
            if (!criticalPatientNotificationsSent.contains(patient.getTransportId())) {
               alertForNewCriticalPatient(patient);
            }
         }
      }
   }

   private void alertForNewCriticalPatient(Patient patient) {
      try {
         PagerTransport transport = PagerSystem.getTransport();
         transport.initialize();
         transport.transmitRequiringAcknowledgement(ADMIN_ON_CALL_DEVICE, "New inbound critical patient: " +
            patient.getTransportId());
         criticalPatientNotificationsSent.add(patient.getTransportId());
      } catch (Throwable t) {
         System.out.println("Failed attempt to use pager system to device " + ADMIN_ON_CALL_DEVICE);
      }
   }

}
