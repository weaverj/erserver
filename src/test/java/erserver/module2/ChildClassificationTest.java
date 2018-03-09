package erserver.module2;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ChildClassificationTest {

    private LocalDate now;


    @Before
    public void setUp() {
        now = LocalDate.now();
    }

    @Test
    public void returnNeonateUpTo30DaysOld() {
        assertEquals(ChildClassification.NEONATE, ChildClassification.calculate(now, now));
        LocalDate birthDate = now.minusDays(29);
        assertEquals(ChildClassification.NEONATE, ChildClassification.calculate(birthDate, now));
    }

    @Test
    public void returnInfantFrom30DaysTo2Years() {
        LocalDate birthDate = now.minusDays(30);
        assertEquals(ChildClassification.INFANT, ChildClassification.calculate(birthDate, now));

        birthDate = now.minusYears(2).plusDays(1);
        assertEquals(ChildClassification.INFANT, ChildClassification.calculate(birthDate, now));
    }

    @Test
    public void returnChildFrom2YearsTo12years() {
        LocalDate birthDate = now.minusYears(2);
        assertEquals(ChildClassification.CHILD, ChildClassification.calculate(birthDate, now));

        birthDate = now.minusYears(12).plusDays(1);
        assertEquals(ChildClassification.CHILD, ChildClassification.calculate(birthDate, now));
    }

    @Test
    public void returnAdolescentFrom12YearsTo16years() {
        LocalDate birthDate = now.minusYears(12);
        assertEquals(ChildClassification.ADOLESCENT, ChildClassification.calculate(birthDate, now));

        birthDate = now.minusYears(16).plusDays(1);
        assertEquals(ChildClassification.ADOLESCENT, ChildClassification.calculate(birthDate, now));
    }

    @Test
    public void returnUndefinedAfter16Years() {
        LocalDate birthDate = now.minusYears(16);
        assertEquals(ChildClassification.UNDEFINED, ChildClassification.calculate(birthDate, now));
    }

    @Test
    public void returnUndefinedIfBirthdateIsNowOrInFuture() {
        assertEquals(ChildClassification.UNDEFINED, ChildClassification.calculate(now.plusDays(1), now));

    }


}