package oaosalty;

import java.io.*;
import java.util.Scanner;

public class FifthVowelsFromFileRemover {

    //Этот метод проверяет существование любого файла, путь к которому был функции передан
    private static boolean fileExistenceChecker(String enteredPath)
    {
        File fileExistenceChecker = new File(enteredPath);
        return fileExistenceChecker.isFile();
    }

    public static void removeVowels()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введи полный путь к файлу:");
        String path = scan.nextLine();

        //Если файл существует, то...
        if (fileExistenceChecker(path))
        {
            try {

                //Открываем поток для чтения файлов
                FileReader fr = new FileReader(path);

                //Сюда будет скопировано содержимое файла в виде "как есть"
                BufferedReader br = new BufferedReader(fr);

                //Здесь будет храниться приведённое к нужному виду содержимое BufferedReader
                StringBuilder textBuilder = new StringBuilder();

                //Пока в файле есть строки для чтения
                while (br.ready())
                {
                    //Читаем строку из файла в BufferedReader, копируем её в StringBuilder, ставим \n в её конце
                    textBuilder.append(br.readLine()).append("\n");
                }

                //Замена всех гласных на звёздочку
                String text = textBuilder.toString().replaceAll( "[aeiouауоыиэяюёеAEIOUАУОЫИЭЯЮЁЕ]", "*" );

                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //Если файл не существует...
        else
        {
            System.out.println("Такого файла по такому пути не найдено.");
        }
    }
}
