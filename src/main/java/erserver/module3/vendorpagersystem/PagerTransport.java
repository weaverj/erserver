package erserver.module3.vendorpagersystem;

public final class PagerTransport {

    public void initialize() {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }

    public void transmit(String targetDevice, String pageText) {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }

    public void transmitRequiringAcknowledgement(String targetDevice, String pageText) {
        throw new RuntimeException("represents a vendor class requiring install on server");
    }
}
