package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main3 {

	private static class HogeThread3<T> extends Thread {
		private List<T> list;

		public HogeThread3(List<T> list) {
			this.list = list;
		}

		public void run() {
			while (true) {
				System.out.println("begin of loop");
				try {
					synchronized (list) {
						list.wait();
						if (list.size() == 0) {
							break;
						}
						list.remove(0);
					}
				} catch (InterruptedException e) {
					System.out.println("interrupted");
					e.printStackTrace(System.out);
				}
				System.out.println("end of loop");
			}
		}
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		HogeThread3<String> th = new HogeThread3<String>(list);
		th.start();
		th.interrupt();
	}

}
