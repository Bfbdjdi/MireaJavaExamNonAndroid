package oaosalty;

import java.math.BigInteger;
import java.util.Scanner;

public class EighthFactorial {

    //Поиск факториала рекурсивно
    /*public static BigInteger factorialCalc(int num) {
        if (num <= 1) return BigInteger.valueOf(1);
        else return (BigInteger.valueOf(num).multiply(factorialCalc(num - 1)));
    }*/

    private static boolean numCheck(String strNum) {
        //Если преобразование String -> Integer получится, то норм, иначе String strNum
        //числом не может являться
        try {
            float intNum = Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException err) {
            return false;
        }
    }

    public static void factorialCalculator() {

        //Запускаем новый поток
        Thread t = new Thread(() ->
        {
            //Число, для которого нужно искать факториал
            int enteredToFactorializeNum;
            String STREnteredToFactorializeNum;

            //Переменная для хранения факториала числа
            BigInteger result = BigInteger.ONE;

            Scanner scan = new Scanner(System.in);
            System.out.println("Введи число, для которого нужно найти факториал:");

            while (true) {
                STREnteredToFactorializeNum = scan.nextLine();

                //Если пользователь ввёл число, то норм
                if (numCheck(STREnteredToFactorializeNum)) {
                    enteredToFactorializeNum = Integer.parseInt(STREnteredToFactorializeNum);

                    //Поиск факториала через цикл
                    int i = 1;
                    while (i <= enteredToFactorializeNum)
                    {
                        result = result.multiply(BigInteger.valueOf(i));
                        i++;
                    }

                    if (enteredToFactorializeNum < 0) {
                        System.out.println("Число не может быть меньше нуля. Повтори ввод:");
                        continue;
                    }
                    if (enteredToFactorializeNum >= 1000) {
                        System.out.println("Можно, но не нужно искать такой большой факториал. Повтори ввод:");
                        continue;
                    }
                    break;
                } else System.out.println("Введено не целое число. Повтори ввод:");
            }

            System.out.println("Факториал числа " + enteredToFactorializeNum + " равен " + /*через рексурсию: factorialCalc(enteredToFactorializeNum)*/ result);
        });
        try {
            t.start();
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
