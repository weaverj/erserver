package erserver.modules.dependencies;

import erserver.modules.dependencies.vendorpagersystem.PagerTransport;
import erserver.modules.testtypes.Patient;
import erserver.modules.dependencies.vendorpagersystem.PagerSystem;

import java.util.ArrayList;
import java.util.List;

public class AlertScanner {

   private static final String ADMIN_ON_CALL_DEVICE = "111-111-1111";

   private InboundPatientSource inboundPatientSource;
   private ArrayList<Integer> criticalPatientNotificationsSent;
   private AlertTransmitter alertTransmitter;

   public AlertScanner(InboundPatientSource inboundPatientSource) {
      this(inboundPatientSource, null);
   }

   public AlertScanner(InboundPatientSource inboundPatientSource, AlertTransmitter alertTransmitter) {
      this.inboundPatientSource = inboundPatientSource;
      this.alertTransmitter = alertTransmitter;
      criticalPatientNotificationsSent = new ArrayList<>();
   }

   public void scan() {
      System.out.println("Scanning for situations requiring alerting...");
      List<Patient> inbound = inboundPatientSource.currentInboundPatients();
      for (Patient patient : inbound) {
         if (Priority.RED.equals(patient.getPriority())) {
            if (!criticalPatientNotificationsSent.contains(patient.getTransportId())) {
               alertForNewCriticalPatient(patient);
            }
         }
         else if ( (Priority.YELLOW.equals(patient.getPriority())) && "heart arrhythmia".equalsIgnoreCase(patient.getCondition())) {
            if (!criticalPatientNotificationsSent.contains(patient.getTransportId())) {
               alertForNewCriticalPatient(patient);
            }
         }
      }
   }

   protected void alertForNewCriticalPatient(Patient patient) {
      try {
         if (Priority.RED.equals(patient.getPriority())) {
            alertTransmitter.transmitRequiringAcknowledgement(ADMIN_ON_CALL_DEVICE, "New inbound critical patient: " +
               patient.getTransportId());
         } else {
            alertTransmitter.transmit(ADMIN_ON_CALL_DEVICE, "New inbound critical patient: " +
               patient.getTransportId());
         }
         criticalPatientNotificationsSent.add(patient.getTransportId());
      } catch (Throwable t) {
         System.out.println("Failed attempt to use pager system to device " + ADMIN_ON_CALL_DEVICE +
            " for patient " + patient.getName());
      }
   }

}
