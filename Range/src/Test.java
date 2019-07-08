import ru.academits.fedorov.range.Range;

public class Test {
    public static void main(String[] args) {
        Range range1 = new Range(1.2, 2.5);
        Range range2 = new Range(1.4, 2.6);

        System.out.println("Интервал A: " + range1.toString());
        System.out.println("Интервал B: " + range2.toString());
        System.out.println();

        System.out.println("Длина интервала A = " + range1.getLength());

        double number = 0.97;
        System.out.println("Число " + number + " в интервале A: " + range1.isInside(number));
        number = 1.0;
        System.out.println("Число " + number + " в интервале A: " + range1.isInside(number));
        number = 2.05;
        System.out.println("Число " + number + " в интервале A: " + range1.isInside(number));
        System.out.println();

        Range range3 = range1.getIntersection(range2);
        System.out.print("A and B: ");
        if (range3 != null) {
            System.out.println(range3.toString());
        } else {
            System.out.println("пустой интервал");
        }
        Range[] rangeArray = range1.getUnion(range2);

        System.out.print("A or B:");
        for (Range element : rangeArray) {
            System.out.print(" " + element.toString());
        }
        System.out.println();

        System.out.print("A - B:");
        rangeArray = range1.getComplement(range2);
        if (rangeArray.length > 0) {
            for (Range element : rangeArray) {
                System.out.print(" " + element.toString());
            }
        } else {
            System.out.print(" пустой интервал");
        }
    }
}
