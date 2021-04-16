import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Renetrantlock {

    private static int state = 0;
    private static Lock lock = new ReentrantLock();

    public static class ThreadPrinterA implements Runnable{

        @Override
        public void run() {
            int count = 10;
            while(count>0){
                try{
                    lock.lock();
                    while(state%3==0){
                        System.out.println("A");
                        count--;
                        state++;
                    }

                }finally {
                    lock.unlock();
                }
            }
        }
    }
    public static class ThreadPrinterB implements Runnable{


        @Override
        public void run() {
            int count = 10;
            while(count>0){
                try{
                    lock.lock();
                    while(state%3==1){
                        System.out.println("B");
                        count--;
                        state++;
                    }

                }finally {
                    lock.unlock();
                }
            }
        }
    }
    public static class ThreadPrinterC implements Runnable{

        @Override
        public void run() {
            int count = 10;
            while(count>0){
                try{
                    lock.lock();
                    while(state%3==2){
                        System.out.println("C");
                        count--;
                        state++;
                    }

                }finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPrinterA a = new ThreadPrinterA();
        ThreadPrinterB b = new ThreadPrinterB();
        ThreadPrinterC c = new ThreadPrinterC();
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}
