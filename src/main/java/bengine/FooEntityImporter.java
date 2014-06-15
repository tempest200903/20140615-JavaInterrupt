package bengine;

import java.io.File;
import java.io.IOException;

class FooEntityImporter {

	FooEntity doImport(File file) throws IOException, EngineCancelledException {
		// キャンセルする判断A
		EngineThreadManager.getInstance().cancelled();
		return new FooEntity();
	}

}
