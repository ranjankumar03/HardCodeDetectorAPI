package com.hardcode.detector.controller;

import java.io.IOException;

import com.hardcode.detect.executor.HardCodeDetectExecutor;
import com.hardcode.detect.executor.HardCodeDetectExecutorImpl;

public class Controller {

	private static HardCodeDetectExecutor executor = null;

	public static void main(String[] args) {

		System.out.println("Controller starts here......................");

		// TODO: Move this hard code path to configuration file
		String modulePath = "E:/sourcecode/3.0.1/server/commons";
		// String modulePath = "E:/sourcecode/3.0.1/server/fi-server";
		// String modulePath = "E:/sourcecode/3.0.1/server/is-agent";
		executor = new HardCodeDetectExecutorImpl();
		try {
			executor.findHardCodedText(modulePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Hard Code detected successfully..............");
		System.exit(0);
	}
}
