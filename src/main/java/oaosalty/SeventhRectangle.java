package oaosalty;

import java.util.Scanner;

public class SeventhRectangle {

    private static boolean numCheck(String strNum) {
        //Если преобразование String -> Integer получится, то норм, иначе String strNum
        //числом не может являться
        try {
            float intNum = Float.parseFloat(strNum);
            return true;
        } catch (NumberFormatException err) {
            return false;
        }
    }

    public static void calcSurface() {

        //Запускаем новый поток через лямбду
        Thread t = new Thread(
                () -> {

                    //Сюда сохраняем ввод
                    String STRLength, STRWidth;
                    float length, width;

                    Scanner scan = new Scanner(System.in);

                    System.out.println("Длина прямоугольника:");
                    while(true)
                    {
                        STRLength = scan.nextLine();

                        //Если пользователь ввёл число, то норм
                        if (numCheck(STRLength)) {
                            length = Float.parseFloat(STRLength);
                            if (length <= 0) {
                                System.out.println("Длина не может быть меньше нуля или равна ему. Повтори ввод:");
                                continue;
                            }
                            break;
                        } else System.out.println("Введено не число. Повтори ввод:");
                    }

                    System.out.println("Ширина прямоугольника:");
                    while(true)
                    {
                        STRWidth = scan.nextLine();

                        //Если пользователь ввёл число, то норм
                        if (numCheck(STRWidth)) {
                            width = Float.parseFloat(STRWidth);
                            if (width <= 0) {
                                System.out.println("Ширина не может быть меньше нуля или равна ему. Повтори ввод:");
                                continue;
                            }
                            break;
                        } else System.out.println("Введено не число. Повтори ввод:");
                    }

                    System.out.println("Площадь прямоугольника S = "+(length *width));
                });
        try {
            t.start();
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
