package ObjectMethodDemo20191026;


class Message{
    private String title;
    private String content;
    //表示生产或消费的形式
    //falg = true,允许生产，但是不允许消费
    //flag = flase,允许消费，不允许生产
    private boolean flag = true;

    public synchronized void set(String title,String content){
        if (!this.flag){
            try{
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
        //已经生产
        this.flag = false;
        //唤醒等待的线程
        super.notify();
    }
    public synchronized String get() {
        //还未生产，需要等待
        if (this.flag){
            try{
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            return this.title + "   -   " + this.content;
        }finally {
            //继续生产
            this.flag = true;
            //唤醒等待线程
            super.notify();
        }
    }
}
class Producer implements Runnable{
    private Message msg;
    public Producer(Message msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++){
            if (x%2 == 0){
                this.msg.set("zt","cool");
            }else {
                this.msg.set("gw","ugly");
            }
        }
    }
}

class Consumer implements Runnable{
    private Message msg;
    public Consumer(Message msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++){
            System.out.println(this.msg.get());
        }
    }
}

/**
 * Object类解决重复操作
 *
 * @author zangtao
 * @create 2019 - 10 -26 9:50
 */
public class ObjectDemo {
    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();
    }


}
