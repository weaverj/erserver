package erserver.module2;

public interface DosingSource {
    String getSingleDose(String medication, ChildClassification childClassification);
}
