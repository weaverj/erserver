package erserver.module3;

import erserver.module2.Patient;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class ERStubSystem {

   private static List<Patient> patientsInTransport;
   private static int transportIds = 1;

   public static void main(String[] args) {

      patientsInTransport = new ArrayList<>();
      Patient initialPatient = new Patient();
      initialPatient.setName("John Doe");
      initialPatient.setBirthDate(null);
      initialPatient.setPriority(Priority.YELLOW);
      initialPatient.setTransportId(transportIds++);
      patientsInTransport.add(initialPatient);

      post("/ack", (request, response) -> {
         return ("acknowledged");
      });

      post("/arrived", (request, response) -> {
         int transportId = Integer.parseInt(request.queryParams("transportId"));
         removeInbound(transportId);
         return ("OK");
      });

      post("/diversion", (request, response) -> {
         int transportId = Integer.parseInt(request.queryParams("priority"));
         return ("OK");
      });

      get("/check", (request, response) -> {
         return "OK";
      });

      get("/inbound", (request, response) -> {
         return patientsToXml();
      });
   }

   private static void removeInbound(int transportId) {
      Patient arrived = null;
      for (Patient patient : patientsInTransport) {
         if (transportId == patient.getTransportId()) {
            arrived = patient;
         }
      }
      if (null != arrived) {
         patientsInTransport.remove(arrived);
      }
   }

   private static String patientsToXml() {
      StringBuilder xml = new StringBuilder();
      xml.append("<Inbound>");
      for (Patient patient : patientsInTransport) {
         xml.append("<Patient>");
         xml.append("<TransportId>").append(patient.getTransportId()).append("</TransportId>");
         xml.append("<Name>").append(patient.getName()).append("</Name>");
         xml.append("<Priority>").append(patient.getPriority()).append("</Priority>");
         xml.append("<Birthdate>");
         if (null != patient.getBirthDate()) {
            xml.append(patient.getBirthDate());
         }
         xml.append("</Birthdate>");
         xml.append("</Patient>");
      }
      xml.append("</Inbound>");
      return xml.toString();
   }
}
