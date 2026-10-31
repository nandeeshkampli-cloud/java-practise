package advcalc;

public class Matrix {
    private final int rows;
    private final int cols;
    private final double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data;
    }

    public void setElement(int r, int c, double value) {
        data[r][c] = value;
    }

    public double getElement(int r, int c) {
        return data[r][c];
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.rows != m2.rows || m1.cols != m2.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match.");
        }
        Matrix result = new Matrix(m1.rows, m1.cols);
        for (int i = 0; i < m1.rows; i++) {
            for (int j = 0; j < m1.cols; j++) {
                result.setElement(i, j, m1.data[i][j] + m2.data[i][j]);
            }
        }
        return result;
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
