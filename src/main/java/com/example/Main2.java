package com.example;

public class Main2 {

    private static class HogeThread2 extends Thread {
        public void run() {
            while (true) {
                System.out.println("begin of loop");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    e.printStackTrace(System.out);
                }
                System.out.println("end of loop");
            }
        }
    }

    public static void main(String[] args) {
        Thread th = new HogeThread2();
        th.start();
        th.interrupt();
    }

}