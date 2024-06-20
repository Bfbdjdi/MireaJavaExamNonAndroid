package oaosalty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ThirdPerson {
    private static boolean numCheck (String strNum)
    {
        //Если преобразование String -> Integer получится, то норм, иначе String strNum
        //числом не может являться
        try
        {
            int intNum = Integer.parseInt(strNum);
            return true;
        }
        catch (NumberFormatException err)
        {
            return false;
        }
    }

    public static void thirdPerson()
    {
        //Массив персон
        ArrayList<Person> persons = new ArrayList<>();

        System.out.println("Сколько персон нужно создать? Ввод:");
        Scanner scan = new Scanner(System.in);
        String STRInput;

        //До победного просим пользователя ввести число людей, которых нужно добавить в массив
        while (true)
        {
            //Считываем введённое в консоль
            STRInput = scan.nextLine();

            //Если норм, то норм
            if (numCheck(STRInput)) break;
            //Иначе...
            else System.out.println("Введено не число. Повтори ввод:");
        }

        //Добавляем персон в массив
        for (int i = 0; i < Integer.parseInt(STRInput); i++)
        {
            String name, STRAge, STRIsCat, STRIsVertolet;
            int age;
            boolean isCat;
            boolean isVertolet;

            System.out.println("Введи имя:");
            name = scan.nextLine();

            System.out.println("Введи возраст:");
            while (true)
            {
                STRAge = scan.nextLine();

                //Если пользователь ввёл число, то норм
                if (numCheck(STRAge))
                {
                    age = Integer.parseInt(STRAge);
                    if ((age > 0) && (age < 110)) break;
                    else System.out.println("Возраст вне рамок дозволенного. Повтори ввод.");
                }
                else System.out.println("Введено не число. Повтори ввод:");
            }

            System.out.println("Человек - кот? Ввод:");
            while (true)
            {
                STRIsCat = scan.nextLine();

                switch (STRIsCat.toLowerCase())
                {
                    case "да":
                        isCat = true;
                        break;
                    case "нет":
                        isCat = false;
                        break;
                    default:
                        System.out.println("Введено не 'да' и не 'нет'. Повтори ввод:");
                        continue;
                }
                break;
            }

            System.out.println("Человек - вертолёт? Ввод:");
            while (true)
            {
                STRIsVertolet = scan.nextLine();

                switch (STRIsVertolet.toLowerCase())
                {
                    case "да":
                        isVertolet = true;
                        break;
                    case "нет":
                        isVertolet = false;
                        break;
                    default:
                        System.out.println("Введено не 'да' и не 'нет'. Повтори ввод:");
                        continue;
                }
                break;
            }

            //Добавляем конкретно эту персону в массив
            persons.add(new Person(name, age, isCat, isVertolet));
        }

        //Сортируем массив
        persons.sort(Comparator.comparing(Person::getName));

        System.out.println("Итоговый список:");
        for (Person entry : persons) System.out.println(entry.getName());
    }
}
