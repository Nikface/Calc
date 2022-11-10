import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = in.nextLine();
        System.out.println(calc(input));

    }
    public static String calc (String input) {
        String[] signs = {"+", "-", "*", "/"};
        String[] signs2 = {"\\+", "-", "\\*", "/"};
        int sign = -1;
        for (int i = 0; i < 4; i++) {
            if (input.contains(signs[i])) {
                sign = i;
            }
        }
        if (sign == -1) {
            return "ОШИБКА, вы не ввели знак действия!";
        }

        String[] numbs = input.split(signs2[sign]);
        int length = numbs.length;

        if (length > 2) {
            return "ОШИБКА, вы ввели больше двух переменных!";
        }
        if (length < 2) {
            return "ОШИБКА, вы ввели меньше двух переменных!";
        }

        if (Check.convert(numbs[0]) == false && Check.convert(numbs[1]) == false) {
            int numb0 = Integer.parseInt(numbs[0]);
            if (numb0 > 10 || numb0 < 1) {
                return "Введенное число не попадает в диапозон от 1 до 10.";
            }
            int numb1 = Integer.parseInt(numbs[1]);
            if (numb1 > 10 || numb1 < 1) {
                return "Введенное число не попадает в диапозон от 1 до 10.";
            }
            switch (sign) {
                case 0: int o = numb0 + numb1; return Integer.toString(o);
                case 1: o = numb0 - numb1; return Integer.toString(o);
                case 2:  o = numb0 * numb1; return Integer.toString(o);
                case 3: o = numb0 / numb1; return Integer.toString(o);
            }
        }
        else if (Check.convert(numbs[0]) == true && Check.convert(numbs[1]) == true) {
            int a = Voz.Rim(numbs[0]);
            if (a == 1234567) {
                return "Римское число записано некорректно!";
            }
            if (a > 10 || a < 1) {
                return "Введенное число не попадает в диапозон от 1 до 10.";
            }
            int b = Voz.Rim(numbs[1]);
            if (b > 10 || b < 1) {
                return "Введенное число не попадает в диапозон от 1 до 10.";
            }
            int c = 0;
            switch (sign) {
                case 0:
                    c = a + b;
                    break;
                case 1:
                    c = a - b;
                    break;
                case 2:
                    c = a * b;
                    break;
                case 3:
                    c = a / b;
                    break;
            }
            if (c < 1) {
                return "ОШИБКА, ответ меньше единицы!";
            }
            else {
                return Voz.Rim1(c);
            }
        }
        else {
            return "ОШИБКА, разные системы счисления!";
        }
        return "";
    }
}

class Check {
    public static boolean convert(String numbs) {
        char[] num = numbs.toCharArray();
        int length = num.length;

        for (int i = 0; i < length; i++) {
            switch (num[i]) {
                case 'I':
                    break;
                case 'V':
                    break;
                case 'X':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
}

class Voz {
    public static int Rim(String num) {
        char[] arr = num.toCharArray();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            switch (arr[i]) {
                case 'I':
                    arr[i] = 1;
                    break;
                case 'V':
                    arr[i] = 5;
                    break;
                case 'X':
                    arr[i] = 10;
                    break;
                case 'L':
                    arr[i] = 50;
                    break;
                case 'C':
                    arr[i] = 100;
                    break;
                case 'D':
                    arr[i] = 500;
                    break;
                case 'M':
                    arr[i] = 1000;
                    break;
                default:
                    System.out.println("Вы ввели не римский символ!");
                    return 0;
            }
        }

        int j = 1;
        for (int i = 0; i < length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                j++;
                if (j > 3) {
                    return 1234567;
                }
            }
            else {
                j = 1;
            }
        }


        int end = arr[length - 1];
        int result = end;
        for (int i = length - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                result -= arr[i - 1];
            }
            else {
                result += arr[i - 1];
            }
        }
        return result;
    }

    public static String Rim1(int result) {
        String itog = "";
        int res2 = result;

        while (res2 != 0) {
            if (res2 >= 100) {
                itog += "C";
                res2 -= 100;
            }
            if (res2 >= 50 && res2 < 100) {
                itog += "L";
                res2 -= 50;
            }
            if (res2 >= 10 && res2 < 50) {
                itog += "X";
                res2 -= 10;
            }
            if (res2 == 9) {
                itog += "IX";
                res2 -= 9;
            }
            if (res2 < 9 && res2 >= 5) {
                itog += "V";
                res2 -= 5;
            }
            if (res2 == 4) {
                itog += "IV";
                res2 -= 4;
            }
            if (res2 < 4 && res2 >= 1) {
                itog += "I";
                res2 -= 1;
            }
        }
        return itog;
    }
}
