package erserver.module2;

public class DosingCalculator {

    public String getSingleDose(Patient patient, String medication) {
        DosingSource dosingSource = DosingSourceFactory.getDosingSourceFor(patient, medication);
        return dosingSource.getSingleDose(medication, patient.getChildClassification());
    }
}
