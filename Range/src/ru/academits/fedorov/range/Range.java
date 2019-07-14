package ru.academits.fedorov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return this.to - this.from;
    }

    public boolean isInside(double number) {
        return number >= this.from && number <= this.to;
    }

    public double getFrom() {
        return this.from;
    }

    public double getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
    }

    public Range getIntersection(Range range) {
        double from = Math.max(this.from, range.from);
        double to = Math.min(this.to, range.to);
        if (from < to) {
            return new Range(from, to);
        }
        return null;
    }

    public Range[] getUnion(Range range) {
        double from = range.from;
        double to = range.to;

        double fromMax;
        double fromMin;
        if (from < this.from) {
            fromMax = this.from;
            fromMin = from;
        } else {
            fromMax = from;
            fromMin = this.from;
        }
        double toMax;
        double toMin;
        if (to < this.to) {
            toMax = this.to;
            toMin = to;
        } else {
            toMax = to;
            toMin = this.to;
        }

        if (fromMax <= toMin) {
            return new Range[]{new Range(fromMin, toMax)};
        }
        return new Range[]{new Range(this.from, this.to), new Range(from, to)};
    }

    public Range[] getComplement(Range range) {
        double from = range.from;
        double to = range.to;

        if (from <= this.from) {
            if (to < this.from) {
                return new Range[]{new Range(this.from, this.to)};
            }
            if (to < this.to) {
                return new Range[]{new Range(to, this.to)};
            }
            return new Range[]{};
        }

        if (from < this.to) {
            if (to < this.to) {
                return new Range[]{new Range(this.from, from), new Range(to, this.to)};
            }
            return new Range[]{new Range(this.from, from)};
        }
        return new Range[]{new Range(this.from, this.to)};
    }
}