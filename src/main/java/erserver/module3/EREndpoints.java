package erserver.module3;


import com.google.gson.Gson;

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

      post("/assignPatientToBed", (request, response) -> {
         int transportId = Integer.parseInt(request.queryParams("transportId"));
         int bedId = Integer.parseInt(request.queryParams("bedId"));
         System.out.println("Client request to assign patient " + transportId + " to bed " + bedId);
         AssignPatientToBedCommand command = new AssignPatientToBedCommand(mainController.getStaffAssignmentManager(),
            mainController.getInboundPatientController());
         command.assignPatientToBed(transportId, bedId);
         return "OK";
      });

      post("/assignStaffToBed", (request, response) -> {
         int bedId = Integer.parseInt(request.queryParams("bedId"));
         int[] staffIds = gson.fromJson(request.body(), int[].class);
         System.out.println("Client request to assign staff to bed " + bedId);
         AssignStaffToBedCommand command = new AssignStaffToBedCommand(mainController.getStaffAssignmentManager());
         command.assignStaffToBed(staffIds, bedId);
         return "OK";
      });


   }

}
