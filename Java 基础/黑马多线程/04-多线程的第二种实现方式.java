package com.example.demoheimaduoxiancheng.多线程;

public class main {
    public static void main(String[] args) {
        //多线程的第二种启动方式：
        //1、自己定义一个实现Runnable接口
        //2、重写里面的run方法
        //3、创建自己的类的对象
        //4、创建一个Thread类的对象，并开启线程

        //创建MyRun的对象
        //表示多线程要执行的任务
        MyRun mr = new MyRun();

        //创建线程对象
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        //给线程设置名字
        t1.setName("线程1");
        t2.setName("线程2");

        //开启线程
        t1.start();
        t2.start();
    }
}

package com.example.demoheimaduoxiancheng.多线程;

public class MyRun implements java.lang.Runnable {
    @Override
    public void run() {
        //书写线程要执行的代码
        for (int i = 0; i < 100; i++) {
            //获取当前线程的对象
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + "HelloWorld!");
        }
    }
}
