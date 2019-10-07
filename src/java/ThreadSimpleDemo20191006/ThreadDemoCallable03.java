package ThreadSimpleDemo20191006;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable实现多线程
 *
 * @author zangtao
 * @create 2019 - 10 -07 10:15
 */
public class ThreadDemoCallable03 {



    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            for(int x = 0;x<10;x++){
                System.out.println("*****线程执行，x="+x);
            }
            return "线程执行完毕";
        });
        new Thread(futureTask).start();
        System.out.println("【线程返回数据】" + futureTask.get());
    }
}

class CallableThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        for(int x = 0;x<10;x++){
            System.out.println("*****线程执行，x="+x);
        }
        return "线程执行完毕";
    }
}