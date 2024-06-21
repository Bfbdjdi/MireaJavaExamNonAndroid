package oaosalty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;

public class NinthDebug {

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

    public static void toDebug() {
        System.out.println("Введите число больше 5");
        Scanner sc = new Scanner(System.in);
        int value;

        //Правка №1 : изначально проверки на ввод не было вообще.
        //Суть правки - применение всего того кода, который я сам использовал
        //для проверки ввода чисел - while(true) и метод numCheck.
        //Используем не изначальную
        //  "int value = sc.nextInt();",
        // а...

        //Исправление с применением моего numCheck
        {
            String STRInput;

            //До победного просим пользователя ввести число людей, которых нужно добавить в массив
            while (true) {
                //Считываем введённое в консоль
                STRInput = sc.nextLine();

                //Если норм, то норм
                if (numCheck(STRInput)) break;
                    //Иначе...
                else System.out.println("Введено не целое число. Повтори ввод:");
            }

            value = Integer.parseInt(STRInput);
        }

        if (value > 5) {
            Integer[] array = new Integer[value];

            //Правка №2 : не "<=", вызывающий ArrayIndexOutOfBoundsException, a "<"
            for (int i = 0; i < value; i++) {
                array[i] = (i + 1) * 2;
            }

            int data = Arrays.stream(array).max((Integer o1, Integer o2) -> o1.compareTo(o2)).get();
            Thread t = new Thread(() -> {
                try {
                    URL url = new URL("https://android-for-students.ru/exam/calc.php");
                    URLConnection connection = url.openConnection();

                    //Правка №3 : ... = (HttpURLConnection) connection - потребовалось преобразование типа
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;

                    httpConnection.setRequestMethod("POST");

                    //Правка №4 : не был указан true
                    httpConnection.setDoOutput(true);

                    OutputStreamWriter osw = new OutputStreamWriter(httpConnection.getOutputStream());
                    osw.write("value=" + data);
                    osw.flush();

                    int responseCode = httpConnection.getResponseCode();
                    System.out.println("Response Code : " + responseCode);
                    if (responseCode == 200) {
                        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
                        BufferedReader br = new BufferedReader(isr);
                        String currentLine;
                        StringBuilder sbResponse = new StringBuilder();
                        while ((currentLine = br.readLine()) != null) {
                            sbResponse.append(currentLine);
                        }
                        String responseBody = sbResponse.toString();
                        System.out.println("Integer value from server: " + Integer.valueOf(responseBody));
                    } else {
                        System.out.println("Error! Bad response code!");
                    }
                } catch (MalformedURLException ex) {
                    System.out.println("URL error" + ex.getLocalizedMessage());
                } catch (IOException ex) {

                    //Правка №5 : ранее был вывод сведений об ошибке, которые не несли полезных сведений
                    System.out.println("Возникла ошибка во время подключения к " + ex.getLocalizedMessage());
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
