package erserver.module2;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ChildClassificationTest {

   private LocalDate currentDate;


   @Before
   public void setUp() {
      currentDate = LocalDate.of(2000, 1, 10);
   }

   @Test
   public void returnNeonateUpTo30DaysOld() {
      assertEquals(ChildClassification.NEONATE,
         ChildClassification.calculate(currentDate, currentDate));

      LocalDate birthDate = currentDate.minusDays(29);
      assertEquals(ChildClassification.NEONATE,
         ChildClassification.calculate(birthDate, currentDate));
   }

   @Test
   public void returnInfantFrom30DaysTo2Years() {
      LocalDate birthDate = currentDate.minusDays(30);
      assertEquals(ChildClassification.INFANT,
         ChildClassification.calculate(birthDate, currentDate));

      birthDate = currentDate.minusYears(2).plusDays(1);
      assertEquals(ChildClassification.INFANT,
         ChildClassification.calculate(birthDate, currentDate));
   }

   @Test
   public void returnChildFrom2YearsTo12years() {
      LocalDate birthDate = currentDate.minusYears(2);
      assertEquals(ChildClassification.CHILD,
         ChildClassification.calculate(birthDate, currentDate));

      birthDate = currentDate.minusYears(12).plusDays(1);
      assertEquals(ChildClassification.CHILD,
         ChildClassification.calculate(birthDate, currentDate));
   }

   @Test
   public void returnAdolescentFrom12YearsTo16years() {
      LocalDate birthDate = currentDate.minusYears(12);
      assertEquals(ChildClassification.ADOLESCENT,
         ChildClassification.calculate(birthDate, currentDate));

      birthDate = currentDate.minusYears(16).plusDays(1);
      assertEquals(ChildClassification.ADOLESCENT,
         ChildClassification.calculate(birthDate, currentDate));
   }

   @Test
   public void returnUndefinedAfter16Years() {
      LocalDate birthDate = currentDate.minusYears(16);
      assertEquals(ChildClassification.UNDEFINED,
         ChildClassification.calculate(birthDate, currentDate));

      birthDate = currentDate.minusYears(80);
      assertEquals(ChildClassification.UNDEFINED,
         ChildClassification.calculate(birthDate, currentDate));
   }

   @Test
   public void returnUndefinedIfBirthdateInFuture() {
      assertEquals(ChildClassification.UNDEFINED,
         ChildClassification.calculate(currentDate.plusDays(1), currentDate));
   }


}