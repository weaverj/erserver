package erserver.module2;

import java.time.LocalDate;

public class DoseCalculationByMedAndAge {

    private String age;
    private String medication;

    private Patient patient;

    public void execute() {
        LocalDate birthDate = LocalDate.now();
        int ageNumberVal = Integer.parseInt(age.split(" ")[0]);
        if (age.contains("day")) {
            birthDate = birthDate.minusDays(ageNumberVal);
        }
        else if (age.contains("month")) {
            birthDate = birthDate.minusMonths(ageNumberVal);
        } else {
            birthDate = birthDate.minusYears(ageNumberVal);
        }
        patient = new Patient();
        patient.setBirthDate(birthDate);
    }


    public String childClassification() {
        return patient.getChildClassification().name();
    }

    public String dose() {
        DosingCalculator calculator = new DosingCalculator();
        return calculator.getRecommendedSingleDose(patient, medication);
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
