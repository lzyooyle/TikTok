package com.example.demoheimaduoxiancheng.吃东西;

public class Cook extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                   //判断桌子上是否有食物
                   if (Desk.foodFlag == 1) {
                       //如果有，就等待
                       try {
                           Desk.lock.wait();
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                   } else {
                       //如果没有，就制作食物
                       System.out.println("厨师做了一碗面条");
                       //修改桌子上的食物状态
                       Desk.foodFlag = 1;
                       //叫醒等待的消费者开吃
                       Desk.lock.notifyAll();
                   }
                }
            }
        }
    }
}

package com.example.demoheimaduoxiancheng.吃东西;

public class Desk {
    /**
     * 生产者和消费者（等待唤醒机制）
     *
     * 消费者
     * 1、判断桌子上是否有食物
     * 2、如果没有就等待
     * 3、如果有就开吃
     * 4、吃完之后，唤醒厨师继续做
     *
     * 生产者
     * 1、判断桌子上是否有食物
     * 2、有：等待
     * 3、没有：制作食物
     * 4、把食物放在桌子上
     * 5、叫醒等待的消费者开吃
     *
     * 作用：控制生产者和消费者的执行
     */

    //是否有面条 0：没有面条  1：有面条
    public static int foodFlag = 0;

    //总个数
    public static int count = 10;

    //锁对象
    public static Object lock = new Object();
}

package com.example.demoheimaduoxiancheng.吃东西;

public class Foodie extends Thread{
    @Override
    public void run() {
        /**
         * 1、循环
         * 2、同步代码块
         * 3、判断共享数据是否到了末尾（到了末尾）
         * 4、判断共享数据是否到了末尾（没有到末尾，执行核心逻辑）
         */
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    //先判断桌子上是否有面条
                    if (Desk.foodFlag == 0) {
                        try {
                            Desk.lock.wait(); //让当前线程跟锁绑定
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        //把吃的总数-1
                        Desk.count--;
                        //如果有，就开吃
                        System.out.println("吃货在吃面条，还能吃" + Desk.count + "碗");
                        //吃完之后，唤醒厨师继续做
                        Desk.lock.notifyAll();
                        //修改桌子的状态
                        Desk.foodFlag = 0;
                    }
                }
            }
        }
    }
}

package com.example.demoheimaduoxiancheng.吃东西;

public class main {
    public static void main(String[] args) {
        Foodie foodie = new Foodie();
        Cook cook = new Cook();
        foodie.setName("吃货");
        cook.setName("厨师");
        foodie.start();
        cook.start();
    }
}
