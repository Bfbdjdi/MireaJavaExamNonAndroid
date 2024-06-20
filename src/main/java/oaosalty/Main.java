package oaosalty;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true)
        {
            System.out.println("\nВведи номер кода для запуска:\n" +
                    "1. Изменение строки\n" +
                    "2. МаксМинЗнач\n" +
                    "3. Сортировка Person\n" +
                    "4. Сортировка HashMap\n" +
                    "5. Удалить гласные из файла\n" +
                    "6. Сериализация\n" +
                    "7. Площадь прямоугольника\n" +
                    "8. Факториал\n" +
                    "9. Ошибка в коде\n" +
                    "10. Сайт\n");

            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            switch (input)
            {
                case "1":
                    FirstLineConverter.lineConverter();
                    break;
                case "2":
                    SecondInInputMinMax.findMinMax();
                    break;
                case "3":
                    ThirdPerson.thirdPerson();
                    break;
                case "4":
                    ForthHashMap.hashMapSorter();
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;

                default:
                    System.out.println("Нужно ввести только число.");
                    break;
            }
        }
    }
}