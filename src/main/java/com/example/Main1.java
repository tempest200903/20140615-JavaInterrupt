package com.example;

public class Main1 {

	private static class HogeThread1 extends Thread {
		public void run() {
			while (true) {
				System.out.println("running");
			}
		}
	}

	public static void main(String[] args) {
		Thread th = new HogeThread1();
		th.start();
		th.interrupt();
	}

}
