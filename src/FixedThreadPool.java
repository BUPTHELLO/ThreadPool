
//FixedThreadPool 启动&&自动关闭的问题

import java.util.concurrent.*;

public class FixedThreadPool {


    public static void main(String[] args) {
        while (true){
            ExecutorService fix = Executors.newFixedThreadPool(8);
            for(int i=0;i<4;i++){
                fix.execute(new command());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fix = null;
        }
    }
}
