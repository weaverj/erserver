package erserver.module3;


import com.google.gson.Gson;
import erserver.module2.Patient;

import java.util.List;

import static spark.Spark.*;

public class EREndpoints {

    public static void initializeEndpoints() {
        Gson gson = new Gson();

        get("/inboundPatients", (request, response) -> {
            System.out.println("Recieved request for inbound patients from client.");
            List<Patient> inboundPatients = null;
            EmergencyResponseService erService = new EmergencyResponseService("http://localhost", 4567, 1000);
            InboundPatientController controller = new InboundPatientController(erService);
            inboundPatients = controller.currentInboundPatients();
            return inboundPatients;
        }, gson::toJson);

    };

}
