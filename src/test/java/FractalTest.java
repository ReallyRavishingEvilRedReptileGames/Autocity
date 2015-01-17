import autocity.core.generators.fractals.DiamondSquareFractal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FractalTest {
    private DiamondSquareFractal diamondSquareFractal;

    @Before
    public void setUp() {
        this.diamondSquareFractal = new DiamondSquareFractal();
    }

    @Test
    public void testDefault() {
        this.diamondSquareFractal.setSeed(1);
    }

    @After
    public void tearDown() {
        this.diamondSquareFractal.setRoughness(0.03);
        Double[][] map = this.diamondSquareFractal.generate();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%5.3f ", map[i][j]);
            }

            System.out.println();
        }
    }
}
