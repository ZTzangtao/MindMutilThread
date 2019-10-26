package ObjectMethodDemo20191026;

/**
 * 线程守护练习
 *
 * @author zangtao
 * @create 2019 - 10 -26 13:17
 */
public class ThreadDaemon {
    public static boolean flag = true;

    public static void main(String[] args) {
        Thread userThread = new Thread(()->{
            for(int x = 0;x < 10;x++){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+
                        "正在运行、x="+ x);
            }
        },"用户线程");

        Thread daemonThread = new Thread(()->{
            for(int x = 0;x<Integer.MAX_VALUE;x++){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+
                        "正在运行、x="+ x);
            }
        },"守护线程");
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();


    }


}
