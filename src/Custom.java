
//高仿FixedThreadPool，验证线程池自动关闭功能

import java.util.concurrent.*;

public class Custom {

//    与FixedThreadPool不同在于核心线程=0，最大线程=nThreads
    public static ThreadPoolExecutor TestThreadExecutor(int nThreads){
        return new ThreadPoolExecutor(0, nThreads,
                10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static void main(String[] args) {
        while (true){
            ExecutorService fix = TestThreadExecutor(8);
            for(int i=0;i<4;i++){
                fix.execute(new command());
            }
            fix = null;
        }
    }
}
