package domain;

public class HelloWorld {

    public static void main(String[] args) {
        //getName() 返回此线程的名字
        //setName() 设置线程的名字
        //细节：
        //	1、如果我们没有给线程设置名字，线程也是有默认的名字的
        //		格式：Thread-x（x序号，从0开始的）
        //	2、如果我们要给线程池设置名字，可以用set方法进行设置，也可以用构造方法设置
        
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
