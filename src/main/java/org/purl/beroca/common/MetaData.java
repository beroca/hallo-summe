package org.purl.beroca.common;

// Simple Enum for MetaData Control of the Application
public enum MetaData {
	V_170209("17.02.09");
	
	private final String VERSION;
	
	MetaData( String version ) {
		VERSION = version;
	}

	public String getVERSION() {
		return VERSION;
	}
}
