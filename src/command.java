public class command implements Runnable{
    @Override
    public void run() {
        String res = "线程id："+  Thread.currentThread().getId()+"线程名：" + Thread.currentThread().getName();
        System.out.println(res);
    }
}
