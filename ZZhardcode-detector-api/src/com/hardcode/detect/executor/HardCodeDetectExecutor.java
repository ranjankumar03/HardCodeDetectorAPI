package com.hardcode.detect.executor;

import java.io.IOException;

public interface HardCodeDetectExecutor {
	
	public void findHardCodedText(String modulePath) throws IOException;

}
