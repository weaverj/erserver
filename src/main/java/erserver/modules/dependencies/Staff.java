package erserver.modules.dependencies;

public class Staff {

   private int staffId;
   private String name;
   private StaffRole role;

   public Staff(int staffId, String name, StaffRole role) {
      this.staffId = staffId;
      this.name = name;
      this.role = role;
   }

   public String getName() {
      return name;
   }

   public StaffRole getRole() {
      return role;
   }

   public int getStaffId() {
      return staffId;
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Staff staff = (Staff) o;

      return staffId == staff.staffId;

   }

   @Override
   public int hashCode() {
      return staffId;
   }
}
