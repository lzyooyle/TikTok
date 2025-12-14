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
    //Thread currentThread() 获取当前线程的对象
	//哪条线程执行到这个方法，此时获取的就是哪条线程的对象
	//细节：
		//当JVM虚拟机启动之后，会自动的启动多条线程
		//其中有一条线程叫做main线程
		//它的作用就是去调用main方法，并执行里面的代码
		//在以前，我们写的所有的代码，其实都是运行在main线程当中

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
