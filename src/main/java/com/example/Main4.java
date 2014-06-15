package com.example;

import java.io.FileWriter;
import java.io.IOException;

public class Main4 {

	private static class HogeThread4 extends Thread {
		public void run() {
			FileWriter fileWriter;
			try {
				fileWriter = new FileWriter("output");
				try {
					while (true) {
						fileWriter.write("xxxxxxxx");
					}
				} finally {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread th = new HogeThread4();
		th.start();
		th.sleep(1000);
		th.interrupt();
	}

}
