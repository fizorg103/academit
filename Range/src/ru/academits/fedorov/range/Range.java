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
        double to = range.getTo();
        double from = range.getFrom();
        if (from < this.from) {
            if (to < this.from) {
                return null;
            }
            if (to < this.to) {
                return new Range(this.from, to);
            }
            return new Range(this.from, this.to);
        }

        if (from < this.to) {
            if (to < this.to) {
                return new Range(from, to);
            }
            return new Range(from, this.to);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        double from = range.getFrom();
        double to = range.getTo();
        if (this.getIntersection(range) != null) {
            from = Math.min(this.from, from);
            to = Math.max(this.to, to);
            return new Range[]{new Range(from, to)};
        }
        return new Range[]{new Range(this.from, this.to), new Range(from, to)};
    }

    public Range[] getComplement(Range range) {
        double from = range.getFrom();
        double to = range.getTo();

        if (from < this.from) {
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