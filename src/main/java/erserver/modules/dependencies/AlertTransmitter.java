package erserver.modules.dependencies;

public interface AlertTransmitter {
   void transmit(String targetDevice, String pageText);

   void transmitRequiringAcknowledgement(String targetDevice, String pageText);
}
