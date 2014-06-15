package bengine;

import java.io.File;
import java.io.IOException;

class FooProcessor {

	boolean process() throws IOException {
		FooEntityImporter importer = new FooEntityImporter();
		File file = new File("foo");
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			importer.doImport(file);
			System.out.println("Continue");
		} catch (EngineCancelledException e) {
			System.out.println("Cancelled");
			e.printStackTrace();
			// 後始末。
			// ステータスファイル更新など。
			return false;
		}
		return true;
	}

}
