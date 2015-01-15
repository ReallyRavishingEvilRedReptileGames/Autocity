import autocity.core.generators.Fractal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Fiskie on 15/01/2015.
 */
public class FractalTest {
    private Fractal fractal;

    @Before
    public void setUp() {
        this.fractal = new Fractal();
    }

    @Test
    public void testDefault() {

    }

    @After
    public void tearDown() {
        Double[][] map = this.fractal.generate();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%5.3f ", map[i][j]);
            }

            System.out.println();
        }
    }
}
