package erserver.modules.testtypes;

import java.time.LocalDate;

public class PatientForTesting extends Patient {

   private LocalDate currentDate;

   public PatientForTesting() {
      this.currentDate = LocalDate.now();
   }

   public void setCurrentDate(LocalDate date) {
      this.currentDate = date;
   }

   @Override
   public LocalDate getSystemCurrentDate() {
      return currentDate;
   }
}
