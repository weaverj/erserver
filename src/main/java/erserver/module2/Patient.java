package erserver.module2;

import java.time.LocalDate;

public class Patient {

    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ChildClassification getChildClassification() {
        return ChildClassification.calculate(birthDate, LocalDate.now());
    }
}
