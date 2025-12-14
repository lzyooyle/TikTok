package com.example.demoheimaduoxiancheng.多线程;

import java.util.concurrent.ExecutionException;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //setDaemon(boolean on) 设置为守护线程
        //细节：
        //当其它的非守护线程执行完毕之后，守护线程会陆续结束
        //通俗易懂：
        //当女神线程结束了，那么备胎也没有存在的必要了
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        t1.setName("女神线程");
        t2.setName("备胎线程");

        //把第二个线程设置为守护线程（备胎线程）
        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}

package com.example.demoheimaduoxiancheng.多线程;

public class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(getName() + "@" + i);
        }
    }
}

package com.example.demoheimaduoxiancheng.多线程;

public class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(getName() + "@" + i);
        }
    }
}
