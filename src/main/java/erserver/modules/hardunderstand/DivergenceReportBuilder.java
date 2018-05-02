package erserver.modules.hardunderstand;

public class DivergenceReportBuilder {

   private int redInboundCount;
   private int yellowInboundCount;
   private int greenInboundCount;
   private int[] availableStaff;
   private int[] neededStaff;
   private int bedsAvailable;
   private int criticalBedsAvailable;

   public DivergenceReportBuilder(int redInboundCount, int yellowInboundCount, int greenInboundCount,
                                  int[] availableStaff, int[] neededStaff, int bedsAvailable, int criticalBedsAvailable) {
      this.redInboundCount = redInboundCount;
      this.yellowInboundCount = yellowInboundCount;
      this.greenInboundCount = greenInboundCount;
      this.availableStaff = availableStaff;
      this.neededStaff = neededStaff;
      this.bedsAvailable = bedsAvailable;
      this.criticalBedsAvailable = criticalBedsAvailable;
   }

   public String buildReport() {
      return "\nSituation Status:\n" + "Inbound patients requiring beds: "
         + redInboundCount + " Red, " + yellowInboundCount + " Yellow, "
         + greenInboundCount + " Green.\n"
         + "Available Docs/Nurses: " + availableStaff[0] + "/" + availableStaff[1] + "\n"
         + "Needed Docs/Nurses: " + neededStaff[0] + "/" + neededStaff[1] + "\n"
         + "Available Total Beds/Crit Beds: " + bedsAvailable + "/" + criticalBedsAvailable;
   }
}
