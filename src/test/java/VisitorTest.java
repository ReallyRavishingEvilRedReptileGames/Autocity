import autocity.core.Character;
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

        for (Character character : townHall.getVisitors()) {
            System.out.println(character);
        }
    }
}
