package com.example.demoheimaduoxiancheng.多线程;

public class MyThread extends Thread {
//    表示这个类所有的对象，都共享ticket数据
    static int ticket = 0;

//    锁对象，一定要是唯一的
    static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            //同步代码块
            synchronized(obj) {
                if (ticket < 100) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {

                    }
                    ticket++;
                    System.out.println(getName() + "正在卖第" + ticket + "张票");
                } else {
                    break;
                }
            }
        }
    }
}

package com.example.demoheimaduoxiancheng.多线程;

import java.util.concurrent.ExecutionException;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
