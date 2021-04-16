public class Sync {
    //交替打印ABC
    public static class ThreadPrinter implements Runnable{
        private char letter;
        private Object prev;
        private Object self;
        public ThreadPrinter(char letter,Object prev,Object self){
            this.letter = letter;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            //循环10次
            int count = 10;
            while(count>0){
                synchronized (prev){
                    synchronized (self){
                        //business
                        System.out.println(letter);
                        count--;
                        self.notify();
                    }
                    try {
                        if(count==0){
                            prev.notifyAll();
                        }else{
                            prev.wait();//挂起，等待下一轮
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter printerA = new ThreadPrinter('A',c,a);//这些都是runnable class
        ThreadPrinter printerB = new ThreadPrinter('B',a,b);
        ThreadPrinter printerC = new ThreadPrinter('C',b,c);
        new Thread(printerA).start();
        Thread.sleep(10);
        new Thread(printerB).start();
        Thread.sleep(10);
        new Thread(printerC).start();
        Thread.sleep(10);
    }
}
