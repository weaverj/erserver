package erserver.module2;

import erserver.module3.Priority;

import java.time.LocalDate;

public class Patient {

    private String name;
    private LocalDate birthDate;
    private Priority priority;

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    private int transportId;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ChildClassification getChildClassification() {
        return ChildClassification.calculate(birthDate, LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


}
