package oaosalty;

import java.util.*;

public class SecondInInputMinMax {

    public static boolean numCheck (String strNum)
    {
        //Если преобразование String -> Float получится, то норм, иначе String
        //числом не может являться
        try
        {
            Float floatNum = Float.valueOf(strNum);
            return true;
        }
        catch (NumberFormatException err)
        {
            return false;
        }
    }

    public static void findMinMax()
    {
        //Выключатель для цикла while, если пользователь ввёл всё в консоль как надо
        boolean isInputOk = false;

        //Сохраняем введённое
        String input = "";
        String[] inputSplit;
        HashSet<Float> numSet = new HashSet<>();

        //Крутимся в цикле, если пользователь вводит в консоль не числа
        while (!isInputOk)
        {
            //Если ввод норм, то более переменная не изменится и немного позже цикл остановится
            isInputOk = true;
            System.out.println("Введи через запятую набор чисел:");

            //Считываем введённоё в консоль
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine().replaceAll(" ", "").replaceAll("[.]{2,}", ".").replaceAll("[+]{2,}", "+").replaceAll("[-]{2,}", "-");

            //Нарезаем одну строку с "числами" в массив
            inputSplit = input.split(",");

            //Если хоть что-то введено в консоль, то ...
            if ((inputSplit.length > 0) && (!input.isEmpty()))
            {

                //Перебираем все элементы массива
                for (String element : inputSplit) {

                    //Если отдельно взятый элемент не число, то ругаемся и цикл заново
                    if ((!numCheck(element)) && (!element.isEmpty())) {
                        System.out.println(element + " - не число. Вводить можно только числа. Повтори ввод.");

                        //false = цикл будет перезапущен
                        isInputOk = false;
                        break;
                    }
                    //Если элемент не пустые "", то...
                    else if (!element.isEmpty())
                    {
                        float numToAdd = Float.parseFloat(element);
                        if (Float.isFinite(numToAdd))
                        {
                            numSet.add(numToAdd);
                        }
                        else
                        {
                            //Не забываем про ограничения типа float
                            System.out.println("Слишком большие числа вводить не допускается, программа их игнорирует.");
                        }
                    }
                }
            }
            else
            {
                System.out.println("Ничего не введено. Повтори ввод.");
                isInputOk = false;
            }
        }

        //Если ввод норм, то заканчиваем:

        //Нет необходимости, но всё же сортируем набор введённых чисел
        List<Float> sortedList = new ArrayList<>(numSet);
        Collections.sort(sortedList);
        System.out.println("Словарик: " + sortedList);

        System.out.println("Наименьшее число: " + numSet.stream().min(Float::compare).get());
        System.out.println("Наибольшее число: " + numSet.stream().max(Float::compare).get());
    }
}
