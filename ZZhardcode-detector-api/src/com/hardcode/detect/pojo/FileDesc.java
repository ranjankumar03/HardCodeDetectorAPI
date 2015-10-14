package com.hardcode.detect.pojo;

public final class FileDesc {

	private final String packagePath;
	private final int lineNumber;
	private final String lineText;
	private final String severity;

	public FileDesc(String fileLocation, int lineNumber, String lineText,
			String severity) {
		super();
		this.packagePath = fileLocation;
		this.lineNumber = lineNumber;
		this.lineText = lineText;
		this.severity = severity;
	}

	public String getFileLocation() {
		return packagePath;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getLineText() {
		return lineText;
	}

	public String getSeverity() {
		return severity;
	}

	@Override
	public String toString() {
		return "FileDesc [fileLocation=" + packagePath + ", lineNumber="
				+ lineNumber + ", lineText=" + lineText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lineNumber;
		result = prime * result
				+ ((lineText == null) ? 0 : lineText.hashCode());
		result = prime * result
				+ ((packagePath == null) ? 0 : packagePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDesc other = (FileDesc) obj;
		if (lineNumber != other.lineNumber)
			return false;
		if (lineText == null) {
			if (other.lineText != null)
				return false;
		} else if (!lineText.equals(other.lineText))
			return false;
		if (packagePath == null) {
			if (other.packagePath != null)
				return false;
		} else if (!packagePath.equals(other.packagePath))
			return false;
		return true;
	}

}
