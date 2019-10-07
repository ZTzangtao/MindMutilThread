package ThreadMethodDemo20191007;

/**
 * 线程中断操作
 *
 * @author zangtao
 * @create 2019 - 10 -07 13:31
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("***补充精力");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("***没睡好");

            }
//            System.out.println("***睡足了");
            }
        );
        thread.start();
        Thread.sleep(1000);
        if (!thread.isInterrupted()){
            System.out.println("打断！！");
            thread.interrupt();
        }
    }
}
