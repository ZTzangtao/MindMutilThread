package ObjectMethodDemo20191026.ComprehensiveCase2;

/**
 * 综合案例2-生产电脑
 设计一个生产电脑和搬运电脑类，要求生产出一台电脑就搬走一台电脑，如果没有新的电脑生产出来，则搬运工要等待新电脑
产出；如果生产出的电脑没有搬走，则要等待电脑搬走后再生产，并统计出生产的电脑数量。

 * @author zangtao
 * @create 2019 - 10 -27 13:47
 */
class Computer{
    /**
     * 表示生产的个数
     */
    private static int count = 0;
    private String name;
    private double price;

    public Computer(String name, double price) {
        this.name = name;
        this.price = price;
        count++;
    }

    @Override
    public String toString() {
        return "第"+count+"台Computer{" +
                "name='" + this.name + '\'' +
                ", price=" + this.price +
                '}';
    }
}
class Resource{
    private Computer computer;
    /**
     * 标记
     */
    private boolean flag = true;
    public synchronized void make() throws InterruptedException {
        if (this.computer != null){
            //已经生产过了
            super.wait();
        }
        Thread.sleep(200);
        this.computer = new Computer("lenovo",4955);
        System.out.println("【生产电脑】" + this.computer);
        this.flag = false;
        super.notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        if (this.computer == null){
            //没有生产过
            super.wait();
        }
        System.out.println("[取走电脑]"+this.computer);
        //取走电脑
        this.computer = null;
        super.notifyAll();
    }
}

class Producer implements Runnable{
    private Resource resource;
    public Producer(Resource resource){
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0;x<50;x++){
            try {
                this.resource.make();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private Resource resource;
    public Consumer(Resource resource){
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0;x<50;x++){
            try {
                this.resource.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProduceComputer {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(new Producer(resource)).start();
        new Thread(new Consumer(resource)).start();
    }
}
