package erserver.module3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffRepository {

   private String staffFile = "/staff.csv";
   private Scanner scanner;

   public StaffRepository() {
      try {
         URL fileUrl = getClass().getResource(staffFile);
         this.scanner = new Scanner(new File(fileUrl.getFile()));
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   public List<Staff> getShiftStaff() {
      ArrayList<Staff> staffList = new ArrayList<>();
      while (scanner.hasNextLine()) {
         String line = scanner.nextLine();
         String[] values = line.split(",");
         String name = values[0];
         String roleString = values[1];
         Staff staff = new Staff(name, StaffRole.valueOf(roleString));
         staffList.add(staff);
      }
      return staffList;
   }

}
