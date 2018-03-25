package erserver.module2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public enum ChildClassification {
   NEONATE,
   INFANT,
   CHILD,
   ADOLESCENT,
   UNDEFINED;

   public static ChildClassification calculate(LocalDate birthDate, LocalDate currentDate) {
      long daysOld = ChronoUnit.DAYS.between(birthDate, currentDate);
      long yearsOld = ChronoUnit.YEARS.between(birthDate, currentDate);
      if ((daysOld >= 0) && (daysOld < 30)) {
         return NEONATE;
      }
      if ((daysOld >= 30) && (yearsOld < 2)) {
         return INFANT;
      }
      if ((yearsOld >= 2) && (yearsOld < 12)) {
         return CHILD;
      }
      if ((yearsOld >= 12) && (yearsOld < 16)) {
         return ADOLESCENT;
      }
      if ((yearsOld >= 16)) {
         return UNDEFINED;
      }
      return UNDEFINED;
   }

}
