package erserver.modules.dependencies;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StaffAssignmentManagerTest {

   @Test
   public void physiciansAndResidentsReturnForPhysiciansOnDuty() {
      StaffProviderDouble staffRepoDouble = new StaffProviderDouble();
      staffRepoDouble.addStaffMemberToReturn(new Staff(1, "John Doctor", StaffRole.DOCTOR));
      staffRepoDouble.addStaffMemberToReturn(new Staff(2, "Frank Resident", StaffRole.RESIDENT));
      StaffAssignmentManager manager = new StaffAssignmentManager(staffRepoDouble, new BedProviderDouble());
      List<Staff> physiciansOnDuty = manager.getPhysiciansOnDuty();
      assertNotNull(physiciansOnDuty);
      assertEquals(2, physiciansOnDuty.size());
      assertEquals(1, physiciansOnDuty.get(0).getStaffId());
      assertEquals(2, physiciansOnDuty.get(1).getStaffId());
   }

   @Test
   public void sameTestWithMockito() {
      ArrayList<Staff> docsAndResidents = new ArrayList<>();
      ArrayList<Bed> beds = new ArrayList<>();
      docsAndResidents.add(new Staff(1, "John Doctor", StaffRole.DOCTOR));
      docsAndResidents.add(new Staff(2, "Frank Resident", StaffRole.RESIDENT));
      StaffProvider staffProviderMock = Mockito.mock(StaffProvider.class);
      BedProvider bedProviderMock = Mockito.mock(BedProvider.class);
      Mockito.when(staffProviderMock.getShiftStaff()).thenReturn(docsAndResidents);
      Mockito.when(bedProviderMock.getAllBeds()).thenReturn(beds);
      StaffAssignmentManager manager = new StaffAssignmentManager(staffProviderMock, bedProviderMock);

      List<Staff> physiciansOnDuty = manager.getPhysiciansOnDuty();

      assertNotNull(physiciansOnDuty);
      assertEquals(2, physiciansOnDuty.size());
      assertEquals(1, physiciansOnDuty.get(0).getStaffId());
      assertEquals(2, physiciansOnDuty.get(1).getStaffId());
   }
}