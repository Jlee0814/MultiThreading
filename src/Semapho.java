
import java.util.concurrent.Semaphore;

public class Semapho {
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);
    public static class ThreadA extends Thread{
        public void run(){
            try{
                for(int i=0;i<10;i++){
                    A.acquire();
                    System.out.println("A");
                    B.release();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static class ThreadB extends Thread{
        public void run(){
            try{
                for(int i=0;i<10;i++){
                    B.acquire();
                    System.out.println("B");
                    C.release();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static class ThreadC extends Thread{
        public void run(){
            try{
                for(int i=0;i<10;i++){
                    C.acquire();
                    System.out.println("C");
                    A.release();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
