package ThreadPool191025;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池第一种练习
 *
 * @author zangtao
 * @create 2019 - 10 -25 10:00
 */
public class ThreadPoolFirstDemo {

    public static void main(String[] args) throws Exception {
        //手动创建线程池

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        //newCachedThreadPool(); 改写
        long cachedKeepAliveTime = 60L;
        ExecutorService executor = new ThreadPoolExecutor(0,10,
                cachedKeepAliveTime,TimeUnit.SECONDS,new SynchronousQueue<>(),namedThreadFactory);

        //newFixedThreadPool(3); 改写
        int nThreadsSize = 3;
        long keepAliveTime = 0L;
        ExecutorService executorService = new ThreadPoolExecutor(nThreadsSize, nThreadsSize,
                keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),namedThreadFactory);

        for (int i = 0; i < 5; i++){
            final int index = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                for(int x = 0;x<10;x++){
                    System.out.println("*****线程执行，x="+x + "index:"+index);
                }
            });
        }
        executorService.shutdown();
    }
}
