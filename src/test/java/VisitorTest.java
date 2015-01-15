import autocity.core.Person;
import autocity.core.civilians.Villager;
import autocity.core.tiles.buildings.TownHall;
import org.junit.Test;

public class VisitorTest {
    @Test
    public void testVisitors() {
        TownHall townHall = new TownHall();

        Villager villager = new Villager();

        townHall.addVisitor(villager);
        townHall.addVisitor(villager);

        System.out.println("Listing visitors...");

        for (Person person : townHall.getVisitors()) {
            System.out.println(person);
        }
    }
}
