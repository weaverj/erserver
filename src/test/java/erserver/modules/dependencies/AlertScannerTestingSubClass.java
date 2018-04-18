package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;

import java.util.ArrayList;
import java.util.List;

public class AlertScannerTestingSubClass extends AlertScanner {

   public ArrayList<Patient> patientsAlertedFor;

   public AlertScannerTestingSubClass(InboundPatientSource inboundSource) {
      super(inboundSource);
      patientsAlertedFor = new ArrayList<>();
   }

   @Override
   protected void alertForNewCriticalPatient(Patient patient) {
      patientsAlertedFor.add(patient);
   }
}
