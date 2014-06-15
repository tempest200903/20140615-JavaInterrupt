package bengine;

import java.io.IOException;

public class EngineMain {

	public static void main(String[] args) throws IOException {
		Thread thread = new Thread() {
			public void run() {
				FooProcessor fooProcessor = new FooProcessor();
				try {
					fooProcessor.process();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		EngineThreadManager.getInstance().requestCancel(thread);
		thread.start();
	}

}
