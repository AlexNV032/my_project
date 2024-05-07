import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите математическое действие между двумя числами от 1 до 10 \n (арабскими или римскими цифрами), \n например (2*8) или (III+V) и нажмите Enter");

        String input = scanner.nextLine();

        scanner.close();

        System.out.println(calc(input));

    }

    public static String calc(String input) throws IOException {

        char[] chars = input.toCharArray();
        int a = 0;
        int b = 0;
        int result = 0;
        char matOperation = 0;
        boolean isOnlyDigits = true;
        boolean isOnlyRomans = true;

        String[] strings = input.split("[+-/*]");

        // Проверяем на количество чисел и наличае математического знака
        if (strings.length != 2) {
            throw new IOException("Введено неверное количество чесел или недопустимые символы");
        }

        // Проверяем на соответствие цифр одной системе счисления
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length(); j++) {
                if (!Character.isDigit(strings[i].charAt(j))) {
                    isOnlyDigits = false;
                }
                int q = Integer.parseInt(convertToArabic(strings[i]));
                if (q == 0) {
                    isOnlyRomans = false;
                }
            }
        }
        if (!isOnlyDigits && !isOnlyRomans) {
            throw new IOException("Введены недопустимые значения");
        }

        // Если числа римские переводим в арабские для вычисления
        if (isOnlyRomans) {
            a = Integer.parseInt(convertToArabic(strings[0]));
            b = Integer.parseInt(convertToArabic(strings[1]));
        }

        // Если числа арабские переводим в формат int
        if (isOnlyDigits) {
            a = Integer.parseInt(strings[0]);
            b = Integer.parseInt(strings[1]);
        }

        // Проверяем величину значений
        if (a < 1 || a > 10 || b < 1 || b > 10){
            throw new IOException("Числа не соответствуют заданному диапазону");
        }

        // Определяем математический знак
        for (int i = 0; i < input.length(); i++) {
            if (chars[i] == '/') {
                matOperation = '/';
            } else if (chars[i] == '*') {
                matOperation = '*';
            } else if (chars[i] == '-') {
                matOperation = '-';
            } else if (chars[i] == '+') {
                matOperation = '+';
            }
        }

        // Проводим вычисление
        switch (matOperation) {
            case '/':
                result = a / b;
                break;
            case '*':
                result = a * b;
                break;
            case '-':
                result = a - b;
                break;
            case '+':
                result = a + b;
                break;
        }

        // Возвращаем результат если римские цифры
        if (isOnlyRomans){
            String resultRoman = "";
            if (result <= 0){
                throw new IOException("Результат вычисления меньше 1");
            }

            if (result / 100 > 0){
                resultRoman += convertToRoman(result / 100 * 100);
            }
            if (result % 100 / 10 > 0){
                resultRoman += convertToRoman(result % 100 / 10 * 10);
            }
            if (result % 10 > 0){
                resultRoman += convertToRoman(result % 10);
            }

            return input = resultRoman;
            // Возвращаем результат если арабские цифры
        } else {
            return input = Integer.toString(result);
        }
    }



    static String convertToArabic(String roman) {

        if (roman.equals("I")) return "1";
        if (roman.equals("II")) return "2";
        if (roman.equals("III")) return "3";
        if (roman.equals("IV")) return "4";
        if (roman.equals("V")) return "5";
        if (roman.equals("VI")) return "6";
        if (roman.equals("VII")) return "7";
        if (roman.equals("VIII")) return "8";
        if (roman.equals("IX")) return "9";
        if (roman.equals("X")) return "10";
        return "0";
    }

    static String convertToRoman(int arabic) {
        if (arabic == 1) return "I";
        if (arabic == 2) return "II";
        if (arabic == 3) return "II";
        if (arabic == 4) return "IV";
        if (arabic == 5) return "V";
        if (arabic == 6) return "VI";
        if (arabic == 7) return "VII";
        if (arabic == 8) return "VIII";
        if (arabic == 9) return "IX";
        if (arabic == 10) return "X";
        if (arabic == 20) return "XX";
        if (arabic == 30) return "XXX";
        if (arabic == 40) return "XL";
        if (arabic == 50) return "L";
        if (arabic == 60) return "LX";
        if (arabic == 70) return "LXX";
        if (arabic == 80) return "LXXX";
        if (arabic == 90) return "XC";
        if (arabic == 100) return "C";
        return "Невозможен подобный результат";
    }

}
