package erserver.module2;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public enum ChildClassification {
    NEONATE,
    INFANT,
    CHILD,
    ADOLESCENT,
    UNDEFINED;

    public static ChildClassification calculate(LocalDate birthDate, LocalDate currentDate) {
        Period timeSinceBirth = Period.between(currentDate, birthDate);
        if (timeSinceBirth.getDays() < 30) {
            return NEONATE;
        }
        if ( (timeSinceBirth.getDays() >= 30) && (timeSinceBirth.getYears() < 2) ) {
            return INFANT;
        }
        if ( (timeSinceBirth.getYears() >= 2) && (timeSinceBirth.getYears() < 12) ) {
            return CHILD;
        }
        if ( (timeSinceBirth.getYears() >= 12 ) && (timeSinceBirth.getYears() < 16) ) {
            return ADOLESCENT;
        }
        if ( (timeSinceBirth.getYears() >= 16)) {
            return UNDEFINED;
        }
        return UNDEFINED;
    }

}
