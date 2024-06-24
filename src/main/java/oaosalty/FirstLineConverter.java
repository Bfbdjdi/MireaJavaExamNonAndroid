package oaosalty;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FirstLineConverter {
    public static void lineConverter()
    {
        System.out.println("Введи строку:");
        //Считываем введённое в консоль
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        //Все 3 преобразования input в одной строке с помощью StringBuilder - тип как String, но изменяемый
//        String modifiedInput = new StringBuffer(input).reverse().replace(0, 1, "")
//                .replace(input.length()-2,input.length()-1,"").toString().toUpperCase();

        //Второй вариант переворота через stringBuffer
        String perevorot2 = new StringBuffer(input).reverse().toString();
        //Конец
        String perevorot = "";
        for (int i = input.length()-1;i>=0;i--){
            perevorot = perevorot + input.charAt(i);
        }
        String oneLastDel = input.substring(1,input.length()-2);
        String vverh = input.toUpperCase();

        try {
            //Создаётся папка на диске. Возможно, придётся запустить IDE от имени администратора
            Path path = Paths.get("C:\\FirstLineConverter\\");
            Files.createDirectories(path);

            //Создаём поток для записи в файл
            FileWriter fw = new FileWriter("C:\\FirstLineConverter\\output.txt");
            String modifiedInput = perevorot+"\n"+oneLastDel+"\n"+vverh;
            //Пишем в файл
            fw.write(modifiedInput);

            //Закрываем поток
            fw.close();
        } catch (IOException e) {
            System.out.println("\nНе удалось записать строку в файл.\n");
        }
    }
}
