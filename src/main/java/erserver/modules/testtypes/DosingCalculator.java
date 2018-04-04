package erserver.modules.testtypes;

public class DosingCalculator {

    public String getRecommendedSingleDose(Patient patient, String medication) {
        DosingSource dosingSource = DosingSourceFactory.getDosingSourceFor(patient, medication);
        return dosingSource.getSingleDose(medication, patient.getChildClassification());
    }
}
