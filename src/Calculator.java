import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();


        //Определяем арифметическое действие
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
//        Проверяем число операндов
        String action = new String();
        int couter = 0;

        for (char i : exp.toCharArray()) {
            if (i == '+' || i == '-' || i == '+' || i == '/') {
                couter++;
                action = "\\" + i;
            }
        }
        if (couter != 1) {
            System.out.println("Ошибка!Выражение записано неверно, должно быть 2 операнда и 1 оператор(+,-,/,*)");
            return;
        }

        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            System.out.println("Некорректное выражение");
            return;
        }


        //Делим строчку по найденному арифметическому знаку
        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //если римские, то конвертируем их в арабские
                //X+V
                //x-10
                //v - 5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }


            //Ошибка если число меньше 1
            if ((a | b) < 1) {
                System.out.println("ошибка, число не может быть меньше 1");
                return;
            } else if ((a | b) > 10) {
                System.out.println("Ошибка, число не может быть больше 10");
                return;
            }

            //выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            //15->XV
            if (isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            } else {
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }


    }

}
