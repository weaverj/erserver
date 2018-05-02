package erserver.modules.hardunderstand;

import erserver.modules.dependencies.Priority;
import erserver.modules.testtypes.Patient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DivergenceControllerTest {

   private DivergenceController controller;
   private ArrayList<Patient> incomingPatients;

   @Before
   public void setUp() {
      controller = new DivergenceController();
      incomingPatients = new ArrayList<>();
   }

   @Test
   public void testGreenPatientsNotRequiringBedFiltered() {
      Patient nonEmerPatient = createPatient(Priority.GREEN, "non-emergency situation, patient is ambulatory");
      incomingPatients.add(nonEmerPatient);
      incomingPatients.add(createPatient(Priority.RED, "non-emergency situation, patient is ambulatory"));
      incomingPatients.add(createPatient(Priority.YELLOW, "non-emergency situation, patient is ambulatory"));
      incomingPatients.add(createPatient(Priority.GREEN, "ambulatory bleeding"));
      incomingPatients.add(createPatient(Priority.GREEN, "non-emergency, but unable to walk"));
      List<Patient> filteredList = controller.patientsAffectingDivergence(incomingPatients);
      assertEquals(4, filteredList.size());
      Assert.assertFalse(filteredList.contains(nonEmerPatient));
   }

   private Patient createPatient(Priority priority, String condition) {
      Patient patient = new Patient();
      patient.setPriority(priority);
      patient.setCondition(condition);
      return patient;
   }

}