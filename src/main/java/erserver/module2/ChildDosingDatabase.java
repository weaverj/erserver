package erserver.module2;

import java.util.HashMap;

public class ChildDosingDatabase implements DosingSource {

    private static HashMap<String, String> dosingMap;

    static {
        dosingMap = new HashMap<String, String>();
        initDose("Tylenol Oral Suspension", ChildClassification.NEONATE, "0");
        initDose("Tylenol Oral Suspension", ChildClassification.INFANT, "2.5 ml");
        initDose("Tylenol Oral Suspension", ChildClassification.CHILD, "5 ml");
        initDose("Tylenol Oral Suspension", ChildClassification.ADOLESCENT, "15 ml");
        initDose("Amoxicillin Oral Suspension", ChildClassification.NEONATE, "15 mg/kg");
        initDose("Amoxicillin Oral Suspension", ChildClassification.INFANT, "50 mg/kg");
        initDose("Amoxicillin Oral Suspension", ChildClassification.CHILD, "80 mg/kg");
        initDose("Amoxicillin Oral Suspension", ChildClassification.ADOLESCENT, "120 mg/kg");
    }

    private static void initDose(String medication, ChildClassification classification, String dose) {
        dosingMap.put(medication + classification.name(), dose);
    }

    public String getSingleDose(String medication, ChildClassification childClassification) {
        if (ChildClassification.UNDEFINED.equals(childClassification))  {
            throw new RuntimeException("Disallowed dosing lookup for " + medication + ", " + childClassification.name());
        }
        return dosingMap.get(medication + childClassification.name());
    }
}
