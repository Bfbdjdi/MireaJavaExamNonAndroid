package oaosalty;

import java.io.*;
import java.util.Scanner;

public class SixthSerialize implements Externalizable {

    private static boolean numCheck(String strNum) {
        //Если преобразование String -> Integer получится, то норм, иначе String strNum
        //числом не может являться
        try {
            int intNum = Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException err) {
            return false;
        }
    }

    static Person person;

    public static void toSerialize() {

        //Следующие строки частично повторяют код
        //третьего задания, связанный с созданием массива из переменных класса Person
        {
            String name, STRAge, STRIsCat, STRIsVertolet;
            int age;
            boolean isCat;
            boolean isVertolet;

            //Собираем данные для объекта класса Person:

            Scanner scan = new Scanner(System.in);
            System.out.println("Введи имя:");
            name = scan.nextLine();

            System.out.println("Введи возраст:");
            while (true) {
                STRAge = scan.nextLine();

                //Если пользователь ввёл число, то норм
                if (numCheck(STRAge)) {
                    age = Integer.parseInt(STRAge);
                    if ((age > 0) && (age < 110)) break;
                    else System.out.println("Возраст вне рамок дозволенного. Повтори ввод.");
                } else System.out.println("Введено не число. Повтори ввод:");
            }

            System.out.println("Человек - кот? Ввод:");
            while (true) {
                STRIsCat = scan.nextLine();

                switch (STRIsCat.toLowerCase()) {
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
            while (true) {
                STRIsVertolet = scan.nextLine();

                switch (STRIsVertolet.toLowerCase()) {
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

            //Инициализируем персону
            person = new Person(name, age, isCat, isVertolet);
        }

        //Начинаем сериализовывать:

        Scanner scan = new Scanner(System.in);
        System.out.println("Введи полный путь к файлу:");
        String path = scan.nextLine();

        //Напоминание: данная переменная в коде не используется, но она нужна компилятору
        final long serialVersionUID = 1L;

        try {

            //Открываем поток для записи в файл
            FileOutputStream fileOutputStream = new FileOutputStream(path);

            //Открываем поток для записи персоны
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            //Пишем персону (функция описана ниже)
            objectOutputStream.writeObject(person);

            //Закрываем потоки
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Сохранено.\n");
        } catch (IOException e) {
            System.out.println("Отказано в доступе для сохранения файла. IDE/Программа запущена от имени администратора?\n");
        }
    }

    //Пишем в файл
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(person);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        person = (Person) in.readObject();
    }
}



