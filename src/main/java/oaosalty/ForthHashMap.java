package oaosalty;

import java.util.*;

public class ForthHashMap {

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

    public static void hashMapSorter()
    {

        //В этот Map всё будет сложено
        HashMap<String, Integer> integerHashMap = new HashMap<>();

        System.out.println("Сколько персон нужно создать? Ввод:");
        Scanner scan = new Scanner(System.in);
        String STRInput;

        //До победного просим пользователя ввести число элементов, которые нужно сохранить
        while (true)
        {
            //Считываем введённое в консоль
            STRInput = scan.nextLine();

            //Если норм, то норм
            if (numCheck(STRInput)) break;
                //Иначе...
            else System.out.println("Введено не число. Повтори ввод:");
        }

        for (int i = 0; i < Integer.parseInt(STRInput); i++)
        {
            String key, STRValue;
            int value;

            //Заполняем Map:
            System.out.println("Название " + (i+1) + "-ого ключа:");
            key = scan.nextLine();

            System.out.println("Значение ключа (целое число):");
            while (true)
            {
                STRValue = scan.nextLine();

                //Если пользователь ввёл число, то норм
                if (numCheck(STRValue))
                {
                    value = Integer.parseInt(STRValue);
                    break;
                }
                else System.out.println("Введено не число. Повтори ввод:");
            }

            //Сохраняем пару ключ+значение
            integerHashMap.put(key, value);
        }

        //Сортируем Map (функция сортировки описана ниже)
        integerHashMap = sortByValue(integerHashMap);

        for (Map.Entry<String, Integer> entry : integerHashMap.entrySet())
        {
            System.out.println(entry.getKey() + " / " + entry.getValue());
        }
    }

    public static HashMap<String, Integer> sortByValue(Map<String, Integer> map) {

        //Список (не ещё один Map), в который копируются все пары ключ+значение
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        //Сортируем список
        //На самом деле, "преобразовать" Map в List, отсортировать List и заново заполнить
        //Map куда проще, чем сортировать Map без "преобразований"
        list.sort(Map.Entry.comparingByValue());

        //Выходной Map
        HashMap<String, Integer> result = new LinkedHashMap<>();

        //Заполняем выходной Map
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
