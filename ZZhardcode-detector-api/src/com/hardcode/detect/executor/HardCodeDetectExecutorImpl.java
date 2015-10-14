package com.hardcode.detect.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hardcode.detect.pojo.FileDesc;
import com.hardcode.detect.util.FileWriter;
import com.hardcode.detect.util.XLSFileWriter;

public class HardCodeDetectExecutorImpl implements HardCodeDetectExecutor {

	private static final Set<FileDesc> set = new HashSet<FileDesc>();
	// final static PropertiesKeyReader keyReader = new PropertiesKeyReader();
	static boolean ifIgnoreFlag = false;
	private static FileWriter writer = null;

	@Override
	public void findHardCodedText(String modulePath) throws IOException {
		// TODO Auto-generated method stub
		Set<FileDesc> result = listFile(modulePath);

		System.out.println(" Set size::" + result.size());
		System.out
				.println("Writing excel report begins..........................................");
		writer = new XLSFileWriter();
		writer.write("E:/result.xls", result);
		System.out
				.println("Writing excel report completed successfully..........................................");

	}

	private Set<FileDesc> listFile(String path) throws IOException {
		// TODO Auto-generated method stub
		File folderPath = new File(path);
		File[] listOfFiles = folderPath.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {

			File fileName = listOfFiles[i];
			if (fileName.isDirectory()) {
				File[] subdirectory = fileName.listFiles();
				for (int j = 0; j < subdirectory.length; j++) {
					if (subdirectory[j].isDirectory()) {
						listFile(subdirectory[j].getAbsolutePath());
					}
				}
			}

			else {
				if (fileName.toString().endsWith(".java")
						&& !fileName.toString().endsWith("Test.java")) {

					System.out.println(fileName);
					Pattern pattern = Pattern.compile("\"*\"");
					BufferedReader r = new BufferedReader(new FileReader(
							fileName.toString()));

					String line = null;
					int lineNumber = 0;

					while ((line = r.readLine()) != null) {
						lineNumber++;
						// System.out.println("Line::"+line);
						// For each match in the line, extract and print it.
						Matcher m = pattern.matcher(line);
						while (m.find()) {
							// TODO : Severity addition to be discussed
							String severity = "Medium";// default
							ifIgnoreFlag = ifIgnore(line);
							if (!ifIgnoreFlag) {
								set.add(new FileDesc(fileName.toString(),
										lineNumber, line, severity));
							}

						}
					}
				}
			}

		}
		return set;

	}

	private boolean ifIgnore(String line) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					"E://myworkspace/ZZhardcode-detector-api/ignoreList.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Object> keys = prop.keySet();

		for (Object key : keys) {

			Pattern ignorePattern = Pattern.compile(prop
					.getProperty((String) key));
			Matcher ignoreMatcher = ignorePattern.matcher(line);
			boolean xx = ignoreMatcher.find();
			// System.out.println("xx:::"+ xx);
			if (xx) {
				ifIgnoreFlag = true;

				break;
			} else {
				ifIgnoreFlag = false;
			}

		}
		return ifIgnoreFlag;
	}

}
