package bengine;

import java.util.HashMap;
import java.util.Map;

class EngineThreadManager {

	static final EngineThreadManager engineThreadManager = new EngineThreadManager();

	static EngineThreadManager getInstance() {
		return engineThreadManager;
	}

	Map<Thread, ThreadState> map = new HashMap<Thread, ThreadState>();

	void requestCancel(Thread thread) {
		map.put(thread, ThreadState.CANCELLED);
	}

	void registerStarting() {
		map.put(Thread.currentThread(), ThreadState.RUNNING);
	}

	void cancelled() throws EngineCancelledException {
		boolean cancelled = map.get(Thread.currentThread()) == ThreadState.CANCELLED;
		if (cancelled) {
			throw new EngineCancelledException();
		}
	}

}
