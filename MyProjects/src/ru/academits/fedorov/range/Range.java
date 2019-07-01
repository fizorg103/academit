package ru.academits.fedorov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from > to) {
            double temp = from;
            from = to;
            to = temp;
        }
        this.from = from;
        this.to = to;
    }

    public double getIntervalDistance() {
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

    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
    }

    public static Range getIntersection(Range range1, Range range2) {
        if (range1.getFrom() > range2.getFrom()) {
            Range temp = range1;
            range1 = range2;
            range2 = temp;
        }
        if (range1.isInside(range2.getFrom())) {
            if (range2.isInside(range1.getTo())) {
                return new Range(range2.getFrom(), range1.getTo());
            } else {
                return new Range(range2.getFrom(), range2.getTo());
            }
        }
        return null;
    }

    public static Range[] getUnion(Range range1, Range range2) {
        if (getIntersection(range1, range2) != null) {
            double from = Math.min(range1.getFrom(), range2.getFrom());
            double to = Math.max(range1.getTo(), range2.getTo());
            return new Range[]{new Range(from, to)};
        }
        return new Range[]{range1, range2};
    }

    public static Range[] getComplement(Range range1, Range range2) {
        if (range1.getFrom() >= range2.getFrom()) {
            if (range1.getFrom() > range2.getTo()) {
                return new Range[]{range1};
            } else if (range1.getTo() > range2.getTo()) {
                return new Range[]{new Range(range2.getTo(), range1.getTo())};
            } else {
                return null;
            }

        } else {
            if (range1.getTo() >= range2.getFrom()) {
                if (range1.getTo() > range2.getTo()) {
                    return new Range[]{new Range(range1.getFrom(), range2.getFrom()),
                            new Range(range2.getTo(), range1.getTo())};
                } else {
                    return new Range[]{new Range(range1.getFrom(), range2.getFrom())};
                }
            } else {
                return new Range[]{range1};
            }
        }
    }

    public static void main(String[] args) {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(1.5, 3);
        System.out.println("Интервал A: " + range1.toString());
        System.out.println("Интервал B: " + range2.toString());
        System.out.println();

        System.out.println("Длина интервала A = " + range1.getIntervalDistance());

        double number = 0.97;
        System.out.println("Число " + number + " в интервале A: " + range1.isInside(number));
        number = 1.0;
        System.out.println("Число " + number + " в интервале A: " + range1.isInside(number));
        number = 2.05;
        System.out.println("Число " + number + " в интервале A: " + range1.isInside(number));
        System.out.println();
        Range range3 = getIntersection(range1, range2);
        System.out.print("A and B: ");
        if (range3 != null) {
            System.out.println(range3.toString());
        } else {
            System.out.println("пустой интервал");
        }
        Range[] rangeArray = getUnion(range1, range2);

        System.out.print("A or B:");
        for (Range element : rangeArray) {
            System.out.print(" " + element.toString());
        }
        System.out.println();

        System.out.print("A - B:");
        rangeArray = getComplement(range1, range2);
        if (rangeArray != null) {
            for (Range element : rangeArray) {
                System.out.print(" " + element.toString());
            }
        } else {
            System.out.print(" пустой интервал");
        }
    }
}