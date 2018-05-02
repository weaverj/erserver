package erserver.modules.hardunderstand;

import org.junit.Test;

import static org.junit.Assert.*;

public class NeededStaffCalculatorTest {

   private int[] redStaffRequired = {1, 2};
   private int[] yellowStaffRequired = {1, 1};
   private int[] greenStaffRequired = {0, 1};

   @Test
   public void docAndNurseValuesCalculatedCorrectly() {
      NeededStaffCalculator neededStaff = new NeededStaffCalculator(redStaffRequired, yellowStaffRequired, greenStaffRequired,
         1, 1, 1);
      int[] neededOverall = neededStaff.overall();
      assertEquals(2, neededOverall[0]);
      assertEquals(4, neededOverall[1]);
   }

   @Test
   public void nursesRequiredReducedByOneWhenOverFive() {
      NeededStaffCalculator neededStaff = new NeededStaffCalculator(redStaffRequired, yellowStaffRequired, greenStaffRequired,
         1, 3, 3);
      int[] neededOverall = neededStaff.overall();
      assertEquals(4, neededOverall[0]);
      assertEquals(7, neededOverall[1]);

   }


}