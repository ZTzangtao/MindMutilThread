package ThreadMethodDemo20191007;

/**
 * 线程休眠
 *
 * @author zangtao
 * @create 2019 - 10 -07 12:36
 */
public class ThreadSleepDemo {
    public static void main(String[] args) {
        new Thread(()->{
           for (int x = 0;x<10;x++){
               System.out.println(Thread.currentThread().getName() + "、x=" + x);
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"线程对象").start();
    }
}
