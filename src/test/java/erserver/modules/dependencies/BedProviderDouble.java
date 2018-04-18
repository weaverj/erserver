package erserver.modules.dependencies;

import java.util.ArrayList;
import java.util.List;

public class BedProviderDouble implements BedProvider {

   private ArrayList<Bed> bedsToReturn;

   public BedProviderDouble() {
      bedsToReturn = new ArrayList<>();
   }

   public void addBedToReturn(Bed bed) {
      bedsToReturn.add(bed);
   }

   @Override
   public List<Bed> getAllBeds() {
      return bedsToReturn;
   }
}
