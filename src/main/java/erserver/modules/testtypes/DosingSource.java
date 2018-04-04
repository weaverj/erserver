package erserver.modules.testtypes;

public interface DosingSource {
    String getSingleDose(String medication, ChildClassification childClassification);
}
