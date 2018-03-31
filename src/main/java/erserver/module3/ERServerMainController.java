package erserver.module3;

public class ERServerMainController {

   private static StaffAssignmentManager staffAssignmentManager;
   private static InboundPatientController inboundPatientController;

   static {
      staffAssignmentManager = new StaffAssignmentManager();
      EmergencyResponseService emergencyTransportService = new EmergencyResponseService("http://localhost", 4567, 1000);
      inboundPatientController = new InboundPatientController(emergencyTransportService);
   }

   public InboundPatientController getInboundPatientController() {
      return inboundPatientController;
   }

   public StaffAssignmentManager getStaffAssignmentManager() {
      return staffAssignmentManager;
   }



}
