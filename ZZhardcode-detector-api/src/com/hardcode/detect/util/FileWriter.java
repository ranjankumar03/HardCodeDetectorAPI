package com.hardcode.detect.util;

import java.util.Set;

import com.hardcode.detect.pojo.FileDesc;

public interface FileWriter {

	public void write(String outputFileLocation, Set<FileDesc> result);

}
