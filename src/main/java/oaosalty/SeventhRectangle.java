package oaosalty;

public class SeventhRectangle {

    public static void calcSurface() {

        SeventhThread threadToLaunch = new SeventhThread();

        //Запускаем новый поток через лямбду
        Thread th = new Thread(threadToLaunch);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
