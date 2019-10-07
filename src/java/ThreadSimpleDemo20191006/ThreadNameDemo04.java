package ThreadSimpleDemo20191006;

/**
 * 线程名称练习
 *
 * @author zangtao
 * @create 2019 - 10 -07 12:11
 */
public class ThreadNameDemo04 {
    public static void main(String[] args) {
        MyThread1 mt = new MyThread1();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt,"线程A").start();
        new Thread(mt).start();
        new Thread(mt,"线程B").start();
        mt.run();
    }
}

class MyThread1 extends Thread{
    /**
     * 线程的主体方法
     */
    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName());
    }
}


