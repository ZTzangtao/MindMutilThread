package ThreadSimpleDemo20191006;


/**
 * 线程的主体类
 */
class MyThread extends Thread{
    private String title;
    public MyThread (String title){
        this.title = title;
    }
    public MyThread (){}

    /**
     * 线程的主体方法
     */
    @Override
    public void run() {
        for(int x = 0;x<10;x++ ){
            System.out.println(this.title + "运行，x=" + x);
        }
    }
}

class MyThreadImplMethod implements Runnable{
    private String title;
    public MyThreadImplMethod (String title){
        this.title = title;
    }
    /**
     * 线程的主体方法
     */
    @Override
    public void run() {
        for(int x = 0;x<10;x++ ){
            System.out.println(this.title + "运行，x=" + x);
        }
    }
}

/**
 * 第一章，线程实现
 *
 * @author zangtao
 * @create 2019 - 10 -06 10:49
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
//        new MyThread("线程A").start();
//        new MyThread("线程B").start();
//        new MyThread("线程C").start();
//        Thread thread = new Thread(new MyThreadImplMethod("线程对象"));
//        thread.start();
        for(int i = 0; i < 3; i++){
            String title = "线程对象-" + i;
            new Thread(
                    ()->{
                        for(int y = 0; y < 10; y++){
                            System.out.println(title + "运行，x=" + y);
                        }
                }
            ).start();
        }
    }

}
