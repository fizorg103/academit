package ru.academits.fedorov.vector;

import java.util.Arrays;

public class Vector {
    private double[] values;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argument N <= 0.");
        }

        values = new double[n];
    }

    public Vector(Vector vector) {
        values = Arrays.copyOf(vector.values, vector.values.length);
    }

    public Vector(double[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Values.length > 0.");
        }

        this.values = Arrays.copyOf(values, values.length);
    }

    public Vector(int n, double[] values) {
        this.values = Arrays.copyOf(values, n);
    }

    public int getSize() {
        return values.length;
    }

    public double[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    public void add(Vector vector) {
        int length = vector.values.length;
        if (length > values.length) {
            values = Arrays.copyOf(values, length);
        }

        for (int i = 0; i < length; ++i) {
            values[i] += vector.values[i];
        }
    }

    public void subtract(Vector vector) {
        int length = vector.values.length;
        if (length > values.length) {
            values = Arrays.copyOf(values, length);
        }

        for (int i = 0; i < length; ++i) {
            values[i] -= vector.values[i];
        }
    }

    public void mulByNumber(double alpha) {
        for (int i = 0; i < values.length; ++i) {
            values[i] *= alpha;
        }
    }

    public void turn() {
        mulByNumber(-1);
    }

    public double getLength() {
        double length = 0;
        for (double value : values) {
            length += Math.pow(value, 2);
        }
        return Math.sqrt(length);
    }

    public double getValue(int index) {
        if (index < 0 || index >= values.length) {
            throw new ArrayIndexOutOfBoundsException("index out of range");
        }
        return values[index];
    }

    public void setValue(int index, double value) {
        if (index < 0 || index >= values.length) {
            throw new ArrayIndexOutOfBoundsException("index out of range");
        }
        values[index] = value;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (values.length != vector.values.length) {
            return false;
        }

        for (int i = 0; i < values.length; ++i) {
            if (values[i] != vector.values[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        int length = values.length;
        for (int i = 0; i < length; ++i) {
            result.append(String.format("%.5f", values[i]));
            if (i == length - 1) {
                break;
            }
            result.append(", ");
        }
        result.append("}");
        return result.toString();
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorRes;
        if (vector1.values.length > vector2.values.length) {
            vectorRes = new Vector(vector1);
            vectorRes.add(vector2);
            return vectorRes;
        }

        vectorRes = new Vector(vector2);
        vectorRes.add(vector1);
        return vectorRes;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorRes;
        if (vector1.values.length > vector2.values.length) {
            vectorRes = new Vector(vector1);
            vectorRes.subtract(vector2);
        }

        vectorRes = new Vector(vector2);
        vectorRes.turn();
        vectorRes.add(vector1);

        return vectorRes;
    }

    public static double dot(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.values.length, vector2.values.length);
        double res = 0;
        for (int i = 0; i < minSize; ++i) {
            res += vector1.values[i] * vector2.values[i];
        }
        return res;
    }
}