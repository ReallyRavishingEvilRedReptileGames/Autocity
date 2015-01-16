import autocity.core.civilians.prefabs.Villager;
import autocity.core.generators.strings.GenericPersonName;
import org.junit.Test;

public class NameTest {
    @Test
    public void test() {
        Villager villager = new Villager();
        GenericPersonName genericPersonName = new GenericPersonName(villager);

        for (int i = 0; i < 100; i++) {
            System.out.println(genericPersonName.getFullName());
        }
    }
}
