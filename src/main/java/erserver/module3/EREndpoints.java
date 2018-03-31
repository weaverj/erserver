package erserver.module3;


import com.google.gson.Gson;
import erserver.module2.Patient;

import java.util.List;

import static spark.Spark.*;

public class EREndpoints {

   private static ERServerMainController mainController;

   static {
      mainController = new ERServerMainController();
   }

   public static void initializeEndpoints() {
      Gson gson = new Gson();

      get("/inboundPatients", (request, response) -> {
         System.out.println("Recieved request for inbound patients from client.");
         return mainController.getInboundPatientController().currentInboundPatients();
      }, gson::toJson);

      get("/shiftStaff", (request, response) -> {
         System.out.println("Recieved request for all shift staff from client.");
         return mainController.getStaffAssignmentManager().getShiftStaff();
      }, gson::toJson);

      get("/availableStaff", (request, response) -> {
         System.out.println("Recieved request for available staff from client.");
         return mainController.getStaffAssignmentManager().getAvailableStaff();
      }, gson::toJson);

      get("/physiciansOnDuty", (request, response) -> {
         System.out.println("Recieved request for physicians on duty from client.");
         return mainController.getStaffAssignmentManager().getPhysiciansOnDuty();
      }, gson::toJson);

      get("/beds", (request, response) -> {
         System.out.println("Recieved request for all beds from client.");
         return mainController.getStaffAssignmentManager().getBeds();
      }, gson::toJson);

      get("/availableBeds", (request, response) -> {
         System.out.println("Recieved request for available beds from client.");
         return mainController.getStaffAssignmentManager().getAvailableBeds();
      }, gson::toJson);

   }

   ;

}
