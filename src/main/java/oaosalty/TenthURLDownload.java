package oaosalty;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TenthURLDownload {

    public static void urlDownloader() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Введи полный адрес страницы, содержимое которой нужно сохранить:");
        String webpageURL = scan.nextLine();

        System.out.println("Введи путь в системе (без названия файла), куда необходимо сохранить html:");
        String path = scan.nextLine();

        //Запускаем новый поток через лямбду
        Thread t = new Thread(() ->
        {
            try {

                //Создаём объект класса URL
                URL url = new URL(webpageURL);

                //Сюда будет сохранено содержимое страницы
                BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));

                //Поток для записи файлов с помощью буфера
                BufferedWriter writer = new BufferedWriter(new FileWriter(path + "\\output.html"));

                //Если указанный пользователем путь хотя бы похож на правильный (содержит
                //"C:\\", например), то...
                if (path.matches("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?")) {
                    //Записываем в выходной файл построчно загружаемую страницу
                    String line;
                    while ((line = readr.readLine()) != null) writer.write(line);

                    //Закрываем потоки
                    readr.close();
                    writer.close();

                    System.out.println("Загрузка страница успешно завершена.");
                } else System.out.println("Указанный путь в файловой системе не может существовать.");
            }

            //Если адрес сайта указан неправильно - нет "https", например
            catch (MalformedURLException mue) {
                System.out.println("Адрес сайта указан неправильно. Протокол подключения необходимо указывать.");
            }
            //Если адрес написан правильно, но такой сайт не существует
            catch (UnknownHostException uhe) {
                System.out.println("Страница с указанным адресом не найдена. Опечатка, проблемы с интернетом?");
            }
            //Проблемы с записью файла или сайт нельзя сохранить (код 403)
            catch (IOException e) {
                System.out.println("Ошибка при работе с файловой системой или сайт не позволяет себя так сохранить.\n Случаем, не было ли указано название выходного файла? Смонтирован ли диск? Правильно ли указан путь?");
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
