import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//拒绝策略

public class Refuse {

    public static ThreadPoolExecutor RefuseThreadExecutor(int nThreads){
        return new ThreadPoolExecutor(nThreads, nThreads,
                10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),new NameTreadFactory(), new MyIgnorePolicy());
    }

    public static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
//            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    public static void main(String[] args) {
        ExecutorService fix = RefuseThreadExecutor(8);
        for(int i=0;i<4;i++){
            fix.execute(new command());
        }
        fix.shutdown();
        fix.execute(new command());

    }

}
