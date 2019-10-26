package ThreadSimpleDemo20191006;

/**
 * 多线程-卖票练习
 *
 * @author zangtao
 * @create 2019 - 10 -07 9:26
 */
public class ThreadDemoTicket02 {

    public static void main(String[] args) {
        TicketThread ticketThread = new TicketThread();
        new Thread(ticketThread,"票贩子A").start();
        new Thread(ticketThread,"票贩子B").start();
        new Thread(ticketThread,"票贩子C").start();
    }
}
class TicketThread implements Runnable{
    /**
     * 直接内存操作，没有中间的拷贝与复制操作
     */
    private volatile Integer ticket = 50000;
    @Override
    public void run() {
        synchronized (this){
            while (this.ticket > 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + "卖票处理，ticket= "+this.ticket--);
            }
        }
    }
}
