package erserver.module3;

import erserver.module2.Patient;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InboundPatientController {

    private EmergencyResponseService transportService;

    public InboundPatientController(EmergencyResponseService transportService) {
        this.transportService = transportService;
    }

    public List<Patient> currentInboundPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        String xmlForInbound = transportService.fetchInboundPatients();
        System.out.println("Recieved XML from transport service: \n" + xmlForInbound);
        SAXBuilder builder = new SAXBuilder();
        try {
            InputStream stream = new ByteArrayInputStream(xmlForInbound.getBytes("UTF-8"));
            Document document = (Document) builder.build(stream);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("Patient");
            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                patients.add(buildPatientFromXml(node));
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        System.out.println("Returning inbound patients: " + patients.size());
        return patients;
    }

    private Patient buildPatientFromXml(Element node) {
        Patient patient = new Patient();
        patient.setTransportId(Integer.parseInt(node.getChildText("TransportId")));
        patient.setName(node.getChildText("Name"));
        patient.setPriority(Priority.getByString(node.getChildText("Priority")));
        return patient;
    }
}
