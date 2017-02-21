package org.purl.beroca.common;

// Simple Enum for MetaData Control of the Application
public enum MetaData {
	V_170209("17.02.09"),
	V_170221("17.02.21");
	
	private final String VERSION;
	
	MetaData( String version ) {
		VERSION = version;
	}

	public String getVERSION() {
		return VERSION;
	}
}
