package ObjectMethodDemo20191026.ComprehensiveCase3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<String>{
    /**
     * 抢答处理
     */
    private boolean flag = true;

    @Override
    public String call() throws Exception {
        synchronized (this){
            if (flag){
                this.flag = false;
                //抢答成功
                return Thread.currentThread().getName() + "抢答成功！";
            }else {
                return Thread.currentThread().getName() + "抢答失败！";
            }
        }
    }
}

/**
 * 竞争抢答程序
 *
 * 实现一个竞拍抢答程序：要求设置三个抢答者（三个线程），而后同时发出抢答指令，抢答成功给出成功提示，
 * 未抢答成功给出失败提示
 * @author zangtao
 * @create 2019 - 10 -27 16:11
 */
public class CompetitionAnswer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask<String> futureTaskA = new FutureTask<>(myThread);
        FutureTask<String> futureTaskB = new FutureTask<>(myThread);
        FutureTask<String> futureTaskC = new FutureTask<>(myThread);


        new Thread(futureTaskA,"竞赛者A").start();
        new Thread(futureTaskB,"竞赛者B").start();
        new Thread(futureTaskC,"竞赛者C").start();
        System.out.println(futureTaskA.get());
        System.out.println(futureTaskB.get());
        System.out.println(futureTaskC.get());
    }
}
