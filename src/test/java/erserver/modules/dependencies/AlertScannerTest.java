package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlertScannerTest {

   @Test
   public void scanAlertsForPriorityRedPatients() throws Exception {
      InboundPatientTestDouble inboundDouble = new InboundPatientTestDouble();
      inboundDouble.simulateNewInboundPatient(createTestPatient(11, Priority.RED, "stroke"));
      inboundDouble.simulateNewInboundPatient(createTestPatient(12, Priority.YELLOW, "mild stroke"));
      AlertTransmitterTestDouble transmitterDouble = new AlertTransmitterTestDouble();

      AlertScanner scanner = new AlertScanner(inboundDouble, transmitterDouble);

      scanner.scan();

      Assert.assertEquals(1, transmitterDouble.getAlertsReceivedRequiringAck().size());
      Assert.assertEquals("111-111-1111: New inbound critical patient: 11",
         transmitterDouble.getAlertsReceivedRequiringAck().get(0));
   }

   @Test
   public void scanAlertsForYellowHeartArrhythmiaPatients() throws Exception {
      InboundPatientTestDouble inboundDouble = new InboundPatientTestDouble();
      inboundDouble.simulateNewInboundPatient(createTestPatient(11, Priority.GREEN, "shortness of breath"));
      inboundDouble.simulateNewInboundPatient(createTestPatient(12, Priority.YELLOW, "heart arrhythmia"));
      AlertTransmitterTestDouble transmitterDouble = new AlertTransmitterTestDouble();

      AlertScanner scanner = new AlertScanner(inboundDouble, transmitterDouble);

      scanner.scan();

      Assert.assertEquals(1, transmitterDouble.getAlertsReceived().size());
      Assert.assertEquals("111-111-1111: New inbound critical patient: 12",
         transmitterDouble.getAlertsReceived().get(0));
   }

   @Test
   public void onlyTransmitOnceForGivenInboundPatient() throws Exception {
      InboundPatientTestDouble inboundDouble = new InboundPatientTestDouble();
      inboundDouble.simulateNewInboundPatient(createTestPatient(11, Priority.GREEN, "shortness of breath"));
      inboundDouble.simulateNewInboundPatient(createTestPatient(12, Priority.YELLOW, "heart arrhythmia"));
      AlertTransmitterTestDouble transmitterDouble = new AlertTransmitterTestDouble();

      AlertScanner scanner = new AlertScanner(inboundDouble, transmitterDouble);

      scanner.scan();
      scanner.scan();

      Assert.assertEquals(1, transmitterDouble.getAlertsReceived().size());
      Assert.assertEquals("111-111-1111: New inbound critical patient: 12",
         transmitterDouble.getAlertsReceived().get(0));

   }

   private Patient createTestPatient(int transportId, Priority priority, String condition) {
      Patient patient = new Patient();
      patient.setTransportId(transportId);
      patient.setPriority(priority);
      patient.setCondition(condition);
      return patient;
   }

}