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
        new Thread(ticketThread).start();
        new Thread(ticketThread).start();
        new Thread(ticketThread).start();
    }
}
class TicketThread implements Runnable{
    private Integer ticket = 5;
    @Override
    public void run() {
        for(int x = 0; x<100;x++){
            if (ticket > 0){
                System.out.println("卖票,ticket = " + this.ticket --);
                }
            }
    }
}
