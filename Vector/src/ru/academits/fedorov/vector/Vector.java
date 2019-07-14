package ru.academits.fedorov.vector;

import java.util.Arrays;

public class Vector {
    private double[] values;

    public Vector(int n) {
        values = new double[n];

        for (int i = 0; i < n - 1; ++i) {
            values[i] = 0;
        }
    }

    public Vector(Vector vector) {
        values = Arrays.copyOf(vector.values, vector.values.length);
    }

    public Vector(double[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }

    public Vector(int n, double[] values) {
        this.values = Arrays.copyOf(values, n);
    }

    @Override
    public String toString() {
        String result = "{";
        int length = values.length;
        for (int i = 0; i < length; ++i) {
            result += String.format("%.5f", values[i]);
            if (i == length - 1) {
                break;
            }
            result += ", ";
        }
        result += "}";
        return result;
    }
}
