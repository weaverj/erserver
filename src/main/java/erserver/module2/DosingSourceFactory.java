package erserver.module2;

public class DosingSourceFactory {


    public static DosingSource getDosingSourceFor(Patient patient, String medication) {
        if (ChildClassification.UNDEFINED != patient.getChildClassification()) {
            return new ChildDosingDatabase();
        }
        throw new RuntimeException("Dosing Calculator to use for patient and medication undefined");
    }
}
