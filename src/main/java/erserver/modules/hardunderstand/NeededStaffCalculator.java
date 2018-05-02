package erserver.modules.hardunderstand;

public class NeededStaffCalculator {
   private int[] redStaffRequired;
   private int[] yellowStaffRequired;
   private int[] greenStaffRequired;
   private int redInboundCount;
   private int yellowInboundCount;
   private int greenInboundCount;

   public NeededStaffCalculator(int[] redStaffRequired, int[] yellowStaffRequired, int[] greenStaffRequired,
                                int redInboundCount, int yellowInboundCount, int greenInboundCount) {
      this.redStaffRequired = redStaffRequired;
      this.yellowStaffRequired = yellowStaffRequired;
      this.greenStaffRequired = greenStaffRequired;
      this.redInboundCount = redInboundCount;
      this.yellowInboundCount = yellowInboundCount;
      this.greenInboundCount = greenInboundCount;
   }

   public int[] overall() {
      int[] neededStaff = {0, 0};
      neededStaff[0] = redInboundCount * redStaffRequired[0];
      neededStaff[0] += yellowInboundCount * yellowStaffRequired[0];
      neededStaff[0] += greenInboundCount * greenStaffRequired[0];
      neededStaff[1] = redInboundCount * redStaffRequired[1];
      neededStaff[1] += yellowInboundCount * yellowStaffRequired[1];
      neededStaff[1] += greenInboundCount * greenStaffRequired[1];
      if (neededStaff[1] > 5) {
         neededStaff[1] -= 1;
      }
      return neededStaff;
   }
}
