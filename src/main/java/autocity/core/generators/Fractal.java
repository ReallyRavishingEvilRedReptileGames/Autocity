package autocity.core.generators;

import java.util.Random;

/**
 * Implements the diamond-square algorithm.
 */
public class Fractal {
    private double roughness = 0.1;
    private int size = 16;
    private Random random;
    private Double[][] map;

    public Fractal() {
        random = new Random();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getRoughness() {
        return roughness;
    }

    public void setRoughness(double roughness) {
        this.roughness = roughness;
    }

    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    public Double[][] generate() {
        map = new Double[size + 1][size + 1];

        // Set the values of each corner to 1 minus our variation
        map[0][0] = 0.5 - getDeviation();
        map[0][size] = 0.5 - getDeviation();
        map[size][0] = 0.5 - getDeviation();
        map[size][size] = 0.5 - getDeviation();

        this.divide(size);

        return map;
    }

    private void divide(double sub) {
        double half = sub / 2;
        double scale = roughness * sub;

        if (half < 1) {
            return;
        }

        // Square
        for (double y = half; y < size; y += sub) {
            for (double x = half; x < size; x += sub) {
                square((int) x, (int) y, (int) half, random.nextDouble() * scale * 2 - scale);
            }
        }

        // Diamond
        for (double y = 0; y <= size; y += half) {
            for (double x = (y + half) % sub; x <= size; x += sub) {
                diamond((int) x, (int) y, (int) half, Math.random() * scale * 2 - scale);
            }
        }

        this.divide(sub / 2);
    }

    private void square(int x, int y, int sub, double offset) {
        Double[] averages = {this.get(x + sub, y - sub), this.get(x - sub, y + sub), this.get(x - sub, y - sub), this.get(x + sub, y + sub)};

        map[x][y] = Math.min(1, Math.max(0, this.average(averages) + offset));
    }

    private void diamond(int x, int y, int sub, double offset) {
        Double[] averages = {this.get(x, y - sub), this.get(x, y + sub), this.get(x - sub, y), this.get(x + sub, y)};

        map[x][y] = Math.min(1, Math.max(0, this.average(averages) + offset));
    }

    private double average(Double[] nums) {
        double sum = 0;
        int count = 0;

        for (Double num : nums) {
            if (num == null) {
                continue;
            }

            sum += num;
            count++;
        }
        return sum / count;
    }

    private Double get(int x, int y) {
        try {
            return map[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private double getDeviation() {
        return random.nextDouble() * this.roughness;
    }
}
