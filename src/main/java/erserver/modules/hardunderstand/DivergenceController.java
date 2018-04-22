package erserver.modules.hardunderstand;

import erserver.modules.dependencies.*;
import erserver.modules.dependencies.vendorpagersystem.PagerSystem;
import erserver.modules.dependencies.vendorpagersystem.PagerTransport;
import erserver.modules.testtypes.Patient;

import java.util.List;

public class DivergenceController {

   private static final String ADMIN_ON_CALL_DEVICE = "111-111-1111";

   private boolean redDivergence;
   private boolean yellowDivergence;
   private boolean greenDivergence;
   private int redCount;
   private int yellowCount;
   private int greenCount;
   private int allowedCount;
   private int redOver;
   private int yellowOver;
   private int greenOver;

   public DivergenceController() {
      this.redDivergence = false;
      this.yellowDivergence = false;
      this.greenDivergence = false;
      this.redCount = 0;
      this.yellowCount = 0;
      this.greenCount = 0;
      this.allowedCount = 3;
      this.redOver = 0;
      this.yellowOver = 1;
      this.greenOver = 4;
   }

   public void check() {
      StaffAssignmentManager manager = new ERServerMainController().getStaffAssignmentManager();
      InboundPatientController controller = new ERServerMainController().getInboundPatientController();
      int[] red = {1, 2};
      int[] yellow = {1, 1};
      int[] green = {0, 1};
      boolean redIncremented = false;
      boolean yellowIncremented = false;
      boolean greenIncremented = false;
      List<Patient> patients = controller.currentInboundPatients();
      List<Staff> staff = manager.getAvailableStaff();
      List<Bed> beds = manager.getAvailableBeds();
      int bedcrits = 0;
      int redin = 0;
      int yellowin = 0;
      int greenin = 0;
      int[] staffcur = {0, 0};
      int[] need = {0, 0};

      for (Bed bed : beds) {
         if (bed.isCriticalCare()) {
            bedcrits ++;
         }
      }
      for (Patient patient : patients) {
         if (Priority.RED.equals(patient.getPriority())) {
            redin++;
         }
         else if (Priority.YELLOW.equals(patient.getPriority())) {
            yellowin ++;
         }
         else if (Priority.GREEN.equals(patient.getPriority())) {
            greenin ++;
         }
      }
      for (Staff cur : staff) {
         if (StaffRole.DOCTOR.equals(cur.getRole())) {
            staffcur[0]++;
         }
         else if (StaffRole.NURSE.equals(cur.getRole())) {
            staffcur[1]++;
         }
      }
      System.out.println("** Running divergence check: \n");
      System.out.println("\t Current Beds Available/With Crit Equip: " + beds.size() + "/" + bedcrits);
      System.out.println("\t Current Staff Available Docs / Nurses: " + staffcur[0] + "/" + staffcur[1]);
      System.out.println("\t Inbound Red / Yellow / Green: " + redin + "/" + yellowin + "/" + greenin);
      if (redin > (bedcrits + redOver)) {
         redCount++;
         redIncremented = true;
      }
      if (yellowin + greenin > (beds.size() - bedcrits + yellowOver + greenOver)) {
         if ( (greenin > (beds.size() - bedcrits + greenOver)) && (yellowin <= (beds.size() - bedcrits + yellowOver)) ) {
            System.out.println("\n\t Green diversion due to bed shortage");
            greenCount++;
            greenIncremented = true;
         } else {
            System.out.println("\n\t Yellow diversion due to bed shortage");
            greenCount++;
            yellowCount++;
            greenIncremented = true;
            yellowIncremented = true;
         }
      }
      need[0] = redin * red[0];
      need[0] += yellowin * yellow[0];
      need[0] += greenin * green[0];
      need[1] = redin * red[1];
      need[1] += yellowin * yellow[1];
      need[1] += greenin * green[1];
      System.out.println("\n\t Staff Need Docs/Nurses: " + need[0] + "/" + need[1]);
      if (need[0] > staffcur[0]) {
         int diff = need[0] - staffcur[0];
         if ((greenin * green[0]) >= diff)  {
            if (!greenIncremented) {
               System.out.println("\t Green diversion will resolve staff shortage.");
               greenIncremented = true;
               greenCount++;
            }
         }
         else {
            int both = (yellowin * yellow[0]) + (greenin * green[0]);
            if (both >= diff) {
               System.out.println("\t Green and yellow diversion will resolve staff shortage.");
               if (!greenIncremented) {
                  greenIncremented = true;
                  greenCount++;
               }
               if (!yellowIncremented) {
                  yellowIncremented = true;
                  yellowCount++;
               }
            }
            else {
               System.out.println("\t Full diversion due to staff shortage");
               if (!greenIncremented) {
                  greenIncremented = true;
                  greenCount++;
               }
               if (!yellowIncremented) {
                  yellowIncremented = true;
                  yellowCount++;
               }
               if (!redIncremented) {
                  redIncremented = true;
                  redCount++;
               }
            }
         }
      }
      if (need[1] > staffcur[1]) {
         int diff = need[1] - staffcur[1];
         if ((greenin * green[1]) >= diff)  {
            if (!greenIncremented) {
               System.out.println("\t Green diversion will resolve staff shortage.");
               greenIncremented = true;
               greenCount++;
            }
         }
         else {
            int both = (yellowin * yellow[1]) + (greenin * green[1]);
            if (both >= diff) {
               System.out.println("\t Green and yellow diversion will resolve staff shortage.");
               if (!greenIncremented) {
                  greenIncremented = true;
                  greenCount++;
               }
               if (!yellowIncremented) {
                  yellowIncremented = true;
                  yellowCount++;
               }
            }
            else {
               System.out.println("\t Full diversion due to staff shortage");
               if (!greenIncremented) {
                  greenIncremented = true;
                  greenCount++;
               }
               if (!yellowIncremented) {
                  yellowIncremented = true;
                  yellowCount++;
               }
               if (!redIncremented) {
                  redIncremented = true;
                  redCount++;
               }
            }
         }
      }
      System.out.println("\t Counts red/yellow/green: " + redCount + "/" + yellowCount + "/" + greenCount);
      EmergencyResponseService transportService = new EmergencyResponseService("http://localhost", 4567, 1000);
      if (redIncremented) {
         if ((redCount > allowedCount) && !redDivergence) {
            System.out.println("Entering priority red divergence");
            redDivergence = true;
            transportService.requestInboundDiversion(Priority.RED);
            sendDivergencePage("Entered divergence for RED priority patients!", true);
            redCount = 0;
         }
      } else {
         redCount = 0;
         if (redDivergence) {
            System.out.println("Ending priority red divergence");
            transportService.removeInboundDiversion(Priority.RED);
            sendDivergencePage("Ended divergence for RED priority patients.", false);
            redDivergence = false;
         }
      }
      if (yellowIncremented) {
         if ((yellowCount > allowedCount) && !yellowDivergence) {
            System.out.println("Entering priority yellow divergence");
            yellowDivergence = true;
            transportService.requestInboundDiversion(Priority.YELLOW);
            sendDivergencePage("Entered divergence for YELLOW priority patients!", true);
            yellowCount = 0;
         }
      } else {
         yellowCount = 0;
         if (yellowDivergence) {
            System.out.println("Ending priority yellow divergence");
            transportService.removeInboundDiversion(Priority.YELLOW);
            sendDivergencePage("Ended divergence for YELLOW priority patients.", false);
            yellowDivergence = false;
         }
      }
      if (greenIncremented) {
         if ((greenCount > allowedCount) && !greenDivergence) {
            System.out.println("Entering priority green divergence");
            greenDivergence = true;
            transportService.requestInboundDiversion(Priority.GREEN);
            sendDivergencePage("Entered divergence for GREEN priority patients!", true);
            greenCount = 0;
         }
      } else {
         greenCount = 0;
         if (greenDivergence) {
            System.out.println("Ending priority green divergence");
            transportService.removeInboundDiversion(Priority.GREEN);
            sendDivergencePage("Ended divergence for GREEN priority patients.", false);
            greenDivergence = false;
         }
      }
   }

   private void sendDivergencePage(String text, boolean requireAck) {
      try {
         PagerTransport transport = PagerSystem.getTransport();
         transport.initialize();
         if (requireAck) {
            transport.transmitRequiringAcknowledgement(ADMIN_ON_CALL_DEVICE, text);
         } else {
            transport.transmit(ADMIN_ON_CALL_DEVICE, text);
         }
      } catch (Throwable t) {
         t.printStackTrace();
      }

   }

}
