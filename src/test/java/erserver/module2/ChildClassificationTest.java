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
        LocalDate birthDate = now.plusDays(29);
        assertEquals(ChildClassification.NEONATE, ChildClassification.calculate(birthDate, now));
    }

    @Test
    public void returnInfantAt30Days() {
        LocalDate birthDate = now.plusDays(30);
        assertEquals(ChildClassification.INFANT, ChildClassification.calculate(birthDate, now));
    }

}