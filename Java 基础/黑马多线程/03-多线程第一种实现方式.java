package domain;

public class HelloWorld {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("线程1");
        t1.start();
    }

}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "Hello World");
        }
    }
}
