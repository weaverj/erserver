package erserver.module3;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmergencyResponseServiceTest {

    @Test
    public void initializes() {
        EmergencyResponseService service = new EmergencyResponseService("http://localhost", 4567, 1000);
        String xml = service.fetchInboundPatients();
        System.out.println(xml);
        service.informOfArrival(1);
        System.out.println(service.fetchInboundPatients());
    }

}