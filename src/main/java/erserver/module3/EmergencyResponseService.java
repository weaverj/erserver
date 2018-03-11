package erserver.module3;

import erserver.module2.Patient;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.List;

public class EmergencyResponseService {

    private String connectionString;
    private int timeout;

    public EmergencyResponseService(String url, int port, int timeout) {
        this.connectionString = url + ":" + port;
        this.timeout = timeout;
        boolean acknowledge = false;
        String response = null;
        System.out.println("Initializing with Emergency Response Services: " + connectionString + "/ack");
        try {
            response = Request.Post(connectionString + "/ack")
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString("ER system ping", ContentType.DEFAULT_TEXT)
                    .execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect with Emergency Respose Services");
        }
        System.out.println(response);
        if (!"acknowledged".equals(response)) {
            throw new RuntimeException("Unable to connect with Emergency Respose Services");
        }
    }

    public String fetchInboundPatients() {
        String xmlResponse = null;
        try {
            xmlResponse = Request.Get(connectionString + "/inbound")
                    .connectTimeout(this.timeout)
                    .socketTimeout(this.timeout)
                    .execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to obtain inbound patients");
        }
        return xmlResponse;
    }

    public void informOfArrival(int transportId) {
        String response = null;
        try {
            response = Request.Post(connectionString + "/arrived" + "?transportId=" + transportId)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to inform of arrival");
        }
        if (!"OK".equals(response)) {
            throw new RuntimeException("Unable to inform of arrival");
        }
    }

    public void requestInboundDiversion(Priority priority) {
        String response = null;
        try {
            response = Request.Post(connectionString + "/diversion" + "?priority=" + priority)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to inform of diversion");
        }
        if (!"OK".equals(response)) {
            throw new RuntimeException("Unable to inform of diversion");
        }
    }


}
