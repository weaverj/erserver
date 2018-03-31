package erserver.module3;

import erserver.module2.Patient;

import java.util.ArrayList;
import java.util.List;

public class Staff {

   private String name;
   private StaffRole role;

   public Staff(String name, StaffRole role) {
      this.name = name;
      this.role = role;
   }

   public String getName() {
      return name;
   }

   public StaffRole getRole() {
      return role;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Staff staff = (Staff) o;

      if (!name.equals(staff.name)) return false;
      return role == staff.role;

   }

   @Override
   public int hashCode() {
      int result = name.hashCode();
      result = 31 * result + role.hashCode();
      return result;
   }
}
