package com.example.demoheimaduoxiancheng.多线程;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        setPriority(int newPriority) 设置线程的优先级
//        getPriority() 获取线程的优先级
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr, "飞机");
        Thread t2 = new Thread(mr, "坦克");

        t1.setPriority(1);
        t2.setPriority(10);

        t1.start();
        t2.start();
    }
}

package com.example.demoheimaduoxiancheng.多线程;

import java.util.concurrent.Callable;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}
