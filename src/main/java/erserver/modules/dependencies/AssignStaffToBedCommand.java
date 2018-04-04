package erserver.modules.dependencies;

public class AssignStaffToBedCommand {

   private StaffAssignmentManager staffAssignmentManager;

   public AssignStaffToBedCommand(StaffAssignmentManager staffAssignmentManager) {
      this.staffAssignmentManager = staffAssignmentManager;
   }

   public void assignStaffToBed(int[] staffIds, int bedId) {
      for (int staffId : staffIds) {
         Staff staff = staffAssignmentManager.getStaffById(staffId);
         Bed bed = staffAssignmentManager.getBedById(bedId);
         staffAssignmentManager.assignStaffMemberToBed(staff, bed);
      }
   }
}
