package ThreadMethodDemo20191007;

/**
 * 线程强制执行
 *
 * @author zangtao
 * @create 2019 - 10 -07 14:35
 */
public class ThreadEnforceDemo {
    public static void main(String[] args) throws InterruptedException {
        //获得主线程
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(()->{
            for (int x = 0; x< 100; x ++){
                if (x==3){
                    try {
                        //main线程先执行
                        mainThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行x=" + x);
            }
        },"玩耍-线程");
        thread.start();
        for (int x = 0; x<100;x++){
            Thread.sleep(100);
            if (x > 3){

            }
            System.out.println("main线程number=" + x);
        }
    }
}
