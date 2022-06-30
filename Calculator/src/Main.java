import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static String calc(String input) throws Exception {
        String[] strings = input.split(" ");
        if (strings.length!=3) {
            throw new Exception();
        }
        String aType = "Roman", bType = "Roman";
        int a, b, res;

        if ("I".equals(strings[0])) {
            a = 1;
        } else if ("II".equals(strings[0])) {
            a = 2;
        } else if ("III".equals(strings[0])) {
            a = 3;
        } else if ("IV".equals(strings[0])) {
            a = 4;
        } else if ("V".equals(strings[0])) {
            a = 5;
        } else if ("VI".equals(strings[0])) {
            a = 6;
        } else if ("VII".equals(strings[0])) {
            a = 7;
        } else if ("VIII".equals(strings[0])) {
            a = 8;
        } else if ("IX".equals(strings[0])) {
            a = 9;
        } else if ("X".equals(strings[0])) {
            a = 10;
        } else {
            aType = "Arabic";
            try {
                a = Integer.parseInt(strings[0]);
                if (a>10) {
                    throw new Exception();
                }
            } catch (NumberFormatException mfe) {
                throw new Exception();
            }
        }

        if ("I".equals(strings[2])) {
            b = 1;
        } else if ("II".equals(strings[2])) {
            b = 2;
        } else if ("III".equals(strings[2])) {
            b = 3;
        } else if ("IV".equals(strings[2])) {
            b = 4;
        } else if ("V".equals(strings[2])) {
            b = 5;
        } else if ("VI".equals(strings[2])) {
            b = 6;
        } else if ("VII".equals(strings[2])) {
            b = 7;
        } else if ("VIII".equals(strings[2])) {
            b = 8;
        } else if ("IX".equals(strings[2])) {
            b = 9;
        } else if ("X".equals(strings[2])) {
            b = 10;
        } else {
            bType = "Arabic";
            try {
                b = Integer.parseInt(strings[2]);
                if (b>10) {
                    throw new Exception();
                }
            } catch (NumberFormatException mfe) {
                throw new Exception();
            }
        }

        if (!aType.equals(bType)) {
            throw new Exception();
        }

        if (strings[1].equals("+")) {
            res = a + b;
        } else if (strings[1].equals("-")) {
            res = a - b;
        } else if (strings[1].equals("*")) {
            res = a * b;
        } else if (strings[1].equals("/")) {
            res = a / b;
        } else throw new Exception();

        if (aType.equals("Roman")) {
            if (res >= 1) {
                return arabicToRoman(res);
            } else {
                throw new Exception();
            }
        } else return String.valueOf(res);

    }

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static String arabicToRoman(int number) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String s = calc(scanner.nextLine());
        System.out.println(s);
    }
}
