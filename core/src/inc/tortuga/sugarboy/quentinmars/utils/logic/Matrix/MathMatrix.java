package inc.tortuga.sugarboy.quentinmars.utils.logic.Matrix;

/**
 * Created by swift on 27.11.2017.
 */

public class MathMatrix<T> {

    private Object[][] matrix;
    private int x = -1;
    private int y = 0;
    private int width;
    private int height;

    public MathMatrix(int width, int height) {
        this.matrix = new Object[width][height];
        this.width = width;
        this.height = height;
    }

    public void set(int x, int y, T obj) {
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        this.matrix[x][y] = obj;
    }

    public <T> T get(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return null;
        return (T) this.matrix[x][y];
    }

    public void generateIter() {
        this.x = 0;
        this.y = 0;
    }

    public <T> T iter() {
        x++;
        if (this.x == width) {
            y++;
            x = -1;
        }
        T obj = (T) matrix[x][y];
        return obj;
    }

    public boolean contains(T obj) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix[x][y].equals(obj)) return true;
            }
        }
        return false;
    }

    public boolean hasNext() {
        return y != height;
    }

    public String toString() {
        for (int y = height-1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                System.out.print(get(x, y) + " ");
            }
            System.out.println();
        }
        return "";
    }

}
