package ObjectMethodDemo20191026.ComprehensiveCase1;

class AddThread implements Runnable{
    private Resource resource;

    public AddThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0;x<50;x++){
            try {
                this.resource.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThread implements Runnable{
    private Resource resource;

    public SubThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0;x<50;x++){
            try {
                this.resource.sub();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 定义一个操作的资源
 */
class Resource{
    /**
     * 这个要进行加减操作的过程
     */
    private int num = 0;
    /**
     * 加减的切换
     * flag = true;表示可以进行加法操作，无法进行减法操作；
     * flag = false；表示可以进行减法操作，无法进行加法操作；
     */
    private boolean flag = true;

    public synchronized void add() throws InterruptedException {
        if (!this.flag){
            //正在进行减法操作，加法操作需要等待
            super.wait();
        }
        Thread.sleep(100);
        this.num++;
        System.out.println("[加法操作 -" + Thread.currentThread().getName() + "] num = " + this.num);
        this.flag = false;
        super.notifyAll();
    }
    public synchronized void sub() throws InterruptedException {
        if (this.flag){
            //减法操作需要等待
            super.wait();
        }
        Thread.sleep(200);
        this.num--;
        System.out.println("[减法操作 -" + Thread.currentThread().getName() + "] num = " + this.num);
        this.flag = true;
        super.notifyAll();
    }
}



/**
 * 综合案例之数字加减
 *
 * @author zangtao
 * @create 2019 - 10 -26 16:37
 */
public class DigitalSubtraction {
    public static void main(String[] args) {
        Resource resource = new Resource();
        AddThread at = new AddThread(resource);
        SubThread st = new SubThread(resource);

        new Thread(at,"加法线程 - A").start();
        new Thread(at,"加法线程 - B").start();
        new Thread(st,"减法线程 - x").start();
        new Thread(st,"减法线程 - y").start();
    }

}
