package 并发;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Singleton {

    private static volatile Singleton instance;
//    private static int id = (int)(Math.random() * 100);
    private Singleton(){}

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 100; i++) {
            es.execute(() -> {
                System.out.println( Singleton.getInstance());
            });
        }
    }
}
