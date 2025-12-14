package com.example.demoheimaduoxiancheng.多线程;

public class MyRunnable implements Runnable {
    int ticket = 0;

    @Override
    public void run() {
        while (true) {
            if ((saleTickt())) break;
        }
    }

    private synchronized boolean saleTickt() {
        if (ticket == 100) {
            return true;
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ticket++;
            System.out.println(Thread.currentThread().getName() + "个窗口开始卖第" + ticket + "张票");
        }
        return false;
    }
}


package com.example.demoheimaduoxiancheng.多线程;

import java.util.concurrent.ExecutionException;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr, "窗口1");
        Thread t2 = new Thread(mr, "窗口2");
        Thread t3 = new Thread(mr, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
